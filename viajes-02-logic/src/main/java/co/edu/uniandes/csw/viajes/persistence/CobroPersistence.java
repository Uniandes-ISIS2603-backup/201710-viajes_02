/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;
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
public class CobroPersistence {
    
    @PersistenceContext(unitName="viajesPU")
    protected EntityManager manager;
    
    public CobroEntity create(CobroEntity entity) {
        manager.persist(entity);
        return entity;
    }
    
    public List<CobroEntity> findAllFromUsuario(Long usuarioId) {
        TypedQuery q = manager.createQuery("SELECT u FROM CobroEntity u WHERE u.idRemitente="+usuarioId, CobroEntity.class);
        return q.getResultList();
    }
    
    public List<CobroEntity> findAll() {
        TypedQuery q = manager.createQuery("SELECT u FROM CobroEntity u", CobroEntity.class);
        return q.getResultList();
    }
    
    public CobroEntity find(Long id) {
        return manager.find(CobroEntity.class, id);
    }
    
    public CobroEntity update(CobroEntity entity) {
        return manager.merge(entity);
    }
    
    public void delete(Long id) {
        CobroEntity entity = manager.find(CobroEntity.class, id);
        manager.remove(entity);
    }
}
