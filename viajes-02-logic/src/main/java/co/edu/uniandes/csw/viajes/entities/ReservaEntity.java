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
    @ManyToOne
    private ViajeroEntity viajero = new ViajeroEntity();
    
    /**
     *
     */
    @ManyToOne
    private ViajeEntity viaje;

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    public ViajeroEntity getViajero() {
        return viajero;
    }

    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }

    /**
     *
     */
    private Long precio;

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
    private Long idViajero;

    /**
     *
     * @return
     */
    public Long getIdReserva() {
        return idReserva;
    }

    /**
     *
     * @param idReserva
     */
    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    /**
     *
     * @return
     */
    public Long getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(Long precio) {
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
     * @return
     */
    public Long getIdViajero() {
        return idViajero;
    }

    /**
     *
     * @param idViajero
     */
    public void setIdViajero(Long idViajero) {
        this.idViajero = idViajero;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this.getIdReserva() != null) {
            return this.getIdReserva().equals(((ReservaEntity) obj).getIdReserva());
        }
        return super.equals(obj);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        if (this.getIdReserva() != null) {
            return this.getIdReserva().hashCode();
        }
        return super.hashCode();
    }

}
