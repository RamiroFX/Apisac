/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.ingrediente;

import static com.ferrus.apisac.util.AppUIConstants.*;
import com.ferrus.apisac.callback.MateriaPrimaCallback;
import com.ferrus.apisac.model.MateriaPrima;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.service.MateriaPrimaService;
import com.ferrus.apisac.model.service.UnidadMedidaService;
import com.ferrus.apisac.model.serviceImp.MateriaPrimaServImpl;
import com.ferrus.apisac.model.serviceImp.UnidadMedidaServImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class CrearMateriaPrima extends JDialog implements ActionListener, KeyListener {

    public static final int CREATE_RAW_MATERIAL = 1;
    public static final int UPDATE_RAW_MATERIAL = 2;
    private JTextField jtfNombre, jtDescripcion, jtPrecio;
    private JButton jbAceptar, jbCancelar;
    private JComboBox<UnidadMedida> jcbUnidadMBoxM;
    private MateriaPrimaCallback callback;
    private UnidadMedidaService unidadMedidaService;
    private MateriaPrimaService materiaPrimaService;
    private int typeForm;
    private Long idRawMaterial;

    public CrearMateriaPrima(JDialog jDialog, int typeFrom) {
        super(jDialog);
        initializeVariables(typeFrom);
        addListeners();
        constructLayout();
        constructAppWindow(jDialog);
        loadData();
    }

    private void initializeVariables(int typeForm) {
        this.typeForm = typeForm;
        unidadMedidaService = new UnidadMedidaServImpl();
        materiaPrimaService = new MateriaPrimaServImpl();
        jtfNombre = new JTextField();
        jtDescripcion = new JTextField();
        jtPrecio = new JTextField();
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
        jtDescripcion.addKeyListener(this);
        jtPrecio.addKeyListener(this);
        jtfNombre.addKeyListener(this);
    }

    private void constructLayout() {
        JPanel jpCenter = new JPanel(new MigLayout());
        jpCenter.add(new JLabel(NAME_RAW_MATERIAL_LABEL));
        jpCenter.add(jtfNombre, "growx, push, wrap");
        jpCenter.add(new JLabel(DESCRIPTION_RAW_MATERIAl_BUTTON_NAME));
        jpCenter.add(jtDescripcion, "growx, push, wrap");
        jpCenter.add(new JLabel(UNIT_RAW_MATERIAl_BUTTON_NAME));
        jpCenter.add(jcbUnidadMBoxM, "growx, push, wrap");
        jpCenter.add(new JLabel(PRECIO_RAW_MATERIAl_BUTTON_NAME));
        jpCenter.add(jtPrecio, "growx, push");
        JPanel jpSouth = new JPanel();
        jpSouth.add(jbAceptar);
        jpSouth.add(jbCancelar);
        getContentPane().add(jpCenter, BorderLayout.CENTER);
        getContentPane().add(jpSouth, BorderLayout.SOUTH);
    }

    private void constructAppWindow(JDialog jDialog) {
        if (typeForm == CREATE_RAW_MATERIAL) {
            setTitle(CREATE_RAW_MATERIAl_FORM_TITLE);
        } else if (typeForm == UPDATE_RAW_MATERIAL) {
            setTitle(UPDATE_RAW_MATERIAl_FORM_TITLE);
        }
        setPreferredSize(new Dimension(400, 300));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(jDialog);
        setVisible(true);
    }

    private void loadData() {
        List<UnidadMedida> unidadMedidas = unidadMedidaService.obtenerUnidadMedidas();
        jcbUnidadMBoxM.removeAllItems();
        for (UnidadMedida unidadMedida : unidadMedidas) {
            jcbUnidadMBoxM.addItem(unidadMedida);
        }
    }

    public void setMateriaPrimaCallback(MateriaPrimaCallback callback) {
        this.callback = callback;
    }

    public void setIdRawMaterial(Long idRawMaterial) {
        this.idRawMaterial = idRawMaterial;
    }

    private void crearMateriaPrima() {
        String nombre = jtfNombre.getText().trim();
        String descripcion = jtDescripcion.getText().trim();
        String precioString = jtPrecio.getText().trim();
        Double precio = -1.0;
        if (nombre.length() < 1 || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_MIN_CHAR_NAME_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nombre.length() > 30) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_MAX_CHAR_NAME_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (descripcion.length() > 150) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_MAX_CHAR_DESC_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (descripcion.isEmpty()) {
            descripcion = null;
        }
        if (precioString.length() < 1 || precioString.isEmpty()) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_MIN_CHAR_PRICE_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            try {
                precio = Double.valueOf(precioString);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, RAW_MATERIAL_NUMBER_VALID_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (precio < 0) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_VALID_POSITIVE_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<MateriaPrima> list = materiaPrimaService.obtenerMateriasPrimas(nombre, false);
        if (list.isEmpty()) {
            MateriaPrima unaMateriaPrima = new MateriaPrima();
            unaMateriaPrima.setNombre(nombre);
            unaMateriaPrima.setDescripcion(descripcion);
            unaMateriaPrima.setPrecio(precio);
            unaMateriaPrima.setUnidadMedida((UnidadMedida) jcbUnidadMBoxM.getSelectedItem());
            materiaPrimaService.insertarMateriaPrima(unaMateriaPrima);
            this.callback.crearMateriaPrima();
            cerrar();
        } else {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_EXIST_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarMateriaPrima() {
        String nombre = jtfNombre.getText().trim();
        String descripcion = jtDescripcion.getText().trim();
        String precioString = jtPrecio.getText().trim();
        Double precio = -1.0;
        if (nombre.length() < 1 || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_MIN_CHAR_NAME_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nombre.length() > 30) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_MAX_CHAR_NAME_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (descripcion.length() > 150) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_MAX_CHAR_DESC_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (descripcion.isEmpty()) {
            descripcion = null;
        }
        if (precioString.length() < 1 || precioString.isEmpty()) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_MIN_CHAR_PRICE_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            try {
                precio = Double.valueOf(precioString);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, RAW_MATERIAL_NUMBER_VALID_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (precio < 0) {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_VALID_POSITIVE_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<MateriaPrima> list = materiaPrimaService.obtenerMateriasPrimas(nombre, false);
        if (list.size() == 1) {
            Long idCateTemp = list.get(0).getId();
            if (Objects.equals(idRawMaterial, idCateTemp)) {
                MateriaPrima unaMateriaPrima = new MateriaPrima();
                unaMateriaPrima.setId(idRawMaterial);
                unaMateriaPrima.setDescripcion(descripcion);
                unaMateriaPrima.setNombre(nombre);
                unaMateriaPrima.setPrecio(precio);
                unaMateriaPrima.setUnidadMedida((UnidadMedida) jcbUnidadMBoxM.getSelectedItem());
                materiaPrimaService.modificarMateriasPrimas(unaMateriaPrima);
                callback.crearMateriaPrima();
                cerrar();
            } else {
                JOptionPane.showMessageDialog(this, RAW_MATERIAL_EXIST_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
        } else if (list.isEmpty()) {
            MateriaPrima unaMateriaPrima = new MateriaPrima();
            unaMateriaPrima.setId(idRawMaterial);
            unaMateriaPrima.setDescripcion(descripcion);
            unaMateriaPrima.setNombre(nombre);
            unaMateriaPrima.setPrecio(precio);
            unaMateriaPrima.setUnidadMedida((UnidadMedida) jcbUnidadMBoxM.getSelectedItem());
            materiaPrimaService.modificarMateriasPrimas(unaMateriaPrima);
            callback.crearMateriaPrima();
            cerrar();
        } else {
            JOptionPane.showMessageDialog(this, RAW_MATERIAL_EXIST_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrar() {
        System.runFinalization();
        dispose();
    }

    private void aceptButtonHandler() {
        if (typeForm == CREATE_RAW_MATERIAL) {
            crearMateriaPrima();
        } else if (typeForm == UPDATE_RAW_MATERIAL) {
            modificarMateriaPrima();
        }
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
