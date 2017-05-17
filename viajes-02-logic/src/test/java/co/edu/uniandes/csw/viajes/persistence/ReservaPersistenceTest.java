/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
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
public class ReservaPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment () {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private ReservaPersistence reservaPersistence;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Inject
    UserTransaction userTransaction;
    
    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();
    
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
        entityManager.createQuery("DELETE FROM ReservaEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        
        for (int i = 0; i < 5; i++) {
            ReservaEntity reservaEntity = podamFactory.manufacturePojo(ReservaEntity.class);
            entityManager.persist(reservaEntity);
            data.add(reservaEntity);
        }
    }
    
    @Test
    public void createReservaTest() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        
        ReservaEntity newReservaEntity = podamFactory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = reservaPersistence.create(newReservaEntity);
        Assert.assertNotNull(result);
        
        ReservaEntity reservaEntity = entityManager.find(ReservaEntity.class, result.getId());
        Assert.assertNotNull(reservaEntity);
        Assert.assertEquals(newReservaEntity, reservaEntity);
    }
    
    @Test
    public void getReservasTest() {
        List<ReservaEntity> reservas = reservaPersistence.findAll();
        Assert.assertEquals(data.size(), reservas.size());
        
        for (ReservaEntity reservaReservas : reservas) {
            boolean encontrado = false;
            
            for (ReservaEntity reservaData : data) {
                if (reservaReservas.getId().equals(reservaData.getId())) {
                    encontrado = true;
                }
            }
            
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void getReservaByIdTest() {
        ReservaEntity reservaEntity = data.get(0);
        
        ReservaEntity newReservaEntity = reservaPersistence.findReserva(reservaEntity.getId());
        Assert.assertNotNull(newReservaEntity);
        Assert.assertEquals(reservaEntity, newReservaEntity);
    }
    
    @Test
    public void deleteReservaTest() {
        ReservaEntity reservaEntity = data.get(0);
        reservaPersistence.delete(reservaEntity.getId());
        ReservaEntity eliminada = entityManager.find(ReservaEntity.class, reservaEntity.getId());
        Assert.assertNull(eliminada);
    }
    
    @Test
    public void updateReservaTest() {
        ReservaEntity reservaEntity = data.get(0);
        PodamFactory podamFactory = new PodamFactoryImpl();
        ReservaEntity newReservaEntity = podamFactory.manufacturePojo(ReservaEntity.class);
        
        newReservaEntity.setId(reservaEntity.getId());
        reservaPersistence.update(newReservaEntity);
        ReservaEntity respReservaEntity = entityManager.find(ReservaEntity.class, reservaEntity.getId());
        Assert.assertEquals(newReservaEntity, respReservaEntity);
    }
}
