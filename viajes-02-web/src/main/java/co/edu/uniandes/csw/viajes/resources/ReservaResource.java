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
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
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
@Path("/reservas")

// TODO este recruso debería ser un subrecurso de viajeros
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource
{

    @Inject
    private ReservaLogic reservaLogic;

    @POST 
    public ReservaDTO createReserva(ReservaDTO reservaDTO)
    {
        return new ReservaDetailDTO(reservaLogic.createReserva(reservaDTO.toEntity()));
    }

    @GET
    @Path("/viajeros/{idViajero: \\d+}")
    public List<ReservaDTO> getReservas(@PathParam("idViajero") Long idViajero)
    {  // TODO si el recurso con el idViajero dado no existe de sedeb disparar WebApplicationException 404
        return listEntity2DTO(reservaLogic.getReservasViajero(idViajero));
    }

    @DELETE
    @Path("{idViajero: \\d+}/{idReserva: \\d+}")
    public void deleteReserva(@PathParam("idViajero") Long idViajero, @PathParam("idReserva") Long idReserva)
    {// TODO si el recurso con el idViajero y el idReserva dado no existe de sedeb disparar WebApplicationException 404
        reservaLogic.deleteReserva(idViajero, idReserva);
    }

    private List<ReservaDTO> listEntity2DTO(List<ReservaEntity> reservaEntityList)
    {
        List<ReservaDTO> listReserva = new ArrayList<>();
        for (ReservaEntity reservaEntity : reservaEntityList)
        {
            listReserva.add(new ReservaDetailDTO(reservaEntity));
        }
        return listReserva;
    }
}
