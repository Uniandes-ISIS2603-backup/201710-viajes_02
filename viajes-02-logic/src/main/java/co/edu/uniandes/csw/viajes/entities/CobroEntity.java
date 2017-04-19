/* 
 * The MIT License
 *
 * Copyright 2017 wr.ravelo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
