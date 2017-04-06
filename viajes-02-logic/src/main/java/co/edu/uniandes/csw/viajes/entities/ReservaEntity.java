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
 * ReservaEntity
 * @author ja.bermudez10
 */
@Entity
public class ReservaEntity implements Serializable {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     *
     */
    private Double precio;

    /**
     *
     */
    private Double valorComision;

    /**
     *
     */
    private Integer puestosReservados;
    
    /**
     *
     */
    @ManyToOne
    private ViajeroEntity viajero;
    
    /**
     *
     */
    @ManyToOne
    private ViajeEntity viaje;
    
    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     *
     * @return
     */
    public Double getValorComision() {
        return valorComision;
    }

    /**
     *
     * @param valorComision
     */
    public void setValorComision(Double valorComision) {
        this.valorComision = valorComision;
    }

    /**
     *
     * @return
     */
    public Integer getPuestosReservados() {
        return puestosReservados;
    }

    /**
     *
     * @param puestosReservados
     */
    public void setPuestosReservados(Integer puestosReservados) {
        this.puestosReservados = puestosReservados;
    }
    
    /**
     * 
     * @return 
     */
    public ViajeroEntity getViajero() {
        return viajero;
    }

    /**
     * 
     * @param viajero 
     */
    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }
    
    /**
     *
     * @return
     */
    public ViajeEntity getViaje() {
        return viaje;
    }

    /**
     *
     * @param viaje
     */
    public void setViaje(ViajeEntity viaje) {
        this.viaje = viaje;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((ReservaEntity) obj).getId());
        }
        return super.equals(obj);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

}
