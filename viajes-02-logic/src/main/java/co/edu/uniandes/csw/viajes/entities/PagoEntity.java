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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ja.bermudez10
 */
@Entity
public class PagoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;
    private Double valor;
    private Long idRemitente;
    private Long idDestinatario;
    private Boolean cancelado;
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

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

    @Override
    public boolean equals(Object obj) {
        if (this.getIdPago() != null) {
            return this.getIdPago().equals(((PagoEntity) obj).getIdPago());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getIdPago() != null) {
            return this.getIdPago().hashCode();
        }
        return super.hashCode();
    }
}
