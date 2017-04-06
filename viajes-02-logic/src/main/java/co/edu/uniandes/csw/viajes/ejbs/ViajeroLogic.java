/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.ViajeroEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ViajeroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author n.aguilar
 */
@Stateless
public class ViajeroLogic
{

    /**
     * La persistencia de la entidad usuario
     */
    @Inject
    private ViajeroPersistence persistence;

    /**
     * Retorna los viajeros en el sistema
     *
     * @return Lista de viajeros en el sistema
     */
    public List<ViajeroEntity> getViajeros()
    {
        return persistence.findAll();
    }

    /**
     * Retorna un viajero que se identifique con el parametro
     *
     * @param id El id de la entidad que se busca
     * @return El viajero que se esta buscando
     * @throws BusinessLogicException en caso de que no exista un usuario
     * identificado con el id
     */
    public ViajeroEntity getViajero(Long id) throws BusinessLogicException
    {
        return persistence.find(id);
    }

    /**
     * Crea un viajero con la entidad que llega por parametro
     *
     * @param entity La entidad que se desea crear
     * @return La entidad que se creo
     * @throws BusinessLogicException en caso de que el genero no se un valor
     * esperado, o la edad este fuera de ciertos rangos
     */
    public ViajeroEntity createViajero(ViajeroEntity entity) throws BusinessLogicException
    {
        if (!entity.getGenero().equalsIgnoreCase("masculino") && !entity.getGenero().equalsIgnoreCase("femenino"))
        {
            throw new BusinessLogicException("El genero del usuario a crear no es valido. (Debe ser masculino o femenino)");
        }
        if (entity.getEdad() < 0 || entity.getEdad() > 115)
        {
            throw new BusinessLogicException("La edad ingresada no es valida");
        }
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza una entidad que llega por parametro
     *
     * @param entity La entidad que se desea actualizar
     * @return La entidad actualizada
     */
    public ViajeroEntity updateViajero(ViajeroEntity entity) throws BusinessLogicException
    {
        if (!entity.getGenero().equalsIgnoreCase("masculino") && !entity.getGenero().equalsIgnoreCase("femenino"))
        {
            throw new BusinessLogicException("El genero del usuario a crear no es valido. (Debe ser masculino o femenino)");
        }
        if (entity.getEdad() < 0 || entity.getEdad() > 115)
        {
            throw new BusinessLogicException("La edad ingresada no es valida");
        }
        persistence.update(entity);
        return entity;
    }

    /**
     * Elimina una entidad identificada con el id que llega por parametro
     *
     * @param id el id de la entidad a eliminar
     */
    public void deleteViajero(Long id)
    {
        persistence.delete(id);
    }

}
