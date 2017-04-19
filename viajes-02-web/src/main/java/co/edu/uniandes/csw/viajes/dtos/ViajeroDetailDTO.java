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

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author n.aguilar
 */
@XmlRootElement
public class ViajeroDetailDTO extends ViajeroDTO
{

    /**
     * Reservas de un viajero
     */
    private List<ReservaDTO> reservas;

    /**
     * Constructor vacio
     */
    public ViajeroDetailDTO()
    {
        super();
    }

    /**
     * REtorna las reservas de un viajero
     *
     * @return reservas
     */
    public List<ReservaDTO> getReservas()
    {
        return reservas;
    }

    /**
     * Modifica las reservas de un viajero por le mparametro
     *
     * @param reservas
     */
    public void setReservas(List<ReservaDTO> reservas)
    {
        this.reservas = reservas;
    }

    /**
     * Se genera el DTO a partir de una entidad que llega por parametro
     *
     * @param entity Base
     */
    public ViajeroDetailDTO(ViajeroEntity entity)
    {
        super(entity);
        reservas = new ArrayList<ReservaDTO>();
        if (entity != null)
        {
            for (ReservaEntity r : entity.getReservas())
            {
                reservas.add(new ReservaDTO(r));
            }
        }
    }

    /**
     * Se genera una entidad a partir de la informacion del DTO
     *
     * @return Entidad generada
     */
    @Override
    public ViajeroEntity toEntity()
    {
        ViajeroEntity entity = super.toEntity();

        List<ReservaEntity> reviewsEntity = new ArrayList<>();
        if (reservas != null)
        {
            for (ReservaDTO r : reservas)
            {
                reviewsEntity.add(r.toEntity());
            }
        }

        entity.setReservas(reviewsEntity);

        return entity;
    }

}
