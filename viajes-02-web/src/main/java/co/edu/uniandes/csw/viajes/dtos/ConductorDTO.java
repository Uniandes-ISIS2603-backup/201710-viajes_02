/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jm.dominguez
 */
@XmlRootElement
public class ConductorDTO implements Serializable
{

    /**
     * Identificado del Conductor
     */
    private Long id;
    /**
     * Nombre del conductor.
     */
    private String nombre;
    /**
     * Genero del conductor
     */
    private String genero;
    /**
     * Telefono del conductor
     */
    private Integer telMovil;
    /**
     * Edad del conductor
     */
    private Integer edad;
    /**
     * Rating del conductor
     */
    private Double rating;
    /**
     * Correo del conductor.
     */
    private String correo;
    /**
     * Imagen del conductor
     */
    private String imagen;

    /**
     * Retorna el id del conductor.
     *
     * @return Retorna el id del conductor
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Asigna un id al conductor.
     *
     * @param id: identificador del conductor.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * retorna el nombre del conductor.
     *
     * @return
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Asigna un nombre al conductor
     *
     * @param nombre: Nombre del conductor.
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Asigna Retorna el genero del conductor.
     *
     * @return genero del conductor.
     */
    public String getGenero()
    {
        return genero;
    }

    /**
     * Asigna un genero a el conductor
     *
     * @param genero del conductor
     */
    public void setGenero(String genero)
    {
        this.genero = genero;
    }

    /**
     * Retorna el telefono del conductor
     *
     * @return Telefono del conductor.
     */
    public Integer getTelMovil()
    {
        return telMovil;
    }

    /**
     * Asigna un telefono al conductor
     *
     * @param telMovil: Telefono del conductor
     */
    public void setTelMovil(Integer telMovil)
    {
        this.telMovil = telMovil;
    }

    /**
     * Retorna la edad del conductor.
     *
     * @return
     */
    public Integer getEdad()
    {
        return edad;
    }

    /**
     * Asigna una edad al conductor
     *
     * @param edad: Edad del conductor.
     */
    public void setEdad(Integer edad)
    {
        this.edad = edad;
    }

    /**
     * Retorna el rating del conductor
     *
     * @return rating del conductor
     */
    public Double getRating()
    {
        return rating;
    }

    /**
     * Asigna un rating al conductor
     *
     * @param rating del conductor
     */
    public void setRating(Double rating)
    {
        this.rating = rating;
    }

    /**
     * Retorna el correo del conductor
     *
     * @return Correo del conductor
     */
    public String getCorreo()
    {
        return correo;
    }

    /**
     * Asigna un correo al conductor
     *
     * @param correo del conductor
     */
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }
    /**
     * Retorna la url de la imagen del conductor
     */
    public String getImagen(){
        return this.imagen;
    }
    /**
     * Asigna una imagen al conductor.
     */
    public void setImagen(String imagen){
        this.imagen = imagen;
    }

    /**
     * Constructor Vacío
     */
    public ConductorDTO()
    {

    }

    /**
     * Constructor para pasar de entity a DTO
     *
     * @param entity Representación del conductor en formato de entidad
     */
    public ConductorDTO(ConductorEntity entity)
    {
        if (entity != null)
        {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.genero = entity.getGenero();
            this.telMovil = entity.getTelMovil();
            this.edad = entity.getEdad();
            this.rating = entity.getRating();
            this.correo = entity.getCorreo();
            this.imagen = entity.getImagen();
        }
    }

    /**
     * Permite pasa de DTO a Entity
     *
     * @return Entidad de condcutor.
     */
    public ConductorEntity DTO2Entity()
    {
        ConductorEntity respuesta = new ConductorEntity();
        respuesta.setId(id);
        respuesta.setNombre(nombre);
        respuesta.setGenero(genero);
        respuesta.setTelMovil(telMovil);
        respuesta.setEdad(edad);
        respuesta.setRating(rating);
        respuesta.setCorreo(correo);
        respuesta.setImagen(imagen);

        return respuesta;
    }

}
