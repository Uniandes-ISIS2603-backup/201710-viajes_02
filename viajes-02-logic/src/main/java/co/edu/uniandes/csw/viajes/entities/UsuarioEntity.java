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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String genero;

    private Integer telMovil;

    private Integer edad;

    private Double rating;

    private String correo;

    @OneToOne(cascade = CascadeType.PERSIST)
    private LugarEntity direccion;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<CobroEntity> cobros;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PagoEntity> pagos;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ReviewEntity> reviews;
    
    public List<PagoEntity> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoEntity> pagos) {
        this.pagos = pagos;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public LugarEntity getLugar() {
        return direccion;
    }

    public List<CobroEntity> getCobros() {
        return cobros;
    }

    public void setCobros(List<CobroEntity> cobros) {
        this.cobros = cobros;
    }

    public void setLugar(LugarEntity lugar) {
        this.direccion = lugar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LugarEntity getDireccion() {
        return direccion;
    }

    public void setDireccion(LugarEntity direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(Integer telMovil) {
        this.telMovil = telMovil;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public boolean equals(Object other) {
        return this.id.equals(((UsuarioEntity) other).getId());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
