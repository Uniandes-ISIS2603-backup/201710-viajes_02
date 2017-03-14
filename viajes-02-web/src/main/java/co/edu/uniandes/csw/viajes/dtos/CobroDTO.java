/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;

/**
 *
 * @author wr.ravelo
 */
public class CobroDTO {
      
    private Long id;
    
    private Long valor;
    
    private Long idRemitente;
    
    private Long idDestinatario;
    
    private Boolean cancelado;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the cobro
     */
    public Long getValor() {
        return valor;
    }

    /**
     * @param cobro the cobro to set
     */
    public void setValor(Long cobro) {
        this.valor = cobro;
    }

    /**
     * @return the idRemitente
     */
    public Long getIdRemitente() {
        return idRemitente;
    }

    /**
     * @param idRemitente the idRemitente to set
     */
    public void setIdRemitente(Long idRemitente) {
        this.idRemitente = idRemitente;
    }

    /**
     * @return the idDestinatario
     */
    public Long getIdDestinatario() {
        return idDestinatario;
    }

    /**
     * @param idDestinatario the idDestinatario to set
     */
    public void setIdDestinatario(Long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    /**
     * @return the candelado
     */
    public Boolean getCancelado() {
        return cancelado;
    }

    /**
     * @param candelado the candelado to set
     */
    public void setCancelado(Boolean candelado) {
        this.cancelado = candelado;
    }
    
    public CobroEntity toEntity() {
        CobroEntity x = new CobroEntity();
        x.setValor(this.getValor());
        x.setCancelado(this.getCancelado());
        x.setId(this.getId());
        x.setIdDestinatario(this.getIdDestinatario());
        x.setIdRemitente(this.getIdRemitente());
        return x;
    }
    
}
