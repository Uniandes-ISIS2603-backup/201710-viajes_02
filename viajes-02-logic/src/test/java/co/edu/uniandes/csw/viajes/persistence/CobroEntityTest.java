/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;
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
public class CobroEntityTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarEntity.class.getPackage())
                .addPackage(LugarPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private CobroPersistence cobroPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CobroEntity> data = new ArrayList<CobroEntity>();

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
     * Prueba la creacion de un cobro
     */
    @Test
    public void createCobroTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CobroEntity newEntity = factory.manufacturePojo(CobroEntity.class);
        CobroEntity result = cobroPersistence.create(newEntity);

        Assert.assertNotNull(result);
        CobroEntity entity = em.find(CobroEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getValor(), entity.getValor());
    }

    /**
     * Prueba que se den todos los cobros
     */
    @Test
    public void getCobrosTest() {
        List<CobroEntity> list = cobroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        
        for(CobroEntity en : list) {
            boolean found = false;
            
            for(CobroEntity en2: data) {
                if(en.getId().equals(en2.getId()))
                    found = true;
            }
            
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba que se de un cobro
     */
    @Test
    public void getCobroTest() {
        CobroEntity en = data.get(0);
        CobroEntity result = cobroPersistence.find(en.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getValor(), en.getValor());
    }
    
    /**
     * Prueba que se actualize un cobro
     */
    @Test
    public void updateCobroTest() {
        CobroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CobroEntity newEntity = factory.manufacturePojo(CobroEntity.class);
        
        newEntity.setId(entity.getId());
        cobroPersistence.update(newEntity);
        CobroEntity resp = em.find(CobroEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getValor(), resp.getValor());
    }
    
    /**
     * Prueba que se elimine un cobro
     */
    @Test
    public void deleteCobroTest() {
        CobroEntity entity = data.get(0);
        cobroPersistence.delete(entity.getId());
        CobroEntity deleted = em.find(CobroEntity.class,entity.getId());
        Assert.assertNull(deleted);
    }

    // Helpers
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
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 10; i++) {
            CobroEntity entity = factory.manufacturePojo(CobroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
}
