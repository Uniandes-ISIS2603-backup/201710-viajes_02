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
    private Long id;
    
    private Double precio;
    
    private Double valorComision;
    
    private Integer puestosReservados;

    public ReservaDTO()
    {

    }

    public ReservaDTO(ReservaEntity reservaEntity)
    {
        if (reservaEntity != null)
        {
            this.id = reservaEntity.getId();
            this.precio = reservaEntity.getPrecio();
            this.valorComision = reservaEntity.getValorComision();
            this.puestosReservados = reservaEntity.getPuestosReservados();
        }
    }

    public ReservaEntity toEntity()
    {
        ReservaEntity reservaEntity = new ReservaEntity();

        reservaEntity.setId(this.id);
        reservaEntity.setPrecio(this.precio);
        reservaEntity.setValorComision(this.valorComision);
        reservaEntity.setPuestosReservados(this.puestosReservados);

        return reservaEntity;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long numRegistro)
    {
        this.id = numRegistro;
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

}
