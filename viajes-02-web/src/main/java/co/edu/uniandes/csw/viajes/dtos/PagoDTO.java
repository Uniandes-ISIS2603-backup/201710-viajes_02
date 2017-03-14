/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;

/**
 *
 * @author ja.bermudez10
 */
public class PagoDTO {

    private Long idPago;
    private Double valor;
    private Long idRemitente;
    private Long idDestinatario;
    private Boolean cancelado;
    
    public PagoDTO() {
        
    }
    
    public PagoDTO(PagoEntity pagoEntity) {
        if(pagoEntity != null) {
            this.idPago = pagoEntity.getIdPago();
            this.valor = pagoEntity.getValor();
            this.idRemitente = pagoEntity.getIdRemitente();
            this.idDestinatario = pagoEntity.getIdDestinatario();
            this.cancelado = pagoEntity.getCancelado();
        }
    }
    
    public PagoEntity toEntity() {
        PagoEntity pagoEntity = new PagoEntity();
        pagoEntity.setIdPago(idPago);
        pagoEntity.setValor(valor);
        pagoEntity.setIdRemitente(idRemitente);
        pagoEntity.setIdDestinatario(idDestinatario);
        pagoEntity.setCancelado(cancelado);
        
        return pagoEntity;
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(Long idRemitente) {
        this.idRemitente = idRemitente;
    }

    public Long getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(Long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }
}
