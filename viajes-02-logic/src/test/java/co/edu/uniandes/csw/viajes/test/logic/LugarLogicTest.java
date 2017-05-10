/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.logic;

import co.edu.uniandes.csw.viajes.ejbs.LugarLogic;
import co.edu.uniandes.csw.viajes.entities.LugarEntity;
import co.edu.uniandes.csw.viajes.persistence.LugarPersistence;
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
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author wr.ravelo
 */
@RunWith(Arquillian.class)
public class LugarLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private LugarLogic lugarLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<LugarEntity> data = new ArrayList<LugarEntity>();

    /**
     * Configuracion de despliegue
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarEntity.class.getPackage())
                .addPackage(LugarLogic.class.getPackage())
                .addPackage(LugarPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configura el estado inicial de las pruebas
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Prueba de crear un lugar
     */
    @Test
    public void createLugarTest() {
        LugarEntity newEntity = factory.manufacturePojo(LugarEntity.class);

        LugarEntity result = lugarLogic.createLugar(newEntity);
        Assert.assertNotNull(result);

        LugarEntity entity = em.find(LugarEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getLugar(), entity.getLugar());
    }

    /**
     * Prueba que se den todos los lugares
     */
    @Test
    public void getLugaresTest() {
        List<LugarEntity> list = lugarLogic.findLugares();
        Assert.assertEquals(data.size(), list.size());
        for (LugarEntity entity : list) {
            boolean found = false;
            for (LugarEntity entity2 : data) {
                if (entity.getId().equals(entity2.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba que se de un lugar especifico.
     */
    @Test
    public void getLugarTest() {
        LugarEntity entity = data.get(0);
        LugarEntity resultEntity = lugarLogic.findLugar(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getLugar(), resultEntity.getLugar());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba que se actualice un lugar.
     */
    @Test
    public void updateLugarTest() {
        LugarEntity entity = data.get(0);
        LugarEntity newEntity = factory.manufacturePojo(LugarEntity.class);

        newEntity.setId(entity.getId());

        lugarLogic.updateLugar(newEntity);

        LugarEntity resp = em.find(LugarEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getLugar(), resp.getLugar());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba que se elimine un lugar.
     */
    @Test
    public void deleteLugarTest() {
        LugarEntity entity = data.get(1);
        lugarLogic.deleteLugar(entity.getId());
        LugarEntity deleted = em.find(LugarEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    // Helpers
    /**
     * Elimina los datos que hay actualmente en la base de datos.
     */
    public void clearData() {
        em.createQuery("delete from LugarEntity").executeUpdate();
    }

    /**
     * Inserta los datos de prueba a la base de datos
     */
    public void insertData() {
        for (int i = 0; i < 10; i++) {
            LugarEntity entity = factory.manufacturePojo(LugarEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
}
