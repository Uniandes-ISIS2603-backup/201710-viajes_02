/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author n.aguilar
 */
@Stateless
public class ViajeroPersistence {
    
    @PersistenceContext (unitName = "viajesPU")
    protected EntityManager em;
    
        public ViajeroEntity find(Long id){
        return em.find(ViajeroEntity.class, id);
    }
    
    public List<ViajeroEntity> findAll(){
        TypedQuery q = em.createQuery("SELECT u FROM ViajeroEntity u", ViajeroEntity.class);
        return q.getResultList();
    }
    
    public ViajeroEntity create(ViajeroEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public ViajeroEntity update(ViajeroEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        ViajeroEntity entity = em.find(ViajeroEntity.class, id);
        em.detach(entity);
    }           
            
    
}
