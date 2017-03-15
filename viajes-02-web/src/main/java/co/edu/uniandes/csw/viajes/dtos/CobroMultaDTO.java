/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wr.ravelo
 */
@XmlRootElement
public class CobroMultaDTO extends CobroDTO {
   
    /**
     * Crea un cobro multa DTO de acuerdo a la informacion de una entidad cobro multa
     * @param en Entidad cobro multa de donde se crea el DTO
     */
    public CobroMultaDTO(CobroMultaEntity en) {
        if(en == null)
            return;
        
        this.id = en.getId();
        this.valor = en.getValor();
        this.cancelado = en.getCancelado();
        this.idDestinatario = en.getIdDestinatario();
        this.idRemitente = en.getIdRemitente();
    }
    
    /**
     * Constructor vacio.
     */
    public CobroMultaDTO() {
        
    }
    
    /**
     * Crea una entidad cobro multa de acueurdo a la informacion del DTO
     * @return Entidad creada a partir de la informacion actual.
     */
    @Override
    public CobroMultaEntity toEntity() {
        CobroMultaEntity x = new CobroMultaEntity();
        x.setValor(this.getValor());
        x.setCancelado(this.getCancelado());
        x.setId(this.getId());
        x.setIdDestinatario(this.getIdDestinatario());
        x.setIdRemitente(this.getIdRemitente());
        return x;
    }
}
