/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author ja.bermudez10
 */
@Stateless
public class ReservaPersistence
{

    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager entityManager;

    public ReservaEntity create(ReservaEntity reservaEntity)
    {
        entityManager.persist(reservaEntity);
        return reservaEntity;
    }

    public void delete(Long idViajero, Long id)
    {
        TypedQuery<ReservaEntity> query = entityManager.createQuery("Select u from ReservaEntity u where u.viajero.id = :idViajero and u.id = :id", ReservaEntity.class);
        query = query.setParameter("idViajero", idViajero);
        query = query.setParameter("idReserva", id);
        ReservaEntity reservaToDelete = query.getSingleResult();
        entityManager.remove(reservaToDelete);
    }

    public ReservaEntity update(ReservaEntity reservaEntity)
    {
        return entityManager.merge(reservaEntity);
    }

    public List<ReservaEntity> findReservasByIdViajero(Long idViajero)
    {
        TypedQuery<ReservaEntity> query = entityManager.createQuery("Select u from ReservaEntity u where u.viajero.id =" + idViajero, ReservaEntity.class);
        List<ReservaEntity> reservasPorIdViajero = query.getResultList();
        
        if (!reservasPorIdViajero.isEmpty())
            return reservasPorIdViajero;
        else
            return null;
    }

    public ReservaEntity findReserva(Long id) {
        return entityManager.find(ReservaEntity.class, id);
    }
    
}
