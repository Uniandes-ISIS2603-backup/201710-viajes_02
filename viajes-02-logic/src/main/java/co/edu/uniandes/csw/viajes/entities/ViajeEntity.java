/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jm.dominguez
 */

@Entity
public class ViajeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idViaje;
    private Integer Kilometros;
    private Double gastosCompartidos;
    @Temporal(TemporalType.DATE)
    private Date diaYHoraPartida;
    @Temporal(TemporalType.DATE)
    private Date diaYHoraLlegada;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private LugarEntity origen;

    @OneToOne(cascade = CascadeType.PERSIST)
    private LugarEntity destino;

    public LugarEntity getOrigen() {
        return origen;
    }

    public void setOrigen(LugarEntity origen) {
        this.origen = origen;
    }

    public LugarEntity getDestino() {
        return destino;
    }

    public void setDestino(LugarEntity destino) {
        this.destino = destino;
    }

    public Long getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Long idViaje) {
        this.idViaje = idViaje;
    }

    public Integer getKilometros() {
        return Kilometros;
    }

    public void setKilometros(Integer Kilometros) {
        this.Kilometros = Kilometros;
    }

    public Double getGastosCompartidos() {
        return gastosCompartidos;
    }

    public void setGastosCompartidos(Double gastosCompartidos) {
        this.gastosCompartidos = gastosCompartidos;
    }

    public Date getDiaYHoraPartida() {
        return diaYHoraPartida;
    }

    public void setDiaYHoraPartida(Date diaYHoraPartida) {
        this.diaYHoraPartida = diaYHoraPartida;
    }

    public Date getDiaYHoraLlegada() {
        return diaYHoraLlegada;
    }

    public void setDiaYHoraLlegada(Date diaYHoraLlegada) {
        this.diaYHoraLlegada = diaYHoraLlegada;
    }
    
    @Override
    public boolean equals(Object obj){
        ViajeEntity v = (ViajeEntity) obj;
        return v.getIdViaje().equals(idViaje);
    }
    
    @Override
    public int hashCode(){
        if(this.getIdViaje() != null){
            return this.getIdViaje().hashCode();
        }
        
        return super.hashCode();
    }
}
