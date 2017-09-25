/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.ingrediente;

import com.ferrus.apisac.callback.MateriaPrimaCallback;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.service.UnidadMedidaService;
import com.ferrus.apisac.model.serviceImp.UnidadMedidaServImpl;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ramiro Ferreira
 */
public class CrearMateriaPrima extends JDialog implements ActionListener, KeyListener {

    public static final String CREATE_RAW_MATERIAl_FORM_TITLE = "Crear materia prima";
    public static final String ACEPT_RAW_MATERIAl_BUTTON_NAME = "Aceptar";
    public static final String CANCEL_RAW_MATERIAl_BUTTON_NAME = "Cancelar";
    public static final String NAME_RAW_MATERIAL_LABEL = "Nombre";
    public static final String DESCRIPTION_RAW_MATERIAl_BUTTON_NAME = "Descripción";
    public static final String PRECIO_RAW_MATERIAl_BUTTON_NAME = "Precio";
    public static final String UNIT_RAW_MATERIAl_BUTTON_NAME = "Unidad de medida";
    private JTextField jtfNombre, jtDescripcion, jtPrecio;
    private JButton jbAceptar, jbCancelar;
    private JComboBox<UnidadMedida> jcbUnidadMBoxM;
    private MateriaPrimaCallback callback;
    private UnidadMedidaService servicio;

    public CrearMateriaPrima(JDialog jDialog) {
        super(jDialog, CREATE_RAW_MATERIAl_FORM_TITLE);
        initializeVariables();
        addListeners();
        constructLayout();
        constructAppWindow(jDialog);
        loadData();
    }

    private void initializeVariables() {
        servicio = new UnidadMedidaServImpl();
        jtfNombre = new JTextField();
        jtDescripcion = new JTextField();
        jtPrecio = new JTextField();
        jbAceptar = new JButton();
        jbCancelar = new JButton();
        jcbUnidadMBoxM = new JComboBox();
    }

    private void addListeners() {
        jbAceptar.addActionListener(this);
        jbCancelar.addActionListener(this);
    }

    private void constructLayout() {
        JPanel jpCenter = new JPanel(new GridLayout(4, 2));
        jpCenter.add(new JLabel(NAME_RAW_MATERIAL_LABEL));
        jpCenter.add(jtfNombre);
        jpCenter.add(new JLabel(DESCRIPTION_RAW_MATERIAl_BUTTON_NAME));
        jpCenter.add(jtDescripcion);
        jpCenter.add(new JLabel(UNIT_RAW_MATERIAl_BUTTON_NAME));
        jpCenter.add(jcbUnidadMBoxM);
        jpCenter.add(new JLabel(PRECIO_RAW_MATERIAl_BUTTON_NAME));
        jpCenter.add(jtPrecio);
        JPanel jpSouth = new JPanel();
        jpSouth.add(jbAceptar);
        jpSouth.add(jbCancelar);
        getContentPane().add(jpCenter, BorderLayout.CENTER);
        getContentPane().add(jpSouth, BorderLayout.SOUTH);
    }

    private void constructAppWindow(JDialog jDialog) {
        setSize(400, 300);
        setLocationRelativeTo(jDialog);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadData() {
        List<UnidadMedida> unidadMedidas = servicio.obtenerUnidadMedidas();
        jcbUnidadMBoxM.removeAllItems();
        for (UnidadMedida unidadMedida : unidadMedidas) {
            jcbUnidadMBoxM.addItem(unidadMedida);
        }
    }

    public void setMateriaPrimaCallback(MateriaPrimaCallback callback) {
        this.callback = callback;
    }

    private void crearMateriaPrima() {
        this.callback.crearMateriaPrima();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
