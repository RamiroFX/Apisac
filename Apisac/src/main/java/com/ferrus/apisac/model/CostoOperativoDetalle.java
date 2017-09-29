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
@Entity(name = "CostoOperativoDetalle")
@Table(name = "Costo_operativo_detalle")
@NamedQueries({
    @NamedQuery(name = "costoOperativoDetalle.obtenerCostosOperativosDetalles", query = "SELECT cod FROM CostoOperativoDetalle cod WHERE cod.precioProducto.id = :idPrecioProducto")
})
public class CostoOperativoDetalle implements Serializable {

    private static long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_costo_operativo", nullable = false)
    private CostoOperativo costoOperativo;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_precio", nullable = false)
    private Precio precioProducto;
    @Column(name = "cantidad", nullable = false, length = 10)
    private Double cantidad;
    @Column(name = "precio", nullable = false, length = 10)
    private Double precioCostoOperativo;

    public CostoOperativoDetalle() {
    }

    public CostoOperativoDetalle(CostoOperativo costoOperativo, Precio precioProducto, Double cantidad, Double precioMateriaPrima) {
        this.costoOperativo = costoOperativo;
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
        this.precioCostoOperativo = precioMateriaPrima;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CostoOperativo getCostoOperativo() {
        return costoOperativo;
    }

    public void setCostoOperativo(CostoOperativo costoOperativo) {
        this.costoOperativo = costoOperativo;
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

    public Double getPrecioCostoOperativo() {
        return precioCostoOperativo;
    }

    public void setPrecioCostoOperativo(Double precioCostoOperativo) {
        this.precioCostoOperativo = precioCostoOperativo;
    }

    public Double subTotal() {
        return getCantidad() * getPrecioCostoOperativo();
    }

    @Override
    public String toString() {
        return getPrecioCostoOperativo() + " " + getCantidad() + " " + getCostoOperativo();
    }

}
