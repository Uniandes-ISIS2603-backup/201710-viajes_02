/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;

/**
 *
 * @author ja.bermudez10
 */
public class PagoDetailDTO extends PagoDTO {
    
    public PagoDetailDTO() {
        super();
    }
    
    public PagoDetailDTO (PagoEntity pagoEntity) {
        super(pagoEntity);
    }
    
    @Override
    public PagoEntity toEntity() {
        PagoEntity pagoEntity = super.toEntity();
        
        return  pagoEntity;
    }
}
