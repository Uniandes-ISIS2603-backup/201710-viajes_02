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
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * ReservaEntity
 * @author ja.bermudez10
 */
@Entity
public class ReservaEntity implements Serializable {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     *
     */
    private Double precio;

    /**
     *
     */
    private Double valorComision;

    /**
     *
     */
    private Integer puestosReservados;
    
    /**
     *
     */
    @ManyToOne
    private ViajeroEntity viajero;
    
    /**
     *
     */
    @ManyToOne
    private ViajeEntity viaje;
    
    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     *
     * @return
     */
    public Double getValorComision() {
        return valorComision;
    }

    /**
     *
     * @param valorComision
     */
    public void setValorComision(Double valorComision) {
        this.valorComision = valorComision;
    }

    /**
     *
     * @return
     */
    public Integer getPuestosReservados() {
        return puestosReservados;
    }

    /**
     *
     * @param puestosReservados
     */
    public void setPuestosReservados(Integer puestosReservados) {
        this.puestosReservados = puestosReservados;
    }
    
    /**
     * 
     * @return 
     */
    public ViajeroEntity getViajero() {
        return viajero;
    }

    /**
     * 
     * @param viajero 
     */
    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }
    
    /**
     *
     * @return
     */
    public ViajeEntity getViaje() {
        return viaje;
    }

    /**
     *
     * @param viaje
     */
    public void setViaje(ViajeEntity viaje) {
        this.viaje = viaje;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((ReservaEntity) obj).getId());
        }
        return super.equals(obj);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

}
