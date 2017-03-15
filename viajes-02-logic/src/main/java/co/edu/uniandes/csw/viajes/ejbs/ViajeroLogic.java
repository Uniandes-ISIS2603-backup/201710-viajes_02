/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ViajeroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author n.aguilar
 */
@Stateless
public class ViajeroLogic {
    
    @Inject private ViajeroPersistence persistence;
    
    public List<ViajeroEntity> getViajeros()
    {
        return persistence.findAll();
    }
    
    public ViajeroEntity getViajero(Long id) throws BusinessLogicException
    {
        ViajeroEntity v= persistence.find(id);
        if(v==null)
            throw new BusinessLogicException("No existe un viajero con el id ingresado por parametro");
        return persistence.find(id);
    }
    
    public ViajeroEntity createViajero(ViajeroEntity entity) throws BusinessLogicException
    {
        if(!entity.getGenero().equalsIgnoreCase("masculino")&&!entity.getGenero().equalsIgnoreCase("femenino"))
            throw new BusinessLogicException("El genero del usuario a crear no es valido. (Debe ser masculino o femenino)");
        if(entity.getEdad()< 0 || entity.getEdad() > 115 )
            throw new BusinessLogicException("La edad ingresada no es valida");
        persistence.create(entity);
        return entity;
    }
    
    public ViajeroEntity updateViajero(ViajeroEntity entity)
    {
        persistence.update(entity);
        return entity;
    }
    
    public void deleteViajero(Long id)
    {
        persistence.delete(id);
    }
    
}
