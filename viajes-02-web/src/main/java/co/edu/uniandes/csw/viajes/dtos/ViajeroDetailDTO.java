/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author n.aguilar
 */
@XmlRootElement
public class ViajeroDetailDTO extends ViajeroDTO{
    
public ViajeroDetailDTO()
    {
        super();
    }
    
    public ViajeroDetailDTO(ViajeroEntity entity)
    {
        super(entity);
    }
    
    
    @Override
    public ViajeroEntity toEntity()
    {
        ViajeroEntity entity = super.toEntity();
        return entity;
    }
    
}
