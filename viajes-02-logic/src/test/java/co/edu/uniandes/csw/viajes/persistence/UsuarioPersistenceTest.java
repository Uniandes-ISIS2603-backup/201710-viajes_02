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
public class UsuarioPersistenceTest {
    
    @Inject
    private UsuarioPersistence usuarioPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
  
    @Test
    public void createUsuarioTest() {
        PodamFactory factory = new PodamFactoryImpl();

        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);

        UsuarioEntity result = usuarioPersistence.create(newEntity);

        Assert.assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    @Test
    public void getUsuariosTest() {
        List<UsuarioEntity> list = usuarioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (UsuarioEntity ent : list) {
            boolean found = false;
            for (UsuarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getUsuarioTest(){
        UsuarioEntity entity = data.get(0);
        UsuarioEntity newEntity = usuarioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    @Test
    public void updateUsuarioTest(){
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);

        newEntity.setId(entity.getId());

        usuarioPersistence.update(newEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    @Test
    public void deleteUsuarioTest(){
        UsuarioEntity entity = data.get(0);
        usuarioPersistence.delete(entity.getId());
        ViajeroEntity deleted = em.find(ViajeroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
