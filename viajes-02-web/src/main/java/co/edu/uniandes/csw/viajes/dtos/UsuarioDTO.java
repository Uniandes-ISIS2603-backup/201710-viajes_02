/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class UsuarioDTO implements Serializable {

    /**
     * ID del ususari
     */
    private Long id;
    /**
     * Nombre del usuario
     */
    private String nombre;
    /**
     * genero del usuario
     */
    private String genero;
    /**
     * Telefono movil del usuario
     */
    private Integer telMovil;
    /**
     * Edad del usuario
     */
    private Integer edad;
    /**
     * Rating del usuario
     */
    private Double rating;
    /**
     * Correo del usuario
     */
    private String correo;
    /**
     * Direccion del usuario
     */
    private LugarDTO direccion;

    /**
     * Retorna la direccion del usuario
     *
     * @return direccion
     */
    public LugarDTO getDireccion() {
        return direccion;
    }

    /**
     * Modifica la direccion del usuario por el parametro
     *
     * @param direccion
     */
    public void setDireccion(LugarDTO direccion) {
        this.direccion = direccion;
    }

    /**
     * Constructor vacio
     */
    public UsuarioDTO() {

    }

    /**
     * Constructo en base a una entity
     *
     * @param entity de la que se obtienen valores
     */
    public UsuarioDTO(UsuarioEntity entity) {
        if (entity != null) {
            this.correo = entity.getCorreo();
            this.edad = entity.getEdad();
            this.genero = entity.getGenero();
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.rating = entity.getRating();
            this.telMovil = entity.getTelMovil();
            if (entity.getLugar() != null) {
                this.direccion = new LugarDTO(entity.getLugar());
            }
        }
    }

    /**
     * Se genera unaentidad p a partir de los valor de la clase
     *
     * @return La entidad que se genera
     */
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setCorreo(correo);
        entity.setEdad(edad);
        entity.setGenero(genero);
        entity.setId(id);
        entity.setNombre(nombre);
        entity.setRating(rating);
        entity.setTelMovil(telMovil);
        if (direccion != null) {
            entity.setLugar(direccion.toEntity());
        }

        return entity;
    }

    /**
     * Retorna la iddel usuario
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id del usuario por el parametro
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el nombredel usuario
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del usuario por el parametro
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el correo del usuario
     *
     * @return correo
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Modifica el genero del usuario por el parametro
     *
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Retorna el telefono movil del usuario
     *
     * @return telMovil
     */
    public Integer getTelMovil() {
        return telMovil;
    }

    /**
     * Modifica el telefono movil del usuario por el parametro
     *
     * @param telMovil
     */
    public void setTelMovil(Integer telMovil) {
        this.telMovil = telMovil;
    }

    /**
     * Retorna la edad del usuario
     *
     * @return edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * Modifica la edad del usuario por el parametro
     *
     * @param edad
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * Retorna el rating del usuario
     *
     * @return rating
     */
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * Retorna el correo del usuario
     *
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Se modifica el corre por el parametro
     *
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
