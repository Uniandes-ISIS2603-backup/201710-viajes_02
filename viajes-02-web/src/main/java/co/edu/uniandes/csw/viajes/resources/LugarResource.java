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

import co.edu.uniandes.csw.viajes.dtos.LugarDTO;
import co.edu.uniandes.csw.viajes.ejbs.LugarLogic;
import co.edu.uniandes.csw.viajes.entities.LugarEntity;
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
@Path("/lugares")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LugarResource
{

    /**
     * Logica del lugar.
     */
    @Inject
    private LugarLogic logic;

    /**
     * Da todos los lugares de la base de datos.
     *
     * @return Todos los lugares registrados
     */
    @GET
    public List<LugarDTO> getLugares()
    {
        return listEntity2DTO(logic.findLugares());
    }

    /**
     * Da el lugar que tiene id igual al parametro.
     *
     * @param id Id del lugar a buscar.
     * @return Da el lugar DTO que tiene id igual al parametro.
     */
    @GET
    @Path("{id: \\d+}")
    public LugarDTO getLugar(@PathParam("id") Long id)
    {
      if(!existsLugar(id))
          throw new WebApplicationException("No existe el lugar con id" +id, 404);
        
        return basicEntity2DTO(logic.findLugar(id));
    }

    /**
     * Crea un nuevo registro de lugar
     *
     * @param lugar DTO que contiene los datos del lugar a crear.
     * @return DTO creado en la base de datos.
     */
    @POST
    public LugarDTO createLugar(LugarDTO lugar)
    {
        return basicEntity2DTO(logic.createLugar(lugar.toEntity()));
    }

    /**
     * Actualiza un lugar que tiene id igual al parametro que entra por
     * parametro
     *
     * @param id Id de lugar a actualizar.
     * @param lugar DTO que contiene la nueva informacion del lugar
     * @return DTO actualizado.
     */
    @PUT
    @Path("{id: \\d+}")
    public LugarDTO updateLugar(@PathParam("id") Long id, LugarDTO lugar)
    {
      if(!existsLugar(id))
          throw new WebApplicationException("No existe lugar con id " +id, 404);
        
        LugarEntity entity = lugar.toEntity();
        entity.setId(id);
        return basicEntity2DTO(logic.updateLugar(entity));
    }

    /**
     * Elimina un lugar con id igual al parametro.
     *
     * @param id Id del lugar que se va a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLugar(@PathParam("id") Long id)
    {
      if(!existsLugar(id))
          throw new WebApplicationException("No existe lugar con el id " +id, 404);
        
        logic.deleteLugar(id);
    }

    // Helpers
     /**
     * Mira si existe un lugar con el id que entra por parametro.
     *
     * @param id Id del lugar que se va a verificar.
     * @return True si el lugar existe, false de lo contrario.
     */
    public boolean existsLugar(Long id) {
        return logic.findLugar(id) != null;
    }
    
    
    /**
     * Convierte una entidad a dto
     *
     * @param entity Entidad que se va a convertir a DTO
     * @return DTO que se conviertio de una entidad.
     */
    public LugarDTO basicEntity2DTO(LugarEntity entity)
    {
        LugarDTO dto = new LugarDTO();
        dto.setDireccion(entity.getDireccion());
        dto.setId(entity.getId());
        dto.setLugar(entity.getLugar());
        dto.setLat(entity.getLat());
        dto.setLon(entity.getLon());
        return dto;
    }

    /**
     * Convierte una lista de DTOs a entidades
     *
     * @param dtos DTOS que se van a convertir a entidades.
     * @return Lista de entidades
     */
    public List<LugarEntity> listDTO2Entity(List<LugarDTO> dtos)
    {
        List<LugarEntity> list = new ArrayList<>();

        for (LugarDTO dto : dtos)
        {
            list.add(dto.toEntity());
        }

        return list;
    }

    /**
     * Convierte una lista de entidades a dtos.
     *
     * @param entities Entidades que se van a convertir a dtos.
     * @return Lista de dtos.
     */
    public List<LugarDTO> listEntity2DTO(List<LugarEntity> entities)
    {
        List<LugarDTO> dtos = new ArrayList<>();

        for (LugarEntity entity : entities)
        {
            dtos.add(basicEntity2DTO(entity));
        }

        return dtos;
    }
}
