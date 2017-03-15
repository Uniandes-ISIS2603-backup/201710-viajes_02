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
    
    @Inject private UsuarioPersistence persistence;
    
    public List<UsuarioEntity> getUsuarios()
    {
        return persistence.findAll();
    }
    
    public UsuarioEntity getUsuario(Long id)throws BusinessLogicException
    {
        UsuarioEntity u = persistence.find(id);
        if(u==null)
            throw new BusinessLogicException("No existe un usuario con el id dado por parametro.");
        return u;
    }
    
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException
    {
        if(!entity.getGenero().equalsIgnoreCase("masculino")&&!entity.getGenero().equalsIgnoreCase("femenino"))
            throw new BusinessLogicException("El genero del usuario a crear no es valido. (Debe ser masculino o femenino)");
        persistence.create(entity);
        return entity;
    }
    
    public UsuarioEntity updateUsuario(UsuarioEntity entity)
    {
        persistence.update(entity);
        return entity;
    }
    
    public void deleteUsuario(Long id)
    {
        persistence.delete(id);
    }
}
