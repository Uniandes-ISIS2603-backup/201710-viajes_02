/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ViajeDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.ViajeLogic;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jm.dominguez
 */
@Path("/viajes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViajeResource
{

    /**
     * Logica de os Viajes
     */
    @Inject
    private ViajeLogic logica;

    /**
     * Método para transoformar una lista de entidades en una lista de DTOS
     *
     * @param entityList. Lista con las entidades a transformar
     * @return List. Lista con las entidades transformadas a DTOs
     */
    public List<ViajeDetailDTO> listEntity2DTO(List<ViajeEntity> entityList)
    {
        List<ViajeDetailDTO> respuesta = new ArrayList();
        for (int i = 0; i < entityList.size(); i++)
        {
            ViajeEntity v = entityList.get(i);
            ViajeDetailDTO v1 = new ViajeDetailDTO(v);
            respuesta.add(v1);
        }
        return respuesta;
    }

    /**
     * Método para obtener todos los viajes que se encuentran guardados en el
     * sistema
     *
     * @return Lista de los viajes que se encuentran en el sistema. En formato
     * DetailDTO.
     */
    @GET
    public List<ViajeDetailDTO> getViajes()
    {
        return listEntity2DTO(logica.findViajes());
    }

    /**
     * Método para obtener un viaje específico con el Id dado.
     *
     * @param . Id del viaje a buscar
     * @return DetailDTO del viaje con el identificador dado.
     */
    @GET
    @Path("{id: \\d+}")
    public ViajeDetailDTO getViaje(@PathParam("id") Long id)
    { // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        ViajeEntity existe = logica.findViaje(id);
        if(existe == null){
            throw new WebApplicationException("No existe el viaje con id" +id, 404);
        }
        ViajeDetailDTO respuesta = new ViajeDetailDTO (existe);
        return respuesta;
    }

    /**
     * Método para crear un viaje a partir de un dto.
     *
     * @param dto. DTO a persistir en la base de datos.
     * @return DTo del viaje que fué agregado en la base de datos.
     * @throws BusinessLogicException
     */
    @POST
    public ViajeDetailDTO createViaje(ViajeDetailDTO dto) throws BusinessLogicException
    {
        return new ViajeDetailDTO(logica.createViaje(dto.DTO2Entity()));
    }

    /**
     * Método para actualizar un viaje con el id dado, que este previamente
     * guardado en la base de datos
     *
     * @param id: Identificador del viaje a actualizar.
     * @param viaje: Nuevos datos del viaje a actualizar
     * @return DTO del viaje después de haberse actualizado
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public ViajeDetailDTO updateViaje(@PathParam("id") Long id, ViajeDetailDTO viaje) throws BusinessLogicException
    { // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        Long identificador = viaje.getIdViaje();
        ViajeEntity existe = logica.findViaje(identificador);
        if(existe == null){
            throw new WebApplicationException("No existe el viaje con id" +id, 404);
        }
        
        ViajeEntity entity = viaje.DTO2Entity();
        entity.setIdViaje(id);
        return new ViajeDetailDTO(logica.updateViaje(entity));
    }

    /**
     * Permite obtener la información de lo viajes con el origen y destino
     * dados.
     *
     * @param origen. Ciudad de origen del viaje.
     * @param destino. Ciudad de destino del viaje.
     * @return Lista con la información de todos los viajes cuyo origen y
     * destino coinciden con los dados por parametro.
     */
    @GET
    @Path("{origen: .+};(destino: .+)") // Deberián ser QueryParam y no path
    public List<ViajeDetailDTO> getViajesPorOrigenYDestino(@PathParam("origen") String origen, @PathParam("destino") String destino)
    {
        List<ViajeEntity> lista = logica.darViajesOrigenYDestino(origen, destino);
        List<ViajeDetailDTO> respuesta = listEntity2DTO(lista);
        return respuesta;
    }

    /**
     * Método para eliminar un viaje con el identificador dado por parametro.
     *
     * @param id : Identificador del viaje a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteViaje(@PathParam("id") Long id)
    { // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        ViajeEntity existe = logica.findViaje(id);
        if(existe == null){
            throw new WebApplicationException("No existe el viaje con id" +id, 404);
        }
        logica.deleteViaje(id);
    }
}
