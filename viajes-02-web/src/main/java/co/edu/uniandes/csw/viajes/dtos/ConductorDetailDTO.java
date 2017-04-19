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

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.ReviewEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jm.dominguez
 */
@XmlRootElement
public class ConductorDetailDTO extends ConductorDTO
{

    private List<AutomovilDetailDTO> automoviles;

    private List<ViajeDTO> viajes;
    
    private List<ReviewDTO> reviews;

    public List<AutomovilDetailDTO> getAutomoviles()
    {
        return automoviles;
    }

    public void setAutomoviles(List<AutomovilDetailDTO> automoviles)
    {
        this.automoviles = automoviles;
    }

    public ConductorDetailDTO()
    {
        super();
    }

    public ConductorDetailDTO(ConductorEntity entity)
    {
        super(entity);
        automoviles = listEntity2DTO(entity.getAutomoviles());
        viajes = listEntity2DTO2(entity.getViajes());
        reviews = listEntity2DTO3(entity.getReviews());
    }

    public List<ViajeDTO> getViajes()
    {
        return viajes;
    }

    public void setViajes(List<ViajeDTO> viajes)
    {
        this.viajes = viajes;
    }
    
    public List<ReviewDTO> getReviews()
    {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews)
    {
        this.reviews = reviews;
    }

    @Override
    public ConductorEntity DTO2Entity()
    {
        ConductorEntity entity = super.DTO2Entity();
        return entity;
    }

    private List<AutomovilDetailDTO> listEntity2DTO(List<AutomovilEntity> entityList)
    {
        List<AutomovilDetailDTO> list = new ArrayList<>();
        for (AutomovilEntity entity : entityList)
        {
            list.add(new AutomovilDetailDTO(entity));
        }
        return list;
    }

    public List<ViajeDTO> listEntity2DTO2(List<ViajeEntity> entityList)
    {
        List<ViajeDTO> respuesta = new ArrayList();
        for (int i = 0; i < entityList.size(); i++)
        {
            ViajeEntity v = entityList.get(i);
            ViajeDetailDTO v1 = new ViajeDetailDTO(v);
            respuesta.add(v1);
        }
        return respuesta;
    }
    public List<ReviewDTO> listEntity2DTO3(List<ReviewEntity> entityList)
    {
        List<ReviewDTO> respuesta = new ArrayList();
        for (int i = 0; i < entityList.size(); i++)
        {
            ReviewEntity r = entityList.get(i);
            ReviewDetailDTO v1 = new ReviewDetailDTO(r);
            respuesta.add(v1);
        }
        return respuesta;
    }
}
