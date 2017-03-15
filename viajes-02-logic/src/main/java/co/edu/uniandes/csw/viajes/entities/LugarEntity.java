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
import javax.persistence.ManyToOne;



/**
 *
 * @author wr.ravelo
 */
@Entity
public class LugarEntity implements Serializable {

    /**
     * Id del lugar
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre del lugar
     */
    private String lugar;
    
    /**
     * Direccion del lugar
     */
    private String direccion;
    
    /**
     * Latitud del lugar
     */
    private Double lat;
    
    /**
     * Longitud del lugar
     */
    private Double lon;
    
    /**
     * Da el id del lugar
     * @return Id del lugar
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el valor del id.
     * @param id Nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Da el nombre del lugar
     * @return Nombre del lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Modifica el nombre del lugar
     * @param lugar Nuevo nombre del lugar
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Da la direccion del lugar
     * @return Direccion del lugar
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica la direccion del lugar
     * @param direccion Nuevo valor de la direccion 
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Da la latitud del lugar
     * @return Latitud del lugar
     */
    public Double getLat() {
        return this.lat;
    }
    
    /**
     * Da la longitud del lugar
     * @return Longitud del lugar
     */
    public Double getLon() {
        return this.lon;
    }
    
    /**
     * Modifica la latitud del lugar
     * @param lat Nuevo valor de la latitud
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    /**
     * Modifica la longitud del lugar
     * @param lon Nuevo valor de la longitud
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }
    
    /**
     * Compara dos objetos para verificar su igualdad
     * @param other Objeto con el que se va a comparar el actual.
     * @return True si son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object other) {
        return this.id.equals(((LugarEntity) other).getId());
    }
    
    /**
     * Da un valor unico con el que se identifica el objeto.
     * @return Valor unico con el que se identifica el objeto.
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}