/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.producto;

import com.ferrus.apisac.callback.CrearProductoCallback;
import com.ferrus.apisac.model.CostoOperativo;
import com.ferrus.apisac.model.CostoOperativoDetalle;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.service.UnidadMedidaService;
import com.ferrus.apisac.model.serviceImp.UnidadMedidaServImpl;
import static com.ferrus.apisac.util.AppUIConstants.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Ramiro Ferreira
 */
public class SeleccionarCantidadCostoOperativo extends JDialog implements ActionListener, KeyListener,
        ItemListener {

    public static final int SELECCIONAR = 1;
    public static final int MODIFICAR = 2;
    private JTextField jtfNombre, jtfCantidad, jtfPrecio, jtfTotal;
    private JButton jbAceptar, jbCancelar;
    private JComboBox<UnidadMedida> jcbUnidadMBoxM;
    private CrearProductoCallback crearProductoCallback;
    private UnidadMedidaService unidadMedidaService;
    private CostoOperativo co;
    private CostoOperativoDetalle cod;
    private int formType;

    public SeleccionarCantidadCostoOperativo(JDialog jDialog, CostoOperativo mp, int formType) {
        super(jDialog, true);
        initializeVariables(formType);
        loadData(mp);
        addListeners();
        constructLayout();
        constructAppWindow(jDialog);
    }

    public SeleccionarCantidadCostoOperativo(JDialog jDialog, CostoOperativoDetalle mpd, int formType) {
        super(jDialog, true);
        initializeVariables(formType);
        loadData(mpd);
        addListeners();
        constructLayout();
        constructAppWindow(jDialog);
    }

    private void initializeVariables(int formType) {
        this.formType = formType;
        unidadMedidaService = new UnidadMedidaServImpl();
        jtfNombre = new JTextField();
        jtfNombre.setEditable(false);
        jtfCantidad = new JTextField("0");
        jtfTotal = new JTextField("0");
        jtfTotal.setEditable(false);
        jtfPrecio = new JTextField("0");
        jbAceptar = new JButton(ACEPT_BUTTON_NAME);
        jbCancelar = new JButton(CANCEL_BUTTON_NAME);
        jcbUnidadMBoxM = new JComboBox();
    }

    private void addListeners() {
        jbAceptar.addActionListener(this);
        jbCancelar.addActionListener(this);
        jcbUnidadMBoxM.addItemListener(this);
        /*
        KEYLISTENERS
         */
        jbAceptar.addKeyListener(this);
        jbCancelar.addKeyListener(this);
        jtfCantidad.addKeyListener(this);
        jtfPrecio.addKeyListener(this);
        jtfNombre.addKeyListener(this);
        jtfTotal.addKeyListener(this);
        jcbUnidadMBoxM.addKeyListener(this);
    }

    private void constructLayout() {
        JPanel jpCenter = new JPanel(new MigLayout());
        jpCenter.add(new JLabel(NAME_LABEL));
        jpCenter.add(jtfNombre, "growx, push, wrap");
        jpCenter.add(new JLabel(UNIT_MEASURE_LABEL));
        jpCenter.add(jcbUnidadMBoxM, "growx, push, wrap");
        jpCenter.add(new JLabel(CANT_RAW_MATERIAl_LABEL_NAME));
        jpCenter.add(jtfCantidad, "growx, push, wrap");
        jpCenter.add(new JLabel(PRECIO_LABEL));
        jpCenter.add(jtfPrecio, "growx, push, wrap");
        jpCenter.add(new JLabel(TOTAL_RAW_MATERIAl_LABEL_NAME));
        jpCenter.add(jtfTotal, "growx, push");
        JPanel jpSouth = new JPanel();
        jpSouth.add(jbAceptar);
        jpSouth.add(jbCancelar);
        getContentPane().add(jpCenter, BorderLayout.CENTER);
        getContentPane().add(jpSouth, BorderLayout.SOUTH);
    }

    private void constructAppWindow(JDialog jDialog) {
        switch (formType) {
            case SELECCIONAR: {
                setTitle(SELECT_RAW_MATERIAl_SELECT_FORM_TITLE);
                break;
            }
            case MODIFICAR: {
                setTitle(SELECT_RAW_MATERIAl_UPDATE_FORM_TITLE);
                break;
            }
        }
        setSize(new Dimension(400, 300));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(jDialog);
        jtfCantidad.requestFocusInWindow();
    }

    private void loadData(CostoOperativo co) {
        this.co = co;
        this.jtfNombre.setText(this.co.getNombre());
        this.jtfPrecio.setText(this.co.getPrecio() + "");
        List<UnidadMedida> unidadMedidas = unidadMedidaService.obtenerUnidadMedidasPorCategoria(this.co.getUnidadMedida().getMedidaCategoria());
        jcbUnidadMBoxM.removeAllItems();
        for (UnidadMedida unidadMedida : unidadMedidas) {
            jcbUnidadMBoxM.addItem(unidadMedida);
        }
        jcbUnidadMBoxM.setSelectedItem(co.getUnidadMedida());
    }

    private void loadData(CostoOperativoDetalle cod) {
        this.cod = cod;
        this.jtfNombre.setText(this.cod.getCostoOperativo().getNombre());
        this.jtfPrecio.setText(this.cod.getCostoOperativo().getPrecio() + "");
        List<UnidadMedida> unidadMedidas = unidadMedidaService.obtenerUnidadMedidasPorCategoria(this.cod.getUnidadMedida().getMedidaCategoria());
        jcbUnidadMBoxM.removeAllItems();
        for (UnidadMedida unidadMedida : unidadMedidas) {
            jcbUnidadMBoxM.addItem(unidadMedida);
        }
        jcbUnidadMBoxM.setSelectedItem(cod.getUnidadMedida());
        jtfCantidad.setText("" + this.cod.getCantidad());
        jtfPrecio.setText("" + this.cod.getPrecioCostoOperativo());
        jtfTotal.setText("" + (this.cod.getCantidad() * this.cod.getPrecioCostoOperativo()));
    }

    public void setCrearProductoCallback(CrearProductoCallback crearProductoCallback) {
        this.crearProductoCallback = crearProductoCallback;
    }

    private void enviarMateriaPrima() {
        if (!controlarCantidadIngresada()) {
            return;
        }
        if (!controlarPrecioIngresado()) {
            return;
        }
        Double cantidad = Double.valueOf(jtfCantidad.getText().trim().replace(",", "."));
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, SELECT_VALID_POSITIVE_CANT_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jtfCantidad.setText("0");
            return;
        }
        Double precio = Double.valueOf(jtfPrecio.getText().trim().replace(",", "."));
        if (precio <= 0) {
            JOptionPane.showMessageDialog(this, VALID_POSITIVE_PRICE_NUMBER_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jtfPrecio.setText("0");
            return;
        }
        switch (formType) {
            case SELECCIONAR: {
                CostoOperativo unCostoOperativo = new CostoOperativo();
                unCostoOperativo.setDescripcion(co.getDescripcion());
                unCostoOperativo.setId(co.getId());
                unCostoOperativo.setNombre(co.getNombre());
                unCostoOperativo.setPrecio(co.getPrecio());
                CostoOperativoDetalle unCOD = new CostoOperativoDetalle();
                unCOD.setCantidad(cantidad);
                unCOD.setCostoOperativo(unCostoOperativo);
                unCOD.setPrecioCostoOperativo(precio);
                unCOD.setUnidadMedida((UnidadMedida) jcbUnidadMBoxM.getSelectedItem());
                this.crearProductoCallback.recibirCostoOperativoDetalle(unCOD);
                break;
            }
            case MODIFICAR: {
                CostoOperativo unCostoOperativo = new CostoOperativo();
                unCostoOperativo.setId(cod.getCostoOperativo().getId());
                unCostoOperativo.setNombre(cod.getCostoOperativo().getNombre());
                unCostoOperativo.setPrecio(cod.getCostoOperativo().getPrecio());
                CostoOperativoDetalle unCOD = new CostoOperativoDetalle();
                unCOD.setCantidad(cantidad);
                unCOD.setCostoOperativo(unCostoOperativo);
                unCOD.setPrecioCostoOperativo(precio);
                unCOD.setUnidadMedida((UnidadMedida) jcbUnidadMBoxM.getSelectedItem());
                this.crearProductoCallback.modificarCostoOperativoDetalle(unCOD);
                break;
            }
        }
        cerrar();
    }

    private void cerrar() {
        System.runFinalization();
        dispose();
    }

    private void aceptButtonHandler() {
        enviarMateriaPrima();
    }

    private boolean controlarPrecioIngresado() {
        String precioString = jtfPrecio.getText().trim().replace(",", ".");
        Double precio = -1.0;
        try {
            precio = Double.valueOf(precioString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, NUMBER_VALID_PRICE_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jtfPrecio.setText("0");
            return false;
        }
        return true;
    }

    private boolean controlarCantidadIngresada() {
        String cantidadString = jtfCantidad.getText().trim().replace(",", ".");
        try {
            Double cantidad = Double.valueOf(cantidadString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, NUMBER_VALID_CANT_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jtfCantidad.setText("0");
            return false;
        }

        return true;
    }

    private void cantidadKeyTypedHandler() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (controlarCantidadIngresada() && controlarPrecioIngresado()) {
                    Double cantidad = Double.valueOf(jtfCantidad.getText().trim().replace(",", "."));
                    Double precio = Double.valueOf(jtfPrecio.getText().trim().replace(",", "."));
                    Double total = cantidad * precio;
                    jtfTotal.setText("" + total);
                }
            }
        });
    }

    private void itemStateHandler() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Double precioNuevo = null;
                Double precio = null;
                Double valorActual = null;
                switch (formType) {
                    case SELECCIONAR: {
                        precio = co.getPrecio();
                        valorActual = co.getUnidadMedida().getValor();
                        break;
                    }
                    case MODIFICAR: {
                        precio = cod.getCostoOperativo().getPrecio();
                        valorActual = cod.getUnidadMedida().getValor();
                        break;
                    }
                }
                UnidadMedida unidadMedidaNueva = (UnidadMedida) jcbUnidadMBoxM.getSelectedItem();
                Double valorNuevo = unidadMedidaNueva.getValor();
                if (valorActual < valorNuevo) {
                    precioNuevo = precio * valorActual;
                } else if (valorActual > valorNuevo) {
                    precioNuevo = precio / valorActual;
                } else if (Objects.equals(valorActual, valorNuevo)) {
                    precioNuevo = precio;
                }
                jtfPrecio.setText("" + precioNuevo);
                cantidadKeyTypedHandler();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src.equals(jbAceptar)) {
            aceptButtonHandler();
        } else if (src.equals(jbCancelar)) {
            cerrar();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER: {
                aceptButtonHandler();
                break;
            }
            case KeyEvent.VK_ESCAPE: {
                cerrar();
                break;
            }
            default: {
                if (e.getSource().equals(jtfCantidad)) {
                    cantidadKeyTypedHandler();
                }
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        itemStateHandler();
    }

}
