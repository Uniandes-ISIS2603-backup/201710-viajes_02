/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.LugarDTO;
import co.edu.uniandes.csw.viajes.ejbs.LugarLogic;
import co.edu.uniandes.csw.viajes.entities.LugarEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author wr.ravelo
 */
@Path("/lugares")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LugarResource
{

    /**
     * Logica del lugar.
     */
    @Inject
    private LugarLogic logic;

    /**
     * Da todos los lugares de la base de datos.
     *
     * @return Todos los lugares registrados
     */
    @GET
    public List<LugarDTO> getLugares()
    {
        return listEntity2DTO(logic.findLugares());
    }

    /**
     * Da el lugar que tiene id igual al parametro.
     *
     * @param id Id del lugar a buscar.
     * @return Da el lugar DTO que tiene id igual al parametro.
     */
    @GET
    @Path("{id: \\d+}")
    public LugarDTO getLugar(@PathParam("id") Long id)
    {// TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
      
        return basicEntity2DTO(logic.findLugar(id));
    }

    /**
     * Crea un nuevo registro de lugar
     *
     * @param lugar DTO que contiene los datos del lugar a crear.
     * @return DTO creado en la base de datos.
     */
    @POST
    public LugarDTO createLugar(LugarDTO lugar)
    {
        return basicEntity2DTO(logic.createLugar(lugar.toEntity()));
    }

    /**
     * Actualiza un lugar que tiene id igual al parametro que entra por
     * parametro
     *
     * @param id Id de lugar a actualizar.
     * @param lugar DTO que contiene la nueva informacion del lugar
     * @return DTO actualizado.
     */
    @PUT
    @Path("{id: \\d+}")
    public LugarDTO updateLugar(@PathParam("id") Long id, LugarDTO lugar)
    {// TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
      
        LugarEntity entity = lugar.toEntity();
        entity.setId(id);
        return basicEntity2DTO(logic.updateLugar(entity));
    }

    /**
     * Elimina un lugar con id igual al parametro.
     *
     * @param id Id del lugar que se va a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLugar(@PathParam("id") Long id)
    {// TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
      
        logic.deleteLugar(id);
    }

    // Helpers
    /**
     * Convierte una entidad a dto
     *
     * @param entity Entidad que se va a convertir a DTO
     * @return DTO que se conviertio de una entidad.
     */
    public LugarDTO basicEntity2DTO(LugarEntity entity)
    {
        LugarDTO dto = new LugarDTO();
        dto.setDireccion(entity.getDireccion());
        dto.setId(entity.getId());
        dto.setLugar(entity.getLugar());
        dto.setLat(entity.getLat());
        dto.setLon(entity.getLon());
        return dto;
    }

    /**
     * Convierte una lista de DTOs a entidades
     *
     * @param dtos DTOS que se van a convertir a entidades.
     * @return Lista de entidades
     */
    public List<LugarEntity> listDTO2Entity(List<LugarDTO> dtos)
    {
        List<LugarEntity> list = new ArrayList<>();

        for (LugarDTO dto : dtos)
        {
            list.add(dto.toEntity());
        }

        return list;
    }

    /**
     * Convierte una lista de entidades a dtos.
     *
     * @param entities Entidades que se van a convertir a dtos.
     * @return Lista de dtos.
     */
    public List<LugarDTO> listEntity2DTO(List<LugarEntity> entities)
    {
        List<LugarDTO> dtos = new ArrayList<>();

        for (LugarEntity entity : entities)
        {
            dtos.add(basicEntity2DTO(entity));
        }

        return dtos;
    }
}
