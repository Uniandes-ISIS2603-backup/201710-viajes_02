package co.edu.uniandes.csw.viajes.test.logic;//package co.edu.uniandes.csw.viajes.test.logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.uniandes.csw.viajes.ejbs.AutomoviLogic;
import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.persistence.AutomovilPersistence;
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
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author d.jaimes
 */


@RunWith(Arquillian.class)
public class AutomovilLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private AutomoviLogic autoLogic;

    @PersistenceContext
    private EntityManager em;

    /**
     *
     */
    @Inject
    private UserTransaction utx;

    /**
     *
     */
    private List<AutomovilEntity> data = new ArrayList<AutomovilEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
        .addPackage(AutomovilEntity.class.getPackage())
        .addPackage(AutomoviLogic.class.getPackage())
        .addPackage(AutomovilPersistence.class.getPackage())
        .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
        .addAsManifestResource("META-INF/beans.xml", "beans.xml");
        }

        @Before
    public void setUp() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

     private void clearData() {
        em.createQuery("delete from AutomovilEntity").executeUpdate();

    }
     //TODO: HACER
 private void insertData() {

        for (int i = 0; i < 3; i++) {
            AutomovilEntity entity = factory.manufacturePojo(AutomovilEntity.class);
            // entity.set(data.get(0));
            em.persist(entity);
            data.add(entity);
        }
    }

 @Test
    public void createAutomovilTest() {
        AutomovilEntity newEntity = factory.manufacturePojo(AutomovilEntity.class);
     AutomovilEntity result = null;
     try {
         result = autoLogic.creatCar(newEntity);
     } catch (Exception e) {
         e.printStackTrace();
     }
     Assert.assertNotNull(result);
        AutomovilEntity entity = em.find(AutomovilEntity.class, result.getId());
        Assert.assertEquals(newEntity.getPlaca(), entity.getPlaca());
        Assert.assertEquals(newEntity.getMarca(), entity.getMarca());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

     @Test
    public void getAutomovilesTest() {
        List<AutomovilEntity> list = autoLogic.getAutomoviles();
        Assert.assertEquals(data.size(), list.size());
        for (AutomovilEntity entity : list) {
            boolean found = false;
            for (AutomovilEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getAutomovilTest() {
        AutomovilEntity entity = data.get(0);
        AutomovilEntity resultEntity = autoLogic.getAuto(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getPlaca(), resultEntity.getPlaca());
        Assert.assertEquals(entity.getMarca(), resultEntity.getMarca());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

     @Test
    public void deleteAutomovilTest() {
        AutomovilEntity entity = data.get(1);
        autoLogic.deletCar(entity.getId());
        AutomovilEntity deleted = em.find(AutomovilEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

      @Test
    public void updateAutomovilTest() {
        AutomovilEntity entity = data.get(0);
        AutomovilEntity pojoEntity = factory.manufacturePojo(AutomovilEntity.class);

        pojoEntity.setId(entity.getId());

        autoLogic.updateCar(pojoEntity);

        AutomovilEntity resp = em.find(AutomovilEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getPlaca(), resp.getPlaca());
        Assert.assertEquals(pojoEntity.getMarca(), resp.getMarca());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

}
