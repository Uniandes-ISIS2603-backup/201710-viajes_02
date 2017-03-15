/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author d.jaimes
 */
@XmlRootElement
public class AutomovilDetailDTO extends AutomovilDTO
{

    /**
     * Crea un automovil detail dto.
     */
    public AutomovilDetailDTO()
    {
        super();
    }

    /**
     * Crea un automovil detail dto de una entidad.
     *
     * @param entity Entidad de la cual se va a crear un detail dto.
     */
    public AutomovilDetailDTO(AutomovilEntity entity)
    {
        super(entity);
    }

    /**
     * Convierte un Automovil detail dto a entidad.
     *
     * @return Entidad creada desde un detail dto.
     */
    public AutomovilEntity toEntity()
    {
        AutomovilEntity entity = super.toEntity();
        return entity;
    }
}
