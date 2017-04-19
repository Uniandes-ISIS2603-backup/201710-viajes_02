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

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jm.dominguez
 */
@Stateless
public class ConductorPersistence
{

    /**
     * Manejador de Entidades del JPA
     */
    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager em;

    /**
     * Permite buscar un conductor con el Id dado en la base de datos
     *
     * @param id: Identificador del conductor a buscar
     * @return
     */
    public ConductorEntity find(Long id)
    {
        return em.find(ConductorEntity.class, id);
    }

    /**
     * Permite obtener la lista de todos los conductores que se encuentran
     * activos en la base de datos.
     *
     * @return List: Lista con los conductores de la base de datos.
     */
    public List<ConductorEntity> finAll()
    {
        Query q = em.createQuery("select u from ConductorEntity u");
        return q.getResultList();
    }

    /**
     * Permite añadir un conductor a la base de datos
     *
     * @param conductor: Conductor a añadir.
     * @return ConductorEntity: Conductor que fué persistido en la base de
     * datos.
     */
    public ConductorEntity create(ConductorEntity conductor)
    {
        em.persist(conductor);
        return conductor;
    }

    /**
     * Permite actualizar un conductor en la base de datos.
     *
     * @param conductor: Conductor a modificar.
     * @return Conductor actualizado en la base de datos
     */
    public ConductorEntity update(ConductorEntity conductor)
    {
        return em.merge(conductor);
    }

    /**
     * Permite eliminar un conductor de la base de datos.
     *
     * @param id: Identificador del usuario a eliminar.
     */
    public void delete(Long id)
    {
        ConductorEntity conductor = em.find(ConductorEntity.class, id);
        em.remove(conductor);
    }

    /**
     * Añadir un vehículo a un conductor dado.
     *
     * @param carro: Carro a añadir
     * @param id: Identificador del conductor al que s ela va a añadir el
     * vehículo.
     * @return Conductor Entity: Conductor actualizado en la base de datos.
     */
    public ConductorEntity addCarro(AutomovilEntity carro, Long id)
    {
        ConductorEntity c = em.find(ConductorEntity.class, id);
        List<AutomovilEntity> lista = c.getAutomoviles();
        lista.add(carro);
        c.setAutomoviles(lista);
        em.merge(c);
        return c;
    }

}
