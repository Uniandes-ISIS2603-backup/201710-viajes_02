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
 * @author wr.ravelo
 */
@Entity
public class CobroEntity implements Serializable {
    
    /**
     * Id del cobro
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Valor del cobro
     */
    private Long valor;
    
    /**
     * Atributo que representa si el cobro ya fue cancelado.
     */
    private Boolean cancelado;
    
     /**
     * Usuario al que pertenee el cobro.
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuarioRemitente;
    
    /**
     * Usuario al que se destina el cobro
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuarioDestinatario;
    
    /**
     * Da el id del cobro.
     * @return El id del cobro
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id del cobro
     * @param id El nuevo id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Da el valor del cobro
     * @return El valor del cobro
     */
    public Long getValor() {
        return valor;
    }

    /**
     * Modifica el valor del cobro
     * @param cobro Nuevo valor del cobro
     */
    public void setValor(Long cobro) {
        this.valor = cobro;
    }
    
    /**
     * Mira si el cobro fue cancelado
     * @return True si fue cancelado el cobro, false de lo contrario.
     */
    public Boolean getCancelado() {
        return cancelado;
    }

    /**
     * Modifica el valor de cancelado
     * @param cancelado Nuevo calor de cancelado
     */
    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }
      
    /**
     * Da el usuario remitente
     * @return El usuario remitente del cobro
     */
    public UsuarioEntity getUsuarioRemitente() {
        return usuarioRemitente;
    }

    /**
     * Modifica el usuario remitnte
     * @param usuarioRemitente Nuevo usuario remitente
     */
    public void setUsuarioRemitente(UsuarioEntity usuarioRemitente) {
        this.usuarioRemitente = usuarioRemitente;
    }

    /**
     * Da el usuario destinatario
     * @return Usuario destinatario
     */
    public UsuarioEntity getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    /**
     * Modifica el usuario destinatario
     * @param usuarioDestinatario Nuevo usuario destinatario
     */
    public void setUsuarioDestinatario(UsuarioEntity usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }
    
        /**
     * Verifica si dos objetos cobro son iguales
     * @param other Otro objeto con el que se va a comparar el actual. 
     * @return  True si son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object other) {
        if(this.getId().equals((((CobroEntity) other)).getId()))
            return true;
        
        return false;
    }
    
    /**
     * Retorna un valor unico para identificador del objeto.
     * @return Valor unico que representa el objeto.
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }  
}
