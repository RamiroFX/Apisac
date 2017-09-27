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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Ramiro Ferreira
 */
@Entity(name = "UnidadMedidaCategoria")
@Table(name = "unidad_medida_categoria")
@NamedQueries({
    @NamedQuery(name = "UnidadMedidaCategoria.obtenerCategorias", query = "SELECT m FROM UnidadMedidaCategoria m WHERE LOWER(m.descripcion) LIKE LOWER(:descripcion)")
})
public class UnidadMedidaCategoria implements Serializable {

    private static long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "descripcion", unique = true, nullable = false, length = 50)
    private String descripcion;

    public UnidadMedidaCategoria() {
    }

    public UnidadMedidaCategoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }
}
