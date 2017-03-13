/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
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
public class ConductorPersistence {
    @PersistenceContext (unitName = "viajesPU")
    protected EntityManager em;
    
    public ConductorEntity find(Long id){
        return em.find(ConductorEntity.class, id);
    }
    
    public List<ConductorEntity> finAll(){
        Query q = em.createQuery("select u from ConductorEntity u");
        return q.getResultList();
    }
    
    public ConductorEntity create (ConductorEntity conductor){
        em.persist(conductor);
        return conductor;
    }
    
    public ConductorEntity update (ConductorEntity conductor){
        return em.merge(conductor);
    }
    
    public void delete (Long id){
        ConductorEntity conductor = em.find(ConductorEntity.class, id);
        em.remove(conductor);
    }
}
