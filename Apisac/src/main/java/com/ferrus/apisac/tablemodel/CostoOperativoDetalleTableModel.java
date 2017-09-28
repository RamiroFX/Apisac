/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.tablemodel;

import com.ferrus.apisac.model.CostoOperativoDetalle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ramiro Ferreira
 */
public class CostoOperativoDetalleTableModel extends AbstractTableModel {

    List<CostoOperativoDetalle> costoOperativoDetalle;
    private String[] colNames = {"Id", "Nombre", "Unidad medida", "Precio", "Cantidad", "SubTotal"};

    public CostoOperativoDetalleTableModel() {
        costoOperativoDetalle = new ArrayList<>();
    }

    @Override
    public String getColumnName(int i) {
        return this.colNames[i];
    }

    @Override
    public int getRowCount() {
        return this.costoOperativoDetalle.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        CostoOperativoDetalle costoOperativo = this.costoOperativoDetalle.get(rowIndex);
        switch (colIndex) {
            case 0: {
                return costoOperativo.getCostoOperativo().getId();
            }
            case 1: {
                return costoOperativo.getCostoOperativo().getNombre();
            }
            case 2: {
                return costoOperativo.getCostoOperativo().getUnidadMedida();
            }
            case 3: {
                return costoOperativo.getPrecioCostoOperativo();
            }
            case 4: {
                return costoOperativo.getCantidad();
            }
            case 5: {
                return costoOperativo.getPrecioCostoOperativo() * costoOperativo.getCantidad();
            }
            default: {
                return null;
            }
        }
    }

    public void setCostoOperativoDetalleList(List<CostoOperativoDetalle> costoOperativoDetalle) {
        this.costoOperativoDetalle = costoOperativoDetalle;
    }

    public List<CostoOperativoDetalle> getCostoOperativoDetalleList() {
        return costoOperativoDetalle;
    }

    public void agregarCostoOperativo(CostoOperativoDetalle cod) {
        this.costoOperativoDetalle.add(cod);
        updateTable();
    }

    public void updateTable() {
        fireTableDataChanged();
    }
}
