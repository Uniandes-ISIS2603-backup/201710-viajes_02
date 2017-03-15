/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ViajeDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.ViajeLogic;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
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

@Path("/viajes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViajeResource {
    
    @Inject private ViajeLogic logica;
    
    public List<ViajeDetailDTO> listEntity2DTO(List<ViajeEntity> entityList){
        List<ViajeDetailDTO> respuesta = new ArrayList();
        for(int i = 0; i < entityList.size(); i++){
            ViajeEntity v = entityList.get(i);
            ViajeDetailDTO v1 = new ViajeDetailDTO(v);
            respuesta.add(v1);
        }
        return respuesta;
    }
    
    @GET
    public List<ViajeDetailDTO> getViajes(){
        return listEntity2DTO(logica.findViajes());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ViajeDetailDTO getViaje(@PathParam("id") Long id){
        return new ViajeDetailDTO(logica.findViaje(id));
    }
    
    @POST
    public ViajeDetailDTO createViaje(ViajeDetailDTO dto){
        return new ViajeDetailDTO(logica.createViaje(dto.DTO2Entity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ViajeDetailDTO updateViaje(@PathParam("id") Long id, ViajeDetailDTO viaje){
        ViajeEntity entity = viaje.DTO2Entity();
        entity.setIdViaje(id);
        return new ViajeDetailDTO(logica.updateViaje(entity));
    }
    @GET
    @Path("{origen: .+};(destino: .+)")
    public List<ViajeDetailDTO> getViajesPorOrigenYDestino(@PathParam("origen") String origen, @PathParam("destino") String destino){
        List<ViajeEntity> lista = logica.darViajesOrigenYDestino(origen, destino);
        List<ViajeDetailDTO> respuesta = listEntity2DTO(lista);
        return respuesta;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteViaje(@PathParam("id") Long id){
        logica.deleteViaje(id);
    }
}
