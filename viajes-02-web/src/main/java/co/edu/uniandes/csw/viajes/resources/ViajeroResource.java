/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ViajeroDTO;
import co.edu.uniandes.csw.viajes.ejbs.ViajeroLogic;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author n.aguilar
 */
@Path("/viajeros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViajeroResource {

    @Inject
    private ViajeroLogic logic;

    private List<ViajeroDTO> listEntity2DTO(List<ViajeroEntity> entityList) {
        List<ViajeroDTO> lista = new ArrayList<>();
        for (ViajeroEntity entity : entityList) {
            lista.add(new ViajeroDTO(entity));
        }
        return lista;
    }

    @GET
    public List<ViajeroDTO> getViajero() {
        return listEntity2DTO(logic.getViajeros());
    }
    
    @POST
    public ViajeroDTO createViajero(ViajeroDTO usuario) {
        return new ViajeroDTO(logic.createViajero(usuario.toEntity()));
    }
}
