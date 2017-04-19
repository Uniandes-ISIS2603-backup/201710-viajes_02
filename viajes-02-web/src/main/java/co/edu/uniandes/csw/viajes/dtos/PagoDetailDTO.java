/* 
 * The MIT License
 *
 * Copyright 2017 wr.ravelo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
