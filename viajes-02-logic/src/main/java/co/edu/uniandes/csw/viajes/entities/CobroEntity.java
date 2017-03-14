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
import javax.persistence.MappedSuperclass;

/**
 *
 * @author wr.ravelo
 */
@Entity
public class CobroEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    
    @Override
    public boolean equals(Object other) {
        if(this.getId().equals((((CobroEntity) other)).getId()))
            return true;
        
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }      
}
