/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.AutomovilDTO;
import co.edu.uniandes.csw.viajes.dtos.AutomovilDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.AutomoviLogic;
import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * @author Danny
 */

@Path( "/automovil" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class AutomovilResource
{
	@Inject
	private AutomoviLogic logic;
	
	@Context
	private HttpServletResponse response;
	
	@QueryParam( "page" )
	private Integer page;
	
	@QueryParam( "limit" )
	private Integer maxRecords;
	
	private List<AutomovilDetailDTO> listEntity2DTO( List<AutomovilEntity> entityList )
	{
		List<AutomovilDetailDTO> list = new ArrayList<>( );
		for( AutomovilEntity entity : entityList )
		{
			list.add( new AutomovilDetailDTO( entity ) );
		}
		return list;
	}
	
	@GET
	public List<AutomovilDetailDTO> getAutos( )
	{
		return listEntity2DTO( logic.getAutomoviles( ) );
	}
	
	@GET
	@Path( "{id: \\d+}" )
	public AutomovilDetailDTO getCar( @PathParam( "id" ) String placa )
	{
		return new AutomovilDetailDTO( logic.getAuto( placa ) );
	}
	
	@POST
	public AutomovilDetailDTO newCar( AutomovilDetailDTO car ) throws Exception
	{
		
		return new AutomovilDetailDTO( logic.creatCar( car.toEntity( ) ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteCar( @PathParam( "id" ) String placa )
	{
		logic.deletCar( placa );
	}
}
