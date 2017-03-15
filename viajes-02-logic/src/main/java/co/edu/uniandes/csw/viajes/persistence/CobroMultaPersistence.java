/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;
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
public class CobroMultaPersistence {
    
    /**
     * Manejador de persistencia
     */
    @PersistenceContext(unitName="viajesPU")
    protected EntityManager manager;
    
    /**
     * Persisten una entidad en la base de datos.
     * @param entity Entidad a la que se va a persistir.
     * @return Entidad que persistio en la base de datos.
     */
    public CobroMultaEntity create(CobroMultaEntity entity) {
        manager.persist(entity);
        return entity;
    }
    
    /**
     * Encuentra todas las entidades cobro multa en la base de datos.
     * @return Array con todos los cobros multa en la base de datos.
     */
    public List<CobroMultaEntity> findAll() {
        TypedQuery q = manager.createQuery("SELECT u FROM CobroMultaEntity u", CobroMultaEntity.class);
        return q.getResultList();
    }
    
    /**
     * Encuentra todas las entidades cobro multa en la base de datos que pertenecen a un usuario con id que entra por parametro.
     * @param id Id del usuario al que se le van a buscar los cobros multa
     * @return Array con las entidades cobro multa que pertenecen a un usuario especifico.
     */
    public List<CobroMultaEntity> findAllFromUsuario(Long id) {
        TypedQuery q = manager.createQuery("SELECT u FROM CobroMultaEntity u WHERE u.idRemitente="+id, CobroMultaEntity.class);
        return q.getResultList();
    }
    
    /**
     * Encuentra la entidad cobro multa con id igual al que entra por parametro.
     * @param id Id de la entidad a la que se va a buscar.
     * @return  Entidad que tiene id igual al parametro.
     */
    public CobroMultaEntity find(Long id) {
        return manager.find(CobroMultaEntity.class, id);
    }
    
    /**
     * Actualiza una entidad cobro multa con los datos de la entidad que entra por parametro.
     * @param entity Entidad que contiene los nuevos datos a actualizar.
     * @return Entidad actualizada.
     */
    public CobroMultaEntity update(CobroMultaEntity entity) {
        return manager.merge(entity);
    }
    
    /**
     * Elimina entidad cobro multa que tiene id igual al parametro.
     * @param id Id de la entidad que se va a eliminar.
     */
    public void delete(Long id) {
        CobroMultaEntity entity = manager.find(CobroMultaEntity.class, id);
        manager.remove(entity);
    }
}
