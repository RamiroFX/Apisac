/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.tablemodel;

import com.ferrus.apisac.model.MateriaPrimaDetalle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ramiro Ferreira
 */
public class MateriaPrimaDetalleTableModel extends AbstractTableModel {

    List<MateriaPrimaDetalle> materiaPrimaDetalleList;
    private String[] colNames = {"Id", "Nombre", "Unidad medida", "Precio", "Cantidad", "SubTotal"};

    public MateriaPrimaDetalleTableModel() {
        materiaPrimaDetalleList = new ArrayList<>();
    }

    @Override
    public String getColumnName(int i) {
        return this.colNames[i];
    }

    @Override
    public int getRowCount() {
        return this.materiaPrimaDetalleList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        MateriaPrimaDetalle materiaPrima = this.materiaPrimaDetalleList.get(rowIndex);
        switch (colIndex) {
            case 0: {
                return materiaPrima.getMateriaPrima().getId();
            }
            case 1: {
                return materiaPrima.getMateriaPrima().getNombre();
            }
            case 2: {
                return materiaPrima.getMateriaPrima().getUnidadMedida();
            }
            case 3: {
                return materiaPrima.getMateriaPrima().getPrecio();
            }
            case 4: {
                return materiaPrima.getCantidad();
            }
            case 5: {
                return materiaPrima.getMateriaPrima().getPrecio() * materiaPrima.getCantidad();
            }
            default: {
                return null;
            }
        }
    }

    public void setMateriaPrimaDetalleList(List<MateriaPrimaDetalle> materiaPrimaDetalleList) {
        this.materiaPrimaDetalleList = materiaPrimaDetalleList;
    }

    public List<MateriaPrimaDetalle> getMateriaPrimaDetalleList() {
        return materiaPrimaDetalleList;
    }

    public void agregarMateriaPrima(MateriaPrimaDetalle mpd) {
        this.materiaPrimaDetalleList.add(mpd);
        updateTable();
    }

    public void updateTable() {
        fireTableDataChanged();
    }
}
