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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jm.dominguez
 */

@Entity
public class ConductorEntity extends UsuarioEntity{

    /**
     * Imagen del conductor
     */
    private String imagen;
    /**
     * Reviews del conductor
     */
    @PodamExclude
    @OneToMany
    private List <ReviewEntity> reviews;
    /**
     * Automoviles del conductor.
     */
    @PodamExclude
    
    @OneToMany(mappedBy = "conductorEntity")
    private List<AutomovilEntity> automoviles;
    /**
     * Viajes del conductor.
     */
    @PodamExclude
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "conductor")
    private List <ViajeEntity> viajes;
    /**
     * Retorna los viajes del conductor
     * @return List: ista de viajes del conductor
     */
    public List<ViajeEntity> getViajes() {
        return viajes;
    }
    /**
     * Asigna una lista de viajes al conductor
     * @param viajes: Lista de viajes del conductor.
     */
    public void setViajes(List<ViajeEntity> viajes) {
        this.viajes = viajes;
    }
    
    
    /**
     * Rtorna la lista de automoviles del conductor.
     * @return automoviles: Lista de automoviles
     */
    public List<AutomovilEntity> getAutomoviles() {
        return automoviles;
    }
    /**
     * Asigna la lista de automoviles del conductor.
     * @param automoviles: Lista de automoviles.
     */
    public void setAutomoviles(List<AutomovilEntity> automoviles) {
        this.automoviles = automoviles;
    }
    /**
     * Retorna los reviews del conductor.
     */
    public List<ReviewEntity> getReviews(){
        return reviews;
    }
    /**
     * Asigan reviews al conductor.
     */
    public void setReviews(List <ReviewEntity> reviews){
        this.reviews = reviews;
    }
    /**
     * Re-escripción del metodo equals
     * @param obj : Objeto a comparar
     * @return true si los objetos son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object obj){
        ConductorEntity conductor = (ConductorEntity) obj;
        
        return this.getId().equals(conductor.getId());
    }
    /**
     * HashCode re-escrito
     * @return int: hashCode del objeto
     */
    @Override
    public int hashCode(){
        if(this.getId() != null){
            return this.getId().hashCode();
        }
        
        return super.hashCode();
    }
    
    public String getImagen(){
        return this.imagen;
    }
    
    public void setImagen(String imagen){
        this.imagen = imagen;
    }
    
    
}
