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
import co.edu.uniandes.csw.viajes.dtos.UsuarioDTO;
import co.edu.uniandes.csw.viajes.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource
{

    /**
     * Logica del usuario
     */
    @Inject
    private UsuarioLogic usuariologic;

    /**
     * Pasa una lista de entidades a dtos
     *
     * @param entityList lista a transformar
     * @return la lista de dtos
     */
    private List<UsuarioDetailDTO> listEntity2DTO(List<UsuarioEntity> entityList)
    {
        List<UsuarioDetailDTO> lista = new ArrayList<>();
        for (UsuarioEntity entity : entityList)
        {
            lista.add(new UsuarioDetailDTO(entity));
        }
        return lista;
    }

    /**
     * Retorn todos los usuarios ne forma de DTOs
     *
     * @return todos los usuarios
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios()
    {
        return listEntity2DTO(usuariologic.getUsuarios());
    }

    /**
     * Retorna un usuario especifico
     *
     * @param id La id del usuario
     * @return El dto de la entidad del usuario
     * @throws BusinessLogicException En caso de que no se sigan ciertas reglas
     * de negocio
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) throws BusinessLogicException
    { // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        UsuarioEntity v = usuariologic.getUsuario(id);
        if(v==null)
        {
            throw new WebApplicationException("No existe el usuario con el id especificado",404);
        }
        return new UsuarioDetailDTO(v);
    }

    /**
     * Se modifica el valor de de una entidad con la id recibida
     *
     * @param id id de la entidada
     * @param dto Informacion nueva de la entidad
     * @return El dto actualizado de la entidad
     */
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("id") Long id, UsuarioDTO dto) throws BusinessLogicException
    { // TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        UsuarioEntity u=usuariologic.updateUsuario(dto.toEntity());
        if (u == null)
        {
            throw new WebApplicationException("No existe el usuario con el id especificado",404);
        }
        return new UsuarioDetailDTO(u);
    }
}
