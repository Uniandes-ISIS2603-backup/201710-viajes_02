/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.CobroEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wr.ravelo
 */
@XmlRootElement
public class CobroDTO
{

    /**
     * Id del cobro
     */
    protected Long id;

    /**
     * Valor del cobro
     */
    protected Long valor;

    /**
     * Atributo que representa si el cobro ya fue cancelado.
     */
    protected Boolean cancelado;

    /**
     * Constructor que crea un DTO de acuerdo a una entidad.
     *
     * @param en Entidad de la cual se va a crear el DTO
     */
    public CobroDTO(CobroEntity en)
    {
        if (en == null)
        {
            return;
        }

        this.id = en.getId();
        this.valor = en.getValor();
        this.cancelado = en.getCancelado();
    }

    /**
     * Constructor vacio
     */
    public CobroDTO()
    {

    }

    /**
     * Da el id del cobro.
     *
     * @return El id del cobro
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica el id del cobro
     *
     * @param id El nuevo id.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Da el valor del cobro
     *
     * @return El valor del cobro
     */
    public Long getValor()
    {
        return valor;
    }

    /**
     * Modifica el valor del cobro
     *
     * @param cobro Nuevo valor del cobro
     */
    public void setValor(Long cobro)
    {
        this.valor = cobro;
    }

    /**
     * Mira si el cobro fue cancelado
     *
     * @return True si fue cancelado el cobro, false de lo contrario.
     */
    public Boolean getCancelado()
    {
        return cancelado;
    }

    /**
     * Modifica el valor de cancelado
     *
     * @param cancelado Nuevo calor de cancelado
     */
    public void setCancelado(Boolean cancelado)
    {
        this.cancelado = cancelado;
    }

    /**
     * Crea una entidad de acuerdo a la informacion del DTO
     *
     * @return El dto convertido en entity.
     */
    public CobroEntity toEntity()
    {
        CobroEntity x = new CobroEntity();
        x.setValor(this.getValor());
        x.setCancelado(this.getCancelado());
        x.setId(this.getId());
        return x;
    }
}
