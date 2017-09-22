/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.tablemodel;

import com.ferrus.apisac.model.Impuesto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ramiro Ferreira
 */
public class ImpuestoTableModel extends AbstractTableModel {

    List<Impuesto> impuestoList;
    private String[] colNames = {"Id", "Valor"};

    public ImpuestoTableModel() {
        impuestoList = new ArrayList<>();
    }

    @Override
    public String getColumnName(int i) {
        return this.colNames[i];
    }

    @Override
    public int getRowCount() {
        return this.impuestoList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Impuesto impuesto = this.impuestoList.get(rowIndex);
        switch (colIndex) {
            case 0: {
                return impuesto.getId();
            }
            case 1: {
                return impuesto.getValor();
            }
            default: {
                return null;
            }
        }
    }

    public void setImpuestoList(List<Impuesto> impuestoList) {
        this.impuestoList = impuestoList;
    }

    public void updateTable() {
        fireTableDataChanged();
    }
}
