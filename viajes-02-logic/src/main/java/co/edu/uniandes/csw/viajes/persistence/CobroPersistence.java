/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;
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
public class CobroPersistence
{

    /**
     * Manejador de persistencia
     */
    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager manager;

    /**
     * Crea un nuevo cobro con los datos de la entidad que entra por parametro
     *
     * @param entity Entidad a persistir
     * @return Entidad que fue persistida en la base de datos.
     */
    public CobroEntity create(CobroEntity entity)
    {
        manager.persist(entity);
        return entity;
    }

    /**
     * Encuentra todos los cobros del usurio que tiene id igual que el parametro
     *
     * @param usuarioId Id del usuario al que se quiere buscar los cobros.
     * @return Array con todos los cobros que perteneces a un usuario en
     * especifico.
     */
    public List<CobroEntity> findAllFromUsuario(Long usuarioId)
    {
        TypedQuery q = manager.createQuery("SELECT u FROM CobroEntity u WHERE u.usuarioRemitente.id=" + usuarioId, CobroEntity.class);
        return q.getResultList();
    }

    /**
     * Da todos los cobros registrados
     *
     * @return Todos los cobros registrados.
     */
    public List<CobroEntity> findAll()
    {
        TypedQuery q = manager.createQuery("SELECT u FROM CobroEntity u", CobroEntity.class);
        return q.getResultList();
    }

    /**
     * Encuentra el cobro que tiene id igual al parametro.
     *
     * @param id Id del cobro a buscar.
     * @return El cobro que tiene id igual al parametro.
     */
    public CobroEntity find(Long id)
    {
        return manager.find(CobroEntity.class, id);
    }

    /**
     * Actualiza una entidad cobro de acuerdo a los datos de la entidad que
     * entra por parametro.
     *
     * @param entity Entidad que contiene los nuevos datos.
     * @return Entidad con los nuevos datos.
     */
    public CobroEntity update(CobroEntity entity)
    {
        return manager.merge(entity);
    }

    /**
     * Elimina el cobro que tiene id igual al parametro.
     *
     * @param id Id del cobro que se quiere eliminar.
     */
    public void delete(Long id)
    {
        CobroEntity entity = manager.find(CobroEntity.class, id);
        manager.remove(entity);
    }
}
