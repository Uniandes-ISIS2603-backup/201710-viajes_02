/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.CobroMultaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author wr.ravelo
 */
@XmlRootElement
public class CobroMultaDetailDTO extends CobroMultaDTO
{
    /**
     * Usuario destinatario del cobro
     */
    @PodamExclude
    protected UsuarioDTO usuarioDestinatario;
    
    /**
     * Usuario remitente del cobro
     */
    @PodamExclude
    protected UsuarioDTO usuarioRemitente;
    
    /**
     * Crea un cobro multa detail dto
     */
    public CobroMultaDetailDTO()
    {
        super();
    }
       
    /**
     * Crea un cobro multa detail dto de una entidad.
     *
     * @param en Entidad de donde se va a crear el detail dto
     */
    public CobroMultaDetailDTO(CobroMultaEntity en) {
        super(en);
        this.usuarioDestinatario = new UsuarioDTO(en.getUsuarioDestinatario());
        this.usuarioRemitente = new UsuarioDTO(en.getUsuarioRemitente());
    }

    /**
     * Convirte una entidad a cobro multa detail dto.
     *
     * @return
     */
    @Override
    public CobroMultaEntity toEntity()
    {
        CobroMultaEntity x =  super.toEntity();
        x.setUsuarioDestinatario(this.getUsuarioDestinatario().toEntity());
        x.setUsuarioRemitente(this.getUsuarioRemitente().toEntity());
        return x;
    }
    
     /**
     * Da el usuario destinatario
     * @return Usuario destinatario
     */
    public UsuarioDTO getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    /**
     * Modifica el usuario destinatario
     * @param usuarioDestinatario Nuevo usuario destinatario
     */
    public void setUsuarioDestinatario(UsuarioDTO usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }

    /**
     * Da el usuario remitente
     * @return Usuario remitente
     */
    public UsuarioDTO getUsuarioRemitente() {
        return usuarioRemitente;
    }

    /**
     * Modifica el usuario remitente
     * @param usuarioRemitente Nuevo usuario remitente.
     */
    public void setUsuarioRemitente(UsuarioDTO usuarioRemitente) {
        this.usuarioRemitente = usuarioRemitente;
    }
}
