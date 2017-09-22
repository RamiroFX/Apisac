/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ramiro Ferreira
 */
@Entity(name = "ProductoCategoria")
@Table(name = "productocategoria")
@NamedQueries({
    @NamedQuery(name = "productoCategoria.obtenerProductoCategoriaNombre", query = "SELECT pc FROM ProductoCategoria pc WHERE pc.descripcion LIKE :descripcion")
})
public class ProductoCategoria implements Serializable {

    private static long SerialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "descripcion", unique = true, nullable = false, length = 50)
    private String descripcion;
    @OneToMany(mappedBy = "productoCategoria", cascade = CascadeType.ALL)
    private List<ProductoSubCategoria> subcategorias;

    public ProductoCategoria() {
        this.subcategorias = new ArrayList<>();
    }

    public ProductoCategoria(String descripcion) {
        this();
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

    public List<ProductoSubCategoria> getSubCategorias() {
        return subcategorias;
    }

    public void setSubCategorias(List<ProductoSubCategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }
}
