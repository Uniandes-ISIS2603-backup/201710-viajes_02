/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.PagoDetailDTO;
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
public class PagoResource {
    @Inject
    private PagoLogic reservaLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    
    @POST
    public PagoDetailDTO createPago(PagoDetailDTO pagoDDTO) {
        return new PagoDetailDTO(reservaLogic.createPago(pagoDDTO.toEntity()));
    }
    
    @GET
    @Path("{idRemitente: \\d+}")
    public List<PagoDetailDTO> getPagos(@PathParam("idRemitente") Long idRemitente) {
        return listEntity2DTO(reservaLogic.getMisPagos(idRemitente));
    }
    
    
    
    private List<PagoDetailDTO> listEntity2DTO(List<PagoEntity> reservaEntityList){
        List<PagoDetailDTO> listPago = new ArrayList<>();
        for (PagoEntity pagoEntity : reservaEntityList) {
            listPago.add(new PagoDetailDTO(pagoEntity));
        }
        return listPago;
    }
}
