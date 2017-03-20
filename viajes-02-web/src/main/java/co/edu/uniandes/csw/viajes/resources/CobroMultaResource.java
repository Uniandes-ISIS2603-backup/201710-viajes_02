/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.CobroMultaDTO;
import co.edu.uniandes.csw.viajes.ejbs.CobroMultaLogic;
import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;
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
@Path("/usuarios/{usuarioId: \\d+}/cobromultas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CobroMultaResource
{

    /**
     * Logica del cobro multa.
     */
    @Inject
    private CobroMultaLogic logic;

    /**
     * Id del usuario al que pertenece el cobro multa
     */
    @PathParam("usuarioId")
    private Long usuarioId;

    /**
     * Da todos los cobros del usuario.
     *
     * @return Todos los cobros que hay del usuario.
     */
    @GET
    public List<CobroMultaDTO> getCobros()
    {
        return listEntity2DTO(logic.findCobroMultas(usuarioId));
    }

    /**
     * Da el cobro multa que tiene id que entra por parametro y pertenece al
     * usuario especificado.
     *
     * @param id Id del cobro multa
     * @return Cobro multa que pertenece al usuario especifico y tiene id igual
     * al que entra por parametro
     */
    @GET
    @Path("{id: \\d+}")
    public CobroMultaDTO getCobro(@PathParam("id") Long id)
    {// TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        return basicEntity2DTO(logic.findCobroMulta(id)); 
    }

    /**
     * Crea un cobro multa de acuerdo a la informacion que tiene el DTO que
     * entra por parametro
     *
     * @param cobro DTO que tiene la informacion del cobro multa que se va a
     * crear en la base de datos.
     * @return Cobro multa DTO que tiene la informacion guardad en la base de
     * datos.
     */
    @POST
    public CobroMultaDTO createCobro(CobroMultaDTO cobro)
    {// TODO si el recurso con el usuarioId dado no existe de sedeb disparar WebApplicationException 404
        
        cobro.setIdRemitente(usuarioId);
        return basicEntity2DTO(logic.createCobroMulta(cobro.toEntity()));
    }

    /**
     * Actualia la informacion de un cobro multa
     *
     * @param id Id del cobro multa que se va a actualizar
     * @param cobro Nueva informacion del cobro multa.
     * @return DTO que contiene la nueva informacion.
     */
    @PUT
    @Path("{id: \\d+}")
    public CobroMultaDTO updateCobro(@PathParam("id") Long id, CobroMultaDTO cobro)
    {// TODO si el recurso con el usuarioId dado no existe de sedeb disparar WebApplicationException 404
        // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        cobro.setIdRemitente(usuarioId);
        CobroMultaEntity entity = cobro.toEntity();
        entity.setId(id);
        return basicEntity2DTO(logic.updateCobroMulta(entity));
    }

    /**
     * Elimina el registro de un cobro multa.
     *
     * @param id Id del cobro multa que se quiere eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCobro(@PathParam("id") Long id)
    {// TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        logic.deleteCobroMulta(id);
    }

    // Helpers
    /**
     * Convierte una entidad a DTO
     *
     * @param entity Entidad a convertir
     * @return DTO creado a partir de la entidad
     */
    public CobroMultaDTO basicEntity2DTO(CobroMultaEntity entity)
    {
        CobroMultaDTO dto = new CobroMultaDTO();
        dto.setCancelado(entity.getCancelado());
        dto.setValor(entity.getValor());
        dto.setId(entity.getId());
        dto.setIdDestinatario(entity.getIdDestinatario());
        dto.setIdRemitente(entity.getIdRemitente());
        return dto;
    }

    /**
     * Convierte una lista de dtos a entidades.
     *
     * @param dtos Lista de dtos a converitr a entidades.
     * @return Lista con las entidades.
     */
    public List<CobroMultaEntity> listDTO2Entity(List<CobroMultaDTO> dtos)
    {
        List<CobroMultaEntity> list = new ArrayList<>();

        for (CobroMultaDTO dto : dtos)
        {
            list.add(dto.toEntity());
        }

        return list;
    }

    /**
     * Convierte una lista de entidades a DTO
     *
     * @param entities Entidades a convertir a dto.
     * @return Lista con los DTOS
     */
    public List<CobroMultaDTO> listEntity2DTO(List<CobroMultaEntity> entities)
    {
        List<CobroMultaDTO> dtos = new ArrayList<>();

        for (CobroMultaEntity entity : entities)
        {
            dtos.add(basicEntity2DTO(entity));
        }

        return dtos;
    }
}
