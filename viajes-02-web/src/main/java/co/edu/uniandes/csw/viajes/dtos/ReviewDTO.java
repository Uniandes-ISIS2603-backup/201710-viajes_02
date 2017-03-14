/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ReviewEntity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Danny
 */

@XmlRootElement
public class ReviewDTO implements Serializable
{
	private Long id;
	
	private Long idCalificado;
	
	private Long idCalificador;
	
	private String coment;
	
	private Integer calificacion;
	
	public ReviewDTO( )
	{
	}
	
	public ReviewDTO( ReviewEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.idCalificado = entity.getIdCalificado( );
			this.idCalificador = entity.getIdCalificador( );
			this.coment = entity.getComent( );
			this.calificacion = entity.getCalificacion( );
		}
		
	}
	
	/**
	 * Retrieves the id of the ReviewDTO
	 *
	 * @return The id of the ReviewDTO
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ReviewDTO by the one given by parameter
	 *
	 * @param id The new id of the ReviewDTO
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	public ReviewEntity toEntity( )
	{
		ReviewEntity reviewA = new ReviewEntity( );
		reviewA.setId( this.id );
		reviewA.setCalificacion( this.getCalificacion( ) );
		reviewA.setComent( this.getComent( ) );
		return reviewA;
	}
	
	public Long getIdCalificado( )
	{
		return idCalificado;
	}
	
	public Long getIdCalificador( )
	{
		return idCalificador;
	}
	
	public String getComent( )
	{
		return coment;
	}
	
	public Integer getCalificacion( )
	{
		return calificacion;
	}
	
	public void setIdCalificado( Long idCalificado )
	{
		this.idCalificado = idCalificado;
	}
	
	public void setIdCalificador( Long idCalificador )
	{
		this.idCalificador = idCalificador;
	}
	
	public void setComent( String coment )
	{
		this.coment = coment;
	}
	
	public void setCalificacion( Integer calificacion )
	{
		this.calificacion = calificacion;
	}
	
}
