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
public class LugarDTO {
    
    private Long id;
    private String lugar;
    private String direccion;

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
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the dirrecion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param dirrecion the dirrecion to set
     */
    public void setDireccion(String dirrecion) {
        this.direccion = dirrecion;
    }
    
    public LugarEntity toEntity() {
        LugarEntity en = new LugarEntity();
        en.setId(this.id);
        en.setLugar(this.lugar);
        en.setDireccion(this.direccion);
        return en;
    }
}
