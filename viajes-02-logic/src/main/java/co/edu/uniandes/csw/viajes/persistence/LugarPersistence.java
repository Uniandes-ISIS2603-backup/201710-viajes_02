/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.LugarEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author wr.ravelo
 */
@Stateless
public class LugarPersistence
{

    /**
     * Manejador de persistencia
     */
    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager em;

    /**
     * Crea una nueva entidad lugar con los datos de la entidad que entra por
     * parametro.
     *
     * @param entity Entidad a persistir.
     * @return Entidad que fue persistida en la base de datos.
     */
    public LugarEntity create(LugarEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * Encuentra todos los lugares que hay en la base de datos.
     *
     * @return Array con todos los lugares registrados.
     */
    public List<LugarEntity> findAll()
    {
        TypedQuery q = em.createQuery("SELECT u FROM LugarEntity u", LugarEntity.class);
        return q.getResultList();
    }

    /**
     * Da el lugar que tiene id igual al parametro.
     *
     * @param id Id del lugar a buscar.
     * @return Entidad con id igual al parametro.
     */
    public LugarEntity find(Long id)
    {
        return em.find(LugarEntity.class, id);
    }

    /**
     * Actualiza un lugar de acuerdo a los datos de la entidad que entra po
     * parametro
     *
     * @param entity Entidad que contiene los nuevos datos.
     * @return Entidad actualizada.
     */
    public LugarEntity update(LugarEntity entity)
    {
        return em.merge(entity);
    }

    /**
     * Elimina el lugar que tiene id igual al parametro.
     *
     * @param id Id del lugar a eliminar.
     */
    public void delete(Long id)
    {
        LugarEntity entity = em.find(LugarEntity.class, id);
        em.remove(entity);
    }
}
