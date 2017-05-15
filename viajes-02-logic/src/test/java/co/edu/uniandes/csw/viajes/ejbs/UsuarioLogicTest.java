/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.UsuarioPersistence;
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
public class UsuarioLogicTest {

    @Inject
    private UsuarioLogic usuarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
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
        UsuarioEntity result = null;
        newEntity.setGenero("Indefinido");
        try {
            result = usuarioLogic.createUsuario(newEntity);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        try {
            newEntity.setGenero("Masculino");
            result = usuarioLogic.createUsuario(newEntity);
            Assert.assertNotNull(result);

            UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
            Assert.assertNotNull(entity);
            Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        usuarioLogic.deleteUsuario(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void getUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity newEntity = null;
        try {
            newEntity = usuarioLogic.getUsuario((long) 964);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        try {
            newEntity = usuarioLogic.getUsuario(entity.getId());
        } catch (Exception e) {
            fail();
        }
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    @Test
    public void getUsuariosTest() {
        List<UsuarioEntity> list = usuarioLogic.getUsuarios();
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
    public void updateUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        try {
            newEntity.setGenero("Indefinido");
            usuarioLogic.updateUsuario(newEntity);
            fail();
        } catch (BusinessLogicException e) {
            assertTrue(true);
        }
        try {
            newEntity.setGenero("Femenino");
            newEntity.setId((long) 11111);
            usuarioLogic.updateUsuario(newEntity);
            fail();

        } catch (BusinessLogicException e) {
            assertTrue(true);
        }
        try {
            newEntity.setGenero("Femenino");
            newEntity.setId(entity.getId());
            usuarioLogic.updateUsuario(newEntity);
            assertTrue(true);
        } catch (BusinessLogicException e) {
            fail();
        }
        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
}
