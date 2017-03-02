/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.LugarEntity;
import co.edu.uniandes.csw.viajes.persistence.LugarPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author wr.ravelo
 */
@Stateless
public class LugarLogic {
    
    @Inject LugarPersistence persistence;
    
    public LugarEntity createLugar(LugarEntity entity) {
        return persistence.create(entity);
    }
    
    public LugarEntity findLugar(Long id) {
        return persistence.find(id);
    }
    
    public List<LugarEntity> findLugares() {
        return persistence.findAll();
    }
    
    public LugarEntity updateLugar(LugarEntity entity) {
        return persistence.update(entity);
    }
    
    public void deleteLugar(Long id) {
        persistence.delete(id);
    }
}
