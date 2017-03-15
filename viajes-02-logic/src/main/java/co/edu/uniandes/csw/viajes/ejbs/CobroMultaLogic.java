/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;
import co.edu.uniandes.csw.viajes.persistence.CobroMultaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author wr.ravelo
 */
@Stateless
public class CobroMultaLogic {
    /**
     * Atributo que representa la persistencia de la base de datos.
     */
     @Inject private CobroMultaPersistence persistence;
    
    /**
     * Crea un nuevo cobro a partir de los datos que hay en la entidad que entra por parametro
     * @param entity Entidad que contiene los datos a guardar en la base de datos.
     * @return Entidad creada a partir de los datos guardados en la base de datos.
     */
    public CobroMultaEntity createCobroMulta(CobroMultaEntity entity) {
        return persistence.create(entity);
    }
    
    /**
     * Encuentra un cobro a partir del id que entra por parametro
     * @param id Id del cobro que se quiere buscar.
     * @return El cobro que tiene el id que entra por parametro, null si no hay ninguno.
     */
    public CobroMultaEntity findCobroMulta(Long id) {
        return persistence.find(id);
    }
    
    /**
     * Da todos los cobros de tipo multa que hay registrados.
     * @return Array con todos los cobros de tipo multa.
     */
    public List<CobroMultaEntity> findCobroMultas() {
        return persistence.findAll();
    }
    
    /**
     * Da los cobros multas que perteneces a un usuario con id igual al parametro.
     * @param id Id del usuario al que se quiere sacar los cobros multa.
     * @return Array con todos los cobros multa que tiene el usuario.
     */
    public List<CobroMultaEntity> findCobroMultas(Long id) {
        return persistence.findAllFromUsuario(id);
    }
    
    /**
     * Actualiza un cobro multa con la informacion de la entidad que entra por parametro
     * @param entity Entidad que contiene los nuevos datos a actualizar.
     * @return Entidad actualizada.
     */
    public CobroMultaEntity updateCobroMulta(CobroMultaEntity entity) {
        return persistence.update(entity);
    }
    
    /**
     * Elimina un cobro multa que tiene id igual al que entra por paramatro.
     * @param id Id del cobro multa a eliminar.
     */
    public void deleteCobroMulta(Long id) {
        persistence.delete(id);
    }
}
