/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.ingrediente;

import com.ferrus.apisac.callback.MateriaPrimaCallback;
import com.ferrus.apisac.model.service.MateriaPrimaService;
import com.ferrus.apisac.model.serviceImp.MateriaPrimaServImpl;
import com.ferrus.apisac.tablemodel.MateriaPrimaTableModel;
import com.ferrus.apisac.util.AppUIConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Ramiro Ferreira
 */
public class GestionMateriaPrima extends JDialog implements ActionListener, KeyListener, MateriaPrimaCallback {

    JTextField jtfBuscar;
    JButton jbBuscar, jbCrear, jbModificar, jbEliminar;
    JTable jtMateriaPrima;
    JScrollPane jspMateriaPrima;
    private MateriaPrimaService servicio;
    private MateriaPrimaTableModel materiaPrimaTableModel;

    public GestionMateriaPrima(JFrame jframe) {
        super(jframe, AppUIConstants.RAW_MATERIAL_TITLE);
        initializeVariables();
        addListeners();
        constructLayout();
        constructAppWindow(jframe);
        loadData();
    }

    private void initializeVariables() {
        materiaPrimaTableModel = new MateriaPrimaTableModel();
        servicio = new MateriaPrimaServImpl();
        jtfBuscar = new JTextField();
        jbBuscar = new JButton(AppUIConstants.SEARCH_RAW_MATERIAL_BUTTON_NAME);
        jbCrear = new JButton(AppUIConstants.CREATE_RAW_MATERIAL_BUTTON_NAME);
        jbModificar = new JButton(AppUIConstants.UPDATE_RAW_MATERIAL_BUTTON_NAME);
        jbEliminar = new JButton(AppUIConstants.DELETE_RAW_MATERIAL_BUTTON_NAME);
        jtMateriaPrima = new JTable();
        jspMateriaPrima = new JScrollPane(jtMateriaPrima);
    }

    private void addListeners() {
        jtfBuscar.addActionListener(this);
        jbBuscar.addActionListener(this);
        jbCrear.addActionListener(this);
        jbModificar.addActionListener(this);
        jbEliminar.addActionListener(this);
        jtMateriaPrima.addKeyListener(this);
    }

    private void constructLayout() {
        setLayout(new BorderLayout());
        JPanel jpNorth = new JPanel(new BorderLayout());
        jpNorth.add(jtfBuscar, BorderLayout.CENTER);
        jpNorth.add(jbBuscar, BorderLayout.EAST);
        JPanel jpSouth = new JPanel();
        jpSouth.add(jbCrear);
        jpSouth.add(jbModificar);
        jpSouth.add(jbEliminar);
        getContentPane().add(jpNorth, BorderLayout.NORTH);
        getContentPane().add(jspMateriaPrima, BorderLayout.CENTER);
        getContentPane().add(jpSouth, BorderLayout.SOUTH);
    }

    private void constructAppWindow(JFrame jframe) {
        setSize(new Dimension(AppUIConstants.RAW_MATERIAL_WINDOWS_SIZE_WIDTH, AppUIConstants.RAW_MATERIAL_WINDOWS_SIZE_HEIGHT));
        setLocationRelativeTo(jframe);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void cerrar() {
        System.runFinalization();
        this.dispose();
    }

    private void loadData() {
        materiaPrimaTableModel.setMateriaPrimaList(servicio.obtenerMateriasPrimas(""));
        jtMateriaPrima.setModel(materiaPrimaTableModel);
        materiaPrimaTableModel.updateTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src.equals(jbBuscar)) {
            System.out.println("com.ferrus.apisac.ui.ingrediente.GestionMateriaPrima.actionPerformed().jbBuscar");
        } else if (src.equals(jbCrear)) {
            System.out.println("com.ferrus.apisac.ui.ingrediente.GestionMateriaPrima.actionPerformed().jbCrear");
        } else if (src.equals(jbModificar)) {
            System.out.println("com.ferrus.apisac.ui.ingrediente.GestionMateriaPrima.actionPerformed().jbModificar");
        } else if (src.equals(jbEliminar)) {
            System.out.println("com.ferrus.apisac.ui.ingrediente.GestionMateriaPrima.actionPerformed().jbEliminar");
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

    @Override
    public void crearMateriaPrima() {
        loadData();
    }

}
