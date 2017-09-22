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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Ramiro Ferreira
 */
@Entity(name = "MateriaPrimaDetalle")
@Table(name = "materia_prima_detalle")
@NamedQueries({
    @NamedQuery(name = "materiaPrimaDetalle.obtenerMateriasPrimasDetalles", query = "SELECT mpd FROM MateriaPrimaDetalle mpd WHERE mpd.precioProducto.id = :idPrecioProducto")
})
public class MateriaPrimaDetalle implements Serializable {

    private static long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    private MateriaPrima materiaPrima;
    @ManyToOne
    private Precio precioProducto;
    @Column(name = "cantidad", nullable = false, length = 10)
    private Double cantidad;
    @Column(name = "precio", nullable = false, length = 10)
    private Double precioMateriaPrima;

    public MateriaPrimaDetalle() {
    }

    public MateriaPrimaDetalle(MateriaPrima materiaPrima, Precio precioProducto, Double cantidad, Double precioMateriaPrima) {
        this.materiaPrima = materiaPrima;
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
        this.precioMateriaPrima = precioMateriaPrima;
    }

    public Double getPrecioMateriaPrima() {
        return precioMateriaPrima;
    }

    public void setPrecioMateriaPrima(Double precioMateriaPrima) {
        this.precioMateriaPrima = precioMateriaPrima;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Precio getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Precio precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double subTotal() {
        return getCantidad() * getPrecioMateriaPrima();
    }

    @Override
    public String toString() {
        return getPrecioMateriaPrima() + " " + getCantidad() + " " + getMateriaPrima();
    }
}
