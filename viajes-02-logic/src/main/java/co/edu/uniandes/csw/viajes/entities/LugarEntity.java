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

/**
 *
 * @author wr.ravelo
 */
@Entity
public class LugarEntity implements Serializable {

    /**
     * Id del lugar
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre del lugar
     */
    private String lugar;
    
    /**
     * Direccion del lugar
     */
    private String direccion;
    
    /**
     * Latitud del lugar
     */
    private Double lat;
    
    /**
     * Longitud del lugar
     */
    private Double lon;
    
    /**
     * Ruta de la imagen del lugar;
     */
    private String rutaImagen;
    
    /**
     * Da el id del lugar
     * @return Id del lugar
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el valor del id.
     * @param id Nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Da el nombre del lugar
     * @return Nombre del lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Modifica el nombre del lugar
     * @param lugar Nuevo nombre del lugar
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Da la direccion del lugar
     * @return Direccion del lugar
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica la direccion del lugar
     * @param direccion Nuevo valor de la direccion 
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Da la latitud del lugar
     * @return Latitud del lugar
     */
    public Double getLat() {
        return this.lat;
    }
    
    /**
     * Da la longitud del lugar
     * @return Longitud del lugar
     */
    public Double getLon() {
        return this.lon;
    }
    
    /**
     * Modifica la latitud del lugar
     * @param lat Nuevo valor de la latitud
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    /**
     * Modifica la longitud del lugar
     * @param lon Nuevo valor de la longitud
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }
    
    /**
     * Da la ruta de imagen del lugar
     * @return Ruta de imagen del lugar
     */
    public String getRutaImagen() {
        return rutaImagen;
    }
    
    /**
     * Cambia la ruta imagen del lugar.
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
    /**
     * Compara dos objetos para verificar su igualdad
     * @param other Objeto con el que se va a comparar el actual.
     * @return True si son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object other) {
        return this.id.equals(((LugarEntity) other).getId());
    }
    
    /**
     * Da un valor unico con el que se identifica el objeto.
     * @return Valor unico con el que se identifica el objeto.
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}