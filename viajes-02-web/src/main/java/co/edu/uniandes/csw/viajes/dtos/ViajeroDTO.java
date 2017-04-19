/* 
 * The MIT License
 *
 * Copyright 2017 wr.ravelo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
