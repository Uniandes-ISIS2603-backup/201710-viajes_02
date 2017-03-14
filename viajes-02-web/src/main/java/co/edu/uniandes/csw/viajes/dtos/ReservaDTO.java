/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import java.io.Serializable;

/**
 *
 * @author ja.bermudez10
 */
public class ReservaDTO implements Serializable {

    private Long idReserva;
    private Long precio;
    private Double valorComision;
    private Integer puestosReservados;
    private Long idViajero;

    public ReservaDTO() {

    }

    public ReservaDTO(ReservaEntity reservaEntity) {
        if (reservaEntity != null) {
            this.idReserva = reservaEntity.getIdReserva();
            this.precio = reservaEntity.getPrecio();
            this.valorComision = reservaEntity.getValorComision();
            this.puestosReservados = reservaEntity.getPuestosReservados();
            this.idViajero = reservaEntity.getIdViajero();
        }
    }

    public ReservaEntity toEntity() {
        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setIdReserva(this.idReserva);
        reservaEntity.setPrecio(this.precio);
        reservaEntity.setValorComision(this.valorComision);
        reservaEntity.setPuestosReservados(this.puestosReservados);
        reservaEntity.setIdViajero(this.idViajero);

        return reservaEntity;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long numRegistro) {
        this.idReserva = numRegistro;
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

    public Long getIdViajeroQueReservo() {
        return idViajero;
    }

    public void setIdViajeroQueReservo(Long idViajeroQueReservo) {
        this.idViajero = idViajeroQueReservo;
    }

}
