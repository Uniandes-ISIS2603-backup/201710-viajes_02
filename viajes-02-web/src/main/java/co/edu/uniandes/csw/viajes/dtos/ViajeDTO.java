/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jm.dominguez
 */
@XmlRootElement
public class ViajeDTO implements Serializable{
    
    
    private Long idViaje;
    private Integer Kilometros;
    private Double gastosCompartidos;
    private Date diaYHoraPartida;
    private Date diaYHoraLlegada;

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
    
    public ViajeDTO(){
        
    }
    
    public ViajeDTO(ViajeEntity entity){
        if(entity != null){
            idViaje = entity.getIdViaje();
            Kilometros = entity.getKilometros();
            gastosCompartidos = entity.getGastosCompartidos();
            diaYHoraPartida = entity.getDiaYHoraPartida();
            diaYHoraLlegada = entity.getDiaYHoraPartida();
            
        }
    }
    
    public ViajeEntity DTO2Entity(){
        ViajeEntity viaje = new ViajeEntity();
        viaje.setIdViaje(idViaje);
        viaje.setKilometros(Kilometros);
        viaje.setGastosCompartidos(gastosCompartidos);
        viaje.setDiaYHoraLlegada(diaYHoraLlegada);
        viaje.getDiaYHoraPartida();
        
        return viaje;
    }
}
