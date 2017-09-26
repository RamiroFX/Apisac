/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.costoOperativo;

import com.ferrus.apisac.callback.CostoOperativoCallback;
import com.ferrus.apisac.callback.MateriaPrimaCallback;
import com.ferrus.apisac.model.CostoOperativo;
import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.service.CostoOperativoService;
import com.ferrus.apisac.model.service.ProductoParametrosService;
import com.ferrus.apisac.model.serviceImp.CostoOperativoServImpl;
import com.ferrus.apisac.model.serviceImp.ProductoParametrosServImpl;
import com.ferrus.apisac.tablemodel.CostoOperativoTableModel;
import com.ferrus.apisac.ui.materiaPrima.CrearMateriaPrima;
import static com.ferrus.apisac.util.AppUIConstants.*;
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
public class GestionCostoOperativo extends JDialog implements ActionListener, KeyListener,
        MouseListener, CostoOperativoCallback {

    JTextField jtfBuscar;
    JTextArea jtaDescripcion;
    JButton jbBuscar, jbCrear, jbModificar, jbEliminar;
    JTable jtCostoOperativo;
    JScrollPane jspCostoOperativo, jspDescripcion;
    private ProductoParametrosService productoParametrosService;
    private CostoOperativoService servicio;
    private CostoOperativoTableModel costoOperativoTableModel;

    public GestionCostoOperativo(JFrame jframe) {
        super(jframe, OPERATIVE_COST_TITLE);
        initializeVariables();
        addListeners();
        constructLayout();
        constructAppWindow(jframe);
        loadData();
    }

    private void initializeVariables() {
        costoOperativoTableModel = new CostoOperativoTableModel();
        servicio = new CostoOperativoServImpl();
        productoParametrosService = new ProductoParametrosServImpl();
        jtfBuscar = new JTextField();
        jtaDescripcion = new JTextArea();
        jtaDescripcion.setEditable(false);
        jbBuscar = new JButton(SEARCH_OPERATIVE_COST_BUTTON_NAME);
        jbCrear = new JButton(CREATE_OPERATIVE_COST_BUTTON_NAME);
        jbModificar = new JButton(UPDATE_OPERATIVE_COST_BUTTON_NAME);
        jbEliminar = new JButton(DELETE_OPERATIVE_COST_BUTTON_NAME);
        jtCostoOperativo = new JTable();
        jspCostoOperativo = new JScrollPane(jtCostoOperativo);
        jspDescripcion = new JScrollPane(jtaDescripcion);
        jspDescripcion.setBorder(new TitledBorder(DESCRIPTION_OPERATIVE_COST_PANEL_NAME));
        jbModificar.setEnabled(false);
        jbEliminar.setEnabled(false);
    }

    private void addListeners() {
        jtfBuscar.addActionListener(this);
        jbBuscar.addActionListener(this);
        jbCrear.addActionListener(this);
        jbModificar.addActionListener(this);
        jbEliminar.addActionListener(this);
        jtCostoOperativo.addKeyListener(this);
        jtfBuscar.addKeyListener(this);
        jtCostoOperativo.addMouseListener(this);
    }

    private void constructLayout() {
        setLayout(new BorderLayout());
        JPanel jpNorth = new JPanel(new BorderLayout());
        jpNorth.add(jtfBuscar, BorderLayout.CENTER);
        jpNorth.add(jbBuscar, BorderLayout.EAST);
        JPanel jpCenter = new JPanel(new BorderLayout());
        jpCenter.add(jspCostoOperativo, BorderLayout.CENTER);
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
        setSize(new Dimension(OPERATIVE_COST_WINDOWS_SIZE_WIDTH, OPERATIVE_COST_WINDOWS_SIZE_HEIGHT));
        setLocationRelativeTo(jframe);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void cerrar() {
        System.runFinalization();
        this.dispose();
    }

    private void loadData() {
        costoOperativoTableModel.setCostoOperativoList(servicio.obtenerCostosOperativos("", true));
        jtCostoOperativo.setModel(costoOperativoTableModel);
        costoOperativoTableModel.updateTable();
    }

    private void invokeCreateRawMaterialForm() {
        CrearCostoOperativo cmp = new CrearCostoOperativo(this, CrearCostoOperativo.CREATE_OPERATIVE_COST);
        cmp.setCostoOperativoCallback(this);
        this.jbModificar.setEnabled(false);
        this.jbEliminar.setEnabled(false);
    }

    private void invokeUpdateRawMaterialForm() {
        int row = this.jtCostoOperativo.getSelectedRow();
        Long idOperativeCost = null;
        if (row > -1) {
            idOperativeCost = (Long) jtCostoOperativo.getValueAt(row, 0);
        }
        if (idOperativeCost == null) {
            return;
        }
        CrearCostoOperativo cmp = new CrearCostoOperativo(this, CrearCostoOperativo.UPDATE_OPERATIVE_COST);
        cmp.setCostoOperativoCallback(this);
        cmp.setIdCostoOperativo(idOperativeCost);
        this.jbModificar.setEnabled(false);
        this.jbEliminar.setEnabled(false);
    }

    private void deleteRawMaterial() {
        int option = JOptionPane.showConfirmDialog(this, CONFIRM_MESSAGE, ALERT_MESSAGE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            try {
                Long idCostoOperativo = Long.valueOf(String.valueOf(this.jtCostoOperativo.getValueAt(jtCostoOperativo.getSelectedRow(), 0)));
                List<Producto> listaProducto = productoParametrosService.obtenerProductosPorMateriaPrimaID(idCostoOperativo);
                if (listaProducto.isEmpty()) {
                    servicio.eliminarCostoOperativo(idCostoOperativo);
                    this.jbModificar.setEnabled(false);
                    this.jbEliminar.setEnabled(false);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, OPERATIVE_COST_EXIST_MESSAGE, ALERT_MESSAGE, JOptionPane.ERROR_MESSAGE);
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
                costoOperativoTableModel.setCostoOperativoList(servicio.obtenerCostosOperativos(nombre, true));
                jtCostoOperativo.setModel(costoOperativoTableModel);
                costoOperativoTableModel.updateTable();
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
    public void crearCostoOperativo() {
        loadData();
    }

    @Override
    public void modificarCostoOperativo() {
        loadData();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(this.jtCostoOperativo)) {
            int fila = this.jtCostoOperativo.rowAtPoint(e.getPoint());
            int columna = this.jtCostoOperativo.columnAtPoint(e.getPoint());
            if ((fila > -1) && (columna > -1)) {
                Long idMp = (Long) this.jtCostoOperativo.getValueAt(fila, 0);
                CostoOperativo co = servicio.obtenerCostoOperativo(idMp);
                this.jtaDescripcion.setText(co.getDescripcion());
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
