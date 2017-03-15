/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author n.aguilar
 */
@Entity
public class ViajeroEntity extends UsuarioEntity{
    
    /*
    * Verifica si el usuario se puede considerar como igual al objeto que llega por parametro
    * return true si se considera igual, false de lo contrario
     */
    @Override
    public boolean equals(Object other) {
        return getId().equals(((ViajeroEntity) other).getId());
    }
    
    /*
    * Reservas que realiza el usuario
    */    
    @OneToMany (cascade = CascadeType.PERSIST)
    public List<ReservaEntity> reservas;

    /*
    * Se obtienen las reservas relacionadas con el usuario
    * return reservas
    */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /*
    * Se modifican las reservas que hizo el usuario por las que llegan por parametro
    */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }   
    
}
