/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.UsuarioDTO;
import co.edu.uniandes.csw.viajes.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.viajes.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import com.gs.collections.impl.utility.internal.primitive.ShortIterableIterate;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author n.aguilar
 */
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private UsuarioLogic usuariologic;

    private List<UsuarioDetailDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> lista = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            lista.add(new UsuarioDetailDTO(entity));
        }
        return lista;
    }

    @GET
    public List<UsuarioDetailDTO> getUsuarios() {
        return listEntity2DTO(usuariologic.getUsuarios());
    }
    
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("id")Long id) throws BusinessLogicException
    {
        return new UsuarioDetailDTO(usuariologic.getUsuario(id));
    }

    @POST
    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO usuario) {
        return new UsuarioDetailDTO(usuariologic.createUsuario(usuario.toEntity()));
    }
    
    @PUT 
    @Path("{id: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam ("id") Long id, UsuarioDTO dto)
    {
        UsuarioEntity entity = dto.toEntity();
        entity.setId(id);
        return new UsuarioDTO(usuariologic.updateUsuario(entity));
    }
}
