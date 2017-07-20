/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model;

import com.ferrus.apisac.util.ParamValidationConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ramiro Ferreira
 */
@Entity(name = "Preferencia")
@Table(name = "preferencia")
@NamedQueries({
    @NamedQuery(name = "preferencia.getAll", query = "SELECT p FROM Preferencia p")
    ,
    @NamedQuery(name = "preferencia.getPreferenciaById", query = "SELECT p FROM Preferencia p WHERE p.id = :id")
    ,
    @NamedQuery(name = "preferencia.getPreferenciaByDescripcion", query = "SELECT p FROM Preferencia p WHERE p.descripcion = :descripcion")
    ,
    @NamedQuery(name = "preferencia.getPreferenciaBySeleccionado", query = "SELECT p FROM Preferencia p WHERE p.seleccionado = :seleccionado")
})
public class Preferencia implements Serializable {

    @Id
    @GeneratedValue
    int id;
    @Column(name = "descripcion", unique = true, nullable = false, length = 100)
    @NotNull(message = ParamValidationConstants.NOT_NULL)
    @Size(max = 100, message = ParamValidationConstants.SIZE)
    String descripcion;
    //
    @Column(name = "seleccionado", nullable = false, length = 1)
    @NotNull(message = ParamValidationConstants.NOT_NULL)
    @Size(max = 1, message = ParamValidationConstants.SIZE)
    String seleccionado;

    public Preferencia() {
    }

    public Preferencia(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
