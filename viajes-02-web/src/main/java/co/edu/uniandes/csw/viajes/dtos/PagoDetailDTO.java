/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * PagoDetailDTO
 *
 * @author ja.bermudez10
 */
@XmlRootElement
public class PagoDetailDTO extends PagoDTO
{

    /**
     * Atributo que define un usuario en el pagoDetailDTO
     */
    private UsuarioDTO usuario;

    /**
     * Constructor por defecto de PagoDetailDTO
     */
    public PagoDetailDTO()
    {
        super();
    }

    /**
     * Constructor de un PagoDetailDTO segun la entidad Pago
     *
     * @param pagoEntity La entidad Pago
     */
    public PagoDetailDTO(PagoEntity pagoEntity)
    {
        super(pagoEntity);
    }

    /**
     * Retorna un PagoEntity basado en el actual PagoDeatailDTO
     *
     * @return La entidad Pago
     */
    @Override
    public PagoEntity toEntity()
    {
        PagoEntity pagoEntity = super.toEntity();
        pagoEntity.setUsuario(usuario.toEntity());

        return pagoEntity;
    }

    public UsuarioDTO getUsuario()
    {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario)
    {
        this.usuario = usuario;
    }
}
