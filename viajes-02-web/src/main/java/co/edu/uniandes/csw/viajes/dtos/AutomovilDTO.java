/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Danny
 */
@XmlRootElement
public class AutomovilDTO implements Serializable
{
	private Long id;
	
	private String placa;
	
	private String marca;
	
	private String modelo;
	
	private Integer cantAsientos;
	
	private String color;
	
	private String compSeguros;
	
	private Integer numSeguro;
	
	public AutomovilDTO( )
	{
	}
	
	public AutomovilDTO( AutomovilEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.placa = entity.getPlaca( );
			this.cantAsientos = entity.getCantAsientos( );
			this.color = entity.getColor( );
			this.compSeguros = entity.getCompSeguros( );
			this.marca = entity.getMarca( );
			this.modelo = entity.getModelo( );
			this.numSeguro = entity.getNumSeguro( );
		}
	}
	
	public AutomovilEntity toEntity( )
	{
		AutomovilEntity auto = new AutomovilEntity( );
		auto.setCantAsientos( this.getCantAsientos( ) );
		auto.setColor( this.getColor( ) );
		auto.setCompSeguros( this.getCompSeguros( ) );
		auto.setMarca( this.getMarca( ) );
		auto.setModelo( this.getModelo( ) );
		auto.setNumSeguro( this.getNumSeguro( ) );
		auto.setPlaca( this.getPlaca( ) );
		auto.setId( this.getId( ) );
		return auto;
		
	}
	
	public Long getId( )
	{
		return id;
	}
	
	public void setId( Long id )
	{
		this.id = id;
	}
	
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
}
