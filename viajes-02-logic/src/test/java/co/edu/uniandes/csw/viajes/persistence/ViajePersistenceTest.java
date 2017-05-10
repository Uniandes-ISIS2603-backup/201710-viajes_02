/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jm.dominguez
 */
@RunWith(Arquillian.class)
public class ViajePersistenceTest {

    @Inject
    private ViajePersistence viajePersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ViajeEntity> data = new ArrayList<ViajeEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeEntity.class.getPackage())
                .addPackage(ViajePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                fail("configuration data base fail");
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from ViajeEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            ViajeEntity entity = factory.manufacturePojo(ViajeEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createViajeroTest() {
        PodamFactory factory = new PodamFactoryImpl();

        ViajeEntity newEntity = factory.manufacturePojo(ViajeEntity.class);

        ViajeEntity result = viajePersistence.create(newEntity);

        Assert.assertNotNull(result);
        ViajeEntity entity = em.find(ViajeEntity.class, result.getIdViaje());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getIdViaje(), entity.getIdViaje());
    }

    @Test
    public void getViajesTest() {
        List<ViajeEntity> list = viajePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ViajeEntity ent : list) {
            boolean found = false;
            for (ViajeEntity entity : data) {
                if (ent.getIdViaje().equals(entity.getIdViaje())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getViajeTest(){
        ViajeEntity entity = data.get(0);
        ViajeEntity newEntity = viajePersistence.find(entity.getIdViaje());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getIdViaje(), newEntity.getIdViaje());
    }
    
    @Test
    public void updateViajeTest(){
        ViajeEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ViajeEntity newEntity = factory.manufacturePojo(ViajeEntity.class);

        newEntity.setIdViaje(entity.getIdViaje());

        viajePersistence.update(newEntity);

        ViajeEntity resp = em.find(ViajeEntity.class, entity.getIdViaje());

        Assert.assertEquals(newEntity.getIdViaje(), resp.getIdViaje());
    }
    
    @Test
    public void deleteViajeTest(){
        ViajeEntity entity = data.get(0);
        viajePersistence.delete(entity.getIdViaje());
        ViajeEntity deleted = em.find(ViajeEntity.class, entity.getIdViaje());
        Assert.assertNull(deleted);
    }
}

