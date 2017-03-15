/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.AutomovilDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.ConductorDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.ConductorLogic;
import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
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
    
    @Inject private ConductorLogic logica;
    
    public List<ConductorDetailDTO> listEntity2DTO(List<ConductorEntity> entityList){
        List<ConductorDetailDTO> respuesta  = new ArrayList();
        for(int i = 0; i < entityList.size();i++){
            ConductorEntity c = entityList.get(i);
            ConductorDetailDTO c1 = new ConductorDetailDTO(c);
            respuesta.add(c1);
        }
        return respuesta;
    }
    
    @GET
    public List <ConductorDetailDTO> getCoductores(){
        return listEntity2DTO(logica.findAll());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ConductorDetailDTO getConductor(@PathParam("id") Long id){
        return new ConductorDetailDTO(logica.getConductor(id));
    }
    
    @POST
    public ConductorDetailDTO createConductor(ConductorDetailDTO dto){
        return new ConductorDetailDTO(logica.createConductor(dto.DTO2Entity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ConductorDetailDTO updateConductor(@PathParam("id") Long id, ConductorDetailDTO c){
        ConductorEntity entity = c.DTO2Entity();
        entity.setId(id);
        return new ConductorDetailDTO(logica.updateConductor(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteConductor(@PathParam("id") Long id){
        logica.deleteConductor(id);
    }
    
    @POST
    @Path("{idConductor: \\d+}")
    public ConductorDetailDTO addCarroToConductor(AutomovilDetailDTO dto, @PathParam("idConductor") Long id){
        return new ConductorDetailDTO (logica.addCarroToConductor(dto.toEntity(), id));
    }
}
