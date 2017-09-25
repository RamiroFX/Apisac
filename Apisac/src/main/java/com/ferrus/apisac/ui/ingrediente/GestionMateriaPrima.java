/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.ingrediente;

import com.ferrus.apisac.callback.MateriaPrimaCallback;
import com.ferrus.apisac.model.MateriaPrima;
import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.service.MateriaPrimaService;
import com.ferrus.apisac.model.service.ProductoParametrosService;
import com.ferrus.apisac.model.serviceImp.MateriaPrimaServImpl;
import com.ferrus.apisac.model.serviceImp.ProductoParametrosServImpl;
import com.ferrus.apisac.tablemodel.MateriaPrimaTableModel;
import com.ferrus.apisac.util.AppUIConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Ramiro Ferreira
 */
public class GestionMateriaPrima extends JDialog implements ActionListener, KeyListener,
        MouseListener, MateriaPrimaCallback {

    JTextField jtfBuscar;
    JTextArea jtaDescripcion;
    JButton jbBuscar, jbCrear, jbModificar, jbEliminar;
    JTable jtMateriaPrima;
    JScrollPane jspMateriaPrima, jspDescripcion;
    private ProductoParametrosService productoParametrosService;
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
        productoParametrosService = new ProductoParametrosServImpl();
        jtfBuscar = new JTextField();
        jtaDescripcion = new JTextArea();
        jtaDescripcion.setEditable(false);
        jbBuscar = new JButton(AppUIConstants.SEARCH_RAW_MATERIAL_BUTTON_NAME);
        jbCrear = new JButton(AppUIConstants.CREATE_RAW_MATERIAL_BUTTON_NAME);
        jbModificar = new JButton(AppUIConstants.UPDATE_RAW_MATERIAL_BUTTON_NAME);
        jbEliminar = new JButton(AppUIConstants.DELETE_RAW_MATERIAL_BUTTON_NAME);
        jtMateriaPrima = new JTable();
        jspMateriaPrima = new JScrollPane(jtMateriaPrima);
        jspDescripcion = new JScrollPane(jtaDescripcion);
        jspDescripcion.setBorder(new TitledBorder("Descripción de materia prima"));
        jbModificar.setEnabled(false);
        jbEliminar.setEnabled(false);
    }

    private void addListeners() {
        jtfBuscar.addActionListener(this);
        jbBuscar.addActionListener(this);
        jbCrear.addActionListener(this);
        jbModificar.addActionListener(this);
        jbEliminar.addActionListener(this);
        jtMateriaPrima.addKeyListener(this);
        jtfBuscar.addKeyListener(this);
        jtMateriaPrima.addMouseListener(this);
    }

    private void constructLayout() {
        setLayout(new BorderLayout());
        JPanel jpNorth = new JPanel(new BorderLayout());
        jpNorth.add(jtfBuscar, BorderLayout.CENTER);
        jpNorth.add(jbBuscar, BorderLayout.EAST);
        JPanel jpCenter = new JPanel(new BorderLayout());
        jpCenter.add(jspMateriaPrima, BorderLayout.CENTER);
        jpCenter.add(jspDescripcion, BorderLayout.SOUTH);
        JPanel jpSouth = new JPanel();
        jpSouth.add(jbCrear);
        jpSouth.add(jbModificar);
        jpSouth.add(jbEliminar);
        getContentPane().add(jpNorth, BorderLayout.NORTH);
        getContentPane().add(jpCenter, BorderLayout.CENTER);
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
        materiaPrimaTableModel.setMateriaPrimaList(servicio.obtenerMateriasPrimas("", true));
        jtMateriaPrima.setModel(materiaPrimaTableModel);
        materiaPrimaTableModel.updateTable();
    }

    private void invokeCreateRawMaterialForm() {
        CrearMateriaPrima cmp = new CrearMateriaPrima(this, CrearMateriaPrima.CREATE_RAW_MATERIAL);
        cmp.setMateriaPrimaCallback(this);
        this.jbModificar.setEnabled(false);
        this.jbEliminar.setEnabled(false);
    }

    private void invokeUpdateRawMaterialForm() {
        int row = this.jtMateriaPrima.getSelectedRow();
        Long idRawMaterial = null;
        if (row > -1) {
            idRawMaterial = (Long) jtMateriaPrima.getValueAt(row, 0);
        }
        if (idRawMaterial == null) {
            return;
        }
        CrearMateriaPrima cmp = new CrearMateriaPrima(this, CrearMateriaPrima.UPDATE_RAW_MATERIAL);
        cmp.setMateriaPrimaCallback(this);
        cmp.setIdRawMaterial(idRawMaterial);
        this.jbModificar.setEnabled(false);
        this.jbEliminar.setEnabled(false);
    }

    private void deleteRawMaterial() {
        int option = JOptionPane.showConfirmDialog(this, "¿Desea confirmar esta operación?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            try {
                Long idMateriaPrima = Long.valueOf(String.valueOf(this.jtMateriaPrima.getValueAt(jtMateriaPrima.getSelectedRow(), 0)));
                List<Producto> listaProducto = productoParametrosService.obtenerProductosPorMateriaPrimaID(idMateriaPrima);
                if (listaProducto.isEmpty()) {
                    servicio.eliminarMateriaPrima(idMateriaPrima);
                    this.jbModificar.setEnabled(false);
                    this.jbEliminar.setEnabled(false);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Existe productos que se encuentran utilizando la Materia prima.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.jbModificar.setEnabled(false);
        this.jbEliminar.setEnabled(false);
    }

    private void buscar() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String nombre = jtfBuscar.getText().trim().toLowerCase();
                materiaPrimaTableModel.setMateriaPrimaList(servicio.obtenerMateriasPrimas(nombre, true));
                jtMateriaPrima.setModel(materiaPrimaTableModel);
                materiaPrimaTableModel.updateTable();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src.equals(jbBuscar)) {
            buscar();
        } else if (src.equals(jbCrear)) {
            invokeCreateRawMaterialForm();
        } else if (src.equals(jbModificar)) {
            invokeUpdateRawMaterialForm();
        } else if (src.equals(jbEliminar)) {
            deleteRawMaterial();
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
            case KeyEvent.VK_ENTER: {
                if (jtfBuscar.hasFocus()) {
                    buscar();
                }
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(this.jtMateriaPrima)) {
            int fila = this.jtMateriaPrima.rowAtPoint(e.getPoint());
            int columna = this.jtMateriaPrima.columnAtPoint(e.getPoint());
            if ((fila > -1) && (columna > -1)) {
                Long idMp = (Long) this.jtMateriaPrima.getValueAt(fila, 0);
                MateriaPrima mp = servicio.obtenerMateriaPrima(idMp);
                this.jtaDescripcion.setText(mp.getDescripcion());
                this.jbModificar.setEnabled(true);
                this.jbEliminar.setEnabled(true);
            } else {
                this.jbModificar.setEnabled(false);
                this.jbEliminar.setEnabled(false);
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
