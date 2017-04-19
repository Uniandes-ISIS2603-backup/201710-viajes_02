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

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author n.aguilar
 */
@Entity
public class ViajeroEntity extends UsuarioEntity {

    /**
     * Imagen del viajero
     */
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /*
    * Verifica si el usuario se puede considerar como igual al objeto que llega por parametro
    * return true si se considera igual, false de lo contrario
     */
    @Override
    public boolean equals(Object other) {
        return getId().equals(((ViajeroEntity) other).getId());
    }

    /*
    * Reservas que realiza el usuario
     */
    @OneToMany(cascade = CascadeType.PERSIST)
    public List<ReservaEntity> reservas;

    /*
    * Se obtienen las reservas relacionadas con el usuario
    * return reservas
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /*
    * Se modifican las reservas que hizo el usuario por las que llegan por parametro
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

}
