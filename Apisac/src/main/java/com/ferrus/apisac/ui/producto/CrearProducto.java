/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.producto;

import com.ferrus.apisac.callback.CrearProductoCallback;
import com.ferrus.apisac.model.MateriaPrimaDetalle;
import com.ferrus.apisac.model.ProductoCategoria;
import com.ferrus.apisac.model.ProductoSubCategoria;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.tablemodel.MateriaPrimaDetalleTableModel;
import com.ferrus.apisac.ui.materiaPrima.GestionMateriaPrima;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Ramiro Ferreira
 */
public class CrearProducto extends JDialog implements ActionListener, CrearProductoCallback {

    public static final int CREATE_PRODUCT_WINDOWS_WIDTH = 800;
    public static final int CREATE_PRODUCT_WINDOWS_HEIGHT = 600;

    public static final String CREATE_PRODUCT_PROD_NAME = "Producto";
    public static final String CREATE_PRODUCT_PROD_COST_TITLE = "Costo de producción";
    public static final String CREATE_PRODUCT_UNIT_PROD_NAME = "Unidades producidas";
    public static final String CREATE_PRODUCT_FIXED_COST_UNIT_PROD_NAME = "Costo fijo unitario";
    public static final String CREATE_PRODUCT_VAR_COST_UNIT_PROD_NAME = "Costo variable unitario";
    public static final String CREATE_PRODUCT_TOTAL_COST_PROD_NAME = "Costo total unitario";
    public static final String CREATE_PRODUCT_TOTAL_VAR_COST_PROD_NAME = "Costo variable total ";
    public static final String CREATE_PRODUCT_TOTAL_FIXED_COST_PROD_NAME = "Costo fijo total ";
    public static final String CREATE_PRODUCT_ADD_MAT_BTN_NAME = "Agregar";
    public static final String CREATE_PRODUCT_UPDATE_MAT_BTN_NAME = "Modificar";
    public static final String CREATE_PRODUCT_REMOVE_MAT_BTN_NAME = "Quitar";

    private JTabbedPane jtpPrincipal;
    private JPanel jpParametros, jpMateriaPrima, jpGastoOperativo, jpPrecio;
    private JComboBox<UnidadMedida> jcbUnidadMedida;
    private JComboBox<ProductoCategoria> jcbProductoCategoria;
    private JComboBox<ProductoSubCategoria> jcbProductoSubCategoria;
    private JTextField jtfNombreProducto, jtfUnidadesProducidas;
    private JFormattedTextField jftCostoFijoUnit, jftCostoVariableUnit,
            jftCostoTotalUnit, jftCostoFijoTotal, jftCostoVariableTotal;
    private JTable jtMateriaPrima;
    private JScrollPane jspMateriaPrima;
    private JButton jbAgregarMat, jbModificarMat, jbQuitarMat;
    private MateriaPrimaDetalleTableModel materiaPrimaDetalleTableModel;

    public static final int CREATE_PRODUCT = 1;
    public static final int UPDATE_PRODUCT = 2;
    private int formType;

    public CrearProducto(JFrame frame, int formType) {
        super(frame);
        initializeVariables(formType);
        constructLayout();
        constructWindows(frame);
        addListeners();
        loadData();
    }

    private void initializeVariables(int formType) {
        this.materiaPrimaDetalleTableModel = new MateriaPrimaDetalleTableModel();
        this.formType = formType;
        this.jtpPrincipal = new JTabbedPane();
        this.jpParametros = new JPanel(new BorderLayout());
        this.jpMateriaPrima = new JPanel();
        this.jpGastoOperativo = new JPanel();
        this.jpPrecio = new JPanel();
        this.jcbUnidadMedida = new JComboBox<>();
        this.jcbProductoCategoria = new JComboBox<>();
        this.jtMateriaPrima = new JTable(materiaPrimaDetalleTableModel);
        this.jtMateriaPrima.getTableHeader().setReorderingAllowed(false);
        this.jspMateriaPrima = new JScrollPane(jtMateriaPrima);
        this.jspMateriaPrima.setBorder(new TitledBorder(CREATE_PRODUCT_PROD_COST_TITLE));

        this.jtfNombreProducto = new JTextField();
        this.jtfUnidadesProducidas = new JTextField();
        this.jftCostoFijoUnit = new JFormattedTextField();
        this.jftCostoFijoUnit.setEditable(false);
        this.jftCostoVariableUnit = new JFormattedTextField();
        this.jftCostoVariableUnit.setEditable(false);
        this.jftCostoTotalUnit = new JFormattedTextField();
        this.jftCostoTotalUnit.setEditable(false);
        this.jftCostoFijoTotal = new JFormattedTextField();
        this.jftCostoFijoTotal.setEditable(false);
        this.jftCostoVariableTotal = new JFormattedTextField();
        this.jftCostoVariableTotal.setEditable(false);

        this.jbAgregarMat = new JButton(CREATE_PRODUCT_ADD_MAT_BTN_NAME);
        this.jbModificarMat = new JButton(CREATE_PRODUCT_UPDATE_MAT_BTN_NAME);
        this.jbQuitarMat = new JButton(CREATE_PRODUCT_REMOVE_MAT_BTN_NAME);
    }

    private void constructLayout() {
        //NORTE
        JPanel jpNorth = new JPanel(new MigLayout());
        jpNorth.add(new JLabel(CREATE_PRODUCT_PROD_NAME));
        jpNorth.add(jtfNombreProducto, "growx, push, wrap");
        jpNorth.add(new JLabel(CREATE_PRODUCT_UNIT_PROD_NAME));
        jpNorth.add(jtfUnidadesProducidas, "growx, push");
        //CENTRO
        JPanel jpEast = new JPanel(new GridLayout(8, 1));
        jpEast.setBorder(new TitledBorder(""));
        jpEast.add(jbAgregarMat);
        jpEast.add(jbModificarMat);
        jpEast.add(jbQuitarMat);
        jpEast.add(new JLabel());
        jpEast.add(new JLabel(CREATE_PRODUCT_TOTAL_VAR_COST_PROD_NAME));
        jpEast.add(jftCostoVariableTotal, "growx, push, wrap");
        jpEast.add(new JLabel(CREATE_PRODUCT_TOTAL_FIXED_COST_PROD_NAME));
        jpEast.add(jftCostoFijoTotal, "growx, push");
        JPanel jpCenter = new JPanel(new BorderLayout());
        jpCenter.add(jspMateriaPrima, BorderLayout.CENTER);
        jpCenter.add(jpEast, BorderLayout.EAST);
        //SUR
        JPanel jpSouth = new JPanel(new MigLayout());
        jpSouth.add(new JLabel(CREATE_PRODUCT_FIXED_COST_UNIT_PROD_NAME));
        jpSouth.add(jftCostoFijoUnit, "growx, push, wrap");
        jpSouth.add(new JLabel(CREATE_PRODUCT_VAR_COST_UNIT_PROD_NAME));
        jpSouth.add(jftCostoVariableUnit, "growx, push, wrap");
        jpSouth.add(new JLabel(CREATE_PRODUCT_TOTAL_COST_PROD_NAME));
        jpSouth.add(jftCostoTotalUnit, "growx, push");
        this.jpParametros.add(jpNorth, BorderLayout.NORTH);
        this.jpParametros.add(jpCenter, BorderLayout.CENTER);
        this.jpParametros.add(jpSouth, BorderLayout.SOUTH);
        this.jtpPrincipal.addTab(CREATE_PRODUCT_PROD_COST_TITLE, jpParametros);

        getContentPane().add(jtpPrincipal, BorderLayout.CENTER);
    }

    private void constructWindows(JFrame jframe) {
        switch (formType) {
            case CREATE_PRODUCT: {
                setTitle(CREATE_PRODUCT_PROD_NAME);
                break;
            }
            case UPDATE_PRODUCT: {
                setTitle(CREATE_PRODUCT_PROD_NAME);
                break;
            }
        }
        setPreferredSize(new Dimension(CREATE_PRODUCT_WINDOWS_WIDTH, CREATE_PRODUCT_WINDOWS_HEIGHT));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(jframe);
        setVisible(true);
    }

    private void addListeners() {
        this.jbAgregarMat.addActionListener(this);
        this.jbModificarMat.addActionListener(this);
        this.jbQuitarMat.addActionListener(this);
    }

    private void loadData() {
    }

    private void recibirMateriaPrima(MateriaPrimaDetalle mpd) {
        this.materiaPrimaDetalleTableModel.agregarMateriaPrima(mpd);
        calcularSubTotales();
    }

    private void calcularSubTotales() {
        Double costoVariableTotal = 0.0;
        for (MateriaPrimaDetalle materiaPrimaDetalle : this.materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList()) {
            Double cant = materiaPrimaDetalle.getCantidad();
            Double precio = materiaPrimaDetalle.getPrecioMateriaPrima();
            costoVariableTotal = costoVariableTotal + (cant * precio);
        }
        this.jftCostoVariableTotal.setText("" + costoVariableTotal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src.equals(jbAgregarMat)) {
            GestionMateriaPrima gmp = new GestionMateriaPrima(this, GestionMateriaPrima.SELECCIONAR);
            gmp.setCrearProductoCallback(this);
        }
    }

    @Override
    public void recibirMateriaPrimaDetalle(MateriaPrimaDetalle mpd) {
        recibirMateriaPrima(mpd);
    }

    @Override
    public void modificarMateriaPrimaDetalle(MateriaPrimaDetalle mpd) {
        recibirMateriaPrima(mpd);
    }

}
