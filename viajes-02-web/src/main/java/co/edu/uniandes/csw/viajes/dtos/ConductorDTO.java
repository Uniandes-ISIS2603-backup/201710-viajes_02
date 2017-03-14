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
public class ConductorDTO implements Serializable{
    
    private Long id;
    
    private String nombre;
    
    private String genero;
    
    private Integer telMovil;
    
    private Integer edad;
    
    private Double rating;
    
    private String correo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(Integer telMovil) {
        this.telMovil = telMovil;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public ConductorDTO(){
        
    }
    
    public ConductorDTO(ConductorEntity entity){
        if(entity != null){
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.genero = entity.getGenero();
            this.telMovil = entity.getTelMovil();
            this.edad = entity.getEdad();
            this.rating = entity.getRating();
            this.correo = entity.getCorreo();
        }
    }
    
    public ConductorEntity DTO2Entity(){
        ConductorEntity respuesta = new ConductorEntity();
        respuesta.setId(id);
        respuesta.setNombre(nombre);
        respuesta.setGenero(genero);
        respuesta.setTelMovil(telMovil);
        respuesta.setEdad(edad);
        respuesta.setRating(rating);
        respuesta.setCorreo(correo);
        
        return respuesta;
    }
    
}
