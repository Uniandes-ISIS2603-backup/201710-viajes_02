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
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * PagoPersistence
 * @author ja.bermudez10
 */
@Stateless
public class PagoPersistence
{

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
    public PagoEntity create(PagoEntity pagoEntity)
    {
        entityManager.persist(pagoEntity);
        return pagoEntity;
    }
    
    public List<PagoEntity> findAll() {
        Query query = entityManager.createQuery("SELECT u FROM PagoEntity u");
        return query.getResultList();
    }
    
    /**
     * Encuentra los Pagos por medio del idRemitente
     * @param idRemitente El idRemitente del Pago
     * @return Pagos asociados a el idRemitente
     */
    public List<PagoEntity> findAllMisPagos(Long idRemitente)
    {
        TypedQuery<PagoEntity> query = entityManager.createQuery("Select u from PagoEntity u where u.remitente.id =" + idRemitente, PagoEntity.class);
        List<PagoEntity> misPagos = query.getResultList();
        
        if (!misPagos.isEmpty())
            return misPagos;
        else
            return null;
    }

    /**
     * Encuentra la entidad Pago por medio del id
     * @param id El idPago de la entidad Pago
     * @return Entidad pago encontrada
     */
    public PagoEntity findPago(Long id)
    {
        return entityManager.find(PagoEntity.class, id);
    }

    /**
     * Actualiza un Pago
     * @param pagoEntity La entidad Pago
     * @return Entidad Pago actualizada
     */
    public PagoEntity update(PagoEntity pagoEntity)
    {
        return entityManager.merge(pagoEntity);
    }
    
    /**
     * Elimina un pago dado el id
     * @param id El id del pago
     */
    public void delete(Long id)
    {
        PagoEntity pagoEntity = entityManager.find(PagoEntity.class, id);
        entityManager.remove(pagoEntity);
    }
    
}
