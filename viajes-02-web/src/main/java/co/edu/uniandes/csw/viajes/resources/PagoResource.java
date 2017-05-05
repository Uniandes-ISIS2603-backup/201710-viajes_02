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
import co.edu.uniandes.csw.viajes.dtos.PagoDTO;
import co.edu.uniandes.csw.viajes.dtos.PagoDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.PagoLogic;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ja.bermudez10
 */
@Path("/usuarios/{idUsuario: \\d+}/pagos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PagoResource
{

    @Inject
    private PagoLogic pagoLogic;

    @POST
    public PagoDetailDTO createPago(PagoDetailDTO pagoDetailDTO)
    {
        return new PagoDetailDTO(pagoLogic.createPago(pagoDetailDTO.toEntity()));
    }

    @GET
    public List<PagoDTO> getMisPagos(@PathParam("idUsuario") Long idUsuario)
    {
        return listEntity2DTO(pagoLogic.getMisPagos(idUsuario));
    }

    @GET
    @Path("/{idPago: \\d+}")
    public PagoDTO getPago(@PathParam("idPago") Long idPago)
    {
        return new PagoDTO(pagoLogic.getPago(idPago));
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
    
    private List<PagoDetailDTO> listEntity2DetailDTO(List<PagoEntity> reservaEntityList)
    {
        List<PagoDetailDTO> listPago = new ArrayList<PagoDetailDTO>();
        for (PagoEntity pagoEntity : reservaEntityList)
        {
            listPago.add(new PagoDetailDTO(pagoEntity));
        }
        return listPago;
    }
}
