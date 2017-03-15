/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Danny
 */
@Entity
public class ReviewEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * dar el id del calificado
     *
     * @return idCalificado
     */
    public Long getIdCalificado() {
        return idCalificado;
    }

    /**
     * dar id del calificador
     *
     * @return idCalificador
     */

    public Long getIdCalificador() {
        return idCalificador;
    }

    /**
     * dar comentario
     *
     * @return coment
     */

    public String getComent() {
        return coment;
    }

    /**
     * dar la calificacion
     *
     * @return calificacion
     */

    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * actualizar el Id del calificador
     *
     * @param idCalificado
     */
    public void setIdCalificado(Long idCalificado) {
        this.idCalificado = idCalificado;
    }

    /**
     * actualizar el id del calificador
     *
     * @param idCalificador
     */

    public void setIdCalificador(Long idCalificador) {
        this.idCalificador = idCalificador;
    }

    /**
     * actualizar el comentario
     *
     * @param coment
     */
    public void setComent(String coment) {
        this.coment = coment;
    }

    /**
     * actualizar la calificacion
     *
     * @param calificacion
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Retrieves the id of the ReviewEntity
     *
     * @return The id of the ReviewEntity
     */
    public Long getId() {
        return id;
    }

    /**
     * Updates the id of the ReviewEntity by the one given by parameter
     *
     * @param id The new id of the ReviewEntity
     */
    public void setId(Long id) {
        this.id = id;
    }
}
