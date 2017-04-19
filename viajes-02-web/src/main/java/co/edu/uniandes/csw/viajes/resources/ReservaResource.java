/* 
 * The MIT License
 *
 * Copyright 2017 wr.ravelo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    @Path("/{id: \\d+}")
    public ReservaDetailDTO getReserva(@PathParam("id") Long id)
    {
        return new ReservaDetailDTO(reservaLogic.getReserva(id));
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
