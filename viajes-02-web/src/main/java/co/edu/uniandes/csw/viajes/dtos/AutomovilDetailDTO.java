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
    //TODO: verificar
    private ConductorDTO conductorDTO;

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
        //TODO: Verificar
        this.conductorDTO = new ConductorDTO(entity.getConductorEntity() );
    }


    /**
     * Convierte un Automovil detail dto a entidad.
     *
     * @return Entidad creada desde un detail dto.
     */

    public AutomovilEntity toEntity( )
    {
        AutomovilEntity en = super.toEntity();
        //TODO: verificar
     en.setConductorEntity(conductorDTO.DTO2Entity());
        return en;
    }
//TODO: verificar
    public ConductorDTO getConductorDTO() {
        return conductorDTO;
    }

    public void setConductorDTO(ConductorDTO conductorDTO) {
        this.conductorDTO = conductorDTO;
    }
}
