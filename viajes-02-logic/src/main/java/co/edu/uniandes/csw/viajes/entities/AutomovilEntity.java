/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Danny
 */
@Entity
public class AutomovilEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * atributo id
     */
    private Long id;

    /**
     * atributo placa
     */
    private String placa;

    /**
     * atributo marca
     */
    private String marca;

    /**
     * atributo modelo
     */
    private String modelo;

    /**
     * atributo cant asientos
     */
    private Integer cantAsientos;

    /**
     * atributo color
     */
    private String color;

    /**
     * atributo compañia de Seguros
     */
    private String compSeguros;

    /**
     * atributo numero de seguro
     */
    private Integer numSeguro;
//TODO: verificar porque falla

    private ConductorEntity conductorEntity;

    public ConductorEntity getConductorEntity() {
        return conductorEntity;
    }

    public void setConductorEntity(ConductorEntity conductorEntity) {
        this.conductorEntity = conductorEntity;
    }

    /**
     * dar placa
     *
     * @return placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * dar marca
     *
     * @return marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * dar maodelo
     *
     * @return modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * dar cantidad de asientos
     *
     * @return cant asientos
     */
    public Integer getCantAsientos() {
        return cantAsientos;
    }

    /**
     * dar color
     *
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * dar compSeguros
     *
     * @return
     */
    public String getCompSeguros() {
        return compSeguros;
    }

    /**
     * dar numero de seguro
     *
     * @return numSeguro
     */
    public Integer getNumSeguro() {
        return numSeguro;
    }
/**
     * dar el id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * actualizar placa
     *
     * @param placa
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * actualizar marca
     *
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * actualizar modelo
     *
     * @param modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * actualizar cantidad de asientos
     *
     * @param cantAsientos
     */
    public void setCantAsientos(Integer cantAsientos) {
        this.cantAsientos = cantAsientos;
    }

    /**
     * actualizar color
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * actualizar compañia de seguros
     *
     * @param compSeguros
     */
    public void setCompSeguros(String compSeguros) {
        this.compSeguros = compSeguros;
    }

    /**
     * actualizar numero de seguro
     *
     * @param numSeguro
     */
    public void setNumSeguro(Integer numSeguro) {
        this.numSeguro = numSeguro;
    }

    /**
     * actualizar id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * verifica si son iguales las placas
     *
     * @param obj
     * @return booleaN
     */
    public boolean equals(Object obj) {
        if (this.getPlaca() != null) {
            return this.getPlaca().equals(((AutomovilEntity) obj).getPlaca());
        }
        return super.equals(obj);
    }

    /**
     * genera el hascode
     *
     * @return int
     */
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
