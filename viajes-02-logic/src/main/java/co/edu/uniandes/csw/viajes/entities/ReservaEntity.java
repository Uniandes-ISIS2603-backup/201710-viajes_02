/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ja.bermudez10
 */
@Entity
public class ReservaEntity implements Serializable {
    
    @PodamExclude
    @ManyToMany
    private List<ViajeroEntity> viajeros = new ArrayList<ViajeroEntity>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    private Long precio;
    private Double valorComision;
    private Integer puestosReservados;
    private Long idViajero;

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Double getValorComision() {
        return valorComision;
    }

    public void setValorComision(Double valorComision) {
        this.valorComision = valorComision;
    }

    public Integer getPuestosReservados() {
        return puestosReservados;
    }

    public void setPuestosReservados(Integer puestosReservados) {
        this.puestosReservados = puestosReservados;
    }

    public Long getIdViajero() {
        return idViajero;
    }

    public void setIdViajero(Long idViajero) {
        this.idViajero = idViajero;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getIdReserva() != null) {
            return this.getIdReserva().equals(((ReservaEntity) obj).getIdReserva());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getIdReserva() != null) {
            return this.getIdReserva().hashCode();
        }
        return super.hashCode();
    }

}
