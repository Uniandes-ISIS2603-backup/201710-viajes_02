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

import co.edu.uniandes.csw.viajes.dtos.ReviewDTO;
import co.edu.uniandes.csw.viajes.ejbs.ReviewLogic;
import co.edu.uniandes.csw.viajes.entities.ReviewEntity;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author Danny
 */
@Path("/usuarios/reviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource
{

    /**
     * injeccion de la logica
     */
    @Inject
    private ReviewLogic logic;
// TODO Eliminar los atributos que no se necesitan
    @Context
    private HttpServletResponse response;

    @QueryParam("page")
    private Integer page;

    @QueryParam("limit")
    private Integer maxRecords;

    /**
     * convierte una lista de entidades a dto
     *
     * @param entityList
     * @return lista de DetailDTO de review
     */
    private List<ReviewDTO> listEntity2DTO(List<ReviewEntity> entityList)
    {
        List<ReviewDTO> list = new ArrayList<>();
        for (ReviewEntity entity : entityList)
        {
            list.add(new ReviewDTO(entity));
        }
        return list;
    }

    /**
     * obtener los reviews guardados en el sistema
     *
     * @return lista de ReviewDTO
     */
    @GET
    public List<ReviewDTO> getReviews()
    {
        return listEntity2DTO(logic.getReviews());
    }

    /**
     * Da un revew especifico para un usuario en particular dado por su id
     *
     * @param id
     * @return el AutomovilDetailDTO del auto solicitado
     */
    @GET
    @Path("{id: \\d+}")
    public List<ReviewDTO> getReviews(@PathParam("id") Long id)
    { // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        List<ReviewDTO> lista = new ArrayList<ReviewDTO>();

        for ( ReviewEntity review : logic.getReviews(id) )
        {
            lista.add(new ReviewDTO(review));
        }

                return lista;
    }

    @GET
    @Path("{id: \\d+}/{idr:\\d+}")
    public ReviewDTO getReview(@PathParam("id") Long id,@PathParam("idr") Long idr)
    { // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        return new ReviewDTO ( logic.getReview(id,idr));

    }
    /**
     * crea un nuevo review en la base de datos
     *
     * @param rev
     * @return ReviewDTO
     * @throws Exception
     */
    @POST
    public ReviewDTO newReview(ReviewDTO rev) throws Exception
    {
            return new ReviewDTO(logic.creatReview(rev.toEntity()));
    }

    /**
     * borra un review de un usuario especifico encontrado por su id
     *
     * @param id
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReview(@PathParam("id") Long id)
    { // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        logic.deletReview(id);
    }
}
