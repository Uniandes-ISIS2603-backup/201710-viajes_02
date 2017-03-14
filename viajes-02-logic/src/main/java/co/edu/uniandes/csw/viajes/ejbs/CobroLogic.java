/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.CobroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author wr.ravelo
 */
@Stateless
public class CobroLogic {
    @Inject private CobroPersistence persistence;
    
    public CobroEntity createCobro(CobroEntity entity) {
        return persistence.create(entity);
    }
    
    public CobroEntity findCobro(Long id) {
        return persistence.find(id);
    }
    
    public List<CobroEntity> findCobros() {
        return persistence.findAll();
    }
    
    public List<CobroEntity> findCobros(Long id) {
        return persistence.findAllFromUsuario(id);
    }
    
    public CobroEntity updateCobro(CobroEntity entity) {
        return persistence.update(entity);
    }
    
    public void deleteCobro(Long id) {
        persistence.delete(id);
    }
}
