/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.persistence.AutomovilPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Danny
 */
@Stateless
public class AutomoviLogic
{
	@Inject
	private AutomovilPersistence persistence;
	
	/**
	 * Obtiene la lista de los automoviles existentes.
	 * publ
	 *
	 * @return Colección de objetos de AutomovilEntity.
	 */
	public List<AutomovilEntity> getAutomoviles( )
	{
		return persistence.findAll( );
	}
	
	/**
	 * Obtiene un automoviles especifico.
	 *
	 * @param id (placa) Identificador del carro
	 * @return AutomovilEntity.
	 */
	public AutomovilEntity getAuto( String placa )
	{
		return persistence.find( placa );
	}
	
	/**
	 * Se encarga de crear un nuevo carro AutomovilEntity en la base de datos.
	 *
	 * @param entity Objeto de AutomovilEntity con los datos nuevos
	 * @return Objeto de AutomovilEntity con los datos nuevos y su ID.
	 * @generated
	 */
	public AutomovilEntity creatCar( AutomovilEntity entity ) throws Exception
	{
		if( persistence.findByPlaca( entity.getPlaca( ) ) == null  )
		{
			AutomovilEntity car = persistence.create( entity );
			return entity;
		}
		else
		{
			throw new Exception( "Ya existe un carro con esa placa" );
		}
	}
	
	/***
	 * Actualiza  la informacion de un carro en particular
	 * @param carro Instancia de AutomovilEntity con los nuevo datos
	 * @return Instancia  de AutomovilEntity actualizada
	 */
	public AutomovilEntity updateCar( AutomovilEntity carro )
	{
		return persistence.update( carro );
	}
	
	/**
	 * Elimina una instancia de Automovil en la base de datos
	 *
	 * @param placa para identificar el carro a eliminar
	 */
	public void deletCar( String placa )
	{
		persistence.delete( placa );
	}
}
