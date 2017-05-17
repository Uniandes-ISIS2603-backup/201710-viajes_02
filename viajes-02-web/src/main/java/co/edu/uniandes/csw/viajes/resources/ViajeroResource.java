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

import co.edu.uniandes.csw.viajes.dtos.ViajeroDTO;
import co.edu.uniandes.csw.viajes.dtos.ViajeroDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.ViajeroLogic;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author n.aguilar
 */
@Path("/viajeros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViajeroResource
{

    /**
     * Logica del usuario
     */
    @Inject
    private ViajeroLogic logic;

    /**
     * Transforma una lista de entidades a dtos
     *
     * @param entityList lista de entidades a transformar
     * @return Lista de dtos generada
     */
    private List<ViajeroDetailDTO> listEntity2DTO(List<ViajeroEntity> entityList)
    {
        List<ViajeroDetailDTO> lista = new ArrayList<>();
        for (ViajeroEntity entity : entityList)
        {
            lista.add(new ViajeroDetailDTO(entity));
        }
        return lista;
    }

    /**
     * Retorna a los viajeros en el sitema
     *
     * @return El viajero
     */
    @GET
    public List<ViajeroDetailDTO> getViajeros()
    {
        return listEntity2DTO(logic.getViajeros());
    }

    /**
     * Retorna a un viajero especifico
     *
     * @param id del viajero a buscar
     * @return el dto del viajero buscado
     * @throws BusinessLogicException en caso de que no se sigan las reglas de
     * negocio
     */
    @GET
    @Path("{id: \\d+}")
    public ViajeroDetailDTO getUsuario(@PathParam("id") Long id) throws BusinessLogicException
    {
        // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        ViajeroEntity v=logic.getViajero(id);
        if (v == null)
        {
            throw new WebApplicationException("No existe el viajero con el id especificado",404);
        }
        return new ViajeroDetailDTO(v);
    }

    /**
     * Crea a un viajero con los datos que llegan por parametro
     *
     * @param usuario Los datos a utilizar
     * @return El dto del usuario creado
     * @throws BusinessLogicException en caso de que no se sigan reglas de
     * necogico
     */
    @POST
    public ViajeroDetailDTO createViajero(ViajeroDetailDTO usuario) throws BusinessLogicException
    {
        return new ViajeroDetailDTO(logic.createViajero(usuario.toEntity()));
    }

    /**
     * Se modifica el valor dee una entidad con cierto codigo
     *
     * @param viajero la entidad con el codigo que se desea modificar
     * @return El DTO de la netidad modificada
     * @throws BusinessLogicException en caso de que no se sigan las reglas de
     * negocio
     */
    @PUT
    @Path("{id: \\d+}")
    // TODO falta el Path id
    public ViajeroDetailDTO updateViajero(ViajeroDetailDTO viajero) throws BusinessLogicException
    {
         // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        ViajeroEntity v=logic.updateViajero(viajero.toEntity());
        if (v == null)
        {
            throw new WebApplicationException("No existe el viajero con el id especificado",404);
        }
        return new ViajeroDetailDTO(v);
    }
    
    @DELETE
    @Path("{id: \\d+}")
    // TODO falta el Path id
    public void deleteViajero(@PathParam("id") int id) throws BusinessLogicException
    {
         // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        logic.deleteViajero((long)id);
        
    }
}
