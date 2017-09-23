/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.tablemodel;

import com.ferrus.apisac.model.ProductoSubCategoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ramiro
 */
public class ProductoSubCategoriaTableModel extends AbstractTableModel {

    List<ProductoSubCategoria> productoSubCategoriaList;
    private String[] colNames = {"Id", "Categoría", "Sub-Categoría"};

    public ProductoSubCategoriaTableModel() {
        productoSubCategoriaList = new ArrayList<>();
    }

    @Override
    public String getColumnName(int i) {
        return this.colNames[i];
    }

    @Override
    public int getRowCount() {
        return this.productoSubCategoriaList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        ProductoSubCategoria productoSubCategoria = this.productoSubCategoriaList.get(rowIndex);
        switch (colIndex) {
            case 0: {
                return productoSubCategoria.getId();
            }
            case 1: {
                return productoSubCategoria.getProductoCategoria().getDescripcion();
            }
            case 2: {
                return productoSubCategoria.getDescripcion();
            }
            default: {
                return null;
            }
        }
    }

    public void setProductoCategoriaList(List<ProductoSubCategoria> productoSubCategoriaList) {
        this.productoSubCategoriaList = productoSubCategoriaList;
    }

    public void updateTable() {
        fireTableDataChanged();
    }
}
