/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.logic;

import co.edu.uniandes.csw.viajes.ejbs.CobroLogic;
import co.edu.uniandes.csw.viajes.entities.CobroEntity;
import co.edu.uniandes.csw.viajes.persistence.CobroPersistence;
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
public class CobroLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CobroLogic cobroLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CobroEntity> data = new ArrayList<CobroEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CobroEntity.class.getPackage())
                .addPackage(CobroLogic.class.getPackage())
                .addPackage(CobroPersistence.class.getPackage())
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
     * Prueba de crear un cobro
     */
    @Test
    public void createCobroTest() {
        CobroEntity newEntity = factory.manufacturePojo(CobroEntity.class);

        CobroEntity result = cobroLogic.createCobro(newEntity);
        Assert.assertNotNull(result);

        CobroEntity entity = em.find(CobroEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getValor(), entity.getValor());
    }

    /**
     * Prueba que se den todos los cobros
     */
    @Test
    public void getCobrosTest() {
        List<CobroEntity> list = cobroLogic.findCobros();
        Assert.assertEquals(data.size(), list.size());
        for (CobroEntity entity : list) {
            boolean found = false;
            for (CobroEntity entity2 : data) {
                if (entity.getId().equals(entity2.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba que se de un cobro especifico.
     */
    @Test
    public void getCobroTest() {
        CobroEntity entity = data.get(0);
        CobroEntity resultEntity = cobroLogic.findCobro(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getValor(), resultEntity.getValor());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba que se actualice un cobro.
     */
    @Test
    public void updateCobroTest() {
        CobroEntity entity = data.get(0);
        CobroEntity newEntity = factory.manufacturePojo(CobroEntity.class);

        newEntity.setId(entity.getId());

        cobroLogic.updateCobro(newEntity);

        CobroEntity resp = em.find(CobroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getValor(), resp.getValor());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba que se elimine un cobro.
     */
    @Test
    public void deleteCobroTest() {
        CobroEntity entity = data.get(1);
        cobroLogic.deleteCobro(entity.getId());
        CobroEntity deleted = em.find(CobroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    //Helpers
    /**
     * Elimina los datos que hay actualmente en la base de datos.
     */
    public void clearData() {
        em.createQuery("delete from CobroEntity").executeUpdate();
    }

    /**
     * Inserta los datos de prueba a la base de datos
     */
    public void insertData() {
        for (int i = 0; i < 10; i++) {
            CobroEntity entity = factory.manufacturePojo(CobroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
}
