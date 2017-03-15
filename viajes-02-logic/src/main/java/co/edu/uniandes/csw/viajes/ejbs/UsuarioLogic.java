/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author n.aguilar
 */

@Stateless
public class UsuarioLogic {
    /**
     * La persistencia de la entidad usuario
     */
    @Inject private UsuarioPersistence persistence;
    /**
     * Retorna los usuarios en el sistema
     * @return Lista de usuarios en el sistema
     */
    public List<UsuarioEntity> getUsuarios()
    {
        return persistence.findAll();
    }
    /**
     * Retorna un usuario que se identifique con el parametro
     * @param id El id de la entidad que se busca
     * @return El usuario que se esta buscando
     * @throws BusinessLogicException en caso de que no exista un usuario identificado con el id
     */
    public UsuarioEntity getUsuario(Long id)throws BusinessLogicException
    {
        UsuarioEntity u = persistence.find(id);
        if(u==null)
            throw new BusinessLogicException("No existe un usuario con el id dado por parametro.");
        return u;
    }
    /**
     * Crea un usuario con la entidad que llega por parametro
     * @param entity La entidad que se desea crear
     * @return La entidad que se creo
     * @throws BusinessLogicException En caso de que el genero de la entidad no sea un valor esperado 
     */
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException
    {
        if(!entity.getGenero().equalsIgnoreCase("masculino")&&!entity.getGenero().equalsIgnoreCase("femenino"))
            throw new BusinessLogicException("El genero del usuario a crear no es valido. (Debe ser masculino o femenino)");
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Actualiza una entidad que llega por parametro
     * @param entity La entidad que se desea actualizar
     * @return La entidad actualizada
     */
    public UsuarioEntity updateUsuario(UsuarioEntity entity)throws BusinessLogicException
    {
        if(!entity.getGenero().equalsIgnoreCase("masculino")&&!entity.getGenero().equalsIgnoreCase("femenino"))
            throw new BusinessLogicException("El genero del usuario a crear no es valido. (Debe ser masculino o femenino)");
        persistence.update(entity);
        return entity;
    }
    /**
     * Elimina una entidad identificada con el id que llega por parametro
     * @param id el id de la entidad a eliminar
     */
    public void deleteUsuario(Long id)
    {
        persistence.delete(id);
    }
}
