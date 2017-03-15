/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
