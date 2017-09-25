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
@Entity(name = "MateriaPrima")
@Table(name = "materia_prima")
@NamedQueries({
    @NamedQuery(name = "materiaPrima.obtenerMateriasPrimas", query = "SELECT mp FROM MateriaPrima mp")
    ,
    @NamedQuery(name = "materiaPrima.obtenerMateriasPrimasNombre", query = "SELECT mp FROM MateriaPrima mp WHERE LOWER(mp.nombre) LIKE :nombre")
})
public class MateriaPrima implements Serializable {

    private static long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre", unique = true, length = 50, nullable = false)
    private String nombre;
    @Column(name = "descripcion", length = 150)
    private String descripcion;
    @Column(name = "precio", unique = true, length = 10, nullable = false)
    private Double precio;
    @ManyToOne
    private UnidadMedida unidadMedida;

    public MateriaPrima() {
    }

    public MateriaPrima(String nombre, String descripcion, Double precio, UnidadMedida unidadMedida) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidadMedida = unidadMedida;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return getNombre() + "-" + getUnidadMedida();
    }

}
