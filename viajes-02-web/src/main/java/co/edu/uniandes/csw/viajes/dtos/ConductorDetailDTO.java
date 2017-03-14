/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.entities.ConductorEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jm.dominguez
 */

@XmlRootElement
public class ConductorDetailDTO extends ConductorDTO{
    
    private List<AutomovilDetailDTO> automoviles;

    public List<AutomovilDetailDTO> getAutomoviles() {
        return automoviles;
    }

    public void setAutomoviles(List<AutomovilDetailDTO> automoviles) {
        this.automoviles = automoviles;
    }
    
    public ConductorDetailDTO(){
        super();
    }
    public ConductorDetailDTO(ConductorEntity entity){
        super(entity);
        automoviles = listEntity2DTO(entity.getAutomoviles());
    }
    
    
    @Override
    public ConductorEntity DTO2Entity(){
        ConductorEntity entity = super.DTO2Entity();
        return entity;
    }
    
    private List<AutomovilDetailDTO> listEntity2DTO( List<AutomovilEntity> entityList )
	{
		List<AutomovilDetailDTO> list = new ArrayList<>( );
		for( AutomovilEntity entity : entityList )
		{
			list.add( new AutomovilDetailDTO( entity ) );
		}
		return list;
	}
}
