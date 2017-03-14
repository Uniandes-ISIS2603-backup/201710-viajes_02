/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author d.jaimes
 */

@XmlRootElement
public class AutomovilDetailDTO extends AutomovilDTO
{
	public AutomovilDetailDTO( )
	{
		super( );
	}
	
	public AutomovilDetailDTO( AutomovilEntity entity )
	{
		super( entity );
	}
	
	public AutomovilEntity toEntity( )
	{
		AutomovilEntity entity = super.toEntity( );
		return entity;
	}
}