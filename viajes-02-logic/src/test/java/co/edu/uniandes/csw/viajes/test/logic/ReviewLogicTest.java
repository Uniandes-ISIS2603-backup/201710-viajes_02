package co.edu.uniandes.csw.viajes.test.logic;//package co.edu.uniandes.csw.viajes.test.logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.edu.uniandes.csw.viajes.ejbs.AutomoviLogic;
import co.edu.uniandes.csw.viajes.ejbs.ReviewLogic;
import co.edu.uniandes.csw.viajes.ejbs.ReviewLogic;
import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.ReviewEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import co.edu.uniandes.csw.viajes.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.viajes.persistence.ReviewPersistence;
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
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author d.jaimes
 */
@RunWith(Arquillian.class)
public class ReviewLogicTest
{

    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private ReviewLogic reviewLogic;

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
    private List<ReviewEntity> data = new ArrayList<ReviewEntity>();

    private ConductorEntity conductor;
    private ViajeroEntity viajero;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReviewEntity.class.getPackage())
                .addPackage(ReviewLogic.class.getPackage())
                .addPackage(ReviewPersistence.class.getPackage())
                .addPackage(ConductorEntity.class.getPackage())
                .addPackage(ViajeroEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void setUp()
    {
        try
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                utx.rollback();
            } catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }

    private void clearData()
    {
        em.createQuery("delete from ReviewEntity").executeUpdate();

    }
    //TODO: HACER

    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        conductor = factory.manufacturePojo(ConductorEntity.class);
        viajero = factory.manufacturePojo(ViajeroEntity.class);
        conductor.setId(1L);
        viajero.setId(1L);
        em.persist(conductor);
        em.persist(viajero);

        for (int i = 0; i < 3; i++)
        {
            ReviewEntity entity = factory.manufacturePojo(ReviewEntity.class);
            // entity.set(data.get(0));
            entity.setIdCalificador(viajero.getId());
            entity.setIdCalificado(conductor.getId());
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createReviewTest()
    {
        ReviewEntity newEntity = factory.manufacturePojo(ReviewEntity.class);
        ReviewEntity result = null;
        try
        {
            result = reviewLogic.creatReview(newEntity);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        Assert.assertNotNull(result);
        ReviewEntity entity = em.find(ReviewEntity.class, result.getId());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(newEntity.getComent(), entity.getComent());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    @Test
    public void getReviewsTest()
    {
        List<ReviewEntity> list = reviewLogic.getReviews();
        Assert.assertEquals(data.size(), list.size());
        for (ReviewEntity entity : list)
        {
            boolean found = false;
            for (ReviewEntity storedEntity : data)
            {
                if (entity.getId().equals(storedEntity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    //  @Test
    //public void getReviewTest() {
    //  ReviewEntity entity = data.get(0);
    //List<ReviewEntity> resultEntity = reviewLogic.getReviews(entity.getId());
    //    Assert.assertNotNull(resultEntity);
    //  Assert.assertEquals(entity.getCalificacion(), resultEntity.getPlaca());
    //      Assert.assertEquals(entity.getMarca(), resultEntity.getMarca());
    //    Assert.assertEquals(entity.getId(), resultEntity.getId());
    //}
    @Test
    public void deleteReviewTest()
    {
        ReviewEntity entity = data.get(1);
        reviewLogic.deletReview(entity.getId());
        ReviewEntity deleted = em.find(ReviewEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateReviewTest()
    {
        ReviewEntity entity = data.get(0);
        ReviewEntity pojoEntity = factory.manufacturePojo(ReviewEntity.class);

        pojoEntity.setId(entity.getId());

        reviewLogic.updateReview(pojoEntity);

        ReviewEntity resp = em.find(ReviewEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getCalificacion(), resp.getCalificacion());
        Assert.assertEquals(pojoEntity.getComent(), resp.getComent());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

}
