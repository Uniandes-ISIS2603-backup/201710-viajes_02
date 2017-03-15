/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ReviewEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Danny
 */
@XmlRootElement
public class ReviewDetailDTO extends ReviewDTO
{

    /**
     * Crea un review detail dto.
     */
    public ReviewDetailDTO()
    {
        super();
    }

    /**
     * Crea un review detail dto de una entidad.
     *
     * @param entity Entidad de la cual se va a crear un detail dto.
     */
    public ReviewDetailDTO(ReviewEntity entity)
    {
        super(entity);
    }

    /**
     * Convierte un review detail dto a entidad..
     *
     * @return Entidad creada desde un detail dto.
     */
    @Override
    public ReviewEntity toEntity()
    {
        ReviewEntity entity = super.toEntity();
        return entity;
    }
}
