/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;

/**
 *
 * @author wr.ravelo
 */
public class CobroDetailDTO extends CobroDTO {
    public CobroDetailDTO() {
        super();
    }
    
    public CobroDetailDTO(CobroEntity entity) {
        super(entity);
    }
    
    @Override
    public CobroEntity toEntity() {
        return super.toEntity();
    }
}
