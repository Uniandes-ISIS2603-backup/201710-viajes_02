/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.CobroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author wr.ravelo
 */
@Stateless
public class CobroLogic
{

    /**
     * Atributo que representa la persistencia de los datos
     */
    @Inject
    private CobroPersistence persistence;

    /**
     * Crea un nuevo cobro en la persistencia.
     *
     * @param entity Entidad que contiene la informacion que va a ir en la base
     * de datos.
     * @return Entity con la informacion ingresada en la base de datos.
     */
    public CobroEntity createCobro(CobroEntity entity)
    {
        return persistence.create(entity);
    }

    /**
     * Encuentra un cobro con el id que entra por parametro.
     *
     * @param id Id del cobro que se quiere buscar.
     * @return El cobro con el id que entra por parametro, null si no existe.
     */
    public CobroEntity findCobro(Long id)
    {
        return persistence.find(id);
    }

    /**
     * Da todos los cobros en la base de datos.
     *
     * @return Todos los cobros registrados.
     */
    public List<CobroEntity> findCobros()
    {
        return persistence.findAll();
    }

    /**
     * Encuentra los cobros que perteneces a un usuario con id que entra por
     * parametro.
     *
     * @param id Id del usuario al que se quiere buscar sus cobros.
     * @return Los cobros del usuario con id que entra por parametro, un Array
     * vacio si no hay.
     */
    public List<CobroEntity> findCobros(Long id)
    {
        return persistence.findAllFromUsuario(id);
    }

    /**
     * Actualiza un cobro combinando la nueva entidad con la vieja.
     *
     * @param entity Entidad que contiene los nuevos datos del cobro.
     * @return La nueva entidad actualizada.
     */
    public CobroEntity updateCobro(CobroEntity entity)
    {
        return persistence.update(entity);
    }

    /**
     * Elimina un cobro con Id que entra por parametro.
     *
     * @param id Id del cobro que se quiere eliminar.
     */
    public void deleteCobro(Long id)
    {
        persistence.delete(id);
    }
}
