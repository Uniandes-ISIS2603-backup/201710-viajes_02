/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.CobroDTO;
import co.edu.uniandes.csw.viajes.ejbs.CobroLogic;
import co.edu.uniandes.csw.viajes.entities.CobroEntity;
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
@Path("/cobros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CobroResource {
    @Inject private CobroLogic logic;
    
    @GET
    public List<CobroDTO> getCobros() {
        return listEntity2DTO(logic.findCobros());
    }
    
    @GET
    @Path("{id: \\d+}")
    public CobroDTO getCobro(@PathParam("id") Long id) {
        return basicEntity2DTO(logic.findCobro(id));
    }
    
    @POST
    public CobroDTO createLugar(CobroDTO cobro) {
        return basicEntity2DTO(logic.createCobro(cobro.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public CobroDTO updateLugar(@PathParam("id") Long id, CobroDTO cobro) {
        CobroEntity entity = cobro.toEntity();
        entity.setId(id);
        return basicEntity2DTO(logic.updateCobro(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCobro(@PathParam("id") Long id) {
            logic.deleteCobro(id);
    }
    
    // Helpers
    
    public CobroDTO basicEntity2DTO(CobroEntity entity) {
        CobroDTO dto = new CobroDTO();
        dto.setCancelado(entity.getCancelado());
        dto.setValor(entity.getValor());
        dto.setId(entity.getId());
        dto.setIdDestinatario(entity.getIdDestinatario());
        dto.setIdRemitente(entity.getIdRemitente());
        return dto;
    }
    
   public List<CobroEntity> listDTO2Entity(List<CobroDTO> dtos) {
       List<CobroEntity> list = new ArrayList<>();
       
       for(CobroDTO dto : dtos) {
           list.add(dto.toEntity());
       }
       
       return list;
   }
   
   public List<CobroDTO> listEntity2DTO(List<CobroEntity> entities) {
       List<CobroDTO> dtos = new ArrayList<>();
       
       for(CobroEntity entity : entities) {
           dtos.add(basicEntity2DTO(entity));
       } 
       
       return dtos;
   }
}
