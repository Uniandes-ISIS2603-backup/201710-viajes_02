/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author n.aguilar
 */
@XmlRootElement
public class ViajeroDetailDTO extends ViajeroDTO {

    /**
     * Reservas de un viajero
     */
    private List<ReservaDTO> reservas;

    /**
     * Constructor vacio
     */
    public ViajeroDetailDTO() {
        super();
    }

    /**
     * REtorna las reservas de un viajero
     *
     * @return reservas
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    /**
     * Modifica las reservas de un viajero por le mparametro
     *
     * @param reservas
     */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    /**
     * Se genera el DTO a partir de una entidad que llega por parametro
     *
     * @param entity Base
     */
    public ViajeroDetailDTO(ViajeroEntity entity) {
        super(entity);
        reservas = new ArrayList<ReservaDTO>();
        if (entity != null) {
            for (ReservaEntity r : entity.getReservas()) {
                reservas.add(new ReservaDTO(r));
            }
        }
    }

    /**
     * Se genera una entidad a partir de la informacion del DTO
     *
     * @return Entidad generada
     */
    @Override
    public ViajeroEntity toEntity() {
        ViajeroEntity entity = super.toEntity();

        List<ReservaEntity> reviewsEntity = new ArrayList<>();
        if (reservas != null) {
            for (ReservaDTO r : reservas) {
                reviewsEntity.add(r.toEntity());
            }
        }

        entity.setReservas(reviewsEntity);

        return entity;
    }

}
