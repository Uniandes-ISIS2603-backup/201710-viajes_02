/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.persistence.PagoPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ja.bermudez10
 */
public class PagoLogic {
    @Inject
    private PagoPersistence pagoPersistence;
    
    public PagoEntity createPago(PagoEntity pagoEntity) {
        pagoPersistence.create(pagoEntity);
        return pagoEntity;
    }
    
    public List<PagoEntity> getMisPagos(Long idRemitente) {
        return pagoPersistence.findAllMisPagos(idRemitente);
    }
    
    public PagoEntity getPago(Long idPago) {
        return pagoPersistence.findPago(idPago);
    }
    
    public PagoEntity updatePago(PagoEntity pagoEntity) {
        return pagoPersistence.update(pagoEntity);
    }
    
    public void deletePago(Long id) {
        pagoPersistence.delete(id);
    }
}
