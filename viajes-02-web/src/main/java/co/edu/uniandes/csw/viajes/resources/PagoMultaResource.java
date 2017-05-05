/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.PagoMultaDTO;
import co.edu.uniandes.csw.viajes.dtos.PagoMultaDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.PagoMultaLogic;
import co.edu.uniandes.csw.viajes.entities.PagoMultaEntity;
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
 * @author ja.bermudez10
 */
@Path("/usuarios/{idUsuario: \\d+}/pagosmulta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PagoMultaResource {
    
    @Inject
    private PagoMultaLogic pagoMultaLogic;

    @POST
    public PagoMultaDetailDTO createPago(PagoMultaDetailDTO pagoMultaDetailDTO)
    {
        return new PagoMultaDetailDTO(pagoMultaLogic.createPagoMulta(pagoMultaDetailDTO.toEntity()));
    }

    @GET
    public List<PagoMultaDTO> getMisPagos(@PathParam("idUsuario") Long idUsuario)
    {
        return listEntity2DTO(pagoMultaLogic.getMisPagosMulta(idUsuario));
    }

    @GET
    @Path("/{idPagoMulta: \\d+}")
    public PagoMultaDTO getPago(@PathParam("idPagoMulta") Long idPagoMulta)
    {
        return new PagoMultaDTO(pagoMultaLogic.getPagoMulta(idPagoMulta));
    }

    private List<PagoMultaDTO> listEntity2DTO(List<PagoMultaEntity> reservaEntityList)
    {
        List<PagoMultaDTO> listPagoMulta = new ArrayList<>();
        for (PagoMultaEntity pagoEntity : reservaEntityList)
        {
            listPagoMulta.add(new PagoMultaDTO(pagoEntity));
        }
        return listPagoMulta;
    }
    
    private List<PagoMultaDetailDTO> listEntity2DetailDTO(List<PagoMultaEntity> reservaEntityList)
    {
        List<PagoMultaDetailDTO> listPagoMulta = new ArrayList<PagoMultaDetailDTO>();
        for (PagoMultaEntity pagoEntity : reservaEntityList)
        {
            listPagoMulta.add(new PagoMultaDetailDTO(pagoEntity));
        }
        return listPagoMulta;
    }
}
