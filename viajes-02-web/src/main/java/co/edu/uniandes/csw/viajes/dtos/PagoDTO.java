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
     * Id de PagoDTO
     */
    private Long idPago;

    /**
     * Valor de PagoDTO
     */
    private Double valor;

    /**
     * idRemitente de PagoDTO
     */
    private Long idRemitente;

    /**
     * idDestinatario de PagoDTO
     */
    private Long idDestinatario;

    /**
     * estado del PagoDTO
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
            this.idPago = pagoEntity.getIdPago();
            this.valor = pagoEntity.getValor();
            this.idRemitente = pagoEntity.getIdRemitente();
            this.idDestinatario = pagoEntity.getIdDestinatario();
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
        pagoEntity.setIdPago(idPago);
        pagoEntity.setValor(valor);
        pagoEntity.setIdRemitente(idRemitente);
        pagoEntity.setIdDestinatario(idDestinatario);
        pagoEntity.setCancelado(cancelado);

        return pagoEntity;
    }

    /**
     * Retorno de idPago de PagoDTO
     *
     * @return El idPago
     */
    public Long getIdPago()
    {
        return idPago;
    }

    /**
     * Modificacion de idPago de PagoDTO
     *
     * @param idPago El idPago que remplazará al actual
     */
    public void setIdPago(Long idPago)
    {
        this.idPago = idPago;
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
     * Retorno de idRemitente del PagoDTO
     *
     * @return El idRemitente
     */
    public Long getIdRemitente()
    {
        return idRemitente;
    }

    /**
     * Modificacion de valor de PagoDTO
     *
     * @param idRemitente El idRemitente que remplazará al actual
     */
    public void setIdRemitente(Long idRemitente)
    {
        this.idRemitente = idRemitente;
    }

    /**
     * Retorno de idDestinatario del PagoDTO
     *
     * @return El idDestinatario
     */
    public Long getIdDestinatario()
    {
        return idDestinatario;
    }

    /**
     * Modificacion de valor de PagoDTO
     *
     * @param idDestinatario El idDestinatario que remplazará al actual
     */
    public void setIdDestinatario(Long idDestinatario)
    {
        this.idDestinatario = idDestinatario;
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
