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
    @OneToMany(mappedBy = "materiaPrima")
    private List<MateriaPrimaDetalle> materiaPrimaDetalles;
    @OneToMany(mappedBy = "costoOperativo")
    private List<CostoOperativoDetalle> costoOperativoDetalles;

    public Precio() {
        this.materiaPrimaDetalles = new ArrayList<>();
        this.costoOperativoDetalles = new ArrayList<>();
    }

    public Precio(Producto producto, Double utilidad) {
        this.producto = producto;
        this.utilidad = utilidad;
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

    public Double costoUnitarioProduccion() {
        Double subTotalMateriaPrima = 0.0;
        for (MateriaPrimaDetalle materiaPrimaDetalle : getMateriaPrimaDetalles()) {
            subTotalMateriaPrima = subTotalMateriaPrima + materiaPrimaDetalle.subTotal();
        }
        return subTotalMateriaPrima / getUnidadesProducidas();
    }

    public Double costoUnitarioOperativo() {
        Double subTotalCostoOperativo = 0.0;
        for (CostoOperativoDetalle costoOperativoDetalle : getCostoOperativoDetalles()) {
            subTotalCostoOperativo = subTotalCostoOperativo + costoOperativoDetalle.subTotal();
        }
        return subTotalCostoOperativo / getUnidadesProducidas();
    }

    public Double costoTotalVenta() {
        return costoUnitarioProduccion() + costoUnitarioOperativo();
    }

    public Double calcularUtilida() {
        return costoTotalVenta() / (100 * getUtilidad());
    }

    public Double precioVentaSinImpuesto() {
        return costoTotalVenta() + calcularUtilida();
    }

    public Double precioVentaConImpuesto() {
        return precioVentaSinImpuesto() * (1 + getProducto().getImpuesto().getValor());
    }
}
