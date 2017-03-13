/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author n.aguilar
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO{
    
    public UsuarioDetailDTO()
    {
        super();
    }
    
    public UsuarioDetailDTO(UsuarioEntity entity)
    {
        super(entity);
    }
    
    
    @Override
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = super.toEntity();
        return entity;
    }
}
