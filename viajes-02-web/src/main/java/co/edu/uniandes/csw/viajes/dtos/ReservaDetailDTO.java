/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;

/**
 *
 * @author ja.bermudez10
 */
public class ReservaDetailDTO extends ReservaDTO {

    public ReservaDetailDTO() {
        super();
    }

    public ReservaDetailDTO(ReservaEntity reservaEntity) {
        super(reservaEntity);
    }

    @Override
    public ReservaEntity toEntity() {
        ReservaEntity reservaEntity = super.toEntity();

        return reservaEntity;
    }
}
