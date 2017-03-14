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
    @PersistenceContext(unitName="viajesPU")
    protected EntityManager manager;
    
    public CobroMultaEntity create(CobroMultaEntity entity) {
        manager.persist(entity);
        return entity;
    }
    
    public List<CobroMultaEntity> findAll() {
        TypedQuery q = manager.createQuery("SELECT u FROM CobroMultaEntity u", CobroMultaEntity.class);
        return q.getResultList();
    }
    
    public List<CobroMultaEntity> findAll(Long id) {
        TypedQuery q = manager.createQuery("SELECT u FROM CobroMultaEntity u WHERE u.idRemitente="+id, CobroMultaEntity.class);
        return q.getResultList();
    }
    
    public CobroMultaEntity find(Long id) {
        return manager.find(CobroMultaEntity.class, id);
    }
    
    public CobroMultaEntity update(CobroMultaEntity entity) {
        return manager.merge(entity);
    }
    
    public void delete(Long id) {
        CobroMultaEntity entity = manager.find(CobroMultaEntity.class, id);
        manager.remove(entity);
    }
}
