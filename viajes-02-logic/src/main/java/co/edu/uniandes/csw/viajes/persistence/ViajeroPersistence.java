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
    /**
     * Manejador de persistencia
     */
    @PersistenceContext (unitName = "viajesPU")
    protected EntityManager em;
    /**
     * En cuentra un viajero identificado con el parametro
     * @param id Id del viajero que se desea encontrar
     * @return El viajero con el id del parametro
     */
        public ViajeroEntity find(Long id){
        return em.find(ViajeroEntity.class, id);
    }
    /**
     * Retorna todos los viajeros almacenado en el sistema BD
     * @return TOdos los viajeros en el sistema
     */
    public List<ViajeroEntity> findAll(){
        TypedQuery q = em.createQuery("SELECT u FROM ViajeroEntity u", ViajeroEntity.class);
        return q.getResultList();
    }
    /**
     * Crea la entidad que llega por parametro en la BD 
     * @param entity La entidad que se desea que persista
     * @return El viajero que se registro en BD
     */
    public ViajeroEntity create(ViajeroEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    /**
     * Se actualizan los valor de la entidad viajero que llega por parametro
     * @param entity La entidad viajero que se desea actualizar
     * @return La entidad que se actualizo
     */
    public ViajeroEntity update(ViajeroEntity entity)
    {
        return em.merge(entity);
    }
    
    /**
     * Elimina la entidad registrada con el id que llega por parametro
     * @param id La id del viajero que se desea eliminar
     */
    public void delete(Long id)
    {
        ViajeroEntity entity = em.find(ViajeroEntity.class, id);
        em.detach(entity);
    }           
            
    
}
