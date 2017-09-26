/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.tablemodel;

import com.ferrus.apisac.model.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ramiro
 */
public class ProductoTableModel extends AbstractTableModel {

    List<Producto> productoList;
    private String[] colNames = {"Id", "Nombre"};

    public ProductoTableModel() {
        productoList = new ArrayList<>();
    }

    @Override
    public String getColumnName(int i) {
        return this.colNames[i];
    }

    @Override
    public int getRowCount() {
        return this.productoList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Producto producto = this.productoList.get(rowIndex);
        switch (colIndex) {
            case 0: {
                return producto.getId();
            }
            case 1: {
                return producto.getNombre();
            }
            default: {
                return null;
            }
        }
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public void updateTable() {
        fireTableDataChanged();
    }
}
