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
@Entity(name = "ProductoSubCategoria")
@Table(name = "productoSubCategoria")
@NamedQueries({
    @NamedQuery(name = "productoSubCategoria.obtenerProductoSubCategoriaNombre",query = "SELECT psc FROM ProductoSubCategoria psc WHERE psc.descripcion LIKE :descripcion")
})
public class ProductoSubCategoria implements Serializable {

    private static long SerialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "id_producto_categoria")
    private ProductoCategoria productoCategoria;

    public ProductoSubCategoria() {
    }

    public ProductoSubCategoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public ProductoSubCategoria(String descripcion, ProductoCategoria productoCategoria) {
        this.descripcion = descripcion;
        this.productoCategoria = productoCategoria;
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

    public ProductoCategoria getProductoCategoria() {
        return productoCategoria;
    }

    public void setProductoCategoria(ProductoCategoria productoCategoria) {
        this.productoCategoria = productoCategoria;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }
}
