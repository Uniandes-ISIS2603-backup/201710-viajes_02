package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.ReviewEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
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

/**
 * Created by Danny on 9/05/2017.
 */
@RunWith(Arquillian.class)

public class ReviewsPersistence {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReviewEntity.class.getPackage())
                .addPackage(ReviewPersistence.class.getPackage())
                .addPackage(ConductorEntity.class.getPackage())
                .addPackage(ViajeroEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    private List<ReviewEntity> reviewData = new ArrayList<>();

    @Inject
    private ReviewPersistence reviewPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private ConductorEntity conductor;
    private ViajeroEntity viajero;

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
        em.createQuery("delete  from ReviewEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        conductor = factory.manufacturePojo(ConductorEntity.class);
        viajero = factory.manufacturePojo(ViajeroEntity.class);
        conductor.setId(1L);
        viajero.setId(1L);
        em.persist(conductor);
        em.persist(viajero);
        for (int i = 0; i < 3; i++) {
            ReviewEntity entity = factory.manufacturePojo(ReviewEntity.class);
            entity.setIdCalificador(viajero.getId());
            entity.setIdCalificado(conductor.getId());
            em.persist(entity);
            reviewData.add(entity);
        }

    }
    @Test
    public void createReviewTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ReviewEntity newEntity = factory.manufacturePojo(ReviewEntity.class);
        ReviewEntity result = reviewPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ReviewEntity entity = em.find(ReviewEntity.class, result.getId());

        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(newEntity.getComent(), entity.getComent());
    }

    @Test
    public void getReviewsporUsuarioTest() {
        List<ReviewEntity> list = reviewPersistence.findReviewsUsuario(conductor.getId());
        Assert.assertEquals(reviewData.size(), list.size());
        for (ReviewEntity ent : list) {
            boolean found = false;
            for (ReviewEntity entity : reviewData) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void deleteReviewTest() {
        ReviewEntity entity = reviewData.get(0);
        reviewPersistence.delete(entity.getId());
        ReviewEntity deleted = em.find(ReviewEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    @Test
    public void updateReviewTest() {
        ReviewEntity entity = reviewData.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReviewEntity newEntity = factory.manufacturePojo(ReviewEntity.class);

        newEntity.setId(entity.getId());

        reviewPersistence.update(newEntity);

        ReviewEntity resp = em.find(ReviewEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getCalificacion(), resp.getCalificacion());
        Assert.assertEquals(newEntity.getComent(), resp.getComent());
    }
}
