/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.LugarEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wr.ravelo
 */
@XmlRootElement
public class LugarDTO {
    
    /**
     * Id del lugar
     */
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
     * Constructor por defecto
     */
    public LugarDTO() {
        
    }
    
    /**
     * Constructor que crea a partir de una lugar entity.
     * @param en Lugar entity de la que se va a crear un DTO
     */
    public LugarDTO(LugarEntity en) {
        this.id = en.getId();
        this.lugar = en.getLugar();
        this.direccion = en.getDireccion();
        this.lat = en.getLat();
        this.lon = en.getLon();
    }
    
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
    public void setDireccion(String direcion) {
        this.direccion = direcion;
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
     * Crea una entidad de acuerdo a la infromacion de DTO
     * @return Entidad con la informacion del DTO
     */
    public LugarEntity toEntity() {
        LugarEntity en = new LugarEntity();
        en.setId(this.id);
        en.setLugar(this.lugar);
        en.setDireccion(this.direccion);
        en.setLat(this.lat);
        en.setLon(this.lon);
        return en;
    }
}
