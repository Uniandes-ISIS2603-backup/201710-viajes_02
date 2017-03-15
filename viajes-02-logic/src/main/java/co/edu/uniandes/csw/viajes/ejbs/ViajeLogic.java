/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import co.edu.uniandes.csw.viajes.persistence.ViajePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jm.dominguez
 */
@Stateless
public class ViajeLogic {
    
    @Inject private ViajePersistence persistence;
    
    public List<ViajeEntity> findViajes(){
        return persistence.findAll();
    }
    
    public ViajeEntity findViaje(Long id){
        return persistence.find(id);
    }
    
    public ViajeEntity createViaje(ViajeEntity v){
        return persistence.create(v);
    }
    
    public ViajeEntity updateViaje(ViajeEntity v){
        return persistence.update(v);
    }
    
    public void deleteViaje(Long id){
        persistence.delete(id);
    }
    
    public List<ViajeEntity> darViajesOrigenYDestino(String origen, String destino){
        return persistence.buscarPorOrigenyDestino(origen, destino);
    }
}
