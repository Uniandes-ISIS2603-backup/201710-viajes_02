/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;

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
public class AutomovilPersistence
{
	@PersistenceContext( unitName = "viajesPU" )
	protected EntityManager em;
	
	public List<AutomovilEntity> findAll( )
	{
		Query q = em.createQuery( "SELECT A FROM AutomovilEntity A" );
		return q.getResultList( );
	}
	
	public AutomovilEntity find( String placa )
	{
		return em.find( AutomovilEntity.class, placa );
	}
	
	public AutomovilEntity create( AutomovilEntity carro )
	{
		em.persist( carro );
		return carro;
	}
	
	public AutomovilEntity update( AutomovilEntity auto )
	{
		return em.merge( auto );
		
	}
	
	public void delete( String placa )
	{
		AutomovilEntity borrar = em.find( AutomovilEntity.class, placa );
		em.remove( borrar );
		
	}
	
	public AutomovilEntity findByPlaca( String placa )
	{
		TypedQuery<AutomovilEntity> q = em.createQuery( "select u from AutomovilEntity u where u.placa = :placa", AutomovilEntity.class );
		q = q.setParameter( "placa", placa );
		
		List<AutomovilEntity> samePlaca = q.getResultList( );
		if( samePlaca.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return samePlaca.get( 0 );
		}
	}
}
