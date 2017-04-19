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

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;

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
public class AutomovilPersistence
{

    /**
     * base de datos
     */
    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager em;

    /**
     * encontrar todos los automoviles
     *
     * @return List de AutomovilEntity
     */
    public List<AutomovilEntity> findAll()
    {
        Query q = em.createQuery("SELECT A FROM AutomovilEntity A");
        return q.getResultList();
    }

    /**
     * encuentra un carro por una placa
     *
     * @param placa
     * @return AutomovilEntity
     */
    public AutomovilEntity find(String placa)
    {
        return em.find(AutomovilEntity.class, placa);
    }

    /**
     * crea un carro
     *
     * @param carro
     * @return AutomovilEntity carro nuevo
     */
    public AutomovilEntity create(AutomovilEntity carro)
    {
        em.persist(carro);
        return carro;
    }

    /**
     * actualiza un carro
     *
     * @param auto
     * @return AutomovilEntity
     */

    public AutomovilEntity update(AutomovilEntity auto)
    {
        return em.merge(auto);

    }

    /**
     * borra un auto con una placa especifica
     *
     * @param placa
     */
    public void delete(String placa)
    {
        AutomovilEntity borrar = em.find(AutomovilEntity.class, placa);
        em.remove(borrar);

    }

    /**
     * busca una placa en el sistema y devuelve un booleano para saber si ya
     * existe o no en el sistema.
     *
     * @param placa
     * @return AutomovilEntity
     */

    public AutomovilEntity findByPlaca(String placa)
    {
        TypedQuery<AutomovilEntity> q = em.createQuery("select u from AutomovilEntity u where u.placa = :placa", AutomovilEntity.class);
        q = q.setParameter("placa", placa);

        List<AutomovilEntity> samePlaca = q.getResultList();
        if (samePlaca.isEmpty())
        {
            return null;
        }
        else
        {
            return samePlaca.get(0);
        }
    }
}
