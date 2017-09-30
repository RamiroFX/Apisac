/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.inicio;

import com.ferrus.apisac.model.Producto;
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
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_CATEGORY_LABEL;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_FIXED_COST_UNIT_PROD_NAME;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_OPER_COST_TITLE;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_PROD_COST_TITLE;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_PROD_NAME;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_SELL_PRICE_LABEL;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_SELL_PRICE_TAX_LABEL;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_SUB_CATEGORY_LABEL;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_TAX_LABEL;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_TOTAL_COST_PROD_NAME;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_TOTAL_FIXED_COST_PROD_NAME;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_TOTAL_UNIT_COST_PROD_NAME;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_TOTAL_VAR_COST_PROD_NAME;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_UM_LABEL;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_UNIT_PROD_NAME;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_UTILITY_LABEL;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_UTILITY_PERCENT_LABEL;
import static com.ferrus.apisac.util.AppUIConstants.CREATE_PRODUCT_VAR_COST_UNIT_PROD_NAME;
import static com.ferrus.apisac.util.AppUIConstants.DESCRIPTION_LABEL;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Ramiro Ferreira
 */
public class MostrarInfoProducto extends JPanel {

    /*
    SELL PRICE VARS    
     */
    private JFormattedTextField jftCostoFijoUnit, jftCostoVariableUnit,
            jftCostoTotalUnit, jftCostoTotal, jftCostoFijoTotal,
            jftCostoVariableTotal, jftUnidadesProducidas,
            jftUtilidadPorcentaje, jftImpuesto,
            jftUtilidad, jftPrecioVentaIVA, jftPrecioVenta;
    private JTextField jtfUnidadMedida;
    private JTextField jtfProductoCategoria;
    private JTextField jtfProductoSubCategoria;
    private JTextField jtfNombreProducto, jtfDescripcionProducto;
    /*    
    PROD COST VARS
     */
    private JTable jtMateriaPrima;
    private JScrollPane jspMateriaPrima;
    /*
    OPERATIVE COST VARS
     */
    private JTable jtCostoOperativo;
    private JScrollPane jspCostoOperativo;
    /*
    GENERAL VARS    
     */
    private JTabbedPane jtpPrincipal;
    private JPanel jpMateriaPrima, jpGastoOperativo, jpPrecio;
    private MateriaPrimaDetalleTableModel materiaPrimaDetalleTableModel;
    private CostoOperativoDetalleTableModel costoOperativoDetalleTableModel;

    private ProductoParametrosService productoServicio;
    private UnidadMedidaService unidadMedidaServicio;
    private PrecioService precioServicio;
    private CostoOperativoDetalleService costoOperativoDetalleService;
    private MateriaPrimaDetalleService materiaPrimaDetalleService;

    public MostrarInfoProducto() {
        initializeVariables();
        constructLayout();
    }

    private void initializeVariables() {
        this.materiaPrimaDetalleTableModel = new MateriaPrimaDetalleTableModel();
        this.costoOperativoDetalleTableModel = new CostoOperativoDetalleTableModel();
        this.productoServicio = new ProductoParametrosServImpl();
        this.unidadMedidaServicio = new UnidadMedidaServImpl();
        this.precioServicio = new PrecioServImpl();
        this.costoOperativoDetalleService = new CostoOperativoDetalleServImpl();
        this.materiaPrimaDetalleService = new MateriaPrimaDetalleServImpl();
        this.jtpPrincipal = new JTabbedPane();
        this.jpMateriaPrima = new JPanel(new BorderLayout());
        this.jpGastoOperativo = new JPanel(new BorderLayout());
        this.jpPrecio = new JPanel(new MigLayout());
        this.jtfUnidadMedida = new JTextField();
        this.jtfUnidadMedida.setEditable(false);
        this.jtfProductoCategoria = new JTextField();
        this.jtfProductoCategoria.setEditable(false);
        this.jtfProductoSubCategoria = new JTextField();
        this.jtfProductoSubCategoria.setEditable(false);
        this.jtMateriaPrima = new JTable(materiaPrimaDetalleTableModel);
        this.jtMateriaPrima.getTableHeader().setReorderingAllowed(false);
        this.jspMateriaPrima = new JScrollPane(jtMateriaPrima);
        this.jtCostoOperativo = new JTable(costoOperativoDetalleTableModel);
        this.jtCostoOperativo.getTableHeader().setReorderingAllowed(false);
        this.jspCostoOperativo = new JScrollPane(jtCostoOperativo);

        this.jtfNombreProducto = new JTextField();
        this.jtfNombreProducto.setEditable(false);
        this.jtfDescripcionProducto = new JTextField();
        this.jtfDescripcionProducto.setEditable(false);

        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        this.jftUnidadesProducidas = new JFormattedTextField(format);
        this.jftUnidadesProducidas.setValue(0.0);
        this.jftUnidadesProducidas.setEditable(false);
        this.jftImpuesto = new JFormattedTextField(format);
        this.jftImpuesto.setValue(0.0);
        this.jftImpuesto.setEditable(false);
        this.jftUtilidadPorcentaje = new JFormattedTextField(format);
        this.jftUtilidadPorcentaje.setValue(0.0);
        this.jftUtilidadPorcentaje.setEditable(false);
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
    }

    private void constructLayout() {
        setLayout(new GridLayout(2, 1));
        constructLayoutPrecio();
        constructLayoutCostoProduccion();
        constructLayoutCostoOperativo();
        add(jpPrecio);
        add(jtpPrincipal);
    }

    private void constructLayoutPrecio() {
        jpPrecio.add(new JLabel(CREATE_PRODUCT_PROD_NAME));
        jpPrecio.add(jtfNombreProducto, "growx, push");
        jpPrecio.add(new JLabel(DESCRIPTION_LABEL));
        jpPrecio.add(jtfDescripcionProducto, "growx, push, wrap");

        jpPrecio.add(new JLabel(CREATE_PRODUCT_UNIT_PROD_NAME));
        jpPrecio.add(jftUnidadesProducidas, "growx, push");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_UM_LABEL));
        jpPrecio.add(jtfUnidadMedida, "growx, push, wrap");

        jpPrecio.add(new JLabel(CREATE_PRODUCT_UTILITY_PERCENT_LABEL));
        jpPrecio.add(jftUtilidadPorcentaje, "growx, push");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_CATEGORY_LABEL));
        jpPrecio.add(jtfProductoCategoria, "growx, push, wrap");

        jpPrecio.add(new JLabel(CREATE_PRODUCT_TAX_LABEL));
        jpPrecio.add(jftImpuesto, "growx, push");
        jpPrecio.add(new JLabel(CREATE_PRODUCT_SUB_CATEGORY_LABEL));
        jpPrecio.add(jtfProductoSubCategoria, "growx, push, wrap");
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
    }

    private void constructLayoutCostoProduccion() {
        jpMateriaPrima.add(jspMateriaPrima, BorderLayout.CENTER);
        this.jtpPrincipal.addTab(CREATE_PRODUCT_PROD_COST_TITLE, jpMateriaPrima);
    }

    private void constructLayoutCostoOperativo() {
        jpGastoOperativo.add(jspCostoOperativo, BorderLayout.CENTER);
        this.jtpPrincipal.addTab(CREATE_PRODUCT_OPER_COST_TITLE, jpGastoOperativo);
    }

    public void loadData(Producto producto) {
        this.jtfNombreProducto.setText(producto.getNombre());
        this.jtfDescripcionProducto.setText(producto.getDescripcion());
        this.jftUnidadesProducidas.setValue(producto.getPrecio().getUnidadesProducidas());
        this.jtfUnidadMedida.setText(producto.getUnidadMedida().toString());
        this.jftUtilidadPorcentaje.setValue(producto.getPrecio().getUtilidad());
        this.jtfProductoCategoria.setText(producto.getProductoCategoria().getDescripcion());
        this.jftImpuesto.setValue(producto.getImpuesto());
        this.jtfProductoSubCategoria.setText(producto.getProductoSubCategoria().getDescripcion());
        //
        this.jftCostoFijoTotal.setValue(producto.getPrecio().costoFijoTotal());
        this.jftCostoFijoUnit.setValue(producto.getPrecio().costoOperativoUnitario());
        //
        this.jftCostoVariableTotal.setValue(producto.getPrecio().costoVariableTotal());
        this.jftCostoVariableUnit.setValue(producto.getPrecio().costoProduccionUnitario());
        //
        this.jftCostoTotal.setValue(producto.getPrecio().costoTotal());
        this.jftCostoTotalUnit.setValue(producto.getPrecio().costoTotalUnitario());
        //
        this.jftUtilidad.setValue(producto.getPrecio().calcularUtilida());
        this.jftPrecioVenta.setValue(producto.getPrecio().precioVentaSinImpuesto());
        this.jftPrecioVentaIVA.setValue(producto.getPrecio().precioVentaConImpuesto(producto.getImpuesto()));
        this.materiaPrimaDetalleTableModel.setMateriaPrimaDetalleList(producto.getPrecio().getMateriaPrimaDetalles());
        this.costoOperativoDetalleTableModel.setCostoOperativoDetalleList(producto.getPrecio().getCostoOperativoDetalles());
        this.jtCostoOperativo.setModel(costoOperativoDetalleTableModel);
        this.costoOperativoDetalleTableModel.updateTable();
        this.jtMateriaPrima.setModel(materiaPrimaDetalleTableModel);
        this.materiaPrimaDetalleTableModel.updateTable();
    }

}
