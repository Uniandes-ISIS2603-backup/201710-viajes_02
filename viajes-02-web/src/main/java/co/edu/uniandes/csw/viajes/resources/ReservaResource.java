/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ReservaDTO;
import co.edu.uniandes.csw.viajes.dtos.ReservaDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.ReservaLogic;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ja.bermudez10
 */
@Path("/reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource {
    
    @Inject
    private ReservaLogic reservaLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    
    @POST
    public ReservaDetailDTO createReserva(ReservaDetailDTO reservaDDTO) {
        return new ReservaDetailDTO(reservaLogic.createReserva(reservaDDTO.toEntity()));
    }
    
    @GET
    @Path("{idViajero: \\d+}")
    public List<ReservaDetailDTO> getReservas(@PathParam("id") Long idViajero) {
        return listEntity2DTO(reservaLogic.getReservasViajero(idViajero));
    }
    
    @GET
    @Path("{idViajero: \\d+}/{idReserva: \\d+}")
    public ReservaDetailDTO getReservaEspecifica(@PathParam("idViajero") Long idViajero, @PathParam("idReserva") Long idReserva) {
        return new ReservaDetailDTO(reservaLogic.getReservaEspecificaViajero(idViajero, idReserva));
    }
    
    private List<ReservaDetailDTO> listEntity2DTO(List<ReservaEntity> reservaEntityList){
        List<ReservaDetailDTO> listReserva = new ArrayList<>();
        for (ReservaEntity reservaEntity : reservaEntityList) {
            listReserva.add(new ReservaDetailDTO(reservaEntity));
        }
        return listReserva;
    }
}
