/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jm.dominguez
 */
@XmlRootElement
public class ViajeDetailDTO extends ViajeDTO{
    
    public ViajeDetailDTO(){
        super();
    }
    
    public ViajeDetailDTO(ViajeEntity entity){
        super(entity);
    }
    
    @Override
    public ViajeEntity DTO2Entity(){
        ViajeEntity entity = super.DTO2Entity();
        return entity;
    }
}
