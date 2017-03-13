/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
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
    
    public ViajeroEntity getViajero(Long id)
    {
        return persistence.find(id);
    }
    
    public ViajeroEntity createViajero(ViajeroEntity entity)
    {
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
