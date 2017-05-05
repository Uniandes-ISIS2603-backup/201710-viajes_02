/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.PagoMultaEntity;
import co.edu.uniandes.csw.viajes.persistence.PagoMultaPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ja.bermudez10
 */
public class PagoMultaLogic {
     /**
     * Inyeccion de persistencia en la logica
     */
    @Inject
    private PagoMultaPersistence pagoMultaPersistence;

    /**
     * Crea Pago desde la logica, usando la persistencia
     *
     * @param pagoMultaEntity
     * @return La entidad APgo
     */
    public PagoMultaEntity createPagoMulta(PagoMultaEntity pagoMultaEntity)
    {
        pagoMultaPersistence.create(pagoMultaEntity);
        return pagoMultaEntity;
    }

    /**
     * Consulta Pagos del Remitente especificado
     *
     * @param idRemitente
     * @return Lista de pagos asociados al ususario remitente
     */
    public List<PagoMultaEntity> getMisPagosMulta(Long idRemitente)
    {
        return pagoMultaPersistence.findAllMisPagosMulta(idRemitente);
    }

    /**
     * Consulta un Pago especifico, dado el idPago
     *
     * @param id
     * @return el Pago encontrado
     */
    public PagoMultaEntity getPagoMulta(Long id)
    {
        return pagoMultaPersistence.findPagoMulta(id);
    }

    /**
     * Actualiza un Pago
     *
     * @param pagoMultaEntity
     * @return La entidad Pago
     */
    public PagoMultaEntity updatePagoMulta(PagoMultaEntity pagoMultaEntity)
    {
        return pagoMultaPersistence.update(pagoMultaEntity);
    }

    /**
     * Elimina Pago desde la logica, usando la persistencia
     *
     * @param id El id del pago
     */
    public void deletePagoMulta(Long id)
    {
        pagoMultaPersistence.delete(id);
    }
}
