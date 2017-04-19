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
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.entities.ReviewEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author n.aguilar
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO
{

    private List<CobroDTO> cobros;

    private List<PagoDTO> pagos;

    private List<ReviewDTO> reviews;

    public List<ReviewDTO> getReviews()
    {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews)
    {
        this.reviews = reviews;
    }

    public List<CobroDTO> getCobros()
    {
        return cobros;
    }

    public void setCobros(List<CobroDTO> cobros)
    {
        this.cobros = cobros;
    }

    public List<PagoDTO> getPagos()
    {
        return pagos;
    }

    public void setPagos(List<PagoDTO> pagos)
    {
        this.pagos = pagos;
    }

    public UsuarioDetailDTO()
    {
        super();
    }

    public UsuarioDetailDTO(UsuarioEntity entity)
    {
        super(entity);
        cobros = new ArrayList<CobroDTO>();
        pagos = new ArrayList<PagoDTO>();
        reviews = new ArrayList<ReviewDTO>();

        if (entity != null)
        {
            for (CobroEntity c : entity.getCobros())
            {
                cobros.add(new CobroDTO(c));
            }

            for (PagoEntity p : entity.getPagos())
            {
                pagos.add(new PagoDTO(p));
            }

            for (ReviewEntity r : entity.getReviews())
            {
                reviews.add(new ReviewDTO(r));
            }
        }
    }

    @Override
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = super.toEntity();

        List<CobroEntity> cobrosEntity = new ArrayList<CobroEntity>();
        if (cobros != null)
        {
            for (CobroDTO c : cobros)
            {
                cobrosEntity.add(c.toEntity());
            }
        }

        List<PagoEntity> pagosEntity = new ArrayList<PagoEntity>();
        if (pagos != null)
        {
            for (PagoDTO p : pagos)
            {
                pagosEntity.add(p.toEntity());
            }
        }

        List<ReviewEntity> reviewsEntity = new ArrayList<>();
        if (reviews != null)
        {
            for (ReviewDTO r : reviews)
            {
                reviewsEntity.add(r.toEntity());
            }
        }

        entity.setCobros(cobrosEntity);
        entity.setPagos(pagosEntity);
        entity.setReviews(reviewsEntity);
        return entity;
    }

}
