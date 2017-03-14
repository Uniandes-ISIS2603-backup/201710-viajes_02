/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;

/**
 *
 * @author wr.ravelo
 */
public class CobroMultaDetailDTO extends CobroMultaDTO {
    public CobroMultaDetailDTO() {
        super();
    }
    
    public CobroMultaDetailDTO(CobroMultaEntity entity) {
        super(entity);
    }
    
    @Override
    public CobroMultaEntity toEntity() {
        return super.toEntity();
    }
}
