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

import co.edu.uniandes.csw.viajes.dtos.CobroDTO;
import co.edu.uniandes.csw.viajes.dtos.CobroDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.UsuarioDTO;
import co.edu.uniandes.csw.viajes.ejbs.CobroLogic;
import co.edu.uniandes.csw.viajes.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.viajes.entities.CobroEntity;
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
 * @author wr.ravelo
 */
@Path("/usuarios/{usuarioId: \\d+}/cobros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CobroResource {

    /**
     * Logica de los cobros.
     */
    @Inject
    private CobroLogic logic;

    /**
     * Logica de los usuarios.
     */
    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * Id del usuario al que pertenece el cobro.
     */
    @PathParam("usuarioId")
    private Long usuarioId;

    /**
     * Da todos los cobros que pertenecen al usuario especificado.
     *
     * @return Cobros del usuario especificado.
     */
    @GET
    public List<CobroDetailDTO> getCobros() {
        return listEntity2DTO(logic.findCobros(usuarioId));
    }

    /**
     * Da el cobro que tiene id igual al parametro y pertenece al usuario
     * especificado
     *
     * @param id Id del cobro
     * @return Cobro que tiene id igual al parametro, null si no existe.
     */
    @GET
    @Path("{id: \\d+}")
    public CobroDetailDTO getCobro(@PathParam("id") Long id) {
        if (!existsCobro(id)) {
            throw new WebApplicationException("El id no existe", 404);
        }

        return basicEntity2DTO(logic.findCobro(id));
    }

    /**
     * Crea un nuevo registro de cobro en la base de datos
     *
     * @param cobro DTO que contiene la informacion que se va a guardar en la
     * base de datos.
     * @return DTO que contiene la informacion que se guardo.
     */
    @POST
    public CobroDetailDTO createCobro(CobroDetailDTO cobro) {
        if (!existsUsuario(usuarioId)) {
            throw new WebApplicationException("No existe el usuario con el id" + usuarioId, 404);
        } else if(!cobro.getUsuarioRemitente().getId().equals(usuarioId))
            throw new WebApplicationException("Solo puede crear cobros con id remitente a " +usuarioId, 404);

        cobro.getUsuarioRemitente().setId(usuarioId);
        return basicEntity2DTO(logic.createCobro(cobro.toEntity()));
    }

    /**
     * Actualiza un cobro que tiene id igual al parametro.
     *
     * @param id Id del cobro que se va a actualizar.
     * @param cobro DTO que contiene la nueva informacion del cobro.
     * @return DTO que contiene la nueva informacion del usuario.
     */
    @PUT
    @Path("{id: \\d+}")
    public CobroDetailDTO updateCobro(@PathParam("id") Long id, CobroDetailDTO cobro) {
        if(!existsCobro(id))
            throw new WebApplicationException("No existe el cobro con id" +id, 404);
        
        cobro.getUsuarioRemitente().setId(usuarioId);
        CobroEntity entity = cobro.toEntity();
        entity.setId(id);
        return basicEntity2DTO(logic.updateCobro(entity));
    }

    /**
     * Elimina el registro de cobro que tiene id igual al parametro.
     *
     * @param id Id del cobro que se va a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCobro(@PathParam("id") Long id) {
        if(!existsCobro(id))
            throw new WebApplicationException("No existe cobro con id " +id, 404);
        
        logic.deleteCobro(id);
    }

    // Helpers
    /**
     * Mira si existe un usuario con el id que entra por parametro.
     *
     * @param id Id del usuario que se va a verificar.
     * @return True si el usuario existe, false de lo contrario.
     */
    public boolean existsUsuario(Long id) {
        try {
            return usuarioLogic.getUsuario(id) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Mira si existe un cobro con el id que entra por parametro.
     *
     * @param id Id del cobro que se va a verificar.
     * @return True si el cobro existe, false de lo contrario.
     */
    public boolean existsCobro(Long id) {
        return logic.findCobro(id) != null;
    }

    /**
     * Convierte una entidad en dto.
     *
     * @param entity Entidad que se va a convertir en DTO
     * @return DTO que se convirtio de entidad.
     */
    public CobroDetailDTO basicEntity2DTO(CobroEntity entity) {
        CobroDetailDTO dto = new CobroDetailDTO();
        dto.setCancelado(entity.getCancelado());
        dto.setValor(entity.getValor());
        dto.setId(entity.getId());
        dto.setUsuarioDestinatario(new UsuarioDTO(entity.getUsuarioDestinatario()));
        dto.setUsuarioRemitente(new UsuarioDTO(entity.getUsuarioRemitente()));
        return dto;
    }

    /**
     * Convierte una lista de DTOS a entidades.
     *
     * @param dtos DTOS que se van a convertir a entidades.
     * @return Entidades convertidas desde DTOS
     */
    public List<CobroEntity> listDTO2Entity(List<CobroDetailDTO> dtos) {
        List<CobroEntity> list = new ArrayList<>();

        for (CobroDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }

        return list;
    }

    /**
     * Convierte una lista de entidades a dtos.
     *
     * @param entities Entidades que se van a convertir a dtos.
     * @return DTOS que se conviertieron desde entidades.
     */
    public List<CobroDetailDTO> listEntity2DTO(List<CobroEntity> entities) {
        List<CobroDetailDTO> dtos = new ArrayList<>();

        for (CobroEntity entity : entities) {
            dtos.add(basicEntity2DTO(entity));
        }

        return dtos;
    }
}
