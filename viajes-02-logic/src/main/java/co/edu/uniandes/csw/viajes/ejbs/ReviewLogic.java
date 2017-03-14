/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.ReviewEntity;
import co.edu.uniandes.csw.viajes.persistence.ReviewPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Danny
 */
@Stateless
public class ReviewLogic
{
	@Inject
	private ReviewPersistence persistence;
	
	/**
	 * Obtiene la calificacion de un usuario.
	 *
	 * @param id (idCalificado) Identificador del usuario
	 * @return ReviewEntity.
	 */
	public ReviewEntity getReview( Long idCalificado )
	{
		return persistence.find( idCalificado );
	}
	
	/**
	 * Se encarga de crear una nueva calificacion en la base de datos.
	 *
	 * @param entity Objeto de ReviewEntity con los datos nuevos
	 * @return Objeto de AutomovilEntity con los datos nuevos y su ID.
	 * @generated
	 */
	public ReviewEntity creatReview( ReviewEntity idCalificado ) throws Exception
	{
		// calcularla y verificar que  el otro usuario este en el sistema
		if( persistence.findByCalificador( idCalificado.getIdCalificador( ) ) != null )
		{
			if( persistence.findByCalificado( idCalificado.getIdCalificado( ) ) != null )
			{
				ReviewEntity reviewA = persistence.create( idCalificado );
				return reviewA;
			}
			else
			{
				throw new Exception( "el usuario a calificar no existe" );
			}
		}
		else
		{
			throw new Exception( "el usuario que califica no existe" );
		}
	}
	
	/***
	 * Actualiza  la informacion de un review
	 * @param review Instancia de ReviewEntity con los nuevo datos
	 * @return Instancia  de ReviewEntity actualizada
	 */
	public ReviewEntity updateReview( ReviewEntity review )
	{
		return persistence.update( review );
	}
	
	/**
	 * Elimina la  calificacion de un usuario en la base de datos
	 *
	 * @param idCalificado para identificar la calificacion del usuario a eliminar
	 */
	public void deletReview( Long idCalificado )
	{
		persistence.delete( idCalificado );
	}
	
        public List<ReviewEntity> getReviews( )
	{
		return persistence.findAll( );
	}
	
        
}
