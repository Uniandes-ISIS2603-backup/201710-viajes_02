/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ReviewEntity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Danny
 */
@XmlRootElement
public class ReviewDTO implements Serializable
{

    /**
     * atributo id
     */
    private Long id;
    /**
     * atributo del id de la persona calificada
     */
    private Long idCalificado;
    /**
     * atributo del id de la persona calificando
     */

    private Long idCalificador;
    /**
     * atributo del comentarios
     */

    private String coment;

    /**
     * atributo de al calificacion
     */
    private Integer calificacion;

    /**
     * constructor vacio
     */
    public ReviewDTO()
    {
    }

    /**
     * constructor
     *
     * @param entity
     */
    public ReviewDTO(ReviewEntity entity)
    {
        if (entity != null)
        {
            this.id = entity.getId();
            this.idCalificado = entity.getIdCalificado();
            this.idCalificador = entity.getIdCalificador();
            this.coment = entity.getComent();
            this.calificacion = entity.getCalificacion();
        }

    }

    public ReviewEntity toEntity()
    {
        ReviewEntity reviewA = new ReviewEntity();
        reviewA.setId(this.id);
        reviewA.setCalificacion(this.getCalificacion());
        reviewA.setComent(this.getComent());
        reviewA.setIdCalificado(this.getIdCalificado());
        return reviewA;
    }

    /**
     * Retrieves the id of the ReviewDTO
     *
     * @return The id of the ReviewDTO
     */
    public Long getId()
    {
        return id;
    }

    /**
     * dar el id del calificado
     *
     * @return idCalificado
     */
    public Long getIdCalificado()
    {
        return idCalificado;
    }

    /**
     * dar id del calificador
     *
     * @return idCalificador
     */
    public Long getIdCalificador()
    {
        return idCalificador;
    }

    /**
     * dar comentario
     *
     * @return coment
     */
    public String getComent()
    {
        return coment;
    }

    /**
     * dar la calificacion
     *
     * @return calificacion
     */
    public Integer getCalificacion()
    {
        return calificacion;
    }

    /**
     * actualizar el Id del calificador
     *
     * @param idCalificado
     */
    public void setIdCalificado(Long idCalificado)
    {
        this.idCalificado = idCalificado;
    }

    /**
     * actualizar el id del calificador
     *
     * @param idCalificador
     */
    public void setIdCalificador(Long idCalificador)
    {
        this.idCalificador = idCalificador;
    }

    /**
     * actualizar el comentario
     *
     * @param coment
     */
    public void setComent(String coment)
    {
        this.coment = coment;
    }

    /**
     * actualizar la calificacion
     *
     * @param calificacion
     */
    public void setCalificacion(Integer calificacion)
    {
        this.calificacion = calificacion;
    }

    /**
     * Updates the id of the ReviewDTO by the one given by parameter
     *
     * @param id The new id of the ReviewDTO
     */
    public void setId(Long id)
    {
        this.id = id;
    }

}
