/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.CobroDTO;
import co.edu.uniandes.csw.viajes.ejbs.CobroLogic;
import co.edu.uniandes.csw.viajes.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.viajes.entities.CobroEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author wr.ravelo
 */
@Path("/usuarios/{usuarioId: \\d+}/cobros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CobroResource
{

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
    public List<CobroDTO> getCobros()
    {
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
    public CobroDTO getCobro(@PathParam("id") Long id)
    {// TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
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
    public CobroDTO createCobro(CobroDTO cobro)
    {// TODO si el recurso con el usuarioId dado no existe de sedeb disparar WebApplicationException 404
        cobro.setIdRemitente(usuarioId);
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
    public CobroDTO updateCobro(@PathParam("id") Long id, CobroDTO cobro)
    {// TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
        cobro.setIdRemitente(id);
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
    public void deleteCobro(@PathParam("id") Long id)
    {// TODO si el recurso con el id dado no existe de sedeb disparar WebApplicationException 404
      
        logic.deleteCobro(id);
    }

    // Helpers
    /**
     * Mira si existe un usuario con el id que entra por parametro.
     *
     * @param id Id del usuario que se va a verificar.
     * @return True si el usuario existe, false de lo contrario.
     */
    public boolean existsUsuario(Long id) throws BusinessLogicException
    {
        return usuarioLogic.getUsuario(id) != null;
    }

    /**
     * Mira si existe un cobro con el id que entra por parametro.
     *
     * @param id Id del cobro que se va a verificar.
     * @return True si el cobro existe, false de lo contrario.
     */
    public boolean existsCobro(Long id)
    {
        return logic.findCobro(id) != null;
    }

    /**
     * Convierte una entidad en dto.
     *
     * @param entity Entidad que se va a convertir en DTO
     * @return DTO que se convirtio de entidad.
     */
    public CobroDTO basicEntity2DTO(CobroEntity entity)
    {
        CobroDTO dto = new CobroDTO();
        dto.setCancelado(entity.getCancelado());
        dto.setValor(entity.getValor());
        dto.setId(entity.getId());
        dto.setIdDestinatario(entity.getIdDestinatario());
        dto.setIdRemitente(entity.getIdRemitente());
        return dto;
    }

    /**
     * Convierte una lista de DTOS a entidades.
     *
     * @param dtos DTOS que se van a convertir a entidades.
     * @return Entidades convertidas desde DTOS
     */
    public List<CobroEntity> listDTO2Entity(List<CobroDTO> dtos)
    {
        List<CobroEntity> list = new ArrayList<>();

        for (CobroDTO dto : dtos)
        {
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
    public List<CobroDTO> listEntity2DTO(List<CobroEntity> entities)
    {
        List<CobroDTO> dtos = new ArrayList<>();

        for (CobroEntity entity : entities)
        {
            dtos.add(basicEntity2DTO(entity));
        }

        return dtos;
    }
}
