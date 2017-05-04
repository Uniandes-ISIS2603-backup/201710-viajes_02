/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.LugarEntity;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author wr.ravelo
 */
@RunWith(Arquillian.class)
public class LugarEntityTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarEntity.class.getPackage())
                .addPackage(LugarPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private LugarPersistence lugarPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<LugarEntity> data = new ArrayList<LugarEntity>();

    /**
     * Configura el estado inicial de las pruebas
     */
    @Before
    public void setup() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
        } catch (Exception e) {
            e.printStackTrace();

            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Prueba la creacion de un lugar
     */
    @Test
    public void createLugarTest() {
        PodamFactory factory = new PodamFactoryImpl();
        LugarEntity newEntity = factory.manufacturePojo(LugarEntity.class);
        LugarEntity result = lugarPersistence.create(newEntity);

        Assert.assertNotNull(result);
        LugarEntity entity = em.find(LugarEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getLugar(), entity.getLugar());
    }

    /**
     * Verifica que se encuentren todos los lugares en la base de datos.
     */
    @Test
    public void getLugaresTest() {
        List<LugarEntity> list = lugarPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());

        for (LugarEntity en : list) {
            boolean found = false;
            for (LugarEntity en2 : data) {
                if (en.getId().equals(en2.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba que se pueda acceder a un lugar
     */
    @Test
    public void getLugarTest() {
        LugarEntity en = data.get(0);
        LugarEntity result = lugarPersistence.find(en.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getLugar(), en.getLugar());
    }

    /**
     * Prueba que se modifique un lugar
     */
    @Test
    public void updateLugarTest() {
        LugarEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LugarEntity newEntity = factory.manufacturePojo(LugarEntity.class);

        newEntity.setId(entity.getId());
        lugarPersistence.update(newEntity);
        LugarEntity resp = em.find(LugarEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getLugar(), resp.getLugar());
    }

    /**
     * Prueba que se elimine un lugar
     */
    @Test
    public void deleteLugarTest() {
        LugarEntity entity = data.get(0);
        lugarPersistence.delete(entity.getId());
        LugarEntity deleted = em.find(LugarEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    // Helpers
    /**
     * Elimina los datos que hay actualmente en la base de datos.
     */
    public void clearData() {
        // Solo elimino lugar entity porque no tiene relaciones inferiores
        em.createQuery("delete from LugarEntity").executeUpdate();
    }

    /**
     * Inserta los datos de prueba a la base de datos
     */
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 10; i++) {
            LugarEntity entity = factory.manufacturePojo(LugarEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
}
