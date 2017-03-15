/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ViajeroDTO;
import co.edu.uniandes.csw.viajes.dtos.ViajeroDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.ViajeroLogic;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    private List<ViajeroDetailDTO> listEntity2DTO(List<ViajeroEntity> entityList) {
        List<ViajeroDetailDTO> lista = new ArrayList<>();
        for (ViajeroEntity entity : entityList) {
            lista.add(new ViajeroDetailDTO(entity));
        }
        return lista;
    }

    @GET
    public List<ViajeroDetailDTO> getViajero() {
        return listEntity2DTO(logic.getViajeros());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ViajeroDetailDTO getUsuario(@PathParam("id")Long id) throws BusinessLogicException
    {
        return new ViajeroDetailDTO(logic.getViajero(id));
    }
    
    @POST
    public ViajeroDetailDTO createViajero(ViajeroDetailDTO usuario) throws BusinessLogicException {
        return new ViajeroDetailDTO(logic.createViajero(usuario.toEntity()));
    }
}
