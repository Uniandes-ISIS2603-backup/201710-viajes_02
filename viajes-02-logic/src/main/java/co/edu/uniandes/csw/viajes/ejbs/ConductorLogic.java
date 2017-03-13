/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.persistence.ConductorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jm.dominguez
 */
@Stateless
public class ConductorLogic {
    
    @Inject private ConductorPersistence persistence;
    
    public List <ConductorEntity> findAll(){
        return persistence.finAll();
    }
    
    public ConductorEntity getConductor(Long id){
        return persistence.find(id);
    }
    
    public ConductorEntity createConductor(ConductorEntity c){
        return persistence.create(c);
    }
    
    public ConductorEntity updateConductor(ConductorEntity c){
        return persistence.update(c);
    }
    
    public void deleteConductor (Long id){
        persistence.delete(id);
    }
    
}
