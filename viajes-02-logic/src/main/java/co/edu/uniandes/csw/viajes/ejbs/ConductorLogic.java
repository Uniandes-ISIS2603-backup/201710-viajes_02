/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
    
    public ConductorEntity getConductor(Long id) throws BusinessLogicException{
        ConductorEntity respuesta = persistence.find(id);
        if(respuesta == null){
            throw new BusinessLogicException("No se ha encontrado ningún conductor con el id dado");
        }
        else{
            return respuesta;
        }
        
    }
    
    public ConductorEntity createConductor(ConductorEntity c) throws BusinessLogicException{
        ConductorEntity conductor = persistence.find(c.getId());
        if(conductor != null){
            throw new BusinessLogicException("El conductor con id dado ya existe en el sistema");
        }
        else if(c.getAutomoviles().isEmpty())
                {
                    throw new BusinessLogicException("Los conductores deben tener al menos 1 vehículo");
        }
        return persistence.create(c);
    }
    
    public ConductorEntity updateConductor(ConductorEntity c) throws BusinessLogicException{
        ConductorEntity conductor = persistence.find(c.getId());
        if(conductor != null){
            throw new BusinessLogicException("El conductor con id dado ya existe en el sistema");
        }
        else if(c.getAutomoviles().isEmpty())
                {
                    throw new BusinessLogicException("Los conductores deben tener al menos 1 vehículo");
        }
        return persistence.update(c);
    }
    
    public void deleteConductor (Long id){
        persistence.delete(id);
    }
    
    public ConductorEntity addCarroToConductor(AutomovilEntity c, Long id) throws BusinessLogicException{
        ConductorEntity conductor = persistence.find(id);
        if(conductor != null){
            throw new BusinessLogicException("El conductor con id dado ya existe en el sistema");
        }
        return persistence.addCarro(c, id);
    }
    
    
}
