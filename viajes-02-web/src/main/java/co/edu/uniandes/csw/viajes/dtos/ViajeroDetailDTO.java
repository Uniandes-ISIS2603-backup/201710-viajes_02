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
public class ViajeroDetailDTO extends ViajeroDTO{
    
    private List<ReservaDTO> reservas;
        
public ViajeroDetailDTO()
    {
        super();
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
    public ViajeroDetailDTO(ViajeroEntity entity)
    {
        super(entity);
        reservas = new ArrayList<ReservaDTO>();
        if(entity!=null)
        {
            for(ReservaEntity r: entity.getReservas())
            {
                reservas.add(new ReservaDTO(r));
            }
        }
    }
    
    
    @Override
    public ViajeroEntity toEntity()
    {
        ViajeroEntity entity = super.toEntity();
        
        List<ReservaEntity> reviewsEntity =  new ArrayList<>();
        if (reservas != null) {
            for (ReservaDTO r : reservas) {
                reviewsEntity.add(r.toEntity());
            }
        }

        entity.setReservas(reviewsEntity);
        
        return entity;
    }
    
}
