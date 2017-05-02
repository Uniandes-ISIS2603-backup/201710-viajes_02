/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author n.aguilar
 */
@RunWith(Arquillian.class)
public class ViajeroPersistenceTest {

    @Inject
    private ViajeroPersistence viajeroPersistence;

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
                .addPackage(ViajeroPersistence.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
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

        ViajeroEntity result = viajeroPersistence.create(newEntity);

        Assert.assertNotNull(result);
        ViajeroEntity entity = em.find(ViajeroEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    @Test
    public void getViajerosTest() {
        List<ViajeroEntity> list = viajeroPersistence.findAll();
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
    public void getViajeroTest(){
        ViajeroEntity entity = data.get(0);
        ViajeroEntity newEntity = viajeroPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    @Test
    public void updateViajeroTest(){
        ViajeroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);

        newEntity.setId(entity.getId());

        viajeroPersistence.update(newEntity);

        ViajeroEntity resp = em.find(ViajeroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    @Test
    public void deleteViajeroTest(){
        ViajeroEntity entity = data.get(0);
        viajeroPersistence.delete(entity.getId());
        ViajeroEntity deleted = em.find(ViajeroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
