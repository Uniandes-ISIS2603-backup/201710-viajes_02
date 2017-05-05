/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.PagoMultaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ja.bermudez10
 */
@Stateless
public class PagoMultaPersistence {

    /**
     * Anotacion que define el contexto de persistencia
     */
    @PersistenceContext(unitName = "viajesPU")

    /**
     * Administrador de entidades de JPA
     */
    protected EntityManager entityManager;

    /**
     * Crea un PagoMulta en la persistencia basado en la entidad PagoMulta
     *
     * @param pagoMultaEntity La entidad PAgo
     * @return La entidad PagoMulta, después de ser persistida
     */
    public PagoMultaEntity create(PagoMultaEntity pagoMultaEntity) {
        entityManager.persist(pagoMultaEntity);
        return pagoMultaEntity;
    }

    public List<PagoMultaEntity> findAll() {
        Query query = entityManager.createQuery("SELECT u FROM PagoMultaEntity u");
        return query.getResultList();
    }

    /**
     * Encuentra los Pagos por medio del idRemitente
     *
     * @param idRemitente El idRemitente del PagoMulta
     * @return Pagos asociados a el idRemitente
     */
    public List<PagoMultaEntity> findAllMisPagosMulta(Long idRemitente) {
        TypedQuery<PagoMultaEntity> query = entityManager.createQuery("Select u from PagoMultaEntity u where u.remitente.id = " + idRemitente, PagoMultaEntity.class);
        List<PagoMultaEntity> misPagos = query.getResultList();

        if (!misPagos.isEmpty()) {
            return misPagos;
        } else {
            return null;
        }
    }

    /**
     * Encuentra la entidad PagoMulta por medio del id
     *
     * @param id El idPago de la entidad PagoMulta
     * @return Entidad pagoMulta encontrada
     */
    public PagoMultaEntity findPagoMulta(Long id) {
        return entityManager.find(PagoMultaEntity.class, id);
    }

    /**
     * Actualiza un PagoMulta
     *
     * @param pagoMultaEntity La entidad PagoMulta
     * @return Entidad PagoMulta actualizada
     */
    public PagoMultaEntity update(PagoMultaEntity pagoMultaEntity) {
        return entityManager.merge(pagoMultaEntity);
    }

    /**
     * Elimina un pagoMulta dado el id
     *
     * @param id El id del pagoMulta
     */
    public void delete(Long id) {
        PagoMultaEntity pagoMultaEntity = entityManager.find(PagoMultaEntity.class, id);
        entityManager.remove(pagoMultaEntity);
    }
}
