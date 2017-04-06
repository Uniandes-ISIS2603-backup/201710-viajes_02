/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * PagoDetailDTO
 *
 * @author ja.bermudez10
 */
@XmlRootElement
public class PagoDetailDTO extends PagoDTO {

    /**
     * Usuario destinatario del cobro
     */
    @PodamExclude
    private UsuarioDTO destinatario;

    /**
     * Usuario remitente del cobro
     */
    @PodamExclude
    private UsuarioDTO remitente;

    // TODO como puedo saber qué está pagando el usuario si no hay una relación con el viaje?
    /**
     * Constructor por defecto de PagoDetailDTO
     */
    public PagoDetailDTO() {
        super();
    }

    /**
     * Constructor de un PagoDetailDTO segun la entidad Pago
     *
     * @param pagoEntity La entidad Pago
     */
    public PagoDetailDTO(PagoEntity pagoEntity) {
        super(pagoEntity);
        this.destinatario = new UsuarioDTO(pagoEntity.getDestinatario());
        this.remitente = new UsuarioDTO(pagoEntity.getRemitente());
    }

    /**
     * Retorna un PagoEntity basado en el actual PagoDeatailDTO
     *
     * @return La entidad Pago
     */
    @Override
    public PagoEntity toEntity() {
        PagoEntity pagoEntity = super.toEntity();
        pagoEntity.setDestinatario(this.destinatario.toEntity());
        pagoEntity.setRemitente(this.remitente.toEntity());

        return pagoEntity;
    }
    
    /**
     * 
     * @return 
     */
    public UsuarioDTO getDestinatario() {
        return destinatario;
    }

    /**
     * 
     * @param destinatario 
     */
    public void setDestinatario(UsuarioDTO destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * 
     * @return 
     */
    public UsuarioDTO getRemitente() {
        return remitente;
    }

    /**
     * 
     * @param remitente 
     */
    public void setRemitente(UsuarioDTO remitente) {
        this.remitente = remitente;
    }

}
