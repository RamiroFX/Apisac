/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ramiro Ferreira
 */
@Entity(name = "Precio")
@Table(name = "precio")
public class Precio implements Serializable {

    private static long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "precio")
    private Producto producto;
    @Column(name = "utilidad", nullable = false, length = 10)
    private Double utilidad;
    @Column(name = "unid_prod", nullable = false, length = 10)
    private Double unidadesProducidas;
    @OneToMany(mappedBy = "precioProducto")
    private List<MateriaPrimaDetalle> materiaPrimaDetalles;
    @OneToMany(mappedBy = "precioProducto")
    private List<CostoOperativoDetalle> costoOperativoDetalles;

    public Precio() {
        this.materiaPrimaDetalles = new ArrayList<>();
        this.costoOperativoDetalles = new ArrayList<>();
    }

    public Precio(Producto producto, Double utilidad, List<MateriaPrimaDetalle> materiaPrimaDetalles, List<CostoOperativoDetalle> costoOperativoDetalles) {
        super();
        this.producto = producto;
        this.utilidad = utilidad;
        this.materiaPrimaDetalles = materiaPrimaDetalles;
        this.costoOperativoDetalles = costoOperativoDetalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(Double utilidad) {
        this.utilidad = utilidad;
    }

    public void setUnidadesProducidas(Double unidadesProducidas) {
        this.unidadesProducidas = unidadesProducidas;
    }

    public Double getUnidadesProducidas() {
        return unidadesProducidas;
    }

    public List<CostoOperativoDetalle> getCostoOperativoDetalles() {
        return costoOperativoDetalles;
    }

    public void setCostoOperativoDetalles(List<CostoOperativoDetalle> costoOperativoDetalles) {
        this.costoOperativoDetalles = costoOperativoDetalles;
    }

    public void setMateriaPrimaDetalles(List<MateriaPrimaDetalle> materiaPrimaDetalles) {
        this.materiaPrimaDetalles = materiaPrimaDetalles;
    }

    public List<MateriaPrimaDetalle> getMateriaPrimaDetalles() {
        return materiaPrimaDetalles;
    }

    @Override
    public String toString() {
        return getProducto() + " " + getUtilidad();
    }

    public Double costoVariableTotal() {
        Double costoVariableTotal = 0.0;
        for (MateriaPrimaDetalle materiaPrimaDetalle : getMateriaPrimaDetalles()) {
            costoVariableTotal = costoVariableTotal + materiaPrimaDetalle.subTotal();
        }
        return costoVariableTotal;
    }

    public Double costoFijoTotal() {
        Double costoFijoTotal = 0.0;
        for (CostoOperativoDetalle costoOperativoDetalle : getCostoOperativoDetalles()) {
            costoFijoTotal = costoFijoTotal + costoOperativoDetalle.subTotal();
        }
        return costoFijoTotal;
    }

    public Double costoProduccionUnitario() {
        return costoVariableTotal() / getUnidadesProducidas();
    }

    public Double costoOperativoUnitario() {
        return costoFijoTotal() / getUnidadesProducidas();
    }

    public Double costoTotal() {
        return costoFijoTotal() + costoVariableTotal();
    }

    public Double costoTotalUnitario() {
        return costoProduccionUnitario() + costoOperativoUnitario();
    }

    public Double calcularUtilida() {
        return (getUtilidad() * costoTotalUnitario()) / 100;
    }

    public Double precioVentaSinImpuesto() {
        return costoTotalUnitario() + calcularUtilida();
    }

    public Double precioVentaConImpuesto(Double impuesto) {
        return precioVentaSinImpuesto() + ((impuesto * precioVentaSinImpuesto()) / 100);
    }
}
