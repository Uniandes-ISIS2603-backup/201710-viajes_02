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
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * PagoDTO
 *
 * @author ja.bermudez10
 */
@XmlRootElement
public class PagoDTO
{

    /**
     * id del pago
     */
    protected Long id;

    /**
     * Valor del Pago
     */
    protected Double valor;
    
    /**
     * Estado del Pago
     */
    protected Boolean cancelado;

    /**
     * Constructor por defecto de PagoDTO
     */
    public PagoDTO()
    {

    }

    /**
     * Constructor de PagoDTO con base en un PagoEntity
     *
     * @param pagoEntity La entidad pago
     */
    public PagoDTO(PagoEntity pagoEntity)
    {
        if (pagoEntity != null)
        {
            this.id = pagoEntity.getId();
            this.valor = pagoEntity.getValor();
            this.cancelado = pagoEntity.getCancelado();
        }
    }

    /**
     * Retorna un PagoEntity basado en el actual PagoDTO
     *
     * @return La entidad Pago
     */
    public PagoEntity toEntity()
    {
        PagoEntity pagoEntity = new PagoEntity();
        pagoEntity.setId(id);
        pagoEntity.setValor(valor);
        pagoEntity.setCancelado(cancelado);

        return pagoEntity;
    }

    /**
     * Retorno de id de PagoDTO
     *
     * @return El id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modificacion de id de PagoDTO
     *
     * @param id El id que remplazará al actual
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Retorno de valor de PagoDTO
     *
     * @return El valor
     */
    public Double getValor()
    {
        return valor;
    }

    /**
     * Modificacion de valor de PagoDTO
     *
     * @param valor El valor que remplazará al actual
     */
    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    /**
     * Retorno de estado del PagoDTO
     *
     * @return El idDestinatario
     */
    public Boolean getCancelado()
    {
        return cancelado;
    }

    /**
     * Modificacion de valor de PagoDTO
     *
     * @param cancelado El estado que remplazará al actual
     */
    public void setCancelado(Boolean cancelado)
    {
        this.cancelado = cancelado;
    }
}
