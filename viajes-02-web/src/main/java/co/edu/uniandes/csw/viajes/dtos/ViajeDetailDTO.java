/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jm.dominguez
 */
@XmlRootElement
public class ViajeDetailDTO extends ViajeDTO{
    
    private List <ReservaDTO> reservas;

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
    
    
    public ViajeDetailDTO(){
        super();
    }
    
    public ViajeDetailDTO(ViajeEntity entity){
        super(entity);
        List<ReservaEntity> r1 = entity.getReserva();
        List<ReservaDTO> r2 = listEntity2DTO(r1);
        reservas = r2;
    }
    
    @Override
    public ViajeEntity DTO2Entity(){
        ViajeEntity entity = super.DTO2Entity();
        return entity;
    }
    
     private List<ReservaDTO> listEntity2DTO(List<ReservaEntity> reservaEntityList) {
        List<ReservaDTO> listReserva = new ArrayList<>();
        for (ReservaEntity reservaEntity : reservaEntityList) {
            listReserva.add(new ReservaDetailDTO(reservaEntity));
        }
        return listReserva;
    }
}
