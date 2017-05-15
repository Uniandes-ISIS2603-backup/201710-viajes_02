/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.UsuarioPersistence;
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
 * @author n.aguilar
 */
@RunWith(Arquillian.class)
public class ViajeroLogicTest {

    @Inject
    private ViajeroLogic viajeroLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ViajeroEntity> data = new ArrayList<ViajeroEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(ViajeroLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
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
        em.createQuery("delete from ViajeroEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            ViajeroEntity entity = factory.manufacturePojo(ViajeroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createViajeroTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        ViajeroEntity result = null;
        newEntity.setGenero("Indefinido");
        try {
            result = viajeroLogic.createViajero(newEntity);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            newEntity.setGenero("Masculino");
            newEntity.setEdad(-58);
            result = viajeroLogic.createViajero(newEntity);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            newEntity.setGenero("Masculino");
            newEntity.setEdad(26);
            result = viajeroLogic.createViajero(newEntity);
            Assert.assertNotNull(result);

            ViajeroEntity entity = em.find(ViajeroEntity.class, result.getId());
            Assert.assertNotNull(entity);
            Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteViajeroTest() {
        ViajeroEntity entity = data.get(0);
        viajeroLogic.deleteViajero(entity.getId());
        ViajeroEntity deleted = em.find(ViajeroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void getViajeroTest() {
        ViajeroEntity entity = data.get(0);
        ViajeroEntity newEntity = null;
        try {
            newEntity = viajeroLogic.getViajero((long) 964);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        try {
            newEntity = viajeroLogic.getViajero(entity.getId());
        } catch (Exception e) {
            fail();
        }
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    @Test
    public void getViajerosTest() {
        List<ViajeroEntity> list = viajeroLogic.getViajeros();
        Assert.assertEquals(data.size(), list.size());
        for (ViajeroEntity ent : list) {
            boolean found = false;
            for (ViajeroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void updateUsuarioTest() {
        ViajeroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        try {
            newEntity.setGenero("Indefinido");
            viajeroLogic.updateViajero(newEntity);
            fail();
        } catch (BusinessLogicException e) {
            assertTrue(true);
        }
        try {
            newEntity.setGenero("Indefinido");
            newEntity.setEdad(250);
            viajeroLogic.updateViajero(newEntity);
            fail();
        } catch (BusinessLogicException e) {
            assertTrue(true);
        }
        try {
            newEntity.setGenero("Femenino");
            newEntity.setEdad(37);
            newEntity.setId((long) 11111);
            viajeroLogic.updateViajero(newEntity);
            fail();
        } catch (BusinessLogicException e) {
            assertTrue(true);
        }
        try {
            newEntity.setGenero("Femenino");
            newEntity.setEdad(37);
            newEntity.setId(entity.getId());
            viajeroLogic.updateViajero(newEntity);
            assertTrue(true);
        } catch (BusinessLogicException e) {
            fail();
        }
        ViajeroEntity resp = em.find(ViajeroEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

}
