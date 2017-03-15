/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ConductorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jm.dominguez
 */
@Stateless
public class ConductorLogic
{

    /**
     * Relación con la clase persistencia.
     */
    @Inject
    private ConductorPersistence persistence;

    /**
     * Retorna la lista con todos los conductores de la base de datos.
     *
     * @return List: Lista con todos los conductores de la base de datos
     */
    public List<ConductorEntity> findAll()
    {
        return persistence.finAll();
    }

    /**
     * Permite buscar un conductor en la base de datos dado su id.
     *
     * @param id: Identificador del conductor a buscar.
     * @return Conductor con el id dado.
     * @throws BusinessLogicException
     */
    public ConductorEntity getConductor(Long id) throws BusinessLogicException
    {
        ConductorEntity respuesta = persistence.find(id);
        if (respuesta == null)
        {
            throw new BusinessLogicException("No se ha encontrado ningún conductor con el id dado");
        }
        else
        {
            return respuesta;
        }

    }

    /**
     * Crea un conductor y lo guarda en la base de datos.
     *
     * @param Conductor a persistir en la base de datos
     * @return Conductor que fue persistido en la base de datos.
     * @throws BusinessLogicException
     */
    public ConductorEntity createConductor(ConductorEntity c) throws BusinessLogicException
    {
        ConductorEntity conductor = persistence.find(c.getId());
        if (conductor != null)
        {
            throw new BusinessLogicException("El conductor con id dado ya existe en el sistema");
        }
        else if (c.getAutomoviles().isEmpty())
        {
            throw new BusinessLogicException("Los conductores deben tener al menos 1 vehículo");
        }
        return persistence.create(c);
    }

    /**
     * Permite actualizar un conductor en la base de datos.
     *
     * @param Conductor: Datos del conductor a actualizar en la base de datos.
     * @return Conductor: Conductor actualizado en la base de datos.
     * @throws BusinessLogicException
     */
    public ConductorEntity updateConductor(ConductorEntity c) throws BusinessLogicException
    {
        ConductorEntity conductor = persistence.find(c.getId());
        if (conductor != null)
        {
            throw new BusinessLogicException("El conductor con id dado ya existe en el sistema");
        }
        else if (c.getAutomoviles().isEmpty())
        {
            throw new BusinessLogicException("Los conductores deben tener al menos 1 vehículo");
        }
        return persistence.update(c);
    }

    /**
     * Permite eliminar un conductor de la base de datos.
     *
     * @param id
     */
    public void deleteConductor(Long id)
    {
        persistence.delete(id);
    }

    /**
     * Permite añadir un carro a un conductor dada su id.
     *
     * @param c Automovil a añadir
     * @param id: Id del conductor al que se quiere añadir un vehículo.
     * @return
     * @throws BusinessLogicException
     */
    public ConductorEntity addCarroToConductor(AutomovilEntity c, Long id) throws BusinessLogicException
    {
        ConductorEntity conductor = persistence.find(id);
        if (conductor != null)
        {
            throw new BusinessLogicException("El conductor con id dado ya existe en el sistema");
        }
        return persistence.addCarro(c, id);
    }

}
