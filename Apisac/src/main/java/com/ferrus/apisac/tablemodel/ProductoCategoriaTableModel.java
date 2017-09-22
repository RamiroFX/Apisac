/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.tablemodel;

import com.ferrus.apisac.model.ProductoCategoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ramiro Ferreira
 */
public class ProductoCategoriaTableModel extends AbstractTableModel {

    List<ProductoCategoria> productoCategoriaList;
    private String[] colNames = {"Id", "Descripcion"};

    public ProductoCategoriaTableModel() {
        productoCategoriaList = new ArrayList<>();
    }

    @Override
    public String getColumnName(int i) {
        return this.colNames[i];
    }

    @Override
    public int getRowCount() {
        return this.productoCategoriaList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        ProductoCategoria productoCategoria = this.productoCategoriaList.get(rowIndex);
        switch (colIndex) {
            case 0: {
                return productoCategoria.getId();
            }
            case 1: {
                return productoCategoria.getDescripcion();
            }
            default: {
                return null;
            }
        }
    }

    public void setProductoCategoriaList(List<ProductoCategoria> productoCategoriaList) {
        this.productoCategoriaList = productoCategoriaList;
    }

    public void updateTable() {
        fireTableDataChanged();
    }
}
