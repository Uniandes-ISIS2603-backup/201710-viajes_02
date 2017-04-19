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

import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jm.dominguez
 */
@Stateless
public class ViajePersistence
{

    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager em;

    /**
     * Busca la información del viaje con el id dado por parámetro.
     *
     * @param id: Long. Identificador del viaje a buscar.
     * @return ViajeEntity. Viaje con el identificador dado por parámetro.
     */
    public ViajeEntity find(Long id)
    {
        return em.find(ViajeEntity.class, id);
    }

    /**
     * Busca la información de todos los viajes que se encuentran en la base de
     * datos.
     *
     * @return List. Lista con todos los viajes que se encuentran en la base de
     * datos.
     */
    public List<ViajeEntity> findAll()
    {
        Query q = em.createQuery("Select u from ViajeEntity u");
        return q.getResultList();
    }

    /**
     * Persiste un viaje dado en la base de datos del sistemas
     *
     * @param viaje: ViajeEntity. Viaje a persistir en la base de datos.
     * @return viaje: ViajeEntity. Viaje después de haber sido persistido en la
     * base de datos.
     */
    public ViajeEntity create(ViajeEntity viaje)
    {
        em.persist(viaje);
        return viaje;
    }

    /**
     * Permite actualizar la información de un viaje que se encuentre registrado
     * en la base de datos.
     *
     * @param viaje: ViajeEntity. Viaje a actualiza en la base de datos.
     * @return viaje: ViajeEntity. Viaje después de haber sido actualizado en la
     * base de datos.
     */
    public ViajeEntity update(ViajeEntity viaje)
    {
        return em.merge(viaje);
    }

    /**
     * Permite eliminar la información del viaje con el id dado por parametro de
     * la base de datos.
     *
     * @param id: Long. Identificador del viaje a eliminar.
     */
    public void delete(Long id)
    {
        ViajeEntity v = em.find(ViajeEntity.class, id);
        em.remove(v);
    }

    /**
     * Permite realizar la búsqueda de todos los viajes con un origen y un
     * destino dados.
     *
     * @param origen: String. Ciudad de origen del viaje.
     * @param destino: String. Ciudad de destino del viaje.
     * @return List. Lista con todos lo viajes cuyos origen y destino coinciden
     * con los dador por parámetro.
     */
    public List<ViajeEntity> buscarPorOrigenyDestino(String origen, String destino)
    {
        TypedQuery<ViajeEntity> q = em.createQuery("SELECT U FROM ViajeEntity U WHERE U.origen.lugar = origen AND U.destino.lugar = destino", ViajeEntity.class);
        List<ViajeEntity> lista = q.getResultList();
        return lista;
    }

    /**
     * Método para verificar la existencia de un conductor en la base de datos.
     *
     * @param conductor: Conductor a buscar en la base de datos.
     * @return true, si el conductor se encuentra en la base de datos. False de
     * lo contrario.
     */
    public boolean existeConductor(ConductorEntity conductor)
    {
        ConductorEntity c = em.find(ConductorEntity.class, conductor.getId());
        if (c == null)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
}
