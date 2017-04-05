/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ja.bermudez10
 */
@XmlRootElement
public class ReservaDTO implements Serializable
{
    private Long idReserva;
    private Double precio;
    private Double valorComision;
    private Integer puestosReservados;
    private Long idViajero;

    public ReservaDTO()
    {

    }

    public ReservaDTO(ReservaEntity reservaEntity)
    {
        if (reservaEntity != null)
        {
            this.idReserva = reservaEntity.getId();
            this.precio = reservaEntity.getPrecio();
            this.valorComision = reservaEntity.getValorComision();
            this.puestosReservados = reservaEntity.getPuestosReservados();
            this.idViajero = reservaEntity.getIdViajero();
        }
    }

    public ReservaEntity toEntity()
    {
        ReservaEntity reservaEntity = new ReservaEntity();

        reservaEntity.setId(this.idReserva);
        reservaEntity.setPrecio(this.precio);
        reservaEntity.setValorComision(this.valorComision);
        reservaEntity.setPuestosReservados(this.puestosReservados);
        reservaEntity.setIdViajero(this.idViajero);

        return reservaEntity;
    }

    public Long getIdReserva()
    {
        return idReserva;
    }

    public void setIdReserva(Long numRegistro)
    {
        this.idReserva = numRegistro;
    }

    public Double getPrecio()
    {
        return precio;
    }

    public void setPrecio(Double precio)
    {
        this.precio = precio;
    }

    public Double getValorComision()
    {
        return valorComision;
    }

    public void setValorComision(Double valorComision)
    {
        this.valorComision = valorComision;
    }

    public Integer getPuestosReservados()
    {
        return puestosReservados;
    }

    public void setPuestosReservados(Integer puestosReservados)
    {
        this.puestosReservados = puestosReservados;
    }

    public Long getIdViajero()
    {
        return idViajero;
    }

    public void setIdViajero(Long idViajero)
    {
        this.idViajero = idViajero;
    }

}
