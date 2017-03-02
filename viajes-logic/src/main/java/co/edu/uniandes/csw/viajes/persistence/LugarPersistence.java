/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.LugarEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author wr.ravelo
 */
@Stateless
public class LugarPersistence {
    
    @PersistenceContext(unitName="")
    protected @Inject EntityManager em;
    
    public LugarEntity create(LugarEntity entity) {
        em.persist(entity);
        return entity;
    }
    
    public List<LugarEntity> findAll() {
        TypedQuery q = em.createQuery("SELECT u FROM LugarEntity u", LugarEntity.class);
        return q.getResultList();
    }
    
    public LugarEntity find(Long id) {
        return em.find(LugarEntity.class, id);
    }
    
    public LugarEntity update(LugarEntity entity) {
        return em.merge(entity);
    }
    
    public void delete(Long id) {
        LugarEntity entity = em.find(LugarEntity.class, id);
        em.remove(entity);
    }
}
