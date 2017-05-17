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

import co.edu.uniandes.csw.viajes.entities.LugarEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wr.ravelo
 */
@XmlRootElement
public class LugarDTO
{

    /**
     * Id del lugar
     */
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
     * Ruta de la imagen del lugar
     */
    private String rutaImagen;

    /**
     * Constructor por defecto
     */
    public LugarDTO()
    {

    }

    /**
     * Constructor que crea a partir de una lugar entity.
     *
     * @param en Lugar entity de la que se va a crear un DTO
     */
    public LugarDTO(LugarEntity en)
    {
        this.id = en.getId();
        this.lugar = en.getLugar();
        this.direccion = en.getDireccion();
        this.lat = en.getLat();
        this.lon = en.getLon();
        this.rutaImagen = en.getRutaImagen();
    }

    /**
     * Da el id del lugar
     *
     * @return Id del lugar
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica el valor del id.
     *
     * @param id Nuevo id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Da el nombre del lugar
     *
     * @return Nombre del lugar
     */
    public String getLugar()
    {
        return lugar;
    }

    /**
     * Modifica el nombre del lugar
     *
     * @param lugar Nuevo nombre del lugar
     */
    public void setLugar(String lugar)
    {
        this.lugar = lugar;
    }

    /**
     * Da la direccion del lugar
     *
     * @return Direccion del lugar
     */
    public String getDireccion()
    {
        return direccion;
    }

    /**
     * Modifica la direccion del lugar
     *
     * @param direccion Nuevo valor de la direccion
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    /**
     * Da la latitud del lugar
     *
     * @return Latitud del lugar
     */
    public Double getLat()
    {
        return this.lat;
    }

    /**
     * Da la longitud del lugar
     *
     * @return Longitud del lugar
     */
    public Double getLon()
    {
        return this.lon;
    }

    /**
     * Modifica la latitud del lugar
     *
     * @param lat Nuevo valor de la latitud
     */
    public void setLat(Double lat)
    {
        this.lat = lat;
    }

    /**
     * Modifica la longitud del lugar
     *
     * @param lon Nuevo valor de la longitud
     */
    public void setLon(Double lon)
    {
        this.lon = lon;
    }
    
    /**
     * Da la ruta imagen del lugar
     * @return 
     */
    public String getRutaImagen() {
        return rutaImagen;
    }
    
    /**
     * Da la ruta imagen del lugar
     * @param rutaImagen Ruta imagen del lugar
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    /**
     * Crea una entidad de acuerdo a la infromacion de DTO
     *
     * @return Entidad con la informacion del DTO
     */
    public LugarEntity toEntity()
    {
        LugarEntity en = new LugarEntity();
        en.setId(this.id);
        en.setLugar(this.lugar);
        en.setDireccion(this.direccion);
        en.setLat(this.lat);
        en.setLon(this.lon);
        en.setRutaImagen(this.rutaImagen);
        return en;
    }
}
