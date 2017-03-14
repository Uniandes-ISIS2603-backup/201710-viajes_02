/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ReviewEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Danny
 */
    @XmlRootElement
public class ReviewDetailDTO
    {


	public ReviewDetailDTO( )
	{
		super( );
	}
	
	//public ReviewDetailDTO( ReviewEntity entity )
	//{
	//	super( entity );
	//}
	//
	//public ReviewEntity toEntity( )
	//{
	//	ReviewEntity entity = super.toEntity( );
	//	return entity;
	//}
}
