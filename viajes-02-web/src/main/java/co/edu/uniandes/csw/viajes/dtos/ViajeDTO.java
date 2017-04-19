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

import co.edu.uniandes.csw.viajes.entities.ViajeEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jm.dominguez
 */
@XmlRootElement
public class ViajeDTO implements Serializable
{

    /**
     * id del viaje
     */
    private Long idViaje;
    /**
     * número de kilometros del viaje.
     */
    private Integer Kilometros;
    /**
     * Cantidad a pagar por una reserva en el viaje.
     */
    private Double gastosCompartidos;
    /**
     * Día y Hora de partida
     */
    private Date diaYHoraPartida;
    /**
     * Dia y Hora de llegada
     */
    private Date diaYHoraLlegada;
    /**
     * Lugar de origen del viaje
     */
    private LugarDTO origen;
    /**
     * Lugar de destino del viaje
     */
    private LugarDTO destino;
    /**
     * Automovil a utilizar en el viaje.
     */
    private AutomovilDTO automovil;
    /**
     * Persona que va a ejercer como conductor a lo largo del viaje
     */
    private ConductorDTO conductor;

    /**
     * Retorna el conductor del viaje
     *
     * @return Conductor del viaje
     */
    public ConductorDTO getConductor()
    {
        return conductor;
    }

    /**
     * Permite cambiar el conductor
     *
     * @param conductor del viaje
     */
    public void setConductor(ConductorDTO conductor)
    {
        this.conductor = conductor;
    }

    /**
     * Retorna el automovil del viaje
     *
     * @return Automovil del viaje
     */
    public AutomovilDTO getAutomovil()
    {
        return automovil;
    }

    /**
     * Asigna el automovil del viaje
     *
     * @param automovil del viaje
     */
    public void setAutomovil(AutomovilDTO automovil)
    {
        this.automovil = automovil;
    }

    /**
     * Retorna el origen del viaje
     *
     * @return Lugar de origen
     */
    public LugarDTO getOrigen()
    {
        return origen;
    }

    /**
     * Establece el origen de viaje
     *
     * @param origen del viaje
     */
    public void setOrigen(LugarDTO origen)
    {
        this.origen = origen;
    }

    /**
     * Establece el destino del viaje
     *
     * @return destino del viaje
     */
    public LugarDTO getDestino()
    {
        return destino;
    }

    /**
     * Establece el destino del viaje
     *
     * @param destino del viaje
     */
    public void setDestino(LugarDTO destino)
    {
        this.destino = destino;
    }

    /**
     * Retorna la id del viaje
     *
     * @return id del viaje
     */
    public Long getIdViaje()
    {
        return idViaje;
    }

    /**
     * Asigna la id del viaje
     *
     * @param idViaje
     */
    public void setIdViaje(Long idViaje)
    {
        this.idViaje = idViaje;
    }

    /**
     * Retorna el número de kilometros del viaje
     *
     * @return Kilometros del viaje
     */
    public Integer getKilometros()
    {
        return Kilometros;
    }

    /**
     * Establece el numero de kilometros del viaje
     *
     * @param Kilometros
     */
    public void setKilometros(Integer Kilometros)
    {
        this.Kilometros = Kilometros;
    }

    /**
     * Retorna el costo de una reserva del viaje
     *
     * @return
     */
    public Double getGastosCompartidos()
    {
        return gastosCompartidos;
    }

    /**
     * Permite ajustar el costo de una reserva en el viaje
     *
     * @param gastosCompartidos
     */
    public void setGastosCompartidos(Double gastosCompartidos)
    {
        this.gastosCompartidos = gastosCompartidos;
    }

    /**
     * Retorna día y hora de partida del viaje
     *
     * @return
     */
    public Date getDiaYHoraPartida()
    {
        return diaYHoraPartida;
    }

    /**
     * Permite ajustar la fecha de partida del viaje
     *
     * @param diaYHoraPartida
     */
    public void setDiaYHoraPartida(Date diaYHoraPartida)
    {
        this.diaYHoraPartida = diaYHoraPartida;
    }

    /**
     * Retorna día y hora de llegada del viaje
     *
     * @return
     */
    public Date getDiaYHoraLlegada()
    {
        return diaYHoraLlegada;
    }

    /**
     * Permite ajustar día y hora de llegada del viaje
     *
     * @param diaYHoraLlegada
     */
    public void setDiaYHoraLlegada(Date diaYHoraLlegada)
    {
        this.diaYHoraLlegada = diaYHoraLlegada;
    }

    /**
     * Constructor Vacío de la calse
     */
    public ViajeDTO()
    {

    }

    /**
     * Constructor para realizar la transformación de entity a DTO.
     *
     * @param entity. Entidad de Viaje a transformar.
     */
    public ViajeDTO(ViajeEntity entity)
    {
        if (entity != null)
        {
            idViaje = entity.getIdViaje();
            Kilometros = entity.getKilometros();
            gastosCompartidos = entity.getGastosCompartidos();
            diaYHoraPartida = entity.getDiaYHoraPartida();
            diaYHoraLlegada = entity.getDiaYHoraPartida();
            origen = new LugarDTO(entity.getOrigen());
            destino = new LugarDTO(entity.getDestino());
            automovil = new AutomovilDTO(entity.getAutomovil());
            conductor = new ConductorDTO(entity.getConductor());

        }
    }

    /**
     * Método para realizar la transformación del dto a una entidad.
     *
     * @return Entity
     */
    public ViajeEntity DTO2Entity()
    {
        ViajeEntity viaje = new ViajeEntity();
        viaje.setIdViaje(idViaje);
        viaje.setKilometros(Kilometros);
        viaje.setGastosCompartidos(gastosCompartidos);
        viaje.setDiaYHoraLlegada(diaYHoraLlegada);
        viaje.setDiaYHoraPartida(diaYHoraPartida);
        viaje.setDestino(destino.toEntity());
        viaje.setOrigen(origen.toEntity());
        viaje.setAutomovil(automovil.toEntity());
        viaje.setConductor(conductor.DTO2Entity());

        return viaje;
    }
}
