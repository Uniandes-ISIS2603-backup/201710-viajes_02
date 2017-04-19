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
