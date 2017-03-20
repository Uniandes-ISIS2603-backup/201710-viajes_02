/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;
// TODO Eliminar los imports que no se necesitan
import co.edu.uniandes.csw.viajes.dtos.PagoDTO;
import co.edu.uniandes.csw.viajes.ejbs.PagoLogic;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ja.bermudez10
 */
@Path("/pagos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PagoResource
{

    @Inject
    private PagoLogic reservaLogic;

    @Context

    @POST
    public PagoDTO createPago(PagoDTO pagoDTO)
    {
        return new PagoDTO(reservaLogic.createPago(pagoDTO.toEntity()));
    }

    @GET
    @Path("/usuario/{idRemitente: \\d+}")
    public List<PagoDTO> getMisPagos(@PathParam("idRemitente") Long idRemitente)
    {
        return listEntity2DTO(reservaLogic.getMisPagos(idRemitente));
    }

    @GET
    @Path("/pago/{idPago: \\d+}")
    public PagoDTO getPago(@PathParam("idPago") Long idPago)
    {
        return new PagoDTO(reservaLogic.getPago(idPago));
    }

    private List<PagoDTO> listEntity2DTO(List<PagoEntity> reservaEntityList)
    {
        List<PagoDTO> listPago = new ArrayList<>();
        for (PagoEntity pagoEntity : reservaEntityList)
        {
            listPago.add(new PagoDTO(pagoEntity));
        }
        return listPago;
    }
}
