/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.costoOperativo;

import com.ferrus.apisac.callback.CostoOperativoCallback;
import static com.ferrus.apisac.util.AppUIConstants.*;
import com.ferrus.apisac.model.CostoOperativo;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.service.CostoOperativoService;
import com.ferrus.apisac.model.service.UnidadMedidaService;
import com.ferrus.apisac.model.serviceImp.CostoOperativoServImpl;
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
public class CrearCostoOperativo extends JDialog implements ActionListener, KeyListener {

    public static final int CREATE_OPERATIVE_COST = 1;
    public static final int UPDATE_OPERATIVE_COST = 2;
    private JTextField jtfNombre, jtDescripcion, jtPrecio;
    private JButton jbAceptar, jbCancelar;
    private JComboBox<UnidadMedida> jcbUnidadMBoxM;
    private CostoOperativoCallback callback;
    private UnidadMedidaService unidadMedidaService;
    private CostoOperativoService costoOperativoService;
    private int typeForm;
    private Long idOperativeCost;

    public CrearCostoOperativo(JDialog jDialog, int typeFrom) {
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
        costoOperativoService = new CostoOperativoServImpl();
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
        jpCenter.add(new JLabel(NAME_OPERATIVE_COST_LABEL));
        jpCenter.add(jtfNombre, "growx, push, wrap");
        jpCenter.add(new JLabel(DESCRIPTION_OPERATIVE_COST_BUTTON_NAME));
        jpCenter.add(jtDescripcion, "growx, push, wrap");
        jpCenter.add(new JLabel(UNIT_OPERATIVE_COST_BUTTON_NAME));
        jpCenter.add(jcbUnidadMBoxM, "growx, push, wrap");
        jpCenter.add(new JLabel(PRECIO_OPERATIVE_COST_BUTTON_NAME));
        jpCenter.add(jtPrecio, "growx, push");
        JPanel jpSouth = new JPanel();
        jpSouth.add(jbAceptar);
        jpSouth.add(jbCancelar);
        getContentPane().add(jpCenter, BorderLayout.CENTER);
        getContentPane().add(jpSouth, BorderLayout.SOUTH);
    }

    private void constructAppWindow(JDialog jDialog) {
        if (typeForm == CREATE_OPERATIVE_COST) {
            setTitle(CREATE_OPERATIVE_COST_FORM_TITLE);
        } else if (typeForm == UPDATE_OPERATIVE_COST) {
            setTitle(UPDATE_OPERATIVE_COST_FORM_TITLE);
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

    public void setCostoOperativoCallback(CostoOperativoCallback callback) {
        this.callback = callback;
    }

    public void setIdCostoOperativo(Long idRawMaterial) {
        this.idOperativeCost = idRawMaterial;
    }

    private void crearCostoOperativo() {
        String nombre = jtfNombre.getText().trim();
        String descripcion = jtDescripcion.getText().trim();
        String precioString = jtPrecio.getText().trim();
        Double precio = -1.0;
        if (nombre.length() < 1 || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_MIN_CHAR_NAME_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nombre.length() > 30) {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_MAX_CHAR_NAME_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (descripcion.length() > 150) {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_MAX_CHAR_DESC_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (descripcion.isEmpty()) {
            descripcion = null;
        }
        if (precioString.length() < 1 || precioString.isEmpty()) {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_MIN_CHAR_PRICE_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            try {
                precio = Double.valueOf(precioString);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, OPERATIVE_COST_NUMBER_VALID_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (precio < 0) {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_VALID_POSITIVE_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<CostoOperativo> list = costoOperativoService.obtenerCostosOperativos(nombre, false);
        if (list.isEmpty()) {
            CostoOperativo costoOperativo = new CostoOperativo();
            costoOperativo.setNombre(nombre);
            costoOperativo.setDescripcion(descripcion);
            costoOperativo.setPrecio(precio);
            costoOperativo.setUnidadMedida((UnidadMedida) jcbUnidadMBoxM.getSelectedItem());
            costoOperativoService.insertarCostoOperativo(costoOperativo);
            this.callback.crearCostoOperativo();
            cerrar();
        } else {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_EXIST_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarCostoOperativo() {
        String nombre = jtfNombre.getText().trim();
        String descripcion = jtDescripcion.getText().trim();
        String precioString = jtPrecio.getText().trim();
        Double precio = -1.0;
        if (nombre.length() < 1 || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_MIN_CHAR_NAME_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nombre.length() > 30) {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_MAX_CHAR_NAME_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (descripcion.length() > 150) {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_MAX_CHAR_DESC_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (descripcion.isEmpty()) {
            descripcion = null;
        }
        if (precioString.length() < 1 || precioString.isEmpty()) {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_MIN_CHAR_PRICE_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            try {
                precio = Double.valueOf(precioString);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, OPERATIVE_COST_NUMBER_VALID_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (precio < 0) {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_VALID_POSITIVE_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<CostoOperativo> list = costoOperativoService.obtenerCostosOperativos(nombre, false);
        if (list.size() == 1) {
            Long idCateTemp = list.get(0).getId();
            if (Objects.equals(idOperativeCost, idCateTemp)) {
                CostoOperativo unCostoOperativo = new CostoOperativo();
                unCostoOperativo.setId(idOperativeCost);
                unCostoOperativo.setDescripcion(descripcion);
                unCostoOperativo.setNombre(nombre);
                unCostoOperativo.setPrecio(precio);
                unCostoOperativo.setUnidadMedida((UnidadMedida) jcbUnidadMBoxM.getSelectedItem());
                costoOperativoService.modificarCostoOperativo(unCostoOperativo);
                callback.modificarCostoOperativo();
                cerrar();
            } else {
                JOptionPane.showMessageDialog(this, OPERATIVE_COST_EXIST_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
        } else if (list.isEmpty()) {
            CostoOperativo unCostoOperativo = new CostoOperativo();
            unCostoOperativo.setId(idOperativeCost);
            unCostoOperativo.setDescripcion(descripcion);
            unCostoOperativo.setNombre(nombre);
            unCostoOperativo.setPrecio(precio);
            unCostoOperativo.setUnidadMedida((UnidadMedida) jcbUnidadMBoxM.getSelectedItem());
            costoOperativoService.modificarCostoOperativo(unCostoOperativo);
            callback.crearCostoOperativo();
            cerrar();
        } else {
            JOptionPane.showMessageDialog(this, OPERATIVE_COST_EXIST_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrar() {
        System.runFinalization();
        dispose();
    }

    private void aceptButtonHandler() {
        if (typeForm == CREATE_OPERATIVE_COST) {
            crearCostoOperativo();
        } else if (typeForm == UPDATE_OPERATIVE_COST) {
            modificarCostoOperativo();
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
