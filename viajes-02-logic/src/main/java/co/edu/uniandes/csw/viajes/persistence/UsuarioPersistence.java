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

import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author n.aguilar
 */
@Stateless
public class UsuarioPersistence
{

    /*
    * Manejador de persistencia
     */
    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager em;

    /**
     * Retorna el usuario entidad que se identifica con la id que llega por
     * parametro
     *
     * @param id La id de la entidad que se desea encontrar
     * @return La entidad relacionada con la id del parametro
     */
    public UsuarioEntity find(Long id)
    {
        return em.find(UsuarioEntity.class, id);
    }

    /**
     * Retorna toda la lista de usuarios registrados en el sistema
     *
     * @return Lista de usuarios en el sistema
     */
    public List<UsuarioEntity> findAll()
    {
        TypedQuery q = em.createQuery("SELECT u FROM UsuarioEntity u", UsuarioEntity.class);
        return q.getResultList();
    }

    /**
     * Almacena la entidad que llega por parametro en la BD
     *
     * @param entity La entidad que se desea que persista
     * @return La entidad que se guardo en BD
     */
    public UsuarioEntity create(UsuarioEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * Actualiza los valores de una entidad que llega por parametro
     *
     * @param entity La entodad quese desea actualizar
     * @return La entidad ya actualizada
     */
    public UsuarioEntity update(UsuarioEntity entity)
    {
        return em.merge(entity);
    }

    /**
     * Elimina un usuario entidad que este identificado con el codigo que llega
     * por parametro
     *
     * @param id El id de la entidad que se desea eliminar
     */
    public void delete(Long id)
    {
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
    }
}
