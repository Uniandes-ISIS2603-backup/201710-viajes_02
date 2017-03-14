/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Danny
 */
@Entity
public class ReviewEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private Long idCalificado;
	
	private Long idCalificador;
	
	private String coment;
	
	private Integer calificacion;
	
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
	
	/**
	 * Retrieves the id of the ReviewEntity
	 *
	 * @return The id of the ReviewEntity
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ReviewEntity by the one given by parameter
	 *
	 * @param id The new id of the ReviewEntity
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
}
