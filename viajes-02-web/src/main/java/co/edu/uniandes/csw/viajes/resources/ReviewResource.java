/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ReviewDTO;
import co.edu.uniandes.csw.viajes.ejbs.ReviewLogic;
import co.edu.uniandes.csw.viajes.entities.ReviewEntity;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author Danny
 */
@Path( "/review" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ReviewResource
{
	@Inject
	private ReviewLogic logic;
	
	@Context
	private HttpServletResponse response;
	
	@QueryParam( "page" )
	private Integer page;
	
	@QueryParam( "limit" )
	private Integer maxRecords;
	
	private List<ReviewDTO> listEntity2DTO( List<ReviewEntity> entityList )
	{
		List<ReviewDTO> list = new ArrayList<>( );
		for( ReviewEntity entity : entityList )
		{
			list.add( new ReviewDTO( entity ) );
		}
		return list;
	}
	
	@GET
	@Path( "{id: \\d+}" )
	public ReviewDTO getReview( @PathParam( "id" ) Long id )
	{
		return new ReviewDTO( logic.getReview( id ) );
	}
	
	@POST
	public ReviewDTO newReview( ReviewDTO rev ) throws Exception
	{
		return new ReviewDTO( logic.creatReview( rev.toEntity( ) ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteReview( @PathParam( "id" ) Long id )
	{
		logic.deletReview( id );
	}
}