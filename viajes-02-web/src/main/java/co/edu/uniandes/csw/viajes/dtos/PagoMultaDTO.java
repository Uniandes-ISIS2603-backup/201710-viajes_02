/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.PagoMultaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ja.bermudez10
 */
@XmlRootElement
public class PagoMultaDTO extends PagoDTO {

    public PagoMultaDTO() {
        
    }
    
    /**
     * Constructor de PagoDTO con base en un PagoMultaEntity
     *
     * @param pagoMultaEntity La entidad pago
     */
    public PagoMultaDTO(PagoMultaEntity pagoMultaEntity) {
        if (pagoMultaEntity != null) {
            this.id = pagoMultaEntity.getId();
            this.valor = pagoMultaEntity.getValor();
            this.cancelado = pagoMultaEntity.getCancelado();
        }
    }

    /**
     * Retorna un PagoMultaEntity basado en el actual PagoDTO
     *
     * @return La entidad Pago
     */
    @Override
    public PagoMultaEntity toEntity() {
        PagoMultaEntity pagoMultaEntity = new PagoMultaEntity();
        pagoMultaEntity.setId(id);
        pagoMultaEntity.setValor(valor);
        pagoMultaEntity.setCancelado(cancelado);

        return pagoMultaEntity;
    }

    /**
     * Retorno de id de PagoDTO
     *
     * @return El id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Modificacion de id de PagoDTO
     *
     * @param id El id que remplazará al actual
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorno de valor de PagoDTO
     *
     * @return El valor
     */
    @Override
    public Double getValor() {
        return valor;
    }

    /**
     * Modificacion de valor de PagoDTO
     *
     * @param valor El valor que remplazará al actual
     */
    @Override
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * Retorno de estado del PagoDTO
     *
     * @return El idDestinatario
     */
    @Override
    public Boolean getCancelado() {
        return cancelado;
    }

    /**
     * Modificacion de valor de PagoDTO
     *
     * @param cancelado El estado que remplazará al actual
     */
    @Override
    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }
    
}
