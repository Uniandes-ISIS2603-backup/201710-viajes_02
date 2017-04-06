/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.persistence.PagoPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 * PagoLogic
 *
 * @author ja.bermudez10
 */
// TODO no hay reglas de negocio y hay unos atributos que no se entiende qué modelan ni qué condiciones deben tener. 
public class PagoLogic
{

    /**
     * Inyeccion de persistencia en la logica
     */
    @Inject
    private PagoPersistence pagoPersistence;

    /**
     * Crea Pago desde la logica, usando la persistencia
     *
     * @param pagoEntity
     * @return La entidad APgo
     */
    public PagoEntity createPago(PagoEntity pagoEntity)
    {
        pagoPersistence.create(pagoEntity);
        return pagoEntity;
    }

    /**
     * Consulta Pagos del Remitente especificado
     *
     * @param idRemitente
     * @return Lista de pagos asociados al ususario remitente
     */
    public List<PagoEntity> getMisPagos(Long idRemitente)
    {
        return pagoPersistence.findAllMisPagos(idRemitente);
    }

    /**
     * Consulta un Pago especifico, dado el idPago
     *
     * @param id
     * @return el Pago encontrado
     */
    public PagoEntity getPago(Long id)
    {
        return pagoPersistence.findPago(id);
    }

    /**
     * Actualiza un Pago
     *
     * @param pagoEntity
     * @return La entidad Pago
     */
    public PagoEntity updatePago(PagoEntity pagoEntity)
    {
        return pagoPersistence.update(pagoEntity);
    }

    /**
     * Elimina Pago desde la logica, usando la persistencia
     *
     * @param id El id del pago
     */
    public void deletePago(Long id)
    {
        pagoPersistence.delete(id);
    }
}
