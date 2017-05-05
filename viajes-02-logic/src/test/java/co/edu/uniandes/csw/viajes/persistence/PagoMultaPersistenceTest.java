/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.PagoMultaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ja.bermudez10
 */
public class PagoMultaPersistenceTest {
     @Deployment
    public static JavaArchive createDeployment () {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoMultaEntity.class.getPackage())
                .addPackage(PagoMultaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private PagoMultaPersistence pagoMultaPersistence;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Inject
    UserTransaction userTransaction;
    
    private List<PagoMultaEntity> data = new ArrayList<PagoMultaEntity>();
    
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
        entityManager.createQuery("DELETE FROM PagoMultaEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        
        for (int i = 0; i < 10; i++) {
            PagoMultaEntity pagoMultaEntity = podamFactory.manufacturePojo(PagoMultaEntity.class);
            entityManager.persist(pagoMultaEntity);
            data.add(pagoMultaEntity);
        }
    }
    
    @Test
    public void createPagoMultaTest() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        PagoMultaEntity newPagoMultaEntity = podamFactory.manufacturePojo(PagoMultaEntity.class);
        PagoMultaEntity result = pagoMultaPersistence.create(newPagoMultaEntity);
        
        Assert.assertNotNull(result);
        PagoMultaEntity pagoMultaEntity = entityManager.find(PagoMultaEntity.class, result.getId());
        Assert.assertNotNull(pagoMultaEntity);
        Assert.assertEquals(newPagoMultaEntity, pagoMultaEntity);
    }
    
    @Test
    public void getPagosMultaTest() {
        List<PagoMultaEntity> pagos = pagoMultaPersistence.findAll();
        Assert.assertEquals(data.size(), pagos.size());
        
        for (PagoMultaEntity pagoPagos : pagos) {
            boolean encontrado = false;
            
            for (PagoMultaEntity pagoData : data) {
                if (pagoPagos.getId().equals(pagoData.getId())) {
                    encontrado = true;
                }
            }
            
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void getPagoMultaByIdTest() {
        PagoMultaEntity pagoMultaEntity = data.get(0);
        PagoMultaEntity newPagoMultaEntity = pagoMultaPersistence.findPagoMulta(pagoMultaEntity.getId());
        Assert.assertNotNull(newPagoMultaEntity);
        Assert.assertEquals(pagoMultaEntity, newPagoMultaEntity);
    }
    
    @Test
    public void deletePagoMultaTest() {
        PagoMultaEntity pagoMultaEntity = data.get(0);
        pagoMultaPersistence.delete(pagoMultaEntity.getId());
        PagoMultaEntity eliminado = entityManager.find(PagoMultaEntity.class, pagoMultaEntity.getId());
        Assert.assertNull(eliminado);
    }
    
    @Test
    public void updatePagoMultaTest() {
        PagoMultaEntity pagoMultaEntity = data.get(0);
        PodamFactory podamFactory = new PodamFactoryImpl();
        PagoMultaEntity newPagoMultaEntity = podamFactory.manufacturePojo(PagoMultaEntity.class);
        
        newPagoMultaEntity.setId(pagoMultaEntity.getId());
        pagoMultaPersistence.update(newPagoMultaEntity);
        PagoMultaEntity respPagoMultaEntity = entityManager.find(PagoMultaEntity.class, pagoMultaEntity.getId());
        Assert.assertEquals(newPagoMultaEntity, respPagoMultaEntity);
    }
    
}
