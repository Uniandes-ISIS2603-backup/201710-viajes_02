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
    
    @Override
    public boolean equals(Object other) {
        return getId().equals(((ViajeroEntity) other).getId());
    }
    
    @OneToMany (cascade = CascadeType.PERSIST)
    public List<ReservaEntity> reservas;

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }   
    
}
