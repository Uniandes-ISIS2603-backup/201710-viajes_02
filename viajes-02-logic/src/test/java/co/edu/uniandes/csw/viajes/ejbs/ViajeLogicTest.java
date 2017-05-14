/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.LugarEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ViajePersistence;
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
 * @author jm.dominguez
 */
@RunWith(Arquillian.class)
public class ViajeLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private ViajeLogic viajeLogic;

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
    private List<ViajeEntity> data = new ArrayList<ViajeEntity>();
    private ConductorEntity conductor;
    private LugarEntity origen;
    private LugarEntity destino;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
        .addPackage(ViajeEntity.class.getPackage())
        .addPackage(ViajeLogic.class.getPackage())
        .addPackage(ViajePersistence.class.getPackage())
        .addPackage(UsuarioEntity.class.getPackage())
        .addPackage(ConductorEntity.class.getPackage())
        .addPackage(LugarEntity.class.getPackage())
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
        em.createQuery("delete from ViajeEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from LugarEntity").executeUpdate();
        

    }
     //TODO: HACER
 private void insertData() {

        for (int i = 0; i < 3; i++) {
            ViajeEntity entity = factory.manufacturePojo(ViajeEntity.class);
            // entity.set(data.get(0));
            em.persist(entity);
            data.add(entity);
        }
        conductor = factory.manufacturePojo(ConductorEntity.class);
        em.persist(conductor);
    }

 @Test
    public void createViajeTest() {
        ViajeEntity newEntity = factory.manufacturePojo(ViajeEntity.class);
        ViajeEntity result = null;
        newEntity.setConductor(conductor);
        origen = factory.manufacturePojo(LugarEntity.class);
        destino = factory.manufacturePojo(LugarEntity.class);
        newEntity.setOrigen(origen);
        newEntity.setDestino(destino);
     
     try{
         result = viajeLogic.createViaje(newEntity);
     
        Assert.assertNotNull(result);
        ViajeEntity entity = em.find(ViajeEntity.class, result.getIdViaje());
        Assert.assertEquals(newEntity.getKilometros(), entity.getKilometros());
        Assert.assertEquals(newEntity.getGastosCompartidos(), entity.getGastosCompartidos());
        Assert.assertEquals(newEntity.getIdViaje(), entity.getIdViaje());
     }
     catch(Exception e){
         System.out.println(e.getMessage());
         Assert.fail();
     }
    }

     @Test
    public void getViajesTest() {
        List<ViajeEntity> list = viajeLogic.findViajes();
        Assert.assertEquals(data.size(), list.size());
        for (ViajeEntity entity : list) {
            boolean found = false;
            for (ViajeEntity storedEntity : data) {
                if (entity.getIdViaje().equals(storedEntity.getIdViaje())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getViajeTest() {
        ViajeEntity entity = data.get(0);
        ViajeEntity resultEntity = viajeLogic.findViaje(entity.getIdViaje());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getKilometros(), resultEntity.getKilometros());
        Assert.assertEquals(entity.getGastosCompartidos(), resultEntity.getGastosCompartidos());
        Assert.assertEquals(entity.getIdViaje(), resultEntity.getIdViaje());
    }

     @Test
    public void deleteViajeTest() {
        ViajeEntity entity = data.get(1);
        viajeLogic.deleteViaje(entity.getIdViaje());
        ViajeEntity deleted = em.find(ViajeEntity.class, entity.getIdViaje());
        Assert.assertNull(deleted);
    }

      @Test
    public void updateViajeTest() {
        ViajeEntity entity = data.get(0);
        ViajeEntity pojoEntity = factory.manufacturePojo(ViajeEntity.class);

        pojoEntity.setIdViaje(entity.getIdViaje());
        pojoEntity.setConductor(conductor);
        origen = factory.manufacturePojo(LugarEntity.class);
        destino = factory.manufacturePojo(LugarEntity.class);
        pojoEntity.setOrigen(origen);
        pojoEntity.setDestino(destino);

        try{
        viajeLogic.updateViaje(pojoEntity);

        ViajeEntity resp = em.find(ViajeEntity.class, entity.getIdViaje());

        Assert.assertEquals(pojoEntity.getKilometros(), resp.getKilometros());
        Assert.assertEquals(pojoEntity.getGastosCompartidos(), resp.getGastosCompartidos());
        Assert.assertEquals(pojoEntity.getIdViaje(), resp.getIdViaje());
        }
        catch(Exception e){
        Assert.fail();
        }
    }

}

