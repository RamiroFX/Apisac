/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.tablemodel;

import com.ferrus.apisac.model.MateriaPrima;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ramiro Ferreira
 */
public class MateriaPrimaTableModel extends AbstractTableModel {

    List<MateriaPrima> materiaPrimaList;
    private String[] colNames = {"Id", "Nombre", "Unidad medida", "Precio"};

    public MateriaPrimaTableModel() {
        materiaPrimaList = new ArrayList<>();
    }

    @Override
    public String getColumnName(int i) {
        return this.colNames[i];
    }

    @Override
    public int getRowCount() {
        return this.materiaPrimaList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        MateriaPrima materiaPrima = this.materiaPrimaList.get(rowIndex);
        switch (colIndex) {
            case 0: {
                return materiaPrima.getId();
            }
            case 1: {
                return materiaPrima.getNombre();
            }
            case 2: {
                return materiaPrima.getUnidadMedida();
            }
            case 3: {
                return materiaPrima.getPrecio();
            }
            default: {
                return null;
            }
        }
    }

    public void setMateriaPrimaList(List<MateriaPrima> materiaPrimaList) {
        this.materiaPrimaList = materiaPrimaList;
    }

    public void updateTable() {
        fireTableDataChanged();
    }
}
