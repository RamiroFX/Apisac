/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.producto;

import com.ferrus.apisac.callback.CrearProductoCallback;
import com.ferrus.apisac.model.CostoOperativo;
import com.ferrus.apisac.model.CostoOperativoDetalle;
import com.ferrus.apisac.model.MateriaPrima;
import com.ferrus.apisac.model.MateriaPrimaDetalle;
import com.ferrus.apisac.model.ProductoCategoria;
import com.ferrus.apisac.model.ProductoSubCategoria;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.tablemodel.CostoOperativoDetalleTableModel;
import com.ferrus.apisac.tablemodel.MateriaPrimaDetalleTableModel;
import com.ferrus.apisac.ui.costoOperativo.GestionCostoOperativo;
import com.ferrus.apisac.ui.materiaPrima.GestionMateriaPrima;
import static com.ferrus.apisac.util.AppUIConstants.ALERT_MESSAGE;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class CrearProducto extends JDialog implements ActionListener, KeyListener,
        MouseListener, CrearProductoCallback {

    public static final int CREATE_PRODUCT_WINDOWS_WIDTH = 800;
    public static final int CREATE_PRODUCT_WINDOWS_HEIGHT = 400;

    public static final String CREATE_PRODUCT_PROD_NAME = "Producto";
    public static final String CREATE_PRODUCT_PROD_COST_TITLE = "Costo de producción";
    public static final String CREATE_PRODUCT_OPER_COST_TITLE = "Costo operativo";
    public static final String CREATE_PRODUCT_PROD_PRICE_TITLE = "Precio de venta";
    public static final String CREATE_PRODUCT_UNIT_PROD_NAME = "Unidades producidas";
    public static final String CREATE_PRODUCT_FIXED_COST_UNIT_PROD_NAME = "Costo fijo unitario";
    public static final String CREATE_PRODUCT_VAR_COST_UNIT_PROD_NAME = "Costo variable unitario";
    public static final String CREATE_PRODUCT_TOTAL_COST_PROD_NAME = "Costo total unitario";
    public static final String CREATE_PRODUCT_TOTAL_VAR_COST_PROD_NAME = "Costo variable total ";
    public static final String CREATE_PRODUCT_TOTAL_FIXED_COST_PROD_NAME = "Costo fijo total ";
    public static final String CREATE_PRODUCT_TAX_LABEL = "Impuesto";
    public static final String CREATE_PRODUCT_UTILITY_LABEL = "Utilidad";

    public static final String CREATE_PRODUCT_ADD_MAT_BTN_NAME = "Agregar";
    public static final String CREATE_PRODUCT_UPDATE_MAT_BTN_NAME = "Modificar";
    public static final String CREATE_PRODUCT_REMOVE_MAT_BTN_NAME = "Quitar";
    public static final String CREATE_PRODUCT_REPEATED_RAW_MATERIAL_MSG = "La materia prima seleccionada ya se encuentra utilizada.";
    public static final String CREATE_PRODUCT_REPEATED_OPER_COST_MSG = "El costo operativo seleccionado ya se encuentra utilizado.";

    /*
    SELL PRICE VARS    
     */
    private JFormattedTextField jftCostoFijoUnit, jftCostoVariableUnit,
            jftCostoTotalUnit, jftCostoFijoTotal,
            jftCostoVariableTotal, jftUnidadesProducidas,
            jftUtilidad, jftImpuesto;
    private JComboBox<UnidadMedida> jcbUnidadMedida;
    private JComboBox<ProductoCategoria> jcbProductoCategoria;
    private JComboBox<ProductoSubCategoria> jcbProductoSubCategoria;
    private JTextField jtfNombreProducto;
    /*    
    PROD COST VARS
     */
    private JFormattedTextField jftCostoVariableTotal2;
    private JTable jtMateriaPrima;
    private JScrollPane jspMateriaPrima;
    private JButton jbAgregarMat, jbModificarMat, jbQuitarMat;
    /*
    OPERATIVE COST VARS
     */
    private JFormattedTextField jftCostoFijoTotal2;
    private JTable jtCostoOperativo;
    private JScrollPane jspCostoOperativo;
    private JButton jbAgregarCO, jbModificarCO, jbQuitarCO;
    /*
    GENERAL VARS    
     */
    private JTabbedPane jtpPrincipal;
    private JPanel jpParametros, jpMateriaPrima, jpGastoOperativo, jpPrecio;
    private MateriaPrimaDetalleTableModel materiaPrimaDetalleTableModel;
    private CostoOperativoDetalleTableModel costoOperativoDetalleTableModel;

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
        this.costoOperativoDetalleTableModel = new CostoOperativoDetalleTableModel();
        this.formType = formType;
        this.jtpPrincipal = new JTabbedPane();
        this.jpParametros = new JPanel(new BorderLayout());
        this.jpMateriaPrima = new JPanel(new BorderLayout());
        this.jpGastoOperativo = new JPanel(new BorderLayout());
        this.jpPrecio = new JPanel(new MigLayout());
        this.jcbUnidadMedida = new JComboBox<>();
        this.jcbProductoCategoria = new JComboBox<>();
        this.jtMateriaPrima = new JTable(materiaPrimaDetalleTableModel);
        this.jtMateriaPrima.getTableHeader().setReorderingAllowed(false);
        this.jspMateriaPrima = new JScrollPane(jtMateriaPrima);
        this.jtCostoOperativo = new JTable(costoOperativoDetalleTableModel);
        this.jtCostoOperativo.getTableHeader().setReorderingAllowed(false);
        this.jspCostoOperativo = new JScrollPane(jtCostoOperativo);

        this.jtfNombreProducto = new JTextField();

        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        this.jftUnidadesProducidas = new JFormattedTextField(format);
        this.jftUnidadesProducidas.setValue(0.0);
        this.jftUnidadesProducidas = new JFormattedTextField(format);
        this.jftUnidadesProducidas.setValue(0.0);
        this.jftImpuesto = new JFormattedTextField(format);
        this.jftImpuesto.setValue(0.0);
        this.jftUtilidad = new JFormattedTextField(format);
        this.jftUtilidad.setValue(0.0);
        this.jftCostoFijoUnit = new JFormattedTextField(0);
        this.jftCostoFijoUnit.setEditable(false);
        this.jftCostoVariableUnit = new JFormattedTextField(0);
        this.jftCostoVariableUnit.setEditable(false);
        this.jftCostoTotalUnit = new JFormattedTextField(0);
        this.jftCostoTotalUnit.setEditable(false);
        this.jftCostoFijoTotal = new JFormattedTextField(0);
        this.jftCostoFijoTotal.setEditable(false);
        this.jftCostoVariableTotal = new JFormattedTextField(0);
        this.jftCostoVariableTotal.setEditable(false);
        this.jftCostoFijoTotal2 = new JFormattedTextField(0);
        this.jftCostoFijoTotal2.setEditable(false);
        this.jftCostoVariableTotal2 = new JFormattedTextField(0);
        this.jftCostoVariableTotal2.setEditable(false);

        this.jbAgregarMat = new JButton(CREATE_PRODUCT_ADD_MAT_BTN_NAME);
        this.jbModificarMat = new JButton(CREATE_PRODUCT_UPDATE_MAT_BTN_NAME);
        this.jbQuitarMat = new JButton(CREATE_PRODUCT_REMOVE_MAT_BTN_NAME);
        this.jbModificarMat.setEnabled(false);
        this.jbQuitarMat.setEnabled(false);

        this.jbAgregarCO = new JButton(CREATE_PRODUCT_ADD_MAT_BTN_NAME);
        this.jbModificarCO = new JButton(CREATE_PRODUCT_UPDATE_MAT_BTN_NAME);
        this.jbQuitarCO = new JButton(CREATE_PRODUCT_REMOVE_MAT_BTN_NAME);
        this.jbModificarCO.setEnabled(false);
        this.jbQuitarCO.setEnabled(false);
    }

    private void constructLayout() {
        constructLayoutPrecio();
        constructLayoutCostoProduccion();
        constructLayoutCostoOperativo();
        getContentPane().add(jtpPrincipal, BorderLayout.CENTER);
    }

    private void constructLayoutPrecio() {
        jpPrecio.add(new JLabel(CREATE_PRODUCT_PROD_NAME));
        jpPrecio.add(jtfNombreProducto, "growx, push, wrap");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_UNIT_PROD_NAME));
        jpPrecio.add(jftUnidadesProducidas, "growx, push, wrap");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_UTILITY_LABEL));
        jpPrecio.add(jftUtilidad, "growx, push, wrap");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_TAX_LABEL));
        jpPrecio.add(jftImpuesto, "growx, push, wrap");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_TOTAL_FIXED_COST_PROD_NAME));
        jpPrecio.add(jftCostoFijoTotal, "growx, push, wrap");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_FIXED_COST_UNIT_PROD_NAME));
        jpPrecio.add(jftCostoFijoUnit, "growx, push, wrap");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_TOTAL_VAR_COST_PROD_NAME));
        jpPrecio.add(jftCostoVariableTotal, "growx, push, wrap");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_VAR_COST_UNIT_PROD_NAME));
        jpPrecio.add(jftCostoVariableUnit, "growx, push, wrap");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_TOTAL_COST_PROD_NAME));
        jpPrecio.add(jftCostoTotalUnit, "growx, push");
        this.jtpPrincipal.addTab(CREATE_PRODUCT_PROD_PRICE_TITLE, jpPrecio);
    }

    private void constructLayoutCostoProduccion() {
        JPanel jpEast = new JPanel(new GridLayout(8, 1));
        jpEast.setBorder(new TitledBorder(""));
        jpEast.add(jbAgregarMat);
        jpEast.add(jbModificarMat);
        jpEast.add(jbQuitarMat);
        jpEast.add(new JLabel());
        jpEast.add(new JLabel());
        jpEast.add(new JLabel());
        jpEast.add(new JLabel(CREATE_PRODUCT_TOTAL_VAR_COST_PROD_NAME));
        jpEast.add(jftCostoVariableTotal2, "growx, push, wrap");
        jpMateriaPrima.add(jspMateriaPrima, BorderLayout.CENTER);
        jpMateriaPrima.add(jpEast, BorderLayout.EAST);
        this.jtpPrincipal.addTab(CREATE_PRODUCT_PROD_COST_TITLE, jpMateriaPrima);
    }

    private void constructLayoutCostoOperativo() {
        JPanel jpEast = new JPanel(new GridLayout(8, 1));
        jpEast.setBorder(new TitledBorder(""));
        jpEast.add(jbAgregarCO);
        jpEast.add(jbModificarCO);
        jpEast.add(jbQuitarCO);
        jpEast.add(new JLabel());
        jpEast.add(new JLabel());
        jpEast.add(new JLabel());
        jpEast.add(new JLabel(CREATE_PRODUCT_TOTAL_FIXED_COST_PROD_NAME));
        jpEast.add(jftCostoFijoTotal2, "growx, push, wrap");
        jpGastoOperativo.add(jspCostoOperativo, BorderLayout.CENTER);
        jpGastoOperativo.add(jpEast, BorderLayout.EAST);
        this.jtpPrincipal.addTab(CREATE_PRODUCT_OPER_COST_TITLE, jpGastoOperativo);
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
        this.jftUnidadesProducidas.addKeyListener(this);
        this.jtMateriaPrima.addMouseListener(this);

    }

    private void loadData() {
    }

    private void recibirMateriaPrima(MateriaPrimaDetalle mpd) {
        Long idNuevo = mpd.getId();
        for (MateriaPrimaDetalle materiaPrimaDetalle : materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList()) {
            if (Objects.equals(idNuevo, materiaPrimaDetalle.getId())) {
                JOptionPane.showMessageDialog(this, CREATE_PRODUCT_REPEATED_RAW_MATERIAL_MSG, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        this.materiaPrimaDetalleTableModel.agregarMateriaPrima(mpd);
        calcularSubTotales();
        checkearUnidadesProducidas();
    }

    private void modificarMateriaPrima(MateriaPrimaDetalle mpd) {
        Long idNuevo = mpd.getId();
        for (MateriaPrimaDetalle materiaPrimaDetalle : materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList()) {
            if (Objects.equals(idNuevo, materiaPrimaDetalle.getId())) {
                materiaPrimaDetalle.setCantidad(mpd.getCantidad());
                materiaPrimaDetalle.setPrecioMateriaPrima(mpd.getPrecioMateriaPrima());
                materiaPrimaDetalle.getMateriaPrima().setUnidadMedida(mpd.getMateriaPrima().getUnidadMedida());
                break;
            }
        }
        materiaPrimaDetalleTableModel.updateTable();
        calcularSubTotales();
        checkearUnidadesProducidas();
    }

    private void removerMateriaPrimaDetalle() {
        int row = this.jtMateriaPrima.getSelectedRow();
        if (row > -1) {
            this.materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList().remove(row);
            this.materiaPrimaDetalleTableModel.updateTable();
            calcularSubTotales();
            checkearUnidadesProducidas();
        }
        this.jbModificarMat.setEnabled(false);
        this.jbQuitarMat.setEnabled(false);
    }

    private void recibirCostoOperativo(CostoOperativoDetalle cod) {
        Long idNuevo = cod.getId();
        for (CostoOperativoDetalle costoOperativoDetalle : costoOperativoDetalleTableModel.getCostoOperativoDetalleList()) {
            if (Objects.equals(idNuevo, costoOperativoDetalle.getId())) {
                JOptionPane.showMessageDialog(this, CREATE_PRODUCT_REPEATED_OPER_COST_MSG, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        this.costoOperativoDetalleTableModel.agregarCostoOperativo(cod);
        calcularSubTotales();
        checkearUnidadesProducidas();
    }

    private void modificarCostoOperativo(CostoOperativoDetalle cod) {
        Long idNuevo = cod.getId();
        for (CostoOperativoDetalle costoOperativoDetalle : costoOperativoDetalleTableModel.getCostoOperativoDetalleList()) {
            if (Objects.equals(idNuevo, costoOperativoDetalle.getId())) {
                costoOperativoDetalle.setCantidad(cod.getCantidad());
                costoOperativoDetalle.setPrecioCostoOperativo(cod.getPrecioCostoOperativo());
                costoOperativoDetalle.getCostoOperativo().setUnidadMedida(cod.getCostoOperativo().getUnidadMedida());
                break;
            }
        }
        materiaPrimaDetalleTableModel.updateTable();
        calcularSubTotales();
        checkearUnidadesProducidas();
    }

    private void removerCostoOperativoDetalle() {
        int row = this.jtCostoOperativo.getSelectedRow();
        if (row > -1) {
            this.costoOperativoDetalleTableModel.getCostoOperativoDetalleList().remove(row);
            this.costoOperativoDetalleTableModel.updateTable();
            calcularSubTotales();
            checkearUnidadesProducidas();
        }
        this.jbModificarCO.setEnabled(false);
        this.jbQuitarCO.setEnabled(false);
    }

    private void calcularSubTotales() {
        Double costoVariableTotal = 0.0;
        Double unidadesProducidas = 0.0;
        try {
            unidadesProducidas = Double.valueOf("" + jftUnidadesProducidas.getValue());
        } catch (NumberFormatException e) {
            jftUnidadesProducidas.setValue(0);
        }

        for (MateriaPrimaDetalle materiaPrimaDetalle : this.materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList()) {
            Double cant = materiaPrimaDetalle.getCantidad();
            Double precio = materiaPrimaDetalle.getPrecioMateriaPrima();
            costoVariableTotal = costoVariableTotal + (cant * precio);
        }
        for (CostoOperativoDetalle costoOperativoDetalle : this.costoOperativoDetalleTableModel.getCostoOperativoDetalleList()) {
            Double cant = costoOperativoDetalle.getCantidad();
            Double precio = costoOperativoDetalle.getPrecioCostoOperativo();
            costoVariableTotal = costoVariableTotal + (cant * precio);
        }
        this.jftCostoVariableTotal.setValue(costoVariableTotal);
        this.jftCostoVariableUnit.setValue(costoVariableTotal / unidadesProducidas);
    }

    private void checkearUnidadesProducidas() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Double costoVariableTotal = null;
                Double costoFijoTotal = null;
                Double unidadesProducidas = null;
                try {
                    unidadesProducidas = Double.valueOf("" + jftUnidadesProducidas.getValue());
                } catch (NumberFormatException e) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Verifique en uno de los campos el parametro:"
                            + e.getMessage().substring(12) + "\n"
                            + "Asegurese de colocar un numero valido\n"
                            + "en el campo Total.",
                            "Parametros incorrectos",
                            javax.swing.JOptionPane.OK_OPTION);
                    jftUnidadesProducidas.setValue(0);
                    return;
                }
                try {
                    costoVariableTotal = Double.valueOf(jftCostoVariableTotal2.getValue().toString());
                } catch (NumberFormatException e) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Verifique en uno de los campos el parametro:"
                            + e.getMessage().substring(12) + "\n"
                            + "Asegurese de colocar un numero valido\n"
                            + "en el campo Cantidad.",
                            "Parametros incorrectos",
                            javax.swing.JOptionPane.OK_OPTION);
                    return;
                }
                try {
                    costoFijoTotal = Double.valueOf(jftCostoFijoTotal2.getValue().toString());
                } catch (NumberFormatException e) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Verifique en uno de los campos el parametro:"
                            + e.getMessage().substring(12) + "\n"
                            + "Asegurese de colocar un numero valido\n"
                            + "en el campo Cantidad.",
                            "Parametros incorrectos",
                            javax.swing.JOptionPane.OK_OPTION);
                    return;
                }
                Double costoVariableUnit = costoVariableTotal / unidadesProducidas;
                Double costoFijoUnit = costoFijoTotal / unidadesProducidas;
                jftCostoVariableUnit.setValue(costoVariableUnit);
                jftCostoFijoUnit.setValue(costoFijoUnit);
            }
        });
    }

    private void invocarSeleccionarMateriaPrimaForm() {
        GestionMateriaPrima gmp = new GestionMateriaPrima(this, GestionMateriaPrima.SELECCIONAR);
        gmp.setCrearProductoCallback(this);
        gmp.setVisible(true);
    }

    private void invocarModificarMateriaPrimaForm() {
        int row = jtMateriaPrima.getSelectedRow();
        if (row > -1) {
            Long idMp = (Long) this.jtMateriaPrima.getValueAt(row, 0);
            String nombre = (String) this.jtMateriaPrima.getValueAt(row, 1);
            UnidadMedida um = (UnidadMedida) this.jtMateriaPrima.getValueAt(row, 2);
            Double precio = (Double) this.jtMateriaPrima.getValueAt(row, 3);
            Double cantidad = (Double) this.jtMateriaPrima.getValueAt(row, 4);
            MateriaPrima mp = new MateriaPrima();
            mp.setId(idMp);
            mp.setNombre(nombre);
            mp.setPrecio(precio);
            mp.setUnidadMedida(um);
            MateriaPrimaDetalle mpd = new MateriaPrimaDetalle();
            mpd.setCantidad(cantidad);
            mpd.setMateriaPrima(mp);
            mpd.setPrecioMateriaPrima(precio);
            SeleccionarCantidadMateriaPrima sc = new SeleccionarCantidadMateriaPrima(this, mpd, SeleccionarCantidadMateriaPrima.MODIFICAR);
            sc.setCrearProductoCallback(this);
            sc.setVisible(true);
            this.jbModificarMat.setEnabled(false);
            this.jbQuitarMat.setEnabled(false);
        }
    }

    private void invocarSeleccionarCostoOperativoForm() {
        GestionCostoOperativo gco = new GestionCostoOperativo(this, GestionCostoOperativo.SELECCIONAR);
        gco.setCrearProductoCallback(this);
        gco.setVisible(true);
    }

    private void invocarModificarCostoOperativoForm() {
        int row = jtCostoOperativo.getSelectedRow();
        if (row > -1) {
            Long idMp = (Long) this.jtCostoOperativo.getValueAt(row, 0);
            String nombre = (String) this.jtCostoOperativo.getValueAt(row, 1);
            UnidadMedida um = (UnidadMedida) this.jtCostoOperativo.getValueAt(row, 2);
            Double precio = (Double) this.jtCostoOperativo.getValueAt(row, 3);
            Double cantidad = (Double) this.jtCostoOperativo.getValueAt(row, 4);
            CostoOperativo co = new CostoOperativo();
            co.setId(idMp);
            co.setNombre(nombre);
            co.setPrecio(precio);
            co.setUnidadMedida(um);
            CostoOperativoDetalle cod = new CostoOperativoDetalle();
            cod.setCantidad(cantidad);
            cod.setCostoOperativo(co);
            cod.setPrecioCostoOperativo(precio);
            SeleccionarCantidadCostoOperativo sc = new SeleccionarCantidadCostoOperativo(this, cod, SeleccionarCantidadCostoOperativo.MODIFICAR);
            sc.setCrearProductoCallback(this);
            sc.setVisible(true);
            this.jbModificarCO.setEnabled(false);
            this.jbQuitarCO.setEnabled(false);
        }
    }

    @Override
    public void recibirMateriaPrimaDetalle(MateriaPrimaDetalle mpd) {
        recibirMateriaPrima(mpd);
    }

    @Override
    public void modificarMateriaPrimaDetalle(MateriaPrimaDetalle mpd) {
        modificarMateriaPrima(mpd);
    }

    @Override
    public void recibirCostoOperativoDetalle(CostoOperativoDetalle cod) {
        recibirCostoOperativo(cod);
    }

    @Override
    public void modificarCostoOperativoDetalle(CostoOperativoDetalle cod) {
        modificarCostoOperativo(cod);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src.equals(jbAgregarMat)) {
            invocarSeleccionarMateriaPrimaForm();
        } else if (src.equals(jbModificarMat)) {
            invocarModificarMateriaPrimaForm();
        } else if (src.equals(jbQuitarMat)) {
            removerMateriaPrimaDetalle();
        } else if (src.equals(jbAgregarCO)) {
            invocarSeleccionarCostoOperativoForm();
        } else if (src.equals(jbModificarCO)) {
            invocarModificarCostoOperativoForm();
        } else if (src.equals(jbQuitarCO)) {
            removerCostoOperativoDetalle();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource().equals(jftUnidadesProducidas)) {
            checkearUnidadesProducidas();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(this.jtMateriaPrima)) {
            int fila = this.jtMateriaPrima.rowAtPoint(e.getPoint());
            int columna = this.jtMateriaPrima.columnAtPoint(e.getPoint());
            if ((fila > -1) && (columna > -1)) {
                this.jbModificarMat.setEnabled(true);
                this.jbQuitarMat.setEnabled(true);
                if (e.getClickCount() == 2) {
                }
            } else {
                this.jbModificarMat.setEnabled(false);
                this.jbQuitarMat.setEnabled(false);
            }
        }
        if (e.getSource().equals(this.jtCostoOperativo)) {
            int fila = this.jtCostoOperativo.rowAtPoint(e.getPoint());
            int columna = this.jtCostoOperativo.columnAtPoint(e.getPoint());
            if ((fila > -1) && (columna > -1)) {
                this.jbModificarCO.setEnabled(true);
                this.jbQuitarCO.setEnabled(true);
                if (e.getClickCount() == 2) {
                }
            } else {
                this.jbModificarCO.setEnabled(false);
                this.jbQuitarCO.setEnabled(false);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
