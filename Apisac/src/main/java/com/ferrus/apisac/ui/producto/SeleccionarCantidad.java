/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.producto;

import com.ferrus.apisac.callback.CrearProductoCallback;
import com.ferrus.apisac.model.MateriaPrima;
import com.ferrus.apisac.model.MateriaPrimaDetalle;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.service.UnidadMedidaService;
import com.ferrus.apisac.model.serviceImp.UnidadMedidaServImpl;
import static com.ferrus.apisac.util.AppUIConstants.ACEPT_RAW_MATERIAl_BUTTON_NAME;
import static com.ferrus.apisac.util.AppUIConstants.ALERT_MESSAGE;
import static com.ferrus.apisac.util.AppUIConstants.CANCEL_RAW_MATERIAl_BUTTON_NAME;
import static com.ferrus.apisac.util.AppUIConstants.CANT_RAW_MATERIAl_LABEL_NAME;
import static com.ferrus.apisac.util.AppUIConstants.SELECT_RAW_MATERIAl_FORM_TITLE;
import static com.ferrus.apisac.util.AppUIConstants.NAME_RAW_MATERIAL_LABEL;
import static com.ferrus.apisac.util.AppUIConstants.PRECIO_RAW_MATERIAl_BUTTON_NAME;
import static com.ferrus.apisac.util.AppUIConstants.RAW_MATERIAL_MIN_CHAR_PRICE_MESSAGE;
import static com.ferrus.apisac.util.AppUIConstants.RAW_MATERIAL_NUMBER_VALID_CANT_MESSAGE;
import static com.ferrus.apisac.util.AppUIConstants.RAW_MATERIAL_NUMBER_VALID_MESSAGE;
import static com.ferrus.apisac.util.AppUIConstants.RAW_MATERIAL_VALID_POSITIVE_MESSAGE;
import static com.ferrus.apisac.util.AppUIConstants.SELECT_MATERIAL_MIN_CHAR_CANT_MESSAGE;
import static com.ferrus.apisac.util.AppUIConstants.SELECT_MATERIAL_VALID_POSITIVE_CANT_MESSAGE;
import static com.ferrus.apisac.util.AppUIConstants.TOTAL_RAW_MATERIAl_LABEL_NAME;
import static com.ferrus.apisac.util.AppUIConstants.UNIT_RAW_MATERIAl_BUTTON_NAME;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
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
public class SeleccionarCantidad extends JDialog implements ActionListener, KeyListener {

    private JTextField jtfNombre, jtfCantidad, jtfPrecio, jtfTotal;
    private JButton jbAceptar, jbCancelar;
    private JComboBox<UnidadMedida> jcbUnidadMBoxM;
    private CrearProductoCallback crearProductoCallback;
    private UnidadMedidaService unidadMedidaService;
    private MateriaPrima mp;

    public SeleccionarCantidad(JDialog jDialog, MateriaPrima mp) {
        super(jDialog);
        initializeVariables();
        addListeners();
        constructLayout();
        constructAppWindow(jDialog);
        loadData(mp);
    }

    private void initializeVariables() {
        unidadMedidaService = new UnidadMedidaServImpl();
        jtfNombre = new JTextField();
        jtfNombre.setEditable(false);
        jtfCantidad = new JTextField();
        jtfTotal = new JTextField();
        jtfTotal.setEditable(false);
        jtfPrecio = new JTextField();
        jbAceptar = new JButton(ACEPT_RAW_MATERIAl_BUTTON_NAME);
        jbCancelar = new JButton(CANCEL_RAW_MATERIAl_BUTTON_NAME);
        jcbUnidadMBoxM = new JComboBox();
    }

    private void addListeners() {
        jbAceptar.addActionListener(this);
        jbCancelar.addActionListener(this);
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
        jpCenter.add(new JLabel(NAME_RAW_MATERIAL_LABEL));
        jpCenter.add(jtfNombre, "growx, push, wrap");
        jpCenter.add(new JLabel(UNIT_RAW_MATERIAl_BUTTON_NAME));
        jpCenter.add(jcbUnidadMBoxM, "growx, push, wrap");
        jpCenter.add(new JLabel(CANT_RAW_MATERIAl_LABEL_NAME));
        jpCenter.add(jtfCantidad, "growx, push, wrap");
        jpCenter.add(new JLabel(PRECIO_RAW_MATERIAl_BUTTON_NAME));
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
        setTitle(SELECT_RAW_MATERIAl_FORM_TITLE);
        setPreferredSize(new Dimension(400, 300));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(jDialog);
        setVisible(true);
    }

    private void loadData(MateriaPrima mp) {
        this.mp = mp;
        this.jtfNombre.setText(this.mp.getNombre());
        this.jtfPrecio.setText(this.mp.getPrecio() + "");
        List<UnidadMedida> unidadMedidas = unidadMedidaService.obtenerUnidadMedidasPorCategoria(this.mp.getUnidadMedida().getMedidaCategoria());
        jcbUnidadMBoxM.removeAllItems();
        for (UnidadMedida unidadMedida : unidadMedidas) {
            jcbUnidadMBoxM.addItem(unidadMedida);
        }
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
        Double cantidad = Double.valueOf(jtfCantidad.getText().trim());
        Double precio = Double.valueOf(jtfPrecio.getText().trim());
        MateriaPrimaDetalle mpd = new MateriaPrimaDetalle();
        mpd.setCantidad(cantidad);
        mpd.setMateriaPrima(this.mp);
        mpd.setPrecioMateriaPrima(precio);
        this.crearProductoCallback.recibirMateriaPrimaDetalle(mpd);
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
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_NUMBER_VALID_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jtfPrecio.setText("0");
            return false;
        }
        if (precio < 0) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_VALID_POSITIVE_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jtfPrecio.setText("0");
            return false;
        }
        return true;
    }

    private boolean controlarCantidadIngresada() {
        Double cantidad = -1.0;
        String cantidadString = jtfCantidad.getText().trim().replace(",", ".");
        try {
            cantidad = Double.valueOf(cantidadString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_NUMBER_VALID_CANT_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jtfCantidad.setText("0");
            return false;
        }
        if (cantidad < 0) {
            JOptionPane.showMessageDialog(this, SELECT_MATERIAL_VALID_POSITIVE_CANT_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
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
                    Double cantidad = Double.valueOf(jtfCantidad.getText().trim());
                    Double precio = Double.valueOf(jtfPrecio.getText().trim());
                    Double total = cantidad * precio;
                    jtfTotal.setText(total.toString());
                }
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
        if (e.getSource().equals(jtfCantidad)) {
            cantidadKeyTypedHandler();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE: {
                cerrar();
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
