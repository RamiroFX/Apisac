/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Ramiro Ferreira
 */
@Entity(name = "UnidadMedida")
@Table(name = "unidad_medida")
@NamedQueries({
    @NamedQuery(name = "unidadMedida.getUnidadesMedida", query = "SELECT um FROM UnidadMedida um")
    ,
    @NamedQuery(name = "unidadMedida.getUnidadesMedidaPorCategoria", query = "SELECT um FROM UnidadMedida um WHERE um.medidaCategoria.descripcion LIKE :descripcion")
})
public class UnidadMedida implements Serializable {

    private static long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre", nullable = false, unique = true, length = 30)
    private String nombre;
    @Column(name = "simbolo", nullable = false, unique = true, length = 10)
    private String simbolo;
    @Column(name = "valor", nullable = false, length = 10)
    private Double valor;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_um_categoria", nullable = false)
    private UnidadMedidaCategoria medidaCategoria;

    public UnidadMedida() {
    }

    public UnidadMedida(String nombre, String simbolo, Double valor, UnidadMedidaCategoria medidaCategoria) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.valor = valor;
        this.medidaCategoria = medidaCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public UnidadMedidaCategoria getMedidaCategoria() {
        return medidaCategoria;
    }

    public void setMedidaCategoria(UnidadMedidaCategoria medidaCategoria) {
        this.medidaCategoria = medidaCategoria;
    }

    @Override
    public String toString() {
        return getNombre() + " - " + getSimbolo();
    }

}
