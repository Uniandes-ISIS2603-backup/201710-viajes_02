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

    public ReviewEntity find(Long idCalificado)
    {
        return em.find(ReviewEntity.class, idCalificado);
    }

    /**
     * crea un nuevo review
     *
     * @param idCalificado
     * @return ReviewEntity
     */
    public ReviewEntity create(ReviewEntity idCalificado)
    {
        em.persist(idCalificado);
        return idCalificado;
    }

    /**
     * actualiza el review de un usuario
     *
     * @param idCalificado
     * @return ReviewEntity
     */
    public ReviewEntity update(ReviewEntity idCalificado)
    {
        return em.merge(idCalificado);

    }

    public void delete(Long idCalificado)
    {
        ReviewEntity borrar = em.find(ReviewEntity.class, idCalificado);
        em.remove(borrar);

    }

    /**
     * busca el idDelCalificado
     *
     * @param idCalificado
     * @return ReviewEntity
     */
    public ReviewEntity findByCalificado(Long idCalificado)
    {
        //		TypedQuery<ReviewEntity> q = em.createQuery( "select u from ReviewEntity u where u.idCalificado = :idCalificado", ReviewEntity.class );
        //		q = q.setParameter( "idCalificado", idCalificado );
        //
        //		List<ReviewEntity> sameID = q.getResultList( );
        //		if( sameID.isEmpty( ) )
        //		{
        //			return null;
        //		}
        //		else
        //		{
        //			return sameID.get( 0 );
        //		}
        TypedQuery<ReviewEntity> q = em.createQuery("select u from ReviewEntity u where u.idCalificado = :idCalificado", ReviewEntity.class);
        return q.getResultList().get(0);
    }

    /**
     * busca el idDelCalificador
     *
     * @param idCalificador
     * @return ReviewEntity
     */
    public ReviewEntity findByCalificador(Long idCalificador)
    {
        TypedQuery<ReviewEntity> q = em.createQuery("select u from ReviewEntity u where u.idCalificador = :idCalificador", ReviewEntity.class);
        q = q.setParameter("idCalificador", idCalificador);

        List<ReviewEntity> sameID = q.getResultList();
        if (sameID.isEmpty())
        {
            return null;
        }
        else
        {
            return sameID.get(0);
        }
    }
}
