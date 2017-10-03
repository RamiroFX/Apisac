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
import com.ferrus.apisac.model.Precio;
import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.ProductoCategoria;
import com.ferrus.apisac.model.ProductoSubCategoria;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.service.CostoOperativoDetalleService;
import com.ferrus.apisac.model.service.MateriaPrimaDetalleService;
import com.ferrus.apisac.model.service.PrecioService;
import com.ferrus.apisac.model.service.ProductoParametrosService;
import com.ferrus.apisac.model.service.UnidadMedidaService;
import com.ferrus.apisac.model.serviceImp.CostoOperativoDetalleServImpl;
import com.ferrus.apisac.model.serviceImp.MateriaPrimaDetalleServImpl;
import com.ferrus.apisac.model.serviceImp.PrecioServImpl;
import com.ferrus.apisac.model.serviceImp.ProductoParametrosServImpl;
import com.ferrus.apisac.model.serviceImp.UnidadMedidaServImpl;
import com.ferrus.apisac.tablemodel.CostoOperativoDetalleTableModel;
import com.ferrus.apisac.tablemodel.MateriaPrimaDetalleTableModel;
import com.ferrus.apisac.ui.costoOperativo.GestionCostoOperativo;
import com.ferrus.apisac.ui.materiaPrima.GestionMateriaPrima;
import static com.ferrus.apisac.util.AppUIConstants.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Ramiro Ferreira
 */
public class CrearProducto extends JDialog implements ActionListener, KeyListener,
        MouseListener, CrearProductoCallback, ItemListener {

    /*
    SELL PRICE VARS    
     */
    private JFormattedTextField jftCostoFijoUnit, jftCostoVariableUnit,
            jftCostoTotalUnit, jftCostoTotal, jftCostoFijoTotal,
            jftCostoVariableTotal, jftUnidadesProducidas,
            jftUtilidadPorcentaje, jftImpuesto,
            jftUtilidad, jftPrecioVentaIVA, jftPrecioVenta;
    private JComboBox<UnidadMedida> jcbUnidadMedida;
    private JComboBox<ProductoCategoria> jcbProductoCategoria;
    private JComboBox<ProductoSubCategoria> jcbProductoSubCategoria;
    private JTextField jtfNombreProducto, jtfDescripcionProducto;
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
    private JPanel jpMateriaPrima, jpGastoOperativo, jpPrecio;
    private MateriaPrimaDetalleTableModel materiaPrimaDetalleTableModel;
    private CostoOperativoDetalleTableModel costoOperativoDetalleTableModel;
    private JButton jbAceptar, jbCancelar;

    public static final int CREATE_PRODUCT = 1;
    public static final int UPDATE_PRODUCT = 2;
    private int formType;
    private ProductoParametrosService productoServicio;
    private UnidadMedidaService unidadMedidaServicio;
    private PrecioService precioServicio;
    private CostoOperativoDetalleService costoOperativoDetalleService;
    private MateriaPrimaDetalleService materiaPrimaDetalleService;
    private Producto producto;

    public CrearProducto(JFrame frame, int formType) {
        super(frame, true);
        initializeVariables(formType);
        loadData();
        addListeners();
        constructLayout();
        constructWindows(frame);
    }

    private void initializeVariables(int formType) {
        this.materiaPrimaDetalleTableModel = new MateriaPrimaDetalleTableModel();
        this.costoOperativoDetalleTableModel = new CostoOperativoDetalleTableModel();
        this.formType = formType;
        this.productoServicio = new ProductoParametrosServImpl();
        this.unidadMedidaServicio = new UnidadMedidaServImpl();
        this.precioServicio = new PrecioServImpl();
        this.costoOperativoDetalleService = new CostoOperativoDetalleServImpl();
        this.materiaPrimaDetalleService = new MateriaPrimaDetalleServImpl();
        this.jtpPrincipal = new JTabbedPane();
        this.jpMateriaPrima = new JPanel(new BorderLayout());
        this.jpGastoOperativo = new JPanel(new BorderLayout());
        this.jpPrecio = new JPanel(new MigLayout());
        this.jcbUnidadMedida = new JComboBox<>();
        this.jcbProductoCategoria = new JComboBox<>();
        this.jcbProductoSubCategoria = new JComboBox<>();
        this.jtMateriaPrima = new JTable(materiaPrimaDetalleTableModel);
        this.jtMateriaPrima.getTableHeader().setReorderingAllowed(false);
        this.jspMateriaPrima = new JScrollPane(jtMateriaPrima);
        this.jtCostoOperativo = new JTable(costoOperativoDetalleTableModel);
        this.jtCostoOperativo.getTableHeader().setReorderingAllowed(false);
        this.jspCostoOperativo = new JScrollPane(jtCostoOperativo);

        this.jtfNombreProducto = new JTextField();
        this.jtfDescripcionProducto = new JTextField();

        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        this.jftUnidadesProducidas = new JFormattedTextField(format);
        this.jftUnidadesProducidas.setValue(0.0);
        this.jftImpuesto = new JFormattedTextField(format);
        this.jftImpuesto.setValue(0.0);
        this.jftUtilidadPorcentaje = new JFormattedTextField(format);
        this.jftUtilidadPorcentaje.setValue(0.0);
        this.jftUtilidad = new JFormattedTextField(format);
        this.jftUtilidad.setValue(0.0);
        this.jftUtilidad.setEditable(false);
        this.jftPrecioVentaIVA = new JFormattedTextField(format);
        this.jftPrecioVentaIVA.setValue(0.0);
        this.jftPrecioVentaIVA.setEditable(false);
        this.jftPrecioVenta = new JFormattedTextField(format);
        this.jftPrecioVenta.setValue(0.0);
        this.jftPrecioVenta.setEditable(false);
        this.jftCostoFijoUnit = new JFormattedTextField(0);
        this.jftCostoFijoUnit.setEditable(false);
        this.jftCostoVariableUnit = new JFormattedTextField(0);
        this.jftCostoVariableUnit.setEditable(false);
        this.jftCostoTotal = new JFormattedTextField(0);
        this.jftCostoTotal.setEditable(false);
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

        this.jbAceptar = new JButton(ACEPT_BUTTON_NAME);
        this.jbCancelar = new JButton(CANCEL_BUTTON_NAME);
    }

    private void constructLayout() {
        constructLayoutPrecio();
        constructLayoutCostoProduccion();
        constructLayoutCostoOperativo();
        JPanel jpBotones = new JPanel();
        jpBotones.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        jpBotones.add(jbAceptar);
        jpBotones.add(jbCancelar);
        getContentPane().add(jtpPrincipal, BorderLayout.CENTER);
        getContentPane().add(jpBotones, BorderLayout.SOUTH);
    }

    private void constructLayoutPrecio() {
        jpPrecio.add(new JLabel(NAME_LABEL));
        jpPrecio.add(jtfNombreProducto, "growx, push");
        jpPrecio.add(new JLabel(DESCRIPTION_LABEL));
        jpPrecio.add(jtfDescripcionProducto, "growx, push, wrap");

        jpPrecio.add(new JLabel(CREATE_PRODUCT_UNIT_PROD_NAME));
        jpPrecio.add(jftUnidadesProducidas, "growx, push");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_UM_LABEL));
        jpPrecio.add(jcbUnidadMedida, "growx, push, wrap");

        jpPrecio.add(new JLabel(CREATE_PRODUCT_UTILITY_PERCENT_LABEL));
        jpPrecio.add(jftUtilidadPorcentaje, "growx, push");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_CATEGORY_LABEL));
        jpPrecio.add(jcbProductoCategoria, "growx, push, wrap");

        jpPrecio.add(new JLabel(CREATE_PRODUCT_TAX_LABEL));
        jpPrecio.add(jftImpuesto, "growx, push");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_SUB_CATEGORY_LABEL));
        jpPrecio.add(jcbProductoSubCategoria, "growx, push, wrap");
        JPanel jpCostoFijo = new JPanel(new MigLayout());
        jpCostoFijo.setBorder(new EtchedBorder());
        jpCostoFijo.add(new JLabel(CREATE_PRODUCT_TOTAL_FIXED_COST_PROD_NAME));
        jpCostoFijo.add(jftCostoFijoTotal, "growx, push, wrap");
        jpCostoFijo.add(new JLabel(CREATE_PRODUCT_FIXED_COST_UNIT_PROD_NAME));
        jpCostoFijo.add(jftCostoFijoUnit, "growx, push, wrap");
        JPanel jpCostoVariable = new JPanel(new MigLayout());
        jpCostoVariable.setBorder(new EtchedBorder());
        jpCostoVariable.add(new JLabel(CREATE_PRODUCT_TOTAL_VAR_COST_PROD_NAME));
        jpCostoVariable.add(jftCostoVariableTotal, "growx, push, wrap");
        jpCostoVariable.add(new JLabel(CREATE_PRODUCT_VAR_COST_UNIT_PROD_NAME));
        jpCostoVariable.add(jftCostoVariableUnit, "growx, push, wrap");
        jpPrecio.add(jpCostoFijo, "growx, push");
        jpPrecio.add(jpCostoVariable, "growx, push");
        JPanel jpCostoTotal = new JPanel(new MigLayout());
        jpCostoTotal.setBorder(new EtchedBorder());
        jpCostoTotal.add(new JLabel(CREATE_PRODUCT_TOTAL_COST_PROD_NAME));
        jpCostoTotal.add(jftCostoTotal, "growx, push, span, wrap");
        jpCostoTotal.add(new JLabel(CREATE_PRODUCT_TOTAL_UNIT_COST_PROD_NAME));
        jpCostoTotal.add(jftCostoTotalUnit, "growx, push, wrap");
        jpPrecio.add(jpCostoTotal, "growx, push, span, wrap");
        //
        JPanel jpPrecioVenta = new JPanel(new MigLayout());
        jpPrecioVenta.setBorder(new EtchedBorder());
        jpPrecioVenta.add(new JLabel(CREATE_PRODUCT_UTILITY_LABEL));
        jpPrecioVenta.add(jftUtilidad, "growx, push");
        jpPrecioVenta.add(new JLabel(CREATE_PRODUCT_SELL_PRICE_LABEL));
        jpPrecioVenta.add(jftPrecioVenta, "growx, push");
        JLabel jlPrecioVentaIVA = new JLabel(CREATE_PRODUCT_SELL_PRICE_TAX_LABEL);
        jlPrecioVentaIVA.setFont(new Font("SansSerif", Font.BOLD, 15));
        jpPrecioVenta.add(jlPrecioVentaIVA);
        jpPrecioVenta.add(jftPrecioVentaIVA, "growx, push");
        jpPrecio.add(jpPrecioVenta, "growx, push, span");
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
                setTitle(CREATE_PRODUCT_TITLE);
                break;
            }
            case UPDATE_PRODUCT: {
                setTitle(UPDATE_PRODUCT_TITLE);
                break;
            }
        }
        setSize(new Dimension(CREATE_PRODUCT_WINDOWS_WIDTH, CREATE_PRODUCT_WINDOWS_HEIGHT));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(jframe);
    }

    private void addListeners() {
        this.jbAgregarMat.addActionListener(this);
        this.jbModificarMat.addActionListener(this);
        this.jbQuitarMat.addActionListener(this);
        this.jftUnidadesProducidas.addKeyListener(this);
        this.jftUtilidadPorcentaje.addKeyListener(this);
        this.jftImpuesto.addKeyListener(this);
        this.jtMateriaPrima.addMouseListener(this);
        this.jbAgregarCO.addActionListener(this);
        this.jbModificarCO.addActionListener(this);
        this.jbQuitarCO.addActionListener(this);
        this.jtCostoOperativo.addMouseListener(this);
        this.jcbProductoCategoria.addItemListener(this);
        this.jbAceptar.addActionListener(this);
        this.jbCancelar.addActionListener(this);
    }

    private void loadData() {
        List<UnidadMedida> unidadMedidas = unidadMedidaServicio.obtenerUnidadMedidas();
        jcbUnidadMedida.removeAllItems();
        for (UnidadMedida unidadMedida : unidadMedidas) {
            jcbUnidadMedida.addItem(unidadMedida);
        }
        List<ProductoCategoria> productoCategorias = productoServicio.obtenerProductosCategorias("", true);
        jcbProductoCategoria.removeAllItems();
        for (ProductoCategoria productoCategoria : productoCategorias) {
            jcbProductoCategoria.addItem(productoCategoria);
        }
        loadDataProductoSubCategoria(jcbProductoCategoria.getItemAt(0));
    }

    private void loadDataProductoSubCategoria(ProductoCategoria pc) {
        List<ProductoSubCategoria> productoSubCategorias = productoServicio.obtenerProductosSubCategorias(pc);
        jcbProductoSubCategoria.removeAllItems();
        for (ProductoSubCategoria productoSubCategoria : productoSubCategorias) {
            jcbProductoSubCategoria.addItem(productoSubCategoria);
        }
    }

    private void recibirMateriaPrima(MateriaPrimaDetalle mpd) {
        Long idNuevo = mpd.getMateriaPrima().getId();
        for (MateriaPrimaDetalle materiaPrimaDetalle : materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList()) {
            if (Objects.equals(idNuevo, materiaPrimaDetalle.getId())) {
                JOptionPane.showMessageDialog(this, CREATE_PRODUCT_REPEATED_RAW_MATERIAL_MSG, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (formType == CREATE_PRODUCT) {
            this.materiaPrimaDetalleTableModel.agregarMateriaPrima(mpd);
        } else if (formType == UPDATE_PRODUCT) {
            System.out.println("com.ferrus.apisac.ui.producto.CrearProducto.recibirMateriaPrima().UPDATE_PRODUCT");
            mpd.setPrecioProducto(producto.getPrecio());
            this.materiaPrimaDetalleService.insertarMateriaPrimaDetalle(mpd);
            this.materiaPrimaDetalleTableModel.setMateriaPrimaDetalleList(materiaPrimaDetalleService.obtenerMateriasPrimasDetalles(producto.getPrecio().getId()));
            this.jtMateriaPrima.setModel(materiaPrimaDetalleTableModel);
            this.materiaPrimaDetalleTableModel.updateTable();
        }
        calcularSubTotales();
        keyStrokeHandler();
    }

    private void modificarMateriaPrima(MateriaPrimaDetalle mpd) {
        Long idNuevo = mpd.getId();
        for (MateriaPrimaDetalle materiaPrimaDetalle : materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList()) {
            if (Objects.equals(idNuevo, materiaPrimaDetalle.getId())) {
                if (formType == CREATE_PRODUCT) {
                    materiaPrimaDetalle.setCantidad(mpd.getCantidad());
                    materiaPrimaDetalle.setPrecioMateriaPrima(mpd.getPrecioMateriaPrima());
                    materiaPrimaDetalle.getMateriaPrima().setUnidadMedida(mpd.getMateriaPrima().getUnidadMedida());
                } else if (formType == UPDATE_PRODUCT) {
                    System.out.println("com.ferrus.apisac.ui.producto.CrearProducto.modificarMateriaPrima().UPDATE_PRODUCT");
                    materiaPrimaDetalle.setCantidad(mpd.getCantidad());
                    materiaPrimaDetalle.setPrecioMateriaPrima(mpd.getPrecioMateriaPrima());
                    materiaPrimaDetalle.getMateriaPrima().setUnidadMedida(mpd.getMateriaPrima().getUnidadMedida());
                    materiaPrimaDetalleService.modificarMateriaPrimaDetalle(materiaPrimaDetalle);
                }
                break;
            }
        }
        materiaPrimaDetalleTableModel.updateTable();
        calcularSubTotales();
        keyStrokeHandler();
    }

    private void removerMateriaPrimaDetalle() {
        int row = this.jtMateriaPrima.getSelectedRow();
        if (row > -1) {
            if (formType == CREATE_PRODUCT) {
                this.materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList().remove(row);
                this.materiaPrimaDetalleTableModel.updateTable();
            } else if (formType == UPDATE_PRODUCT) {
                System.out.println("com.ferrus.apisac.ui.producto.CrearProducto.removerMateriaPrimaDetalle().UPDATE_PRODUCT");
                Long idMpd = this.materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList().get(row).getId();
                this.materiaPrimaDetalleService.eliminarMateriaPrimaDetalle(idMpd);
                this.materiaPrimaDetalleTableModel.setMateriaPrimaDetalleList(materiaPrimaDetalleService.obtenerMateriasPrimasDetalles(producto.getPrecio().getId()));
                this.jtMateriaPrima.setModel(materiaPrimaDetalleTableModel);
                this.materiaPrimaDetalleTableModel.updateTable();
            }
            calcularSubTotales();
            keyStrokeHandler();
        }
        this.jbModificarMat.setEnabled(false);
        this.jbQuitarMat.setEnabled(false);
    }

    private void recibirCostoOperativo(CostoOperativoDetalle cod) {
        Long idNuevo = cod.getCostoOperativo().getId();
        for (CostoOperativoDetalle costoOperativoDetalle : costoOperativoDetalleTableModel.getCostoOperativoDetalleList()) {
            if (Objects.equals(idNuevo, costoOperativoDetalle.getCostoOperativo().getId())) {
                JOptionPane.showMessageDialog(this, CREATE_PRODUCT_REPEATED_OPER_COST_MSG, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (formType == CREATE_PRODUCT) {
            this.costoOperativoDetalleTableModel.agregarCostoOperativo(cod);
        } else if (formType == UPDATE_PRODUCT) {
            cod.setPrecioProducto(producto.getPrecio());
            costoOperativoDetalleService.insertarCostoOperativoDetalle(cod);
        }
        calcularSubTotales();
        keyStrokeHandler();
    }

    private void modificarCostoOperativo(CostoOperativoDetalle cod) {
        Long idNuevo = cod.getId();
        for (CostoOperativoDetalle costoOperativoDetalle : costoOperativoDetalleTableModel.getCostoOperativoDetalleList()) {
            if (Objects.equals(idNuevo, costoOperativoDetalle.getId())) {
                if (formType == CREATE_PRODUCT) {
                    costoOperativoDetalle.setCantidad(cod.getCantidad());
                    costoOperativoDetalle.setPrecioCostoOperativo(cod.getPrecioCostoOperativo());
                    costoOperativoDetalle.getCostoOperativo().setUnidadMedida(cod.getCostoOperativo().getUnidadMedida());
                } else if (formType == UPDATE_PRODUCT) {
                    costoOperativoDetalle.setCantidad(cod.getCantidad());
                    costoOperativoDetalle.setPrecioCostoOperativo(cod.getPrecioCostoOperativo());
                    costoOperativoDetalle.getCostoOperativo().setUnidadMedida(cod.getCostoOperativo().getUnidadMedida());
                    costoOperativoDetalleService.modificarCostoOperativoDetalle(costoOperativoDetalle);
                }
                break;
            }
        }
        materiaPrimaDetalleTableModel.updateTable();
        calcularSubTotales();
        keyStrokeHandler();
    }

    private void removerCostoOperativoDetalle() {
        int row = this.jtCostoOperativo.getSelectedRow();
        if (row > -1) {
            if (formType == CREATE_PRODUCT) {
                this.costoOperativoDetalleTableModel.getCostoOperativoDetalleList().remove(row);
                this.costoOperativoDetalleTableModel.updateTable();
            } else if (formType == UPDATE_PRODUCT) {
                Long idCod = this.costoOperativoDetalleTableModel.getCostoOperativoDetalleList().get(row).getId();
                this.costoOperativoDetalleService.eliminarCostoOperativoDetalle(idCod);
            }
            calcularSubTotales();
            keyStrokeHandler();
        }
        this.jbModificarCO.setEnabled(false);
        this.jbQuitarCO.setEnabled(false);
    }

    private void calcularSubTotales() {
        Double costoVariableTotal = 0.0;
        Double costoFijoTotal = 0.0;
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
            costoFijoTotal = costoFijoTotal + (cant * precio);
        }
        this.jftCostoVariableTotal.setValue(costoVariableTotal);
        this.jftCostoVariableTotal2.setValue(costoVariableTotal);
        this.jftCostoFijoTotal.setValue(costoFijoTotal);
        this.jftCostoFijoTotal2.setValue(costoFijoTotal);
        this.jftCostoVariableUnit.setValue(costoVariableTotal / unidadesProducidas);
        this.jftCostoFijoUnit.setValue(costoFijoTotal / unidadesProducidas);
    }

    private boolean checkearInputs() {
        Double productoImpuesto = null;
        Double productoUtilidad = null;
        Double productoUnidadesProd = null;
        //CONTROLAR UNIDADES PRODUCIDAS
        try {
            productoUnidadesProd = Double.valueOf("" + jftUnidadesProducidas.getValue());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, NUMBER_VALID_UNIT_PROD_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jftUnidadesProducidas.setValue(0);
            return false;
        }
        if (productoUnidadesProd < 0) {
            JOptionPane.showMessageDialog(this, SELECT_VALID_POSITIVE_UNIT_PROD_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jftUnidadesProducidas.setValue(0);
            return false;
        }
        //CONTROLAR UTILIDAD
        try {
            productoUtilidad = Double.valueOf("" + jftUtilidadPorcentaje.getValue());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, NUMBER_VALID_UTILITY_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jftImpuesto.setValue(0);
            return false;

        }
        if (productoUtilidad < 0) {
            JOptionPane.showMessageDialog(this, SELECT_VALID_POSITIVE_UTILITY_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jftUtilidadPorcentaje.setValue(0);
            return false;
        }
        //CONTROLAR IMPUESTP
        try {
            productoImpuesto = Double.valueOf("" + jftImpuesto.getValue());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, NUMBER_VALID_TAX_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jftImpuesto.setValue(0);
            return false;
        }
        if (productoImpuesto < 0) {
            JOptionPane.showMessageDialog(this, SELECT_VALID_POSITIVE_TAX_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jftImpuesto.setValue(0);
            return false;
        }
        return true;
    }

    private void keyStrokeHandler() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Double costoVariableTotal = null;
                Double costoFijoTotal = null;
                Double unidadesProducidas = null;
                Double utilidad = null;
                Double impuesto = null;
                if (checkearInputs()) {
                    unidadesProducidas = Double.valueOf("" + jftUnidadesProducidas.getValue());
                    utilidad = Double.valueOf("" + jftUtilidadPorcentaje.getValue());
                    impuesto = Double.valueOf("" + jftImpuesto.getValue());
                    try {
                        costoVariableTotal = Double.valueOf("" + jftCostoVariableTotal2.getValue());
                    } catch (NumberFormatException e) {
                        javax.swing.JOptionPane.showMessageDialog(null, "",
                                ALERT_MESSAGE,
                                javax.swing.JOptionPane.OK_OPTION);
                        return;
                    }
                    try {
                        costoFijoTotal = Double.valueOf("" + jftCostoFijoTotal2.getValue());
                    } catch (NumberFormatException e) {
                        javax.swing.JOptionPane.showMessageDialog(null, "",
                                ALERT_MESSAGE,
                                javax.swing.JOptionPane.OK_OPTION);
                        return;
                    }
                    Double costoVariableUnit = costoVariableTotal / unidadesProducidas;
                    Double costoFijoUnit = costoFijoTotal / unidadesProducidas;
                    Double costoTotal = costoVariableTotal + costoFijoTotal;
                    Double costoTotalUnitario = costoVariableUnit + costoFijoUnit;
                    Double calculoUtilidad = (utilidad * costoTotalUnitario) / 100;
                    Double precioVentaSinImpuesto = costoTotalUnitario + calculoUtilidad;
                    Double precioVentaConImpuesto = precioVentaSinImpuesto + ((impuesto * precioVentaSinImpuesto) / 100);
                    jftCostoVariableUnit.setValue(costoVariableUnit);
                    jftCostoFijoUnit.setValue(costoFijoUnit);
                    jftCostoTotal.setValue(costoTotal);
                    jftCostoTotalUnit.setValue(costoTotalUnitario);
                    jftUtilidad.setValue(calculoUtilidad);
                    jftPrecioVenta.setValue(precioVentaSinImpuesto);
                    jftPrecioVentaIVA.setValue(precioVentaConImpuesto);
                }
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

    private void itemStateHandler() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                loadDataProductoSubCategoria((ProductoCategoria) jcbProductoCategoria.getSelectedItem());
            }
        });
    }

    private void crearOModificarProducto() {
        String productoNombre = jtfNombreProducto.getText().trim();
        String productoDescripcion = jtfDescripcionProducto.getText().trim();
        Double productoImpuesto = null;
        Double productoUtilidad = null;
        Double productoUnidadesProd = null;
        //CONTROLAR NOMBRE DE PRODUCTO
        if (productoNombre.length() < 1 || productoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, MIN_CHAR_PROD_NAME_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (productoNombre.length() > 30) {
            JOptionPane.showMessageDialog(this, MAX_CHAR_PROD_NAME_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        //CONTROLAR DESCRIPCION DE PRODUCTO
        if (productoDescripcion.length() > 150) {
            JOptionPane.showMessageDialog(this, MAX_CHAR_DESCRIPTION_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (productoDescripcion.isEmpty()) {
            productoDescripcion = null;
        }
        if (checkearInputs()) {
            productoUnidadesProd = Double.valueOf("" + jftUnidadesProducidas.getValue());
            productoUtilidad = Double.valueOf("" + jftUtilidadPorcentaje.getValue());
            productoImpuesto = Double.valueOf("" + jftImpuesto.getValue());
            //CONTROLAR COSTOS OPERATIVOS
            if (costoOperativoDetalleTableModel.getCostoOperativoDetalleList().isEmpty()) {
                int opcion = JOptionPane.showConfirmDialog(this, EMPTY_OPER_COST_LIST_MSG, ALERT_MESSAGE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (opcion != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            //CONTROLAR MATERIA PRIMA
            if (materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList().isEmpty()) {
                int opcion = JOptionPane.showConfirmDialog(this, EMPTY_RAW_MATERIAL_LIST_MSG, ALERT_MESSAGE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (opcion != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            if (formType == CREATE_PRODUCT) {
                Precio precio = new Precio();
                precio.setCostoOperativoDetalles(costoOperativoDetalleTableModel.getCostoOperativoDetalleList());
                precio.setMateriaPrimaDetalles(materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList());
                precio.setUnidadesProducidas(productoUnidadesProd);
                precio.setUtilidad(productoUtilidad);
                for (CostoOperativoDetalle costoOperativoDetalle : costoOperativoDetalleTableModel.getCostoOperativoDetalleList()) {
                    costoOperativoDetalle.setPrecioProducto(precio);
                }
                for (MateriaPrimaDetalle materiaPrimaDetalle : materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList()) {
                    materiaPrimaDetalle.setPrecioProducto(precio);
                }
                precioServicio.insertarPrecio(precio);
                for (CostoOperativoDetalle costoOperativoDetalle : costoOperativoDetalleTableModel.getCostoOperativoDetalleList()) {
                    costoOperativoDetalle.setPrecioProducto(precio);
                    costoOperativoDetalleService.insertarCostoOperativoDetalle(costoOperativoDetalle);
                }
                for (MateriaPrimaDetalle materiaPrimaDetalle : materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList()) {
                    materiaPrimaDetalle.setPrecioProducto(precio);
                    materiaPrimaDetalleService.insertarMateriaPrimaDetalle(materiaPrimaDetalle);
                }
                Producto unProducto = new Producto();
                unProducto.setNombre(productoNombre);
                unProducto.setDescripcion(productoDescripcion);
                unProducto.setNombre(productoNombre);
                unProducto.setImpuesto(productoImpuesto);
                unProducto.setPrecio(precio);
                unProducto.setProductoCategoria((ProductoCategoria) jcbProductoCategoria.getSelectedItem());
                unProducto.setProductoSubCategoria((ProductoSubCategoria) jcbProductoSubCategoria.getSelectedItem());
                unProducto.setUnidadMedida((UnidadMedida) jcbUnidadMedida.getSelectedItem());
                productoServicio.insertarProducto(unProducto);
            } else if (formType == UPDATE_PRODUCT) {
                Precio precio = new Precio();
                precio.setId(producto.getPrecio().getId());
                precio.setCostoOperativoDetalles(costoOperativoDetalleTableModel.getCostoOperativoDetalleList());
                precio.setMateriaPrimaDetalles(materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList());
                precio.setUnidadesProducidas(productoUnidadesProd);
                precio.setUtilidad(productoUtilidad);
                /*for (CostoOperativoDetalle costoOperativoDetalle : costoOperativoDetalleTableModel.getCostoOperativoDetalleList()) {
                    costoOperativoDetalle.setPrecioProducto(precio);
                }
                for (MateriaPrimaDetalle materiaPrimaDetalle : materiaPrimaDetalleTableModel.getMateriaPrimaDetalleList()) {
                    materiaPrimaDetalle.setPrecioProducto(precio);
                }*/
                precioServicio.modificarPrecio(precio);
                Producto unProducto = new Producto();
                unProducto.setId(producto.getId());
                unProducto.setNombre(productoNombre);
                unProducto.setDescripcion(productoDescripcion);
                unProducto.setNombre(productoNombre);
                unProducto.setImpuesto(productoImpuesto);
                unProducto.setPrecio(precio);
                unProducto.setProductoCategoria((ProductoCategoria) jcbProductoCategoria.getSelectedItem());
                unProducto.setProductoSubCategoria((ProductoSubCategoria) jcbProductoSubCategoria.getSelectedItem());
                unProducto.setUnidadMedida((UnidadMedida) jcbUnidadMedida.getSelectedItem());
                productoServicio.modificarProducto(unProducto);
            }
            cerrar();
        }
    }

    private void cerrar() {
        System.runFinalization();
        this.dispose();
    }

    public void loadData(Producto prod) {
        this.jtfNombreProducto.setText(prod.getNombre());
        this.jtfDescripcionProducto.setText(prod.getDescripcion());
        this.jftUnidadesProducidas.setValue(prod.getPrecio().getUnidadesProducidas());
        this.jcbUnidadMedida.setSelectedItem(prod.getUnidadMedida());
        this.jftUtilidadPorcentaje.setValue(prod.getPrecio().getUtilidad());
        this.jcbProductoCategoria.setSelectedItem(prod.getProductoCategoria());
        loadDataProductoSubCategoria((ProductoCategoria) jcbProductoCategoria.getSelectedItem());
        this.jftImpuesto.setValue(prod.getImpuesto());
        //
        this.jftCostoFijoTotal.setValue(prod.getPrecio().costoFijoTotal());
        this.jftCostoFijoUnit.setValue(prod.getPrecio().costoOperativoUnitario());
        //
        this.jftCostoVariableTotal.setValue(prod.getPrecio().costoVariableTotal());
        this.jftCostoVariableUnit.setValue(prod.getPrecio().costoProduccionUnitario());
        //
        this.jftCostoTotal.setValue(prod.getPrecio().costoTotal());
        this.jftCostoTotalUnit.setValue(prod.getPrecio().costoTotalUnitario());
        //
        this.jftUtilidad.setValue(prod.getPrecio().calcularUtilida());
        this.jftPrecioVenta.setValue(prod.getPrecio().precioVentaSinImpuesto());
        this.jftPrecioVentaIVA.setValue(prod.getPrecio().precioVentaConImpuesto(prod.getImpuesto()));

        this.costoOperativoDetalleTableModel.setCostoOperativoDetalleList(prod.getPrecio().getCostoOperativoDetalles());
        this.jtCostoOperativo.setModel(costoOperativoDetalleTableModel);
        this.costoOperativoDetalleTableModel.updateTable();
        this.materiaPrimaDetalleTableModel.setMateriaPrimaDetalleList(prod.getPrecio().getMateriaPrimaDetalles());
        this.jtMateriaPrima.setModel(materiaPrimaDetalleTableModel);
        this.materiaPrimaDetalleTableModel.updateTable();
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
        } else if (src.equals(jbAceptar)) {
            crearOModificarProducto();
        } else if (src.equals(jbCancelar)) {
            cerrar();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyStrokeHandler();
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        itemStateHandler();
    }
}
