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
 * PagoEntity
 * @author ja.bermudez10
 */
@Entity
public class PagoEntity implements Serializable {

    /**
     * Generacion de idPago
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    /**
     * Valor del Pago
     */
    private Double valor;

    /**
     * idRemitente del Pago
     */
    private Long idRemitente;

    /**
     * idDestinatrio del Pago
     */
    private Long idDestinatario;

    /**
     * Estado del Pago
     */
    private Boolean cancelado;

    /**
     * Relacion muchos a uno con la entidad Usuario
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    /**
     * Retorna el idPago del Pago
     * @return idPago
     */
    public Long getIdPago() {
        return idPago;
    }

    /**
     * Modificacion del idPago 
     * @param idPago El idPago de la entidad Pago
     */
    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    /**
     * Retorna el valor del Pago
     * @return
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Modificacion del valor
     * @param valor El valor de la entidad Pago
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * Retorna el idRemitente del Pago
     * @return
     */
    public Long getIdRemitente() {
        return idRemitente;
    }

    /**
     * Modificacion del idRemitente
     * @param idRemitente El idRemitente que remplazara el idRemitente actual
     */
    public void setIdRemitente(Long idRemitente) {
        this.idRemitente = idRemitente;
    }

    /**
     * Retorna el idDestinatario del Pago
     * @return el idDestinatario
     */
    public Long getIdDestinatario() {
        return idDestinatario;
    }

    /**
     * Modificacion del idDestinatario
     * @param idDestinatario El idDestinatario que remplazara el idDestinatario actual
     */
    public void setIdDestinatario(Long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    /**
     * Retorna el estado del Pago
     * @return estado del pago
     */
    public Boolean getCancelado() {
        return cancelado;
    }

    /**
     * Modificacion del cancelado
     * @param cancelado El estado que remplazara el estado actual
     */
    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    /**
     * Retorna el usuario por el cual se relacion el Pago
     * @return el usuario
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    /**
     * Modificacion del usuario
     * @param usuario El usuario que remplazara el usuario actual
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna true si un Objeto es igual al Pago actual
     * @param obj objeto a comparar
     * @return true o false
     */
    @Override
    public boolean equals(Object obj) {
        if (this.getIdPago() != null) {
            return this.getIdPago().equals(((PagoEntity) obj).getIdPago());
        }
        return super.equals(obj);
    }

    /**
     * Retorna el hashCode del Pago, por medio del idPago
     * @return hashCode del obj
     */
    @Override
    public int hashCode() {
        if (this.getIdPago() != null) {
            return this.getIdPago().hashCode();
        }
        return super.hashCode();
    }

}
