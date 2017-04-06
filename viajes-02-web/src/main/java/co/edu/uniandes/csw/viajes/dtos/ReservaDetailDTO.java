/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * ReservaDetailDTO
 * @author ja.bermudez10
 */

@XmlRootElement
public class ReservaDetailDTO extends ReservaDTO {
    
    @PodamExclude
    private ViajeroDTO viajero;

    @PodamExclude
    private ViajeDTO viaje;

    public ViajeroDTO getViajero() {
        return viajero;
    }

    public void setViajero(ViajeroDTO viajero) {
        this.viajero = viajero;
    }

    public ViajeDTO getViaje()
    {
        return viaje;
    }

    public void setViaje(ViajeDTO viaje)
    {
        this.viaje = viaje;
    }

    public ReservaDetailDTO()
    {
        super();
    }

    public ReservaDetailDTO(ReservaEntity reservaEntity)
    {
        super(reservaEntity);
        viaje = new ViajeDTO(reservaEntity.getViaje());
        viajero = new ViajeroDTO(reservaEntity.getViajero());
    }

    @Override
    public ReservaEntity toEntity()
    {
        ReservaEntity reservaEntity = super.toEntity();
        reservaEntity.setViaje(viaje.DTO2Entity());
        reservaEntity.setViajero(viajero.toEntity());

        return reservaEntity;
    }
}
