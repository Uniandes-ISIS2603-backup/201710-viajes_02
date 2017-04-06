/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;
// TODO Eliminar los imports que no se necesitan
import co.edu.uniandes.csw.viajes.dtos.ReservaDTO;
import co.edu.uniandes.csw.viajes.dtos.ReservaDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.ReservaLogic;
import co.edu.uniandes.csw.viajes.ejbs.ViajeroLogic;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.ManyToMany;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ja.bermudez10
 */
@Path("/viajeros/{idViajero: \\d+}/reservas")
// TODO este recruso debería ser un subrecurso de viajeros
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource
{
    @Inject
    private ReservaLogic reservaLogic;
    
    private ViajeroLogic viajeroLogic;

    @POST 
    public ReservaDTO createReserva(ReservaDTO reservaDTO)
    {
        return new ReservaDetailDTO(reservaLogic.createReserva(reservaDTO.toEntity()));
    }

    @GET
    public List<ReservaDetailDTO> getReservas(@PathParam("idViajero") Long idViajero)
    {  // TODO si el recurso con el idViajero dado no existe de se debe disparar WebApplicationException 404
        if(reservaLogic.getReservasViajero(idViajero) == null)
            throw new WebApplicationException("No existe(n) reserva(s) asociadas al viajero con id " + idViajero, 404);
        else
            return listEntity2DDTO(reservaLogic.getReservasViajero(idViajero));
    }
    
    @GET
    @Path("/{idReserva: \\d+}")
    public ReservaDetailDTO getReserva(@PathParam("idReserva") Long idReserva)
    {
        return new ReservaDetailDTO(reservaLogic.getReserva(idReserva));
    }

    @DELETE
    @Path("{idViajero: \\d+}/{idReserva: \\d+}")
    public void deleteReserva(@PathParam("idViajero") Long idViajero, @PathParam("idReserva") Long idReserva)
    {// TODO si el recurso con el idViajero y el idReserva dado no existe de sedeb disparar WebApplicationException 404
        reservaLogic.deleteReserva(idViajero, idReserva);
    }

    private List<ReservaDetailDTO> listEntity2DDTO(List<ReservaEntity> reservaEntityList)
    {
        List<ReservaDetailDTO> listReserva = new ArrayList<>();
        
        for (ReservaEntity reservaEntity : reservaEntityList) {
            listReserva.add(new ReservaDetailDTO(reservaEntity));
        }
        return listReserva;
    }
    
    private boolean existeViajero(Long idViajero) {
        try {
            return (viajeroLogic.getViajero(idViajero) != null);
        } catch (BusinessLogicException ex) {
            return false;
        }
    }
    
}
