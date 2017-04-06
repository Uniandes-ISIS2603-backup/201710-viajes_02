/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * PagoDTO
 *
 * @author ja.bermudez10
 */
@XmlRootElement
public class PagoDTO
{

    /**
     * id del pago
     */
    private Long id;

    /**
     * Valor del Pago
     */
    private Double valor;
    
    /**
     * Estado del Pago
     */
    private Boolean cancelado;

    /**
     * Constructor por defecto de PagoDTO
     */
    public PagoDTO()
    {

    }

    /**
     * Constructor de PagoDTO con base en un PagoEntity
     *
     * @param pagoEntity La entidad pago
     */
    public PagoDTO(PagoEntity pagoEntity)
    {
        if (pagoEntity != null)
        {
            this.id = pagoEntity.getId();
            this.valor = pagoEntity.getValor();
            this.cancelado = pagoEntity.getCancelado();
        }
    }

    /**
     * Retorna un PagoEntity basado en el actual PagoDTO
     *
     * @return La entidad Pago
     */
    public PagoEntity toEntity()
    {
        PagoEntity pagoEntity = new PagoEntity();
        pagoEntity.setId(id);
        pagoEntity.setValor(valor);
        pagoEntity.setCancelado(cancelado);

        return pagoEntity;
    }

    /**
     * Retorno de id de PagoDTO
     *
     * @return El id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modificacion de id de PagoDTO
     *
     * @param id El id que remplazará al actual
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Retorno de valor de PagoDTO
     *
     * @return El valor
     */
    public Double getValor()
    {
        return valor;
    }

    /**
     * Modificacion de valor de PagoDTO
     *
     * @param valor El valor que remplazará al actual
     */
    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    /**
     * Retorno de estado del PagoDTO
     *
     * @return El idDestinatario
     */
    public Boolean getCancelado()
    {
        return cancelado;
    }

    /**
     * Modificacion de valor de PagoDTO
     *
     * @param cancelado El estado que remplazará al actual
     */
    public void setCancelado(Boolean cancelado)
    {
        this.cancelado = cancelado;
    }
}
