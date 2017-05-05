/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Assert;
import org.junit.Before;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ja.bermudez10
 */
@RunWith(Arquillian.class)
public class PagoPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment () {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private PagoPersistence pagoPersistence;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Inject
    UserTransaction userTransaction;
    
    private List<PagoEntity> data = new ArrayList<PagoEntity>();
    
    @Before
    public void setUp() {
        try {
            userTransaction.begin();
            entityManager.joinTransaction();
            clearData();
            insertData();
            userTransaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                userTransaction.rollback();
            }
            catch (Exception innerE1) {
               innerE1.printStackTrace();
            }
        }
    }
    
    private void clearData() {
        entityManager.createQuery("DELETE FROM PagoEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        
        for (int i = 0; i < 10; i++) {
            PagoEntity pagoEntity = podamFactory.manufacturePojo(PagoEntity.class);
            entityManager.persist(pagoEntity);
            data.add(pagoEntity);
        }
    }
    
    @Test
    public void createPagoTest() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        PagoEntity newPagoEntity = podamFactory.manufacturePojo(PagoEntity.class);
        PagoEntity result = pagoPersistence.create(newPagoEntity);
        
        Assert.assertNotNull(result);
        PagoEntity pagoEntity = entityManager.find(PagoEntity.class, result.getId());
        Assert.assertNotNull(pagoEntity);
        Assert.assertEquals(newPagoEntity, pagoEntity);
    }
    
    @Test
    public void getPagosTest() {
        List<PagoEntity> pagos = pagoPersistence.findAll();
        Assert.assertEquals(data.size(), pagos.size());
        
        for (PagoEntity pagoPagos : pagos) {
            boolean encontrado = false;
            
            for (PagoEntity pagoData : data) {
                if (pagoPagos.getId().equals(pagoData.getId())) {
                    encontrado = true;
                }
            }
            
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void getPagoByIdTest() {
        PagoEntity pagoEntity = data.get(0);
        PagoEntity newPagoEntity = pagoPersistence.findPago(pagoEntity.getId());
        Assert.assertNotNull(newPagoEntity);
        Assert.assertEquals(pagoEntity, newPagoEntity);
    }
    
    @Test
    public void deletePagoTest() {
        PagoEntity pagoEntity = data.get(0);
        pagoPersistence.delete(pagoEntity.getId());
        PagoEntity eliminado = entityManager.find(PagoEntity.class, pagoEntity.getId());
        Assert.assertNull(eliminado);
    }
    
    @Test
    public void updatePagoTest() {
        PagoEntity pagoEntity = data.get(0);
        PodamFactory podamFactory = new PodamFactoryImpl();
        PagoEntity newPagoEntity = podamFactory.manufacturePojo(PagoEntity.class);
        
        newPagoEntity.setId(pagoEntity.getId());
        pagoPersistence.update(newPagoEntity);
        PagoEntity respPagoEntity = entityManager.find(PagoEntity.class, pagoEntity.getId());
        Assert.assertEquals(newPagoEntity, respPagoEntity);
    }
}

