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
@Entity(name = "CostoOperativo")
@Table(name = "costo_operativo")
@NamedQueries({
    @NamedQuery(name="costoOperativo.obtenerCostosOperativos", query="SELECT co FROM CostoOperativo co WHERE co.nombre = :nombre")
})
public class CostoOperativo implements Serializable {

    private static long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre", nullable = false, unique = true, length = 30)
    private String nombre;
    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;
    @Column(name = "precio", nullable = false, length = 10)
    private Double precio;
    @ManyToOne
    private UnidadMedida unidadMedida;
    @Column(name = "dias_laborales", nullable = false, length = 10)
    private int diasLaborales;

    public CostoOperativo() {
    }

    public CostoOperativo(String nombre, String descripcion, Double precio, int diasLaborales) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.diasLaborales = diasLaborales;
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

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public int getDiasLaborales() {
        return diasLaborales;
    }

    public void setDiasLaborales(int diasLaborales) {
        this.diasLaborales = diasLaborales;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
