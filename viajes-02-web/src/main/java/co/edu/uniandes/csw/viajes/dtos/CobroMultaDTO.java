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

import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wr.ravelo
 */
@XmlRootElement
public class CobroMultaDTO extends CobroDTO
{

    /**
     * Crea un cobro multa DTO de acuerdo a la informacion de una entidad cobro
     * multa
     *
     * @param en Entidad cobro multa de donde se crea el DTO
     */
    public CobroMultaDTO(CobroMultaEntity en)
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
     * Constructor vacio.
     */
    public CobroMultaDTO()
    {

    }

    /**
     * Crea una entidad cobro multa de acueurdo a la informacion del DTO
     *
     * @return Entidad creada a partir de la informacion actual.
     */
    @Override
    public CobroMultaEntity toEntity()
    {
        CobroMultaEntity x = new CobroMultaEntity();
        x.setValor(this.getValor());
        x.setCancelado(this.getCancelado());
        x.setId(this.getId());
        return x;
    }
}
