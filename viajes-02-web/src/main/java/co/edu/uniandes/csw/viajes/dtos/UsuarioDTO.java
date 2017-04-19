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

import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author n.aguilar
 */
@XmlRootElement
public class UsuarioDTO implements Serializable
{

    private Long id;

    private String nombre;

    private String genero;

    private Integer telMovil;

    private Integer edad;

    private Double rating;

    private String correo;

    private LugarDTO direccion;

    public LugarDTO getDireccion()
    {
        return direccion;
    }

    public void setDireccion(LugarDTO direccion)
    {
        this.direccion = direccion;
    }

    public UsuarioDTO()
    {

    }

    public UsuarioDTO(UsuarioEntity entity)
    {
        if (entity != null)
        {
            this.correo = entity.getCorreo();
            this.edad = entity.getEdad();
            this.genero = entity.getGenero();
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.rating = entity.getRating();
            this.telMovil = entity.getTelMovil();
            if (entity.getLugar() != null)
            {
                this.direccion = new LugarDTO(entity.getLugar());
            }
        }
    }

    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setCorreo(correo);
        entity.setEdad(edad);
        entity.setGenero(genero);
        entity.setId(id);
        entity.setNombre(nombre);
        entity.setRating(rating);
        entity.setTelMovil(telMovil);
        if (direccion != null)
        {
            entity.setLugar(direccion.toEntity());
        }

        return entity;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getGenero()
    {
        return genero;
    }

    public void setGenero(String genero)
    {
        this.genero = genero;
    }

    public Integer getTelMovil()
    {
        return telMovil;
    }

    public void setTelMovil(Integer telMovil)
    {
        this.telMovil = telMovil;
    }

    public Integer getEdad()
    {
        return edad;
    }

    public void setEdad(Integer edad)
    {
        this.edad = edad;
    }

    public Double getRating()
    {
        return rating;
    }

    public void setRating(Double rating)
    {
        this.rating = rating;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

}
