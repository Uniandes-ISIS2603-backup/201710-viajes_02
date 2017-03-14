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
public class AutomovilEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String placa;
	
	private String marca;
	
	private String modelo;
	
	private Integer cantAsientos;
	
	private String color;
	
	private String compSeguros;
	
	private Integer numSeguro;
	
	public String getPlaca( )
	{
		return placa;
	}
	
	public String getMarca( )
	{
		return marca;
	}
	
	public String getModelo( )
	{
		return modelo;
	}
	
	public Integer getCantAsientos( )
	{
		return cantAsientos;
	}
	
	public String getColor( )
	{
		return color;
	}
	
	public String getCompSeguros( )
	{
		return compSeguros;
	}
	
	public Integer getNumSeguro( )
	{
		return numSeguro;
	}
	
	public Long getId( )
	{
		return id;
	}
	
	public void setPlaca( String placa )
	{
		this.placa = placa;
	}
	
	public void setMarca( String marca )
	{
		this.marca = marca;
	}
	
	public void setModelo( String modelo )
	{
		this.modelo = modelo;
	}
	
	public void setCantAsientos( Integer cantAsientos )
	{
		this.cantAsientos = cantAsientos;
	}
	
	public void setColor( String color )
	{
		this.color = color;
	}
	
	public void setCompSeguros( String compSeguros )
	{
		this.compSeguros = compSeguros;
	}
	
	public void setNumSeguro( Integer numSeguro )
	{
		this.numSeguro = numSeguro;
	}
	
	public void setId( Long id )
	{
		this.id = id;
	}
	
	public boolean equals( Object obj )
	{
		if( this.getPlaca( ) != null )
		{
			return this.getPlaca( ).equals( ( ( AutomovilEntity ) obj ).getPlaca( ) );
		}
		return super.equals( obj );
	}
	
	public int hashCode( )
	{
		if( this.getId( ) != null )
		{
			return this.getId( ).hashCode( );
		}
		return super.hashCode( );
	}
}
