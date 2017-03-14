/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.CobroDTO;
import co.edu.uniandes.csw.viajes.dtos.CobroMultaDTO;
import co.edu.uniandes.csw.viajes.ejbs.CobroLogic;
import co.edu.uniandes.csw.viajes.ejbs.CobroMultaLogic;
import co.edu.uniandes.csw.viajes.entities.CobroEntity;
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
public class CobroMultaResource {
    @Inject private CobroMultaLogic logic;
    
    @PathParam("usuarioId") private Long usuarioId;
    
    @GET
    public List<CobroMultaDTO> getCobros() {
        return listEntity2DTO(logic.findCobros(usuarioId));
    }
    
    @GET
    @Path("{id: \\d+}")
    public CobroMultaDTO getCobro(@PathParam("id") Long id) {
        return basicEntity2DTO(logic.findCobro(id));
    }
    
    @POST
    public CobroMultaDTO createCobro(CobroMultaDTO cobro) {
        cobro.setIdRemitente(usuarioId);
        return basicEntity2DTO(logic.createCobro(cobro.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public CobroMultaDTO updateCobro(@PathParam("id") Long id, CobroMultaDTO cobro) {
        cobro.setIdRemitente(usuarioId);
        CobroMultaEntity entity = cobro.toEntity();
        entity.setId(id);
        return basicEntity2DTO(logic.updateCobro(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCobro(@PathParam("id") Long id) {
            logic.deleteCobro(id);
    }
    
    // Helpers
    
    public CobroMultaDTO basicEntity2DTO(CobroMultaEntity entity) {
        CobroMultaDTO dto = new CobroMultaDTO();
        dto.setCancelado(entity.getCancelado());
        dto.setValor(entity.getValor());
        dto.setId(entity.getId());
        dto.setIdDestinatario(entity.getIdDestinatario());
        dto.setIdRemitente(entity.getIdRemitente());
        return dto;
    }
    
   public List<CobroMultaEntity> listDTO2Entity(List<CobroMultaDTO> dtos) {
       List<CobroMultaEntity> list = new ArrayList<>();
       
       for(CobroMultaDTO dto : dtos) {
           list.add(dto.toEntity());
       }
       
       return list;
   }
   
   public List<CobroMultaDTO> listEntity2DTO(List<CobroMultaEntity> entities) {
       List<CobroMultaDTO> dtos = new ArrayList<>();
       
       for(CobroMultaEntity entity : entities) {
           dtos.add(basicEntity2DTO(entity));
       } 
       
       return dtos;
   }
}
