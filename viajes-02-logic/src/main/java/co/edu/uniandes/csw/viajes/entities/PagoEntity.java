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
     * Generacion de id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Valor del Pago
     */
    private Double valor;

    /**
     * Conexion con el usuario remitente
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity remitente;

    /**
     * Conexion con el usuario destinatario
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity destinatario;

    /**
     * Estado del Pago
     */
    private Boolean cancelado;

    /**
     * Retorna el id del Pago
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modificacion del id 
     * @param id El id de la entidad Pago
     */
    public void setId(Long id) {
        this.id = id;
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
     * Retorna el remitente del Pago
     * @return
     */
    public UsuarioEntity getRemitente() {
        return remitente;
    }

    /**
     * Modificacion del remitente
     * @param remitente El remitente que remplazara el remitente actual
     */
    public void setRemitente(UsuarioEntity remitente) {
        this.remitente = remitente;
    }

    /**
     * Retorna el destinatario del Pago
     * @return el destinatario
     */
    public UsuarioEntity getDestinatario() {
        return destinatario;
    }

    /**
     * Modificacion del destinatario
     * @param destinatario El destinatario que remplazara el destinatario actual
     */
    public void setDestinatario(UsuarioEntity destinatario) {
        this.destinatario = destinatario;
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
     * Retorna true si un Objeto es igual al Pago actual
     * @param obj objeto a comparar
     * @return true o false
     */
    @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((PagoEntity) obj).getId());
        }
        return super.equals(obj);
    }

    /**
     * Retorna el hashCode del Pago, por medio del id
     * @return hashCode del obj
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

}
