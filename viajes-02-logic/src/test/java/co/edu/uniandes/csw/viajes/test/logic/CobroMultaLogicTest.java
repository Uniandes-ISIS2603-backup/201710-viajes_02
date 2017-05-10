/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.test.logic;

import co.edu.uniandes.csw.viajes.ejbs.CobroMultaLogic;
import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;
import co.edu.uniandes.csw.viajes.persistence.CobroMultaPersistence;
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
public class CobroMultaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CobroMultaLogic cobroMultaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CobroMultaEntity> data = new ArrayList<CobroMultaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CobroMultaEntity.class.getPackage())
                .addPackage(CobroMultaLogic.class.getPackage())
                .addPackage(CobroMultaPersistence.class.getPackage())
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
     * Prueba de crear un cobro multa
     */
    @Test
    public void createCobroMultaTest() {
        CobroMultaEntity newEntity = factory.manufacturePojo(CobroMultaEntity.class);

        CobroMultaEntity result = cobroMultaLogic.createCobroMulta(newEntity);
        Assert.assertNotNull(result);

        CobroMultaEntity entity = em.find(CobroMultaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getValor(), entity.getValor());
    }

    /**
     * Prueba que se den todos los cobros multa
     */
    @Test
    public void getCobrosMultaTest() {
        List<CobroMultaEntity> list = cobroMultaLogic.findCobroMultas();
        Assert.assertEquals(data.size(), list.size());
        for (CobroMultaEntity entity : list) {
            boolean found = false;
            for (CobroMultaEntity entity2 : data) {
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
    public void getCobroMultaTest() {
        CobroMultaEntity entity = data.get(0);
        CobroMultaEntity resultEntity = cobroMultaLogic.findCobroMulta(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getValor(), resultEntity.getValor());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba que se actualice un cobro.
     */
    @Test
    public void updateCobroMultaTest() {
        CobroMultaEntity entity = data.get(0);
        CobroMultaEntity newEntity = factory.manufacturePojo(CobroMultaEntity.class);

        newEntity.setId(entity.getId());

        cobroMultaLogic.updateCobroMulta(newEntity);

        CobroMultaEntity resp = em.find(CobroMultaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getValor(), resp.getValor());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba que se elimine un cobro multa.
     */
    @Test
    public void deleteCobroMultaTest() {
        CobroMultaEntity entity = data.get(1);
        cobroMultaLogic.deleteCobroMulta(entity.getId());
        CobroMultaEntity deleted = em.find(CobroMultaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    //Helpers
    /**
     * Elimina los datos que hay actualmente en la base de datos.
     */
    public void clearData() {
        em.createQuery("delete from CobroMultaEntity").executeUpdate();
    }

    /**
     * Inserta los datos de prueba a la base de datos
     */
    public void insertData() {
        for (int i = 0; i < 10; i++) {
            CobroMultaEntity entity = factory.manufacturePojo(CobroMultaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
}
