/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;


import co.edu.uniandes.csw.viajes.entities.PagoMultaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ja.bermudez10
 */
@XmlRootElement
public class PagoMultaDetailDTO extends PagoMultaDTO {
    
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
     * Constructor por defecto de PagoMultaDetailDTO
     */
    public PagoMultaDetailDTO() {
        super();
    }

    /**
     * Constructor de un PagoMultaDetailDTO segun la entidad Pago
     *
     * @param pagoMultaEntity La entidad Pago
     */
    public PagoMultaDetailDTO(PagoMultaEntity pagoMultaEntity) {
        super(pagoMultaEntity);
        this.destinatario = new UsuarioDTO(pagoMultaEntity.getDestinatario());
        this.remitente = new UsuarioDTO(pagoMultaEntity.getRemitente());
    }

    /**
     * Retorna un PagoMultaEntity basado en el actual PagoDeatailDTO
     *
     * @return La entidad Pago
     */
    @Override
    public PagoMultaEntity toEntity() {
        PagoMultaEntity pagoMultaEntity = super.toEntity();
        pagoMultaEntity.setDestinatario(this.destinatario.toEntity());
        pagoMultaEntity.setRemitente(this.remitente.toEntity());

        return pagoMultaEntity;
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
