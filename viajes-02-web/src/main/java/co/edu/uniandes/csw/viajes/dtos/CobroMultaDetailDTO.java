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
public class CobroMultaDetailDTO extends CobroMultaDTO
{ // TODO como puedo saber de qué es el cobro al usuario si no hay una relación con el viaje?
   

    /**
     * Crea un cobro multa detail dto
     */
    public CobroMultaDetailDTO()
    {
        super();
    }

    /**
     * Crea un cobro multa detail dto de una entidad.
     *
     * @param entity Entidad de donde se va a crear el detail dto
     */
    public CobroMultaDetailDTO(CobroMultaEntity entity)
    {
        super(entity);
    }

    /**
     * Convirte una entidad a cobro multa detail dto.
     *
     * @return
     */
    @Override
    public CobroMultaEntity toEntity()
    {
        return super.toEntity();
    }
}
