/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author n.aguilar
 */
@Entity
public class UsuarioEntity implements Serializable {

    /*
    * Id del usuario 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    * Nombre del usuario 
     */
    private String nombre;

    /*
    * Genero del usuario 
     */
    private String genero;

    /*
    * Telefono movil del usuario 
     */
    private Integer telMovil;

    /*
    * Edad del usuario 
     */
    private Integer edad;

    /*
    * Rating del usuario 
     */
    private Double rating;

    /*
    * correo del usuario 
     */
    private String correo;

    /*
    * Direccion especifica del usuario 
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    private LugarEntity direccion;

    /*
    * Lista de cobros relacionados con el usuario 
     */
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<CobroEntity> cobros;

    /*
    * Reviews realizadas sobre el usuario
     */
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ReviewEntity> reviews;

    /*
    * Pagos relacionados con el usuario 
     */
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PagoEntity> pagos;

    /*
    * Retorna los pagos que estan relacionados con el usuario
    * @return pagos los pagos del usuario
     */
    public List<PagoEntity> getPagos() {
        return pagos;
    }

    /*
    * Modifica los pagos que estan relacionados con el usuario
     */
    public void setPagos(List<PagoEntity> pagos) {
        this.pagos = pagos;
    }

    /*
    * Retorna las reviews realizadas sobre el usuario
    * @return reviews 
     */
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    /*
    * Modifica las reviews sobre el usuario por las que llegan por parametro
     */
    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    /*
    * Retorna la direccion especidica del usuario
    * @return direccion
     */
    public LugarEntity getLugar() {
        return direccion;
    }

    /*
    * Retorna los cobros que estan relacionados con el usuario
    * @return cobros los cobros del usuario
     */
    public List<CobroEntity> getCobros() {
        return cobros;
    }

    /*
    * Modifica los cobros del usuario por los que llegan por parametro
     */
    public void setCobros(List<CobroEntity> cobros) {
        this.cobros = cobros;
    }

    /*
    * Modifica la direccion del usuario por la que llega por parametro
     */
    public void setLugar(LugarEntity lugar) {
        this.direccion = lugar;
    }

    /*
    * Retorna el id del usuario en el sistema
    * @return id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
    * Retorna la direccion especifica del usuario
    * @return direccion del usuario
     */
    public LugarEntity getDireccion() {
        return direccion;
    }

    /*
    * Modifica la direccion del usuario por la que llega por parametro
     */
    public void setDireccion(LugarEntity direccion) {
        this.direccion = direccion;
    }

    /*
    * Retorna el nombre del usuario
    * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /*
    * Modifica el nombre del usuario por el que llega por parametro
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
    * Retorna el genero del usuario
    * @return genero
     */
    public String getGenero() {
        return genero;
    }

    /*
    * Modifica el genero del usuario por el que llega por parametro
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /*
    * Retorna el telefono movil del usuario
    * @return telMovil
     */
    public Integer getTelMovil() {
        return telMovil;
    }

    /*
    * Modifica el telefono movil del usuario por el valor que llega por parametro
     */
    public void setTelMovil(Integer telMovil) {
        this.telMovil = telMovil;
    }

    /*
    * Retorna la edad del usuario
    * @return edad
     */
    public Integer getEdad() {
        return edad;
    }

    /*
    * Modifica la edad del usuario por el valor que llega por parametro
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /*
    * Retorna el rating del usuario
    * @return rating
     */
    public Double getRating() {
        return rating;
    }

    /*
    * Modifica el rating del usuario por el valor que llega por parametro
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /*
    * Retorna el correo del usaurio
    * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /*
    * Modifica el correo del usuario por el valor que llega por parametro
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /*
    * Verifica si el usuario se puede considerar como igual al objeto que llega por parametro
    * return true si se considera igual, false de lo contrario
     */
    @Override
    public boolean equals(Object other) {
        return this.id.equals(((UsuarioEntity) other).getId());
    }

    
    /*
    * Se genera el codigo hash en base al id del usuario
    * Se retorna el codigo hash
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
