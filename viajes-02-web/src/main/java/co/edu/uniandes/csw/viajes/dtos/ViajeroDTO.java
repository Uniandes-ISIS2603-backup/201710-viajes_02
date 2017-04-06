/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author n.aguilar
 */
@XmlRootElement
public class ViajeroDTO extends UsuarioDTO
{

    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    /**
     * Constructor vacio
     */
    public ViajeroDTO()
    {

    }

    /**
     * Construccion de la case a partir de una entidad
     *
     * @param entity Base de la construccion
     */
    public ViajeroDTO(ViajeroEntity entity)
    {
        if (entity != null)
        {
            setCorreo(entity.getCorreo());
            setEdad(entity.getEdad());
            setGenero(entity.getGenero());
            setId(entity.getId());
            setNombre(entity.getNombre());
            setRating(entity.getRating());
            setTelMovil(entity.getTelMovil());
            setImagen(entity.getImagen());
            if (entity.getLugar() != null)
            {
                setDireccion(new LugarDTO(entity.getLugar()));
            }
        }
    }

    /**
     * Se genera una entidad a partir de los datos del DTO
     *
     * @return entidad generada
     */
    public ViajeroEntity toEntity()
    {
        ViajeroEntity entity = new ViajeroEntity();
        entity.setImagen(getImagen());
        entity.setCorreo(getCorreo());
        entity.setEdad(getEdad());
        entity.setGenero(getGenero());
        entity.setId(getId());
        entity.setNombre(getNombre());
        entity.setRating(getRating());
        entity.setTelMovil(getTelMovil());
        if (getDireccion() != null)
        {
            entity.setLugar(getDireccion().toEntity());
        }

        return entity;
    }
}
