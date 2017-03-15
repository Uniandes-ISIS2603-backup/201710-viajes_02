/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.LugarEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wr.ravelo
 */
@XmlRootElement
public class LugarDetailDTO extends LugarDTO
{

    /**
     * Crea un lugar detail dto.
     */
    public LugarDetailDTO()
    {
        super();
    }

    /**
     * Crea un detail dto de una entidad
     *
     * @param entity Entidad de la cual se va a crear un lugar detail dto.
     */
    public LugarDetailDTO(LugarEntity entity)
    {
        super(entity);
    }

    /**
     * Convierte un detail dto lugar a entidad
     *
     * @return Entidad que se convirtio desde un detail dto lugar.
     */
    @Override
    public LugarEntity toEntity()
    {
        return super.toEntity();
    }
}
