/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.LugarEntity;

/**
 *
 * @author wr.ravelo
 */
public class LugarDetailDTO extends LugarDTO {
    
    
    public LugarDetailDTO() {
        super();
    }
    
    public LugarDetailDTO(LugarEntity entity) {
        super(entity);
    }
    
    @Override
    public LugarEntity toEntity() {
        return super.toEntity();
    }
}
