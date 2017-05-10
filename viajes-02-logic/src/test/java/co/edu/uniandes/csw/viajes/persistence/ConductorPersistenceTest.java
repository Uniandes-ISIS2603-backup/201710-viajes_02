/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
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
public class ConductorPersistenceTest {

    @Inject
    private ConductorPersistence conductorPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ConductorEntity> data = new ArrayList<ConductorEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(ConductorEntity.class.getPackage())
                .addPackage(ConductorPersistence.class.getPackage())
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
        em.createQuery("delete from ConductorEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            ConductorEntity entity = factory.manufacturePojo(ConductorEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createConductorTest() {
        PodamFactory factory = new PodamFactoryImpl();

        ConductorEntity newEntity = factory.manufacturePojo(ConductorEntity.class);

        ConductorEntity result = conductorPersistence.create(newEntity);

        Assert.assertNotNull(result);
        ConductorEntity entity = em.find(ConductorEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    @Test
    public void getConductoresTest() {
        List<ConductorEntity> list = conductorPersistence.finAll();
        Assert.assertEquals(data.size(), list.size());
        for (ConductorEntity ent : list) {
            boolean found = false;
            for (ConductorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getConductorTest(){
        ConductorEntity entity = data.get(0);
        ConductorEntity newEntity = conductorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    @Test
    public void updateConductorTest(){
        ConductorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ConductorEntity newEntity = factory.manufacturePojo(ConductorEntity.class);

        newEntity.setId(entity.getId());

        conductorPersistence.update(newEntity);

        ConductorEntity resp = em.find(ConductorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    @Test
    public void deleteConductorTest(){
        ConductorEntity entity = data.get(0);
        conductorPersistence.delete(entity.getId());
        ConductorEntity deleted = em.find(ConductorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
