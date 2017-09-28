/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.inicio;

import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.service.ProductoParametrosService;
import com.ferrus.apisac.model.serviceImp.ProductoParametrosServImpl;
import com.ferrus.apisac.tablemodel.ProductoTableModel;
import static com.ferrus.apisac.util.AppUIConstants.*;
import com.ferrus.apisac.util.PackColumn;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Ramiro Ferreira
 */
public class PanelPrincipal extends JPanel {

    private JPanel jpProductos, jpBotones, jpPrecio;
    public JButton jbCrear, jbModificar, jbBorrar, jbExportar, jbParametros,
            jbBuscar, jbMateriaPrima, jbCostoOperativo;
    public JTextField jtfBuscar;
    public JTable jtProductos;
    public JScrollPane jspProductos;
    public ProductoParametrosService servicio;
    public ProductoTableModel productoTableModel;

    public PanelPrincipal() {
        setLayout(new BorderLayout());
        initializeVariables();
        constructLayout();
        loadData();
    }

    private void initializeVariables() {
        this.servicio = new ProductoParametrosServImpl();
        this.productoTableModel = new ProductoTableModel();
        this.jpProductos = new JPanel(new BorderLayout());
        this.jpProductos.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        this.jpPrecio = new JPanel();
        this.jpBotones = new JPanel();
        this.jpBotones.setBorder(new EtchedBorder());
        this.jbCrear = new JButton(CREATE_PRODUCT_BUTTON_NAME);
        this.jbModificar = new JButton(MODIFY_PRODUCT_BUTTON_NAME);
        this.jbModificar.setEnabled(false);
        this.jbBorrar = new JButton(DELETE_PRODUCT_BUTTON_NAME);
        this.jbBorrar.setEnabled(false);
        this.jbExportar = new JButton(EXPORT_PRODUCT_BUTTON_NAME);
        this.jbExportar.setEnabled(false);
        this.jbParametros = new JButton(PARAM_BUTTON_NAME);
        this.jbMateriaPrima = new JButton(RAW_MATERIAL_BUTTON_NAME);
        this.jbCostoOperativo = new JButton(OPERATIVE_COST_BUTTON_NAME);
        this.jbBuscar = new JButton(SEARCH_BUTTON_NAME);
        this.jtfBuscar = new JTextField(15);
        this.jtProductos = new JTable();
        this.jspProductos = new JScrollPane(jtProductos);
    }

    private void constructLayout() {
        this.jpBotones.add(jbCrear);
        this.jpBotones.add(jbModificar);
        this.jpBotones.add(jbBorrar);
        this.jpBotones.add(jbExportar);
        this.jpBotones.add(jbParametros);
        this.jpBotones.add(jbMateriaPrima);
        this.jpBotones.add(jbCostoOperativo);
        JPanel jpBuscar = new JPanel();
        jpBuscar.setBorder(new BevelBorder(BevelBorder.RAISED));
        jpBuscar.add(this.jtfBuscar);
        jpBuscar.add(this.jbBuscar);
        this.jpProductos.add(jpBuscar, BorderLayout.NORTH);
        this.jpProductos.add(jspProductos, BorderLayout.CENTER);

        add(this.jpProductos, BorderLayout.WEST);
        add(this.jpPrecio, BorderLayout.CENTER);
        add(this.jpBotones, BorderLayout.SOUTH);
    }

    private void loadData() {
        List<Producto> productos = servicio.obtenerProductos("", true);
        productoTableModel.setProductoList(productos);
        jtProductos.setModel(productoTableModel);
        productoTableModel.updateTable();
        PackColumn.packColumns(jtProductos, 1);
    }

}
