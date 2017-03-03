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
public class LugarResource {
    @Inject LugarLogic logic;
    
    @GET
    public List<LugarDTO> getLugares() {
        return listEntity2DTO(logic.findLugares());
    }
    
    @GET
    @PathParam("{id: \\+}")
    public LugarDTO getLugar(@PathParam("id") Long id) {
        return basicEntity2DTO(logic.findLugar(id));
    }
    
    @POST
    public LugarDTO createLugar(LugarDTO lugar) {
        return basicEntity2DTO(logic.createLugar(lugar.toEntity()));
    }
    
    @PUT
    @PathParam("{id: \\+}")
    public LugarDTO updateLugar(@PathParam("id") Long id, LugarDTO lugar) {
        LugarEntity entity = lugar.toEntity();
        entity.setId(id);
        return basicEntity2DTO(logic.updateLugar(entity));
    }
    
    @DELETE
    @PathParam("{id: \\+}")
    public void deleteLugar(@PathParam("id") Long id) {
            logic.deleteLugar(id);
    }
    
    // Helpers
    
    public LugarDTO basicEntity2DTO(LugarEntity entity) {
        LugarDTO dto = new LugarDTO();
        dto.setDirrecion(entity.getDireccion());
        dto.setId(entity.getId());
        dto.setLugar(entity.getLugar());
        return dto;
    }
    
   public List<LugarEntity> listDTO2Entity(List<LugarDTO> dtos) {
       List<LugarEntity> list = new ArrayList<>();
       
       for(LugarDTO dto : dtos) {
           list.add(dto.toEntity());
       }
       
       return list;
   }
   
   public List<LugarDTO> listEntity2DTO(List<LugarEntity> entities) {
       List<LugarDTO> dtos = new ArrayList<>();
       
       for(LugarEntity entity : entities) {
           dtos.add(basicEntity2DTO(entity));
       } 
       
       return dtos;
   }
}
