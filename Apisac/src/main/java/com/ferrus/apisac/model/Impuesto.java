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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Ramiro Ferreira
 */
@Entity(name = "Impuesto")
@Table(name = "impuesto")
@NamedQueries({
    @NamedQuery(name = "impuesto.obtenerImpuestos", query = "SELECT i FROM Impuesto i ")
    ,
    @NamedQuery(name = "impuesto.obtenerImpuestoValor", query = "SELECT i FROM Impuesto i WHERE i.valor = :valor")
})
public class Impuesto implements Serializable {

    private static long SerialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "valor", unique = true, nullable = false, length = 6)
    private Double valor;

    public Impuesto() {
    }

    public Impuesto(Double valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return getValor().toString();
    }
}
