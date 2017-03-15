/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;

/**
 *
 * @author wr.ravelo
 */
public class CobroMultaDTO extends CobroDTO {
   
    public CobroMultaDTO(CobroMultaEntity en) {
        if(en == null)
            return;
        
        this.id = en.getId();
        this.valor = en.getValor();
        this.cancelado = en.getCancelado();
        this.idDestinatario = en.getIdDestinatario();
        this.idRemitente = en.getIdRemitente();
    }
    
    public CobroMultaDTO() {
        
    }
    
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