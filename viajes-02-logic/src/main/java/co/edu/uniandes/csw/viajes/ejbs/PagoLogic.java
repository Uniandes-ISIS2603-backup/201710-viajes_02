/* 
 * The MIT License
 *
 * Copyright 2017 wr.ravelo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
