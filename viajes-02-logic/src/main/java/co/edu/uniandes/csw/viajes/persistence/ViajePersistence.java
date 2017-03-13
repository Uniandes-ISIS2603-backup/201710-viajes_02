/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jm.dominguez
 */
@Stateless
public class ViajePersistence {
    
    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager em;
    
    public ViajeEntity find(Long id){
        return em.find(ViajeEntity.class, id);
    }
    
    public List<ViajeEntity> findAll(){
        Query q = em.createQuery("Select u from ViajeEntity u");
        return q.getResultList();
    }
    
    public ViajeEntity create(ViajeEntity viaje){
        em.persist(viaje);
        return viaje;
    }
    
    public ViajeEntity update(ViajeEntity viaje){
        return em.merge(viaje);
    }
    
    public void delete(Long id){
        ViajeEntity v = em.find(ViajeEntity.class,id);
        em.remove(v);
    }
}
