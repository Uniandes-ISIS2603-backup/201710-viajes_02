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

import co.edu.uniandes.csw.viajes.entities.CobroEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wr.ravelo
 */
@XmlRootElement
public class CobroDTO
{

    /**
     * Id del cobro
     */
    protected Long id;

    /**
     * Valor del cobro
     */
    protected Long valor;

    /**
     * Atributo que representa si el cobro ya fue cancelado.
     */
    protected Boolean cancelado;

    /**
     * Constructor que crea un DTO de acuerdo a una entidad.
     *
     * @param en Entidad de la cual se va a crear el DTO
     */
    public CobroDTO(CobroEntity en)
    {
        if (en == null)
        {
            return;
        }

        this.id = en.getId();
        this.valor = en.getValor();
        this.cancelado = en.getCancelado();
    }

    /**
     * Constructor vacio
     */
    public CobroDTO()
    {

    }

    /**
     * Da el id del cobro.
     *
     * @return El id del cobro
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica el id del cobro
     *
     * @param id El nuevo id.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Da el valor del cobro
     *
     * @return El valor del cobro
     */
    public Long getValor()
    {
        return valor;
    }

    /**
     * Modifica el valor del cobro
     *
     * @param cobro Nuevo valor del cobro
     */
    public void setValor(Long cobro)
    {
        this.valor = cobro;
    }

    /**
     * Mira si el cobro fue cancelado
     *
     * @return True si fue cancelado el cobro, false de lo contrario.
     */
    public Boolean getCancelado()
    {
        return cancelado;
    }

    /**
     * Modifica el valor de cancelado
     *
     * @param cancelado Nuevo calor de cancelado
     */
    public void setCancelado(Boolean cancelado)
    {
        this.cancelado = cancelado;
    }

    /**
     * Crea una entidad de acuerdo a la informacion del DTO
     *
     * @return El dto convertido en entity.
     */
    public CobroEntity toEntity()
    {
        CobroEntity x = new CobroEntity();
        x.setValor(this.getValor());
        x.setCancelado(this.getCancelado());
        x.setId(this.getId());
        return x;
    }
}
