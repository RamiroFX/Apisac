/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ramiro Ferreira
 */
@Entity(name = "Producto")
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "producto.obtenerProductos", query = "SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE :nombre")
    ,
    @NamedQuery(name = "producto.obtenerProductosMarca", query = "SELECT p FROM Producto p WHERE p.marca.descripcion LIKE :marca")
    ,
    @NamedQuery(name = "producto.obtenerProductosCategoria", query = "SELECT p FROM Producto p WHERE p.productoCategoria.descripcion LIKE :categoria")
        ,
    @NamedQuery(name = "producto.obtenerProductosImpuesto", query = "SELECT p FROM Producto p WHERE p.impuesto.valor = :valor")
})
public class Producto implements Serializable {

    private static long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "id_precio", nullable = false)
    private Precio precio;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_impuesto", nullable = false)
    private Impuesto impuesto;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_productoCategoria", nullable = false)
    private ProductoCategoria productoCategoria;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_productoSubCategoria", nullable = false)
    private ProductoSubCategoria productoSubCategoria;

    public Producto() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Precio getPrecio() {
        return precio;
    }

    public void setPrecio(Precio precio) {
        this.precio = precio;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public ProductoCategoria getProductoCategoria() {
        return productoCategoria;
    }

    public void setProductoCategoria(ProductoCategoria productoCategoria) {
        this.productoCategoria = productoCategoria;
    }

    public ProductoSubCategoria getProductoSubCategoria() {
        return productoSubCategoria;
    }

    public void setProductoSubCategoria(ProductoSubCategoria productoSubCategoria) {
        this.productoSubCategoria = productoSubCategoria;
    }

}
