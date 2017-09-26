/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.tablemodel;

import com.ferrus.apisac.model.CostoOperativo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ramiro Ferreira
 */
public class CostoOperativoTableModel extends AbstractTableModel {

    List<CostoOperativo> costoOperativoList;
    private String[] colNames = {"Id", "Nombre", "Unidad medida", "Precio"};

    public CostoOperativoTableModel() {
        costoOperativoList = new ArrayList<>();
    }

    @Override
    public String getColumnName(int i) {
        return this.colNames[i];
    }

    @Override
    public int getRowCount() {
        return this.costoOperativoList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        CostoOperativo costoOperativo = this.costoOperativoList.get(rowIndex);
        switch (colIndex) {
            case 0: {
                return costoOperativo.getId();
            }
            case 1: {
                return costoOperativo.getNombre();
            }
            case 2: {
                return costoOperativo.getUnidadMedida();
            }
            case 3: {
                return costoOperativo.getPrecio();
            }
            default: {
                return null;
            }
        }
    }

    public void setCostoOperativoList(List<CostoOperativo> costoOperativoList) {
        this.costoOperativoList = costoOperativoList;
    }

    public void updateTable() {
        fireTableDataChanged();
    }
}
