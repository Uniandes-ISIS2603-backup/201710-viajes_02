/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.persistence.ReservaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.bermudez10
 */
@Stateless
public class ReservaLogic {

    @Inject
    private ReservaPersistence reservaPersistence;

    public ReservaEntity createReserva(ReservaEntity reservaEntity) {
        reservaPersistence.create(reservaEntity);
        return reservaEntity;
    }
    
    public List<ReservaEntity> getReservasViajero(Long idViajero) {
        return reservaPersistence.findAllByIdViajero(idViajero);
    }
    
    public ReservaEntity getReservaEspecificaViajero(Long idViajero, Long idReserva) {
        return reservaPersistence.findReservaByIdViajeroIdReserva(idViajero, idReserva);
    }
    
    public void deleteReserva(Long id) {
        reservaPersistence.delete(id);
    }
}
