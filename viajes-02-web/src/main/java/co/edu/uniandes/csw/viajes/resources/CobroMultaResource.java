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

import co.edu.uniandes.csw.viajes.dtos.CobroMultaDTO;
import co.edu.uniandes.csw.viajes.dtos.CobroMultaDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.UsuarioDTO;
import co.edu.uniandes.csw.viajes.ejbs.CobroMultaLogic;
import co.edu.uniandes.csw.viajes.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;
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
@Path("/usuarios/{usuarioId: \\d+}/cobromultas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CobroMultaResource {

    /**
     * Logica del cobro multa.
     */
    @Inject
    private CobroMultaLogic logic;

    /**
     * Id del usuario al que pertenece el cobro multa
     */
    @PathParam("usuarioId")
    private Long usuarioId;

    /**
     * Logica de los usuarios.
     */
    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * Da todos los cobros del usuario.
     *
     * @return Todos los cobros que hay del usuario.
     */
    @GET
    public List<CobroMultaDetailDTO> getCobros() {
        return listEntity2DTO(logic.findCobroMultas(usuarioId));
    }

    /**
     * Da el cobro multa que tiene id que entra por parametro y pertenece al
     * usuario especificado.
     *
     * @param id Id del cobro multa
     * @return Cobro multa que pertenece al usuario especifico y tiene id igual
     * al que entra por parametro
     */
    @GET
    @Path("{id: \\d+}")
    public CobroMultaDetailDTO getCobro(@PathParam("id") Long id) {
        if(!existsCobroMulta(id))
            throw new WebApplicationException("No existe cobro multa con id"+id, 404);
        
        return basicEntity2DTO(logic.findCobroMulta(id));
    }

    /**
     * Crea un cobro multa de acuerdo a la informacion que tiene el DTO que
     * entra por parametro
     *
     * @param cobro DTO que tiene la informacion del cobro multa que se va a
     * crear en la base de datos.
     * @return Cobro multa DTO que tiene la informacion guardad en la base de
     * datos.
     */
    @POST
    public CobroMultaDetailDTO createCobro(CobroMultaDetailDTO cobro) {
        if(!existsUsuario(usuarioId))
            throw new WebApplicationException("No existe usuario con id" +usuarioId, 404);
        else if(!cobro.getUsuarioRemitente().getId().equals(usuarioId))
            throw new WebApplicationException("Solo puede crear cobros multa con id remitente a " +usuarioId, 404);
        
        cobro.getUsuarioRemitente().setId(usuarioId);
        return basicEntity2DTO(logic.createCobroMulta(cobro.toEntity()));
    }

    /**
     * Actualia la informacion de un cobro multa
     *
     * @param id Id del cobro multa que se va a actualizar
     * @param cobro Nueva informacion del cobro multa.
     * @return DTO que contiene la nueva informacion.
     */
    @PUT
    @Path("{id: \\d+}")
    public CobroMultaDTO updateCobro(@PathParam("id") Long id, CobroMultaDetailDTO cobro) {
        if(!existsCobroMulta(id)|| !existsUsuario(usuarioId))
            throw new WebApplicationException("No existe el id", 404);
        
        cobro.getUsuarioRemitente().setId(usuarioId);
        CobroMultaEntity entity = cobro.toEntity();
        entity.setId(id);
        return basicEntity2DTO(logic.updateCobroMulta(entity));
    }

    /**
     * Elimina el registro de un cobro multa.
     *
     * @param id Id del cobro multa que se quiere eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCobro(@PathParam("id") Long id) {
       if(!existsCobroMulta(id))
           throw new WebApplicationException("No existe cobro multa con id " +id, 404);
        
        logic.deleteCobroMulta(id);
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
    public boolean existsCobroMulta(Long id) {
        return logic.findCobroMulta(id) != null;
    }

    /**
     * Convierte una entidad a DTO
     *
     * @param entity Entidad a convertir
     * @return DTO creado a partir de la entidad
     */
    public CobroMultaDetailDTO basicEntity2DTO(CobroMultaEntity entity) {
        CobroMultaDetailDTO dto = new CobroMultaDetailDTO();
        dto.setCancelado(entity.getCancelado());
        dto.setValor(entity.getValor());
        dto.setId(entity.getId());
        dto.setUsuarioDestinatario(new UsuarioDTO(entity.getUsuarioDestinatario()));
        dto.setUsuarioRemitente(new UsuarioDTO(entity.getUsuarioRemitente()));
        return dto;
    }

    /**
     * Convierte una lista de dtos a entidades.
     *
     * @param dtos Lista de dtos a converitr a entidades.
     * @return Lista con las entidades.
     */
    public List<CobroMultaEntity> listDTO2Entity(List<CobroMultaDetailDTO> dtos) {
        List<CobroMultaEntity> list = new ArrayList<>();

        for (CobroMultaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }

        return list;
    }

    /**
     * Convierte una lista de entidades a DTO
     *
     * @param entities Entidades a convertir a dto.
     * @return Lista con los DTOS
     */
    public List<CobroMultaDetailDTO> listEntity2DTO(List<CobroMultaEntity> entities) {
        List<CobroMultaDetailDTO> dtos = new ArrayList<>();

        for (CobroMultaEntity entity : entities) {
            dtos.add(basicEntity2DTO(entity));
        }

        return dtos;
    }
}
