/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wr.ravelo
 */
@XmlRootElement
public class CobroDetailDTO extends CobroDTO
{

    /**
     * Crea un cobro detail dto.
     */
    public CobroDetailDTO()
    {
        super();
    }

    /**
     * Crea un cobro detail dto de una entidad.
     *
     * @param entity Entidad de la cual se va a crear un detail dto.
     */
    public CobroDetailDTO(CobroEntity entity)
    {
        super(entity);
    }

    /**
     * Convierte un cobro detail dto a entidad..
     *
     * @return Entidad creada desde un detail dto.
     */
    @Override
    public CobroEntity toEntity()
    {
        return super.toEntity();
    }
}
