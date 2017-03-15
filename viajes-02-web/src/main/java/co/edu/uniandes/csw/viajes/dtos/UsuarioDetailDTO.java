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

    /**
     * Cobros relacionados con el usuario
     */
    private List<CobroDTO> cobros;
    /**
     * Pagos relacionados con el usuario
     */
    private List<PagoDTO> pagos;

    /**
     * Se retornan los pagos relaciondos con el usuario
     *
     * @return pagos
     */
    public List<PagoDTO> getPagos() {
        return pagos;
    }

    /**
     * Se modifican los pagos relacionas con el usuario por los que llegan por
     * parametro
     *
     * @param pagos Nuevos pagos a asignar
     */
    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }
    /**
     * Reviews sobre el ususario
     */
    private List<ReviewDTO> reviews;

    /**
     * Se obtienen los reviews relacionados con elusuario
     *
     * @return lista de revies
     */
    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    /**
     * Se modifican los reviews por el parametro
     *
     * @param reviews La nueva lista de revies
     */
    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    /**
     * Se retornan los cobros del usuario
     *
     * @return cobros
     */
    public List<CobroDTO> getCobros() {
        return cobros;
    }

    /**
     * Se modifican los cobros por el parametro
     *
     * @param cobros Los nuevos cobros
     */
    public void setCobros(List<CobroDTO> cobros) {
        this.cobros = cobros;
    }

    /**
     * Constructor vacio
     */
    public UsuarioDetailDTO() {
        super();
    }

    /**
     * Constructor de la clase en base a una entidad
     *
     * @param entity Base
     */
    public UsuarioDetailDTO(UsuarioEntity entity) {
        super(entity);
        cobros = new ArrayList<CobroDTO>();
        reviews = new ArrayList<ReviewDTO>();
        pagos = new ArrayList<PagoDTO>();
        if (entity != null) {
            for (CobroEntity c : entity.getCobros()) {
                cobros.add(new CobroDTO(c));
            }

            for (ReviewEntity r : entity.getReviews()) {
                reviews.add(new ReviewDTO(r));
            }

            for (PagoEntity p : entity.getPagos()) {
                pagos.add(new PagoDTO(p));
            }
        }
    }

    /**
     * Se construye una entidad en base a la informacion del DTO
     *
     * @return la entidad generada
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = super.toEntity();
        List<CobroEntity> cobrosEntity = new ArrayList<CobroEntity>();
        if (cobros != null) {
            for (CobroDTO c : cobros) {
                cobrosEntity.add(c.toEntity());
            }
        }

        List<ReviewEntity> reviewsEntity = new ArrayList<>();
        if (reviews != null) {
            for (ReviewDTO r : reviews) {
                reviewsEntity.add(r.toEntity());
            }
        }

        List<PagoEntity> pagosEntity = new ArrayList<>();
        if (pagos != null) {
            for (PagoDTO r : pagos) {
                pagosEntity.add(r.toEntity());
            }
        }

        entity.setPagos(pagosEntity);
        entity.setCobros(cobrosEntity);
        entity.setReviews(reviewsEntity);
        return entity;
    }
}
