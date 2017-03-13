/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jm.dominguez
 */

@Entity
public class ConductorEntity extends UsuarioEntity{
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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

    @Override
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
    
    @Override
    public boolean equals(Object obj){
        ConductorEntity conductor = (ConductorEntity) obj;
        
        return this.getId().equals(conductor.getId());
    }
    
    @Override
    public int hashCode(){
        if(this.getId() != null){
            return this.getId().hashCode();
        }
        
        return super.hashCode();
    }
    
}
