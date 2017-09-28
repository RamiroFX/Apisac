/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.materiaPrima;

import com.ferrus.apisac.callback.CrearProductoCallback;
import com.ferrus.apisac.callback.MateriaPrimaCallback;
import com.ferrus.apisac.model.MateriaPrima;
import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.service.MateriaPrimaService;
import com.ferrus.apisac.model.service.ProductoParametrosService;
import com.ferrus.apisac.model.serviceImp.MateriaPrimaServImpl;
import com.ferrus.apisac.model.serviceImp.ProductoParametrosServImpl;
import com.ferrus.apisac.tablemodel.MateriaPrimaTableModel;
import com.ferrus.apisac.ui.producto.SeleccionarCantidadMateriaPrima;
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

    public static final int GESTIONAR = 1;
    public static final int SELECCIONAR = 2;

    JTextField jtfBuscar;
    JTextArea jtaDescripcion;
    JButton jbBuscar, jbCrear, jbModificar, jbEliminar, jbSeleccionar;
    JTable jtMateriaPrima;
    JScrollPane jspMateriaPrima, jspDescripcion;
    private ProductoParametrosService productoParametrosService;
    private MateriaPrimaService materiaPrimaService;
    private MateriaPrimaTableModel materiaPrimaTableModel;
    private int formType;
    private CrearProductoCallback crearProductoCallback;

    public GestionMateriaPrima(JFrame jframe, int formType) {
        super(jframe, true);
        initializeVariables(formType);
        loadData();
        addListeners();
        constructLayout();
        constructAppWindow(jframe);
    }

    public GestionMateriaPrima(JDialog jdialog, int formType) {
        super(jdialog, true);
        initializeVariables(formType);
        loadData();
        addListeners();
        constructLayout();
        constructAppWindow(jdialog);
    }

    private void initializeVariables(int formType) {
        this.formType = formType;
        materiaPrimaTableModel = new MateriaPrimaTableModel();
        materiaPrimaService = new MateriaPrimaServImpl();
        productoParametrosService = new ProductoParametrosServImpl();
        jtfBuscar = new JTextField();
        jtaDescripcion = new JTextArea();
        jtaDescripcion.setEditable(false);
        jbBuscar = new JButton(AppUIConstants.SEARCH_BUTTON_NAME);
        jbCrear = new JButton(AppUIConstants.CREATE_BUTTON_NAME);
        jbModificar = new JButton(AppUIConstants.UPDATE_BUTTON_NAME);
        jbEliminar = new JButton(AppUIConstants.DELETE_BUTTON_NAME);
        jtMateriaPrima = new JTable();
        jtMateriaPrima.getTableHeader().setReorderingAllowed(false);
        jspMateriaPrima = new JScrollPane(jtMateriaPrima);
        jspDescripcion = new JScrollPane(jtaDescripcion);
        jspDescripcion.setBorder(new TitledBorder(AppUIConstants.DESCRIPTION_RAW_MATERIAL_PANEL_NAME));
        jbModificar.setEnabled(false);
        jbEliminar.setEnabled(false);
        if (formType == SELECCIONAR) {
            this.jbSeleccionar = new JButton(AppUIConstants.SELECT_BUTTON_NAME);
            this.jbSeleccionar.setEnabled(false);
        }
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
        if (formType == SELECCIONAR) {
            this.jbSeleccionar.addActionListener(this);
        }
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
        if (formType == SELECCIONAR) {
            jpSouth.add(jbSeleccionar);
        }
        jpSouth.add(jbCrear);
        jpSouth.add(jbModificar);
        jpSouth.add(jbEliminar);
        getContentPane().add(jpNorth, BorderLayout.NORTH);
        getContentPane().add(jpCenter, BorderLayout.CENTER);
        getContentPane().add(jpSouth, BorderLayout.SOUTH);
    }

    private void constructAppWindow(JFrame jframe) {
        if (this.formType == SELECCIONAR) {
            setSize(new Dimension(AppUIConstants.SELECT_RAW_MATERIAL_WINDOWS_SIZE_WIDTH, AppUIConstants.SELECT_RAW_MATERIAL_WINDOWS_SIZE_HEIGHT));
        } else {
            setSize(new Dimension(AppUIConstants.RAW_MATERIAL_WINDOWS_SIZE_WIDTH, AppUIConstants.RAW_MATERIAL_WINDOWS_SIZE_HEIGHT));
        }
        setTitle(AppUIConstants.RAW_MATERIAL_TITLE);
        setLocationRelativeTo(jframe);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void constructAppWindow(JDialog jdialog) {
        if (this.formType == SELECCIONAR) {
            setSize(new Dimension(AppUIConstants.SELECT_RAW_MATERIAL_WINDOWS_SIZE_WIDTH, AppUIConstants.SELECT_RAW_MATERIAL_WINDOWS_SIZE_HEIGHT));
        } else {
            setSize(new Dimension(AppUIConstants.RAW_MATERIAL_WINDOWS_SIZE_WIDTH, AppUIConstants.RAW_MATERIAL_WINDOWS_SIZE_HEIGHT));
        }
        setTitle(AppUIConstants.RAW_MATERIAL_TITLE);
        setLocationRelativeTo(jdialog);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void cerrar() {
        System.runFinalization();
        this.dispose();
    }

    private void loadData() {
        materiaPrimaTableModel.setMateriaPrimaList(materiaPrimaService.obtenerMateriasPrimas("", true));
        jtMateriaPrima.setModel(materiaPrimaTableModel);
        materiaPrimaTableModel.updateTable();
    }

    private void invokeCreateRawMaterialForm() {
        CrearMateriaPrima cmp = new CrearMateriaPrima(this, CrearMateriaPrima.CREATE_RAW_MATERIAL);
        cmp.setMateriaPrimaCallback(this);
        this.jbModificar.setEnabled(false);
        this.jbEliminar.setEnabled(false);
        if (formType == SELECCIONAR) {
            this.jbSeleccionar.setEnabled(false);
        }
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
        if (formType == SELECCIONAR) {
            this.jbSeleccionar.setEnabled(false);
        }
    }

    private void deleteRawMaterial() {
        int option = JOptionPane.showConfirmDialog(this, "¿Desea confirmar esta operación?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            try {
                Long idMateriaPrima = Long.valueOf(String.valueOf(this.jtMateriaPrima.getValueAt(jtMateriaPrima.getSelectedRow(), 0)));
                List<Producto> listaProducto = productoParametrosService.obtenerProductosPorMateriaPrimaID(idMateriaPrima);
                if (listaProducto.isEmpty()) {
                    materiaPrimaService.eliminarMateriaPrima(idMateriaPrima);
                    this.jbModificar.setEnabled(false);
                    this.jbEliminar.setEnabled(false);
                    if (formType == SELECCIONAR) {
                        this.jbSeleccionar.setEnabled(false);
                    }
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
        if (formType == SELECCIONAR) {
            this.jbSeleccionar.setEnabled(false);
        }
    }

    private void invokeSelectRawMaterialForm() {
        int row = jtMateriaPrima.getSelectedRow();
        if (row > -1) {
            Long idMp = (Long) this.jtMateriaPrima.getValueAt(row, 0);
            MateriaPrima mp = materiaPrimaService.obtenerMateriaPrima(idMp);
            SeleccionarCantidadMateriaPrima sc = new SeleccionarCantidadMateriaPrima(this, mp, SeleccionarCantidadMateriaPrima.SELECCIONAR);
            sc.setCrearProductoCallback(crearProductoCallback);
            sc.setVisible(true);
            this.jbModificar.setEnabled(false);
            this.jbEliminar.setEnabled(false);
            if (formType == SELECCIONAR) {
                this.jbSeleccionar.setEnabled(false);
            }
        }
    }

    private void buscar() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String nombre = jtfBuscar.getText().trim().toLowerCase();
                materiaPrimaTableModel.setMateriaPrimaList(materiaPrimaService.obtenerMateriasPrimas(nombre, true));
                jtMateriaPrima.setModel(materiaPrimaTableModel);
                materiaPrimaTableModel.updateTable();
            }
        });
    }

    public void setCrearProductoCallback(CrearProductoCallback crearProductoCallback) {
        this.crearProductoCallback = crearProductoCallback;
    }

    @Override
    public void crearMateriaPrima() {
        loadData();
    }

    @Override
    public void modificarMateriaPrima() {
        loadData();
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
        } else if (formType == SELECCIONAR) {
            if (src.equals(jbSeleccionar)) {
                invokeSelectRawMaterialForm();
            }
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
                    break;
                }
            }
        }
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
                Long idMp = (Long) this.jtMateriaPrima.getValueAt(fila, 0);
                MateriaPrima mp = materiaPrimaService.obtenerMateriaPrima(idMp);
                this.jtaDescripcion.setText(mp.getDescripcion());
                this.jbModificar.setEnabled(true);
                this.jbEliminar.setEnabled(true);
                if (formType == SELECCIONAR) {
                    this.jbSeleccionar.setEnabled(true);
                }
                if (e.getClickCount() == 2) {
                    if (formType == SELECCIONAR) {
                        invokeSelectRawMaterialForm();
                    } else if (formType == GESTIONAR) {
                        invokeUpdateRawMaterialForm();
                    }
                }
            } else {
                this.jbModificar.setEnabled(false);
                this.jbEliminar.setEnabled(false);
                if (formType == SELECCIONAR) {
                    this.jbSeleccionar.setEnabled(false);
                }
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
