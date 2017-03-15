/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class UsuarioDetailDTO extends UsuarioDTO {

    private List<CobroDTO> cobros;
    
    private List<PagoDTO> pagos;

    private List<ReviewDTO> reviews;

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public List<CobroDTO> getCobros() {
        return cobros;
    }

    public void setCobros(List<CobroDTO> cobros) {
        this.cobros = cobros;
    }
    
    public List<PagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }

    public UsuarioDetailDTO() {
        super();
    }

    public UsuarioDetailDTO(UsuarioEntity entity) {
        super(entity);
        cobros = new ArrayList<CobroDTO>();
        pagos = new ArrayList<PagoDTO>();
        reviews = new ArrayList<ReviewDTO>();
        
        if (entity != null) {
            for (CobroEntity c : entity.getCobros()) {
                cobros.add(new CobroDTO(c));
            }

            for (PagoEntity p : entity.getPagos()) {
                pagos.add(new PagoDTO(p));
            }

            for (ReviewEntity r : entity.getReviews()) {
                reviews.add(new ReviewDTO(r));
            }            
        }
    }

    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = super.toEntity();

        List<CobroEntity> cobrosEntity = new ArrayList<CobroEntity>();
        if (cobros != null) {
            for (CobroDTO c : cobros) {
                cobrosEntity.add(c.toEntity());
            }
        }

        List<PagoEntity> pagosEntity = new ArrayList<PagoEntity>();
        if (pagos != null) {
            for (PagoDTO p : pagos) {
                pagosEntity.add(p.toEntity());
            }
        }

        List<ReviewEntity> reviewsEntity = new ArrayList<>();
        if (reviews != null) {
            for (ReviewDTO r : reviews) {
                reviewsEntity.add(r.toEntity());
            }
        }
        
        entity.setCobros(cobrosEntity);
        entity.setPagos(pagosEntity);
        entity.setReviews(reviewsEntity);
        return entity;
    }

}
