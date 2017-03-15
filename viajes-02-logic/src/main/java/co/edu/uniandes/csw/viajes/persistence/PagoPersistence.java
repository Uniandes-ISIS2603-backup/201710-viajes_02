/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * PagoPersistence
 * @author ja.bermudez10
 */
@Stateless
public class PagoPersistence {

    /**
     * Anotacion que define el contexto de persistencia
     */
    @PersistenceContext(unitName = "viajesPU")
    
    /**
     * Administrador de entidades de JPA
     */
    protected EntityManager entityManager;

    /**
     * Crea un Pago en la persistencia basado en la entidad Pago
     * @param pagoEntity La entidad PAgo
     * @return La entidad Pago, después de ser persistida
     */
    public PagoEntity create(PagoEntity pagoEntity) {
        entityManager.persist(pagoEntity);
        return pagoEntity;
    }

    /**
     * Elimina un pago dado el idPago
     * @param idPago El idPago
     */
    public void delete(Long idPago) {
        PagoEntity pagoEntity = entityManager.find(PagoEntity.class, idPago);
        entityManager.remove(pagoEntity);
    }

    /**
     * Actualiza un Pago
     * @param pagoEntity La entidad Pago
     * @return Entidad Pago actualizada
     */
    public PagoEntity update(PagoEntity pagoEntity) {
        return entityManager.merge(pagoEntity);
    }

    /**
     * Encuentra los Pagos por medio del idRemitente
     * @param idRemitente El idRemitente del Pago
     * @return Pagos asociados a el idRemitente
     */
    public List<PagoEntity> findAllMisPagos(Long idRemitente) {
        TypedQuery<PagoEntity> query = entityManager.createQuery("Select u from PagoEntity u where u.idRemitente = :idRemitente", PagoEntity.class);
        query = query.setParameter("idRemitente", idRemitente);

        List<PagoEntity> misPagos = query.getResultList();
        if (!misPagos.isEmpty()) {
            return misPagos;
        } else {
            return null;
        }
    }

    /**
     * Encuentra la entidad Pago por medio del idPago
     * @param idPago El idPago de la entidad Pago
     * @return Entidad pago encontrada
     */
    public PagoEntity findPago(Long idPago) {
        return entityManager.find(PagoEntity.class, idPago);
    }
    
}
