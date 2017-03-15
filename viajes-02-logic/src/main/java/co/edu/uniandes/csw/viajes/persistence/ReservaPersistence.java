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

    public void delete(Long idViajero, Long idReserva)
    {
        TypedQuery<ReservaEntity> query = entityManager.createQuery("Select u from ReservaEntity u where u.idViajero = :idViajero and u.idReserva = :idReserva", ReservaEntity.class);
        query = query.setParameter("idViajero", idViajero);
        query = query.setParameter("idReserva", idReserva);
        ReservaEntity reservaToDelete = query.getSingleResult();
        entityManager.remove(reservaToDelete);
    }

    public ReservaEntity update(ReservaEntity reservaEntity)
    {
        return entityManager.merge(reservaEntity);
    }

    public List<ReservaEntity> findAllByIdViajero(Long idViajero)
    {
        TypedQuery<ReservaEntity> query = entityManager.createQuery("Select u from ReservaEntity u where u.idViajero =" + idViajero, ReservaEntity.class);

        List<ReservaEntity> reservasPorIdViajero = query.getResultList();
        if (!reservasPorIdViajero.isEmpty())
        {
            return reservasPorIdViajero;
        }
        else
        {
            return null;
        }
    }

}
