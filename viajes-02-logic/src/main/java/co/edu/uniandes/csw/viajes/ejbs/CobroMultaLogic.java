/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;
import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;
import co.edu.uniandes.csw.viajes.persistence.CobroMultaPersistence;
import co.edu.uniandes.csw.viajes.persistence.CobroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author wr.ravelo
 */
@Stateless
public class CobroMultaLogic {
     @Inject private CobroMultaPersistence persistence;
    
    public CobroMultaEntity createCobro(CobroMultaEntity entity) {
        return persistence.create(entity);
    }
    
    public CobroMultaEntity findCobro(Long id) {
        return persistence.find(id);
    }
    
    public List<CobroMultaEntity> findCobros() {
        return persistence.findAll();
    }
    
    public CobroMultaEntity updateCobro(CobroMultaEntity entity) {
        return persistence.update(entity);
    }
    
    public void deleteCobro(Long id) {
        persistence.delete(id);
    }
}
