/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.tablemodel;

import com.ferrus.apisac.model.Marca;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ramiro
 */
public class MarcaTableModel extends AbstractTableModel {

    List<Marca> marcaList;
    private String[] colNames = {"Id", "Descripcion"};

    public MarcaTableModel() {
        marcaList = new ArrayList<>();
    }

    @Override
    public String getColumnName(int i) {
        return this.colNames[i];
    }

    @Override
    public int getRowCount() {
        return this.marcaList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Marca marca = this.marcaList.get(rowIndex);
        switch (colIndex) {
            case 0: {
                return marca.getId();
            }
            case 1: {
                return marca.getDescripcion();
            }
            default: {
                return null;
            }
        }
    }

    public void setMarcaList(List<Marca> marcaList) {
        this.marcaList = marcaList;
    }

    public void updateTable() {
        fireTableDataChanged();
    }
}
