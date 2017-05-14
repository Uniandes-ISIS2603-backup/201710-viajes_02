/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ConductorPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jm.dominguez
 */
@RunWith(Arquillian.class)
public class ConductorLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private ConductorLogic conductorLogic;

    @PersistenceContext
    private EntityManager em;

    /**
     *
     */
    @Inject
    private UserTransaction utx;

    /**
     *
     */
    private List<ConductorEntity> data = new ArrayList<ConductorEntity>();
    private AutomovilEntity automovil;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
        .addPackage(ConductorLogic.class.getPackage())
        .addPackage(ConductorPersistence.class.getPackage())
        .addPackage(UsuarioEntity.class.getPackage())
        .addPackage(ConductorEntity.class.getPackage())
        .addPackage(AutomovilEntity.class.getPackage())
        .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
        .addAsManifestResource("META-INF/beans.xml", "beans.xml");
        }

        @Before
    public void setUp() {
        try {
            utx.begin();
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from AutomovilEntity").executeUpdate();
        
        

    }
     //TODO: HACER
 private void insertData() {

        for (int i = 0; i < 3; i++) {
            ConductorEntity entity = factory.manufacturePojo(ConductorEntity.class);
            // entity.set(data.get(0));
            em.persist(entity);
            data.add(entity);
        }
        automovil = factory.manufacturePojo(AutomovilEntity.class);
        em.persist(automovil);
    }

 @Test
    public void createConductorTest() {
        List <AutomovilEntity> carros = new ArrayList();
        carros.add(automovil);
        ConductorEntity newEntity = factory.manufacturePojo(ConductorEntity.class);
        ConductorEntity result = null;
        newEntity.setAutomoviles(carros);
     
     try{
         result = conductorLogic.createConductor(newEntity);
     
        Assert.assertNotNull(result);
        ConductorEntity entity = em.find(ConductorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getEdad(), entity.getEdad());
        Assert.assertEquals(newEntity.getId(), entity.getId());
     }
     catch(Exception e){
         System.out.println(e.getMessage());
         Assert.fail();
     }
    }

     @Test
    public void getConductoresTest() {
        List<ConductorEntity> list = conductorLogic.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ConductorEntity entity : list) {
            boolean found = false;
            for (ConductorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getConductorTest() {
        ConductorEntity entity = data.get(0);
        ConductorEntity resultEntity = null;
        try {
            resultEntity = conductorLogic.getConductor(entity.getId());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ConductorLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getCorreo(), resultEntity.getCorreo());
        Assert.assertEquals(entity.getEdad(), resultEntity.getEdad());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

     @Test
    public void deleteConductorTest() {
        ConductorEntity entity = data.get(1);
        conductorLogic.deleteConductor(entity.getId());
        ConductorEntity deleted = em.find(ConductorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

      @Test
    public void updateConductorTest() {
        ConductorEntity entity = data.get(0);
        ConductorEntity pojoEntity = factory.manufacturePojo(ConductorEntity.class);

        pojoEntity.setId(entity.getId());

        try{
        conductorLogic.updateConductor(pojoEntity);

        ConductorEntity resp = em.find(ConductorEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getCorreo(), resp.getCorreo());
        Assert.assertEquals(pojoEntity.getEdad(), resp.getEdad());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        }
        catch(Exception e){
        Assert.fail();
        System.out.println(e.fillInStackTrace());
        }
    }

}
