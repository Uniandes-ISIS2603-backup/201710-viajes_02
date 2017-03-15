/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ReviewEntity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Danny
 */
@Stateless
public class ReviewPersistence
{

    /**
     * base de datos
     */
    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager em;

    /**
     * encontrar todas las reviews
     *
     * @return List de ReviewEntity
     */
    public List<ReviewEntity> findAll()
    {
        Query q = em.createQuery("select u from ReviewEntity u");
        return q.getResultList();
    }

    /**
     * encuentra el review de un usuario en particular
     *
     * @param idCalificado
     * @return ReviewEntity
     */

    public ReviewEntity find(Long idCalificado)
    {
        return em.find(ReviewEntity.class, idCalificado);
    }

    /**
     * crea un nuevo review
     *
     * @param idCalificado
     * @return ReviewEntity
     */
    public ReviewEntity create(ReviewEntity idCalificado)
    {
        em.persist(idCalificado);
        return idCalificado;
    }

    /**
     * actualiza el review de un usuario
     *
     * @param idCalificado
     * @return ReviewEntity
     */
    public ReviewEntity update(ReviewEntity idCalificado)
    {
        return em.merge(idCalificado);

    }

    public void delete(Long idCalificado)
    {
        ReviewEntity borrar = em.find(ReviewEntity.class, idCalificado);
        em.remove(borrar);

    }

    /**
     * busca el idDelCalificado
     *
     * @param idCalificado
     * @return ReviewEntity
     */
    public ReviewEntity findByCalificado(Long idCalificado)
    {
        //		TypedQuery<ReviewEntity> q = em.createQuery( "select u from ReviewEntity u where u.idCalificado = :idCalificado", ReviewEntity.class );
        //		q = q.setParameter( "idCalificado", idCalificado );
        //
        //		List<ReviewEntity> sameID = q.getResultList( );
        //		if( sameID.isEmpty( ) )
        //		{
        //			return null;
        //		}
        //		else
        //		{
        //			return sameID.get( 0 );
        //		}
        TypedQuery<ReviewEntity> q = em.createQuery("select u from ReviewEntity u where u.idCalificado = :idCalificado", ReviewEntity.class);
        return q.getResultList().get(0);
    }

    /**
     * busca el idDelCalificador
     *
     * @param idCalificador
     * @return ReviewEntity
     */
    public ReviewEntity findByCalificador(Long idCalificador)
    {
        TypedQuery<ReviewEntity> q = em.createQuery("select u from ReviewEntity u where u.idCalificador = :idCalificador", ReviewEntity.class);
        q = q.setParameter("idCalificador", idCalificador);

        List<ReviewEntity> sameID = q.getResultList();
        if (sameID.isEmpty())
        {
            return null;
        }
        else
        {
            return sameID.get(0);
        }
    }
}
