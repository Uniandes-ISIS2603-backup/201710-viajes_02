/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;

/**
 *
 * @author wr.ravelo
 */
public class CobroDTO {
    
     /**
     * Id del cobro
     */
    protected Long id;
    
    /**
     * Valor del cobro
     */
    protected Long valor;
    
    /**
     * Id del usuario remitente al que se le hace el cobro.
     */
    protected Long idRemitente;
    
    /**
     * Id del usuario destinatario al que se le hace el cobro.
     */
    protected Long idDestinatario;
    
     /**
     * Atributo que representa si el cobro ya fue cancelado.
     */
    protected Boolean cancelado;

    /**
     * Constructor que crea un DTO de acuerdo a una entidad.
     * @param en Entidad de la cual se va a crear el DTO
     */
    public CobroDTO(CobroEntity en) {
        if(en == null)
            return;

        this.id = en.getId();
        this.valor = en.getValor();
        this.idDestinatario = en.getIdDestinatario();
        this.idRemitente = en.getIdRemitente();
        this.cancelado = en.getCancelado();
    }
    
    /**
     * Constructor vacio
     */
    public CobroDTO() {
        
    }
    
    /**
     * Da el id del cobro.
     * @return El id del cobro
     */
    public Long getId() {
        return id;
    }

     /**
     * Modifica el id del cobro
     * @param id El nuevo id.
     */
    public void setId(Long id) {
        this.id = id;
    }

     /**
     * Da el valor del cobro
     * @return El valor del cobro
     */
    public Long getValor() {
        return valor;
    }

    /**
     * Modifica el valor del cobro
     * @param cobro Nuevo valor del cobro
     */
    public void setValor(Long cobro) {
        this.valor = cobro;
    }

    /**
     * Id del remitente al que se le hace el cobro.
     * @return Id remitente.
     */
    public Long getIdRemitente() {
        return idRemitente;
    }

    /**
     * Modifica el id remitente del cobro
     * @param idRemitente Nuevo id del remitente
     */
    public void setIdRemitente(Long idRemitente) {
        this.idRemitente = idRemitente;
    }

    /**
     * Da el id del destinatario al que se le hace el cobro.
     * @return Nuevo id destinatario
     */
    public Long getIdDestinatario() {
        return idDestinatario;
    }

   /**
     * Modifica el id del destinatario.
     * @param idDestinatario Nuevo id destinatario
     */
    public void setIdDestinatario(Long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    /**
     * Mira si el cobro fue cancelado
     * @return True si fue cancelado el cobro, false de lo contrario.
     */
    public Boolean getCancelado() {
        return cancelado;
    }

    /**
     * Modifica el valor de cancelado
     * @param cancelado Nuevo calor de cancelado
     */
    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }
    
    /**
     * Crea una entidad de acuerdo a la informacion del DTO
     * @return El dto convertido en entity.
     */
    public CobroEntity toEntity() {        
        CobroEntity x = new CobroEntity();
        x.setValor(this.getValor());
        x.setCancelado(this.getCancelado());
        x.setId(this.getId());
        x.setIdDestinatario(this.getIdDestinatario());
        x.setIdRemitente(this.getIdRemitente());
        return x;
    }
    
}
