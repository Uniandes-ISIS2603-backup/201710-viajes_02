/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jm.dominguez
 */

@XmlRootElement
public class ConductorDetailDTO extends ConductorDTO{
    
    public ConductorDetailDTO(){
        super();
    }
    public ConductorDetailDTO(ConductorEntity entity){
        super(entity);
    }
    
    @Override
    public ConductorEntity DTO2Entity(){
        ConductorEntity entity = super.DTO2Entity();
        return entity;
    }
}
