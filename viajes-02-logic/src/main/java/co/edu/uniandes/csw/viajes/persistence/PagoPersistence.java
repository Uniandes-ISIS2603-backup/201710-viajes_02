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
 *
 * @author ja.bermudez10
 */
@Stateless
public class PagoPersistence {

    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager entityManager;

    public PagoEntity create(PagoEntity pagoEntity) {
        entityManager.persist(pagoEntity);
        return pagoEntity;
    }

    public void delete(Long idPago) {
        PagoEntity pagoEntity = entityManager.find(PagoEntity.class, idPago);
        entityManager.remove(pagoEntity);
    }

    public PagoEntity update(PagoEntity pagoEntity) {
        return entityManager.merge(pagoEntity);
    }

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

    public PagoEntity findPago(Long idPago) {
        return entityManager.find(PagoEntity.class, idPago);
    }
}
