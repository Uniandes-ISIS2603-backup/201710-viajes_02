/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class CobroMultaEntityTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarEntity.class.getPackage())
                .addPackage(LugarPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private CobroMultaPersistence cobroMultaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CobroMultaEntity> data = new ArrayList<>();

    @Before
    public void setup() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Prueba que se cree un cobro multa.
     */
    @Test
    public void createCobroMultaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CobroMultaEntity newEntity = factory.manufacturePojo(CobroMultaEntity.class);
        CobroMultaEntity result = cobroMultaPersistence.create(newEntity);

        Assert.assertNotNull(result);
        CobroMultaEntity entity = em.find(CobroMultaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getValor(), entity.getValor());
    }

    /**
     * Pruena que se den todos los cobros multa
     */
    @Test
    public void getCobrosMultaTest() {
        List<CobroMultaEntity> list = cobroMultaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());

        for (CobroMultaEntity en : list) {
            boolean found = false;
            for (CobroMultaEntity en2 : data) {
                if (en.getId().equals(en2.getId())) {
                    found = true;
                }
            }

            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba que se de un cobro multa
     */
    @Test
    public void getCobroMultaTest() {
        CobroMultaEntity en = data.get(0);
        CobroMultaEntity result = cobroMultaPersistence.find(en.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getValor(), en.getValor());
    }

    /**
     * Prueba que se modifique un cobro multa 
     *
     */
    @Test
    public void updateCobroMultaTest() {
        CobroMultaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CobroMultaEntity newEntity = factory.manufacturePojo(CobroMultaEntity.class);

        newEntity.setId(entity.getId());
        cobroMultaPersistence.update(newEntity);
        CobroMultaEntity resp = em.find(CobroMultaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getValor(), resp.getValor());
    }

    /**
     * Prueba que se elimine un cobro multa
     */
    @Test
    public void deleteCobroMultaTest() {
        CobroMultaEntity entity = data.get(0);
        cobroMultaPersistence.delete(entity.getId());
        CobroMultaEntity deleted = em.find(CobroMultaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    // Helpers
    /**
     * Elimina los datos que hay actualmente en la base de datos.
     */
    public void clearData() {
        em.createQuery("delete from CobroMultaEntity");
    }

    /**
     * Inserta los datos de prueba a la base de datos
     */
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 10; i++) {
            CobroMultaEntity entity = factory.manufacturePojo(CobroMultaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

}
