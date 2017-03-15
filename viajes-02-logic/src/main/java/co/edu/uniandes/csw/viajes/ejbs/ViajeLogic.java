/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ViajePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jm.dominguez
 */
@Stateless
public class ViajeLogic
{

    @Inject
    private ViajePersistence persistence;

    /**
     * Recupera todos los viajes que se encuentran en la base de datos.
     *
     * @return List<Viaje Entity> Retorna una lista con todos los viajes que se
     * encuentran en la base de datos.
     */
    public List<ViajeEntity> findViajes()
    {
        return persistence.findAll();
    }

    /**
     * Recupera el viaje que tenga el id dado por parámetro.
     *
     * @param id: Long. Identificador del viaje a buscar.
     * @return ViajeEntity: Viaje con el identificador dado por parámetro.
     */
    public ViajeEntity findViaje(Long id)
    {
        return persistence.find(id);
    }

    /**
     * Añade un nuevo viaje a la base de datos.
     *
     * @param v: ViajeEntity. Viaje a persistir en la base de datos.
     * @return ViajeEntity: Viaje que fue persistido en la base de datos.
     * @throws BusinessLogicException Si el origen y destiono del viaje son en
     * la misma ciudad. Si el conductor no se encuetra registrado en el sistema.
     */
    public ViajeEntity createViaje(ViajeEntity v) throws BusinessLogicException
    {
        String origen = v.getOrigen().getLugar();
        String destino = v.getDestino().getLugar();
        if (origen.equals(destino))
        {
            throw new BusinessLogicException("Los viajes deben ser interestatales");
        }
        ConductorEntity conductor = v.getConductor();
        boolean existe = persistence.existeConductor(conductor);
        if (existe == false)
        {
            throw new BusinessLogicException("El conductor dado no existe en el sistema");
        }
        else
        {
            return persistence.create(v);
        }
    }

    /**
     * Método para actualizar la información de un viaje que previamente se
     * encontraba guardado en la base de datos.
     *
     * @param v: ViajeEntity. Viaje a persistir en la base de datos.
     * @return ViajeEntity: Viaje que fue persistido en la base de datos.
     * @throws BusinessLogicException Si el origen y destiono del viaje son en
     * la misma ciudad. Si el conductor no se encuetra registrado en el sistema.
     */
    public ViajeEntity updateViaje(ViajeEntity v) throws BusinessLogicException
    {
        String origen = v.getOrigen().getLugar();
        String destino = v.getDestino().getLugar();
        if (origen.equals(destino))
        {
            throw new BusinessLogicException("Los viajes deben ser interestatales");
        }
        else
        {
            return persistence.update(v);
        }

    }

    /**
     * Elimina el viaje con id dado por parámetro.
     *
     * @param id: Long. Identificador del viaje a eliminar.
     */
    public void deleteViaje(Long id)
    {
        persistence.delete(id);
    }

    /**
     * Permite obtener todos los viaje con un origen y destino dados por
     * parámetro.
     *
     * @param origen: String, ciudad de origen del viaje
     * @param destino: String, ciudad de destino del viaje
     * @return List<ViajeEntity>: Lista de los viajes con el origen y el destino
     * dados por parámetro.
     */
    public List<ViajeEntity> darViajesOrigenYDestino(String origen, String destino)
    {
        return persistence.buscarPorOrigenyDestino(origen, destino);
    }
}
