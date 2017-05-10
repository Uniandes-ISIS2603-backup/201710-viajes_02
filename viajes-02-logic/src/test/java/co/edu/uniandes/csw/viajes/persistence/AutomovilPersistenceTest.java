package co.edu.uniandes.csw.viajes.persistence;


import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.persistence.AutomovilPersistence;
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

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author d.jaimes
 */
@RunWith(Arquillian.class)
public class AutomovilPersistenceTest
{

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AutomovilEntity.class.getPackage())
                .addPackage(AutomovilPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    private List<AutomovilEntity> automovilData = new ArrayList<>();
    @Inject
    private AutomovilPersistence automovilPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
        em.createQuery("delete  from AutomovilEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AutomovilEntity entity = factory.manufacturePojo(AutomovilEntity.class);

            em.persist(entity);
            automovilData.add(entity);
        }

    }

    @Test
    public void createAutomovilTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AutomovilEntity newEntity = factory.manufacturePojo(AutomovilEntity.class);
        AutomovilEntity result = automovilPersistence.create(newEntity);

        Assert.assertNotNull(result);

        AutomovilEntity entity = em.find(AutomovilEntity.class, result.getId());

        Assert.assertEquals(newEntity.getPlaca(), entity.getPlaca());
        Assert.assertEquals(newEntity.getColor(), entity.getColor());
    }

    @Test
    public void getAutomovilesTest() {
        List<AutomovilEntity> list = automovilPersistence.findAll();
        Assert.assertEquals(automovilData.size(), list.size());
        for (AutomovilEntity ent : list) {
            boolean found = false;
            for (AutomovilEntity entity : automovilData) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getAutomovilTest() {
        AutomovilEntity entity = automovilData.get(0);
        AutomovilEntity newEntity = automovilPersistence.find(entity.getId());

        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPlaca(), newEntity.getPlaca());
        Assert.assertEquals(entity.getMarca(), newEntity.getMarca());
    }

    @Test
    public void deleteAutomovilTest() {
        AutomovilEntity entity = automovilData.get(0);
        automovilPersistence.delete(entity.getId());
        AutomovilEntity deleted = em.find(AutomovilEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    @Test
    public void updateEmployeeTest() {
        AutomovilEntity entity = automovilData.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AutomovilEntity newEntity = factory.manufacturePojo(AutomovilEntity.class);

        newEntity.setId(entity.getId());

        automovilPersistence.update(newEntity);

        AutomovilEntity resp = em.find(AutomovilEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getPlaca(), resp.getPlaca());
        Assert.assertEquals(newEntity.getMarca(), resp.getMarca());
    }
}
