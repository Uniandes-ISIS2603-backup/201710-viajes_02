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
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ReviewEntity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Danny
 */
@Stateless
public class ReviewPersistence
{

    /**
     * base de datos
     */
    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager em;

    /**
     * encontrar todas las reviews
     *
     * @return List de ReviewEntity
     */
    public List<ReviewEntity> findAll()
    {
        Query q = em.createQuery("select u from ReviewEntity u");
        return q.getResultList();
    }

    /**
     * encuentra el review de un usuario en particular
     *
     * @param idCalificado
     * @return ReviewEntity
     */

    public List<ReviewEntity> findReviewsUsuario(Long idCalificado)
    {
        Query q = em.createQuery("select u from ReviewEntity u where u.idCalificado = :pollo");
        q.setParameter("pollo",idCalificado);
        return q.getResultList();
    }


    public ReviewEntity findReviewUsuario(Long idCalificado, Long review)
    {
        TypedQuery<ReviewEntity> q = em.createQuery("select u from ReviewEntity u where u.idCalificado = :pollo and u.id=:pollito", ReviewEntity.class);
        q.setParameter("pollo",idCalificado);
        q.setParameter("pollito",review);
        return q.getSingleResult();
    }
    /**
     * crea un nuevo review
     *
     * @param review
     * @return ReviewEntity
     */
    public ReviewEntity create(ReviewEntity review)
    {
        em.persist(review);
        return review;
    }

    /**
     * actualiza el review de un usuario
     *
     * @param review
     * @return ReviewEntity
     */

    //TODO: verificar  review
    public ReviewEntity update(ReviewEntity review)
    {
        return em.merge(review);

    }
 //TODO: verificar que busque por el id del review
    public void delete(Long idReview)
    {
        ReviewEntity borrar = em.find(ReviewEntity.class,  idReview);
        em.remove(borrar);

    }

   }
