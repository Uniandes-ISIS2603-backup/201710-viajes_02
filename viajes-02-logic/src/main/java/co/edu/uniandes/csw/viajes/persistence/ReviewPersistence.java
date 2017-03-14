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
	@PersistenceContext( unitName = "viajesPU" )
	protected EntityManager em;
	
	public List<ReviewEntity> findAll( )
	{
		Query q = em.createQuery( "select u from ReviewEntity u" );
		return q.getResultList( );
	}
	
	public ReviewEntity find( Long idCalificado )
	{
		return em.find( ReviewEntity.class, idCalificado );
	}
	
	public ReviewEntity create( ReviewEntity idCalificado )
	{
		em.persist( idCalificado );
		return idCalificado;
	}
	
	public ReviewEntity update( ReviewEntity idCalificado )
	{
		return em.merge( idCalificado );
		
	}
	
	public void delete( Long idCalificado )
	{
		ReviewEntity borrar = em.find( ReviewEntity.class, idCalificado );
		em.remove( borrar );
		
	}
	
	public ReviewEntity findByCalificado( Long idCalificado )
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
		TypedQuery<ReviewEntity> q = em.createQuery( "select u from ReviewEntity u where u.idCalificado = :idCalificado", ReviewEntity.class );
		return q.getResultList().get( 0 );
	}
	
	public ReviewEntity findByCalificador( Long idCalificador )
	{
		TypedQuery<ReviewEntity> q = em.createQuery( "select u from ReviewEntity u where u.idCalificador = :idCalificador", ReviewEntity.class );
		q = q.setParameter( "idCalificador", idCalificador );
		
		List<ReviewEntity> sameID = q.getResultList( );
		if( sameID.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameID.get( 0 );
		}
	}
}
