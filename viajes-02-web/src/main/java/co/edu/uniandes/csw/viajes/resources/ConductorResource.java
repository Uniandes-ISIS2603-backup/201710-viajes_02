/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.AutomovilDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.ConductorDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.ViajeDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.ConductorLogic;
import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jm.dominguez
 */

@Path("/Conductores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConductorResource {
    /**
     * Relación con la clase de la lógica.
     */
    @Inject private ConductorLogic logica;
    /**
     * Realiza la conversión de una lista de Entities a una lista de DTOs
     * @param entityList; Lista con los conductores de la base de datos
     * @return List: Lista de los conductores en formato DTO
     */
    public List<ConductorDetailDTO> listEntity2DTO(List<ConductorEntity> entityList){
        List<ConductorDetailDTO> respuesta  = new ArrayList();
        for(int i = 0; i < entityList.size();i++){
            ConductorEntity c = entityList.get(i);
            ConductorDetailDTO c1 = new ConductorDetailDTO(c);
            respuesta.add(c1);
        }
        return respuesta;
    }
    /**
     * Permite obtener la lista de los conductores.
     * @return List: Lista de coonductores.
     */
    @GET
    public List <ConductorDetailDTO> getCoductores(){
        return listEntity2DTO(logica.findAll());
    }
    /**
     * Permite realizar la búsqueda de un conductor dada su id.
     * @param id: identificador del conductor a buscar.
     * @return Conductor en formato DTO con el id buscado
     * @throws BusinessLogicException si el conductor no existe.
     */
    @GET
    @Path("{id: \\d+}")
    public ConductorDetailDTO getConductor(@PathParam("id") Long id) throws BusinessLogicException{
        return new ConductorDetailDTO(logica.getConductor(id));
    }
    /**
     * Permite crear un conductor
     * @param dto: representacion del conductor en dto.
     * @return Conductor: Conductor que fue guardado en la base de datos.
     * @throws BusinessLogicException 
     */
    @POST
    public ConductorDetailDTO createConductor(ConductorDetailDTO dto) throws BusinessLogicException{
        return new ConductorDetailDTO(logica.createConductor(dto.DTO2Entity()));
    }
    /**
     * Permite actualizar la información de un conductor en la base de datos.
     * @param id: identificador del conductor.
     * @param c: Representacion DTO del conductor
     * @return Condcutor actualizado enr epresentacion DTO
     * @throws BusinessLogicException Si el conductor no existe en la base de datos.
     */
    @PUT
    @Path("{id: \\d+}")
    public ConductorDetailDTO updateConductor(@PathParam("id") Long id, ConductorDetailDTO c) throws BusinessLogicException{
        ConductorEntity entity = c.DTO2Entity();
        entity.setId(id);
        return new ConductorDetailDTO(logica.updateConductor(entity));
    }
    /**
     * Eliminar un conductor de l a base de datos
     * @param id: Id del conductor a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteConductor(@PathParam("id") Long id){
        logica.deleteConductor(id);
    }
    /**
     * Permite añadir un vehículo a un conductor con el Id dado.
     * @param dto: Representación DTO del vehículo a añadir.
     * @param id: Id del conductor a modificar.
     * @return Conductor: representación DTO del conductor actualizado
     * @throws BusinessLogicException Si el id del conductor dado no existe.
     */
    @POST
    @Path("{idConductor: \\d+}")
    public ConductorDetailDTO addCarroToConductor(AutomovilDetailDTO dto, @PathParam("idConductor") Long id) throws BusinessLogicException{
        return new ConductorDetailDTO (logica.addCarroToConductor(dto.toEntity(), id));
    }
}
