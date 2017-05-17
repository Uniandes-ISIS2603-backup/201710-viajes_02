/* 
 * The MIT License
 *
 * Copyright 2017 wr.ravelo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.ReviewEntity;
import co.edu.uniandes.csw.viajes.persistence.ConductorPersistence;
import co.edu.uniandes.csw.viajes.persistence.ReviewPersistence;
import co.edu.uniandes.csw.viajes.persistence.ViajeroPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Danny
 */
@Stateless
public class ReviewLogic
{
    /**
     * injeccion de la persistencia en la logica
     */
	@Inject
	private ReviewPersistence persistence;

	@Inject
	private ConductorPersistence conductorPersistence;

	@Inject
	private ViajeroPersistence viajeroPersistence;

	/**
	 * Obtiene la calificacion de un usuario.
	 *
	 * @param idCalificado (idCalificado) Identificador del usuario
	 * @return ReviewEntity.
	 */
	public List<ReviewEntity> getReviews(Long idCalificado )
	{
		return persistence.findReviewsUsuario( idCalificado );
	}


	public ReviewEntity getReview(Long idCalificado , Long review)
	{
		return persistence.findReviewUsuario(idCalificado,review);
	}

	/**
	 * Se encarga de crear una nueva calificacion en la base de datos.
	 *
	 * @param review Objeto de ReviewEntity con los datos nuevos
	 * @return Objeto de AutomovilEntity con los datos nuevos y su ID.
	 * @generated
	 */
	public ReviewEntity creatReview( ReviewEntity review ) throws Exception
	{
		// calcularla y verificar que  el otro usuario este en el sistema
            System.out.println(review);
            System.out.println(review.getIdCalificador());
		if( viajeroPersistence.find( review.getIdCalificador( ) ) != null )
		{

			if( conductorPersistence.find( review.getIdCalificado( ) ) != null )
			{
				return persistence.create( review );
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
	 * @param idReview para identificar la calificacion del usuario a eliminar
	 */
	public void deleteReview(Long idReview )
	{
		persistence.delete( idReview );
	}
        /**
         * Da todas las reviews
         * @return reviews
         */
	
        public List<ReviewEntity> getReviews( )
	{
		return persistence.findAll( );
	}
	
        
}
