/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.inicio;

import com.ferrus.apisac.model.Impuesto;
import com.ferrus.apisac.model.Marca;
import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.ProductoCategoria;
import com.ferrus.apisac.model.service.ProductoParametrosService;
import com.ferrus.apisac.model.serviceImp.ProductoParametrosServImpl;
import com.ferrus.apisac.tablemodel.ImpuestoTableModel;
import com.ferrus.apisac.tablemodel.MarcaTableModel;
import com.ferrus.apisac.tablemodel.ProductoCategoriaTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Ramiro Ferreira
 */
public class Parametros extends javax.swing.JDialog implements ActionListener, MouseListener, KeyListener {

    private javax.swing.JButton jbCrear, jbModificar, jbEliminar;
    private JTabbedPane jtpCenter;
    private JPanel jpSouth;
    private JScrollPane jspMarcas, jspCategorias, jspImpuestos;
    private JTable jtMarcas, jtCategorias, jtImpuestos;
    private ProductoParametrosService servicio;
    private MarcaTableModel marcaTableModel;
    private ProductoCategoriaTableModel productoCategoriaTableModel;
    private ImpuestoTableModel impuestoTableModel;

    public Parametros(App app) {
        super(app, true);
        setTitle("Parametros");
        setSize(new java.awt.Dimension(400, 300));
        setLocationRelativeTo(app);
        initComponents();
        inicializarVista();
        loadData();
        agregarListener();
    }

    private void loadData() {
        loadDataMarca();
        loadDataCategoria();
        loadDataImpuesto();
    }

    private void loadDataMarca() {
        marcaTableModel.setMarcaList(servicio.obtenerMarcas("", true));
        jtMarcas.setModel(marcaTableModel);
        marcaTableModel.updateTable();
    }

    private void loadDataCategoria() {
        productoCategoriaTableModel.setProductoCategoriaList(servicio.obtenerProductosCategorias("", true));
        jtCategorias.setModel(productoCategoriaTableModel);
        productoCategoriaTableModel.updateTable();
    }

    private void loadDataImpuesto() {
        impuestoTableModel.setImpuestoList(servicio.obtenerImpuestos());
        jtImpuestos.setModel(impuestoTableModel);
        impuestoTableModel.updateTable();
    }

    private void inicializarVista() {
        jbEliminar.setEnabled(false);
        jbModificar.setEnabled(false);
    }

    private void initComponents() {
        servicio = new ProductoParametrosServImpl();
        marcaTableModel = new MarcaTableModel();
        productoCategoriaTableModel = new ProductoCategoriaTableModel();
        impuestoTableModel = new ImpuestoTableModel();
        jtMarcas = new JTable();
        jtMarcas.getTableHeader().setReorderingAllowed(false);
        jspMarcas = new JScrollPane(jtMarcas);
        jtCategorias = new JTable();
        jtCategorias.getTableHeader().setReorderingAllowed(false);
        jspCategorias = new JScrollPane(jtCategorias);
        jtImpuestos = new JTable();
        jtImpuestos.getTableHeader().setReorderingAllowed(false);
        jspImpuestos = new JScrollPane(jtImpuestos);

        jtpCenter = new JTabbedPane();
        jtpCenter.add("Marcas", jspMarcas);
        jtpCenter.add("Categorias", jspCategorias);
        jtpCenter.add("Impuestos", jspImpuestos);
        jpSouth = new JPanel();
        jbCrear = new javax.swing.JButton("Agregar");
        jbModificar = new javax.swing.JButton("Modificar");
        jbEliminar = new javax.swing.JButton("Eliminar");
        jpSouth.add(jbCrear);
        jpSouth.add(jbModificar);
        jpSouth.add(jbEliminar);
        getContentPane().setLayout(new MigLayout());
        getContentPane().add(jtpCenter, "dock center");
        getContentPane().add(jpSouth, "dock south");
    }

    private void agregarListener() {
        jtpCenter.addMouseListener(this);
        jtCategorias.addMouseListener(this);
        jtMarcas.addMouseListener(this);
        jtImpuestos.addMouseListener(this);
        jbCrear.addActionListener(this);
        jbModificar.addActionListener(this);
        jbEliminar.addActionListener(this);
        /*
        KEYLISTENERS
         */
        jtpCenter.addKeyListener(this);
        jtCategorias.addKeyListener(this);
        jtMarcas.addKeyListener(this);
        jtImpuestos.addKeyListener(this);
        jbCrear.addKeyListener(this);
        jbModificar.addKeyListener(this);
        jbEliminar.addKeyListener(this);
    }

    private void agregarMarca(String marca) {
        String m = marca.trim();
        if (m.length() < 1 || m.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserte 1 caracter por lo menos.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (m.length() > 30) {
            JOptionPane.showMessageDialog(this, "Máximo permitido 30 caracteres.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Marca> list = servicio.obtenerMarcas(m, false);
        if (list.isEmpty()) {
            Marca unaMarca = new Marca();
            unaMarca.setDescripcion(m);
            servicio.insertarMarca(unaMarca);
            this.jbModificar.setEnabled(false);
            this.jbEliminar.setEnabled(false);
            loadDataMarca();
        } else {
            JOptionPane.showMessageDialog(this, "Marca existente.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarMarca(String marca) {
        String m = marca.trim();
        if (m.length() < 1 || m.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserte 1 caracter por lo menos.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (m.length() > 30) {
            JOptionPane.showMessageDialog(this, "Máximo permitido 30 caracteres.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Marca> list = servicio.obtenerMarcas(m, false);
        if (list.size() == 1) {
            Long idMarca = Long.valueOf(String.valueOf(this.jtMarcas.getValueAt(jtMarcas.getSelectedRow(), 0)));
            Long idMarcaTemp = list.get(0).getId();
            if (Objects.equals(idMarca, idMarcaTemp)) {
                Marca unaMarca = new Marca();
                unaMarca.setDescripcion(m);
                unaMarca.setId(idMarca);
                servicio.modificarMarca(unaMarca);
                this.jbModificar.setEnabled(false);
                this.jbEliminar.setEnabled(false);
                loadDataMarca();
            } else {
                JOptionPane.showMessageDialog(this, "Marca existente.", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        } else if (list.isEmpty()) {
            Long idMarca = Long.valueOf(String.valueOf(this.jtMarcas.getValueAt(jtMarcas.getSelectedRow(), 0)));
            Marca unaMarca = new Marca();
            unaMarca.setDescripcion(m);
            unaMarca.setId(idMarca);
            servicio.modificarMarca(unaMarca);
            this.jbModificar.setEnabled(false);
            this.jbEliminar.setEnabled(false);
            loadDataMarca();
        } else {
            JOptionPane.showMessageDialog(this, "Marca existente.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarMarca() {
        String nombreMarca = String.valueOf(this.jtMarcas.getValueAt(jtMarcas.getSelectedRow(), 1));
        List<Producto> listaProducto = servicio.obtenerProductosPorMarca(nombreMarca);
        if (listaProducto.isEmpty()) {
            int option = JOptionPane.showConfirmDialog(this, "¿Desea confirmar esta operación?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    Long idMarca = Long.valueOf(String.valueOf(this.jtMarcas.getValueAt(jtMarcas.getSelectedRow(), 0)));
                    servicio.eliminarMarca(idMarca);
                    this.jbModificar.setEnabled(false);
                    this.jbEliminar.setEnabled(false);
                    loadData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Existe productos que se encuentran utilizando la marca seleccionada.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarCategoria(String categoria) {
        String c = categoria.trim();
        if (c.length() < 1 || c.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserte 1 caracter por lo menos.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (c.length() > 30) {
            JOptionPane.showMessageDialog(this, "Máximo permitido 30 caracteres.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<ProductoCategoria> listaCategoria = servicio.obtenerProductosCategorias(c, false);
        if (listaCategoria.isEmpty()) {
            ProductoCategoria productoCategoria = new ProductoCategoria();
            productoCategoria.setDescripcion(c);
            servicio.insertarProductoCategoria(productoCategoria);
            this.jbModificar.setEnabled(false);
            this.jbEliminar.setEnabled(false);
            loadDataCategoria();
        } else {
            JOptionPane.showMessageDialog(this, "Categoría existente.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarCategoria(String categoria) {
        String c = categoria.trim();
        if (c.length() < 1 || c.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserte 1 caracter por lo menos.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (c.length() > 30) {
            JOptionPane.showMessageDialog(this, "Máximo permitido 30 caracteres.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<ProductoCategoria> list = servicio.obtenerProductosCategorias(c, false);
        if (list.size() == 1) {
            Long idCate = Long.valueOf(String.valueOf(this.jtCategorias.getValueAt(jtCategorias.getSelectedRow(), 0)));
            Long idCateTemp = list.get(0).getId();
            if (Objects.equals(idCate, idCateTemp)) {
                ProductoCategoria productoCategoria = new ProductoCategoria();
                productoCategoria.setDescripcion(c);
                productoCategoria.setId(idCate);
                servicio.modificarProductoCategoria(productoCategoria);
                this.jbModificar.setEnabled(false);
                this.jbEliminar.setEnabled(false);
                loadDataMarca();
            } else {
                JOptionPane.showMessageDialog(this, "Categoría existente.", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        } else if (list.isEmpty()) {
            Long idCate = Long.valueOf(String.valueOf(this.jtCategorias.getValueAt(jtCategorias.getSelectedRow(), 0)));
            ProductoCategoria productoCategoria = new ProductoCategoria();
            productoCategoria.setDescripcion(c);
            productoCategoria.setId(idCate);
            servicio.modificarProductoCategoria(productoCategoria);
            this.jbModificar.setEnabled(false);
            this.jbEliminar.setEnabled(false);
            loadDataMarca();
        } else {
            JOptionPane.showMessageDialog(this, "Categoría existente.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCategoria() {
        String nombreCategoria = String.valueOf(this.jtCategorias.getValueAt(jtCategorias.getSelectedRow(), 1));
        List<Producto> listaProducto = servicio.obtenerProductosPorCategoria(nombreCategoria);
        if (listaProducto.isEmpty()) {
            int option = JOptionPane.showConfirmDialog(this, "¿Desea confirmar esta operación?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    Long idCate = Long.valueOf(String.valueOf(this.jtCategorias.getValueAt(jtCategorias.getSelectedRow(), 0)));
                    servicio.eliminarProductoCategoria(idCate);
                    this.jbModificar.setEnabled(false);
                    this.jbEliminar.setEnabled(false);
                    loadData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Existe productos que se encuentran utilizando la categoría seleccionada.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarImpuesto(String impuesto) {
        Double impuestoValor = -1.0;
        try {
            impuestoValor = Double.valueOf(impuesto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El impuesto debe ser un número válido", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (impuestoValor < 0) {
            JOptionPane.showMessageDialog(this, "El impuesto debe ser un número positivo", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Impuesto imp = servicio.obtenerImpuesto(impuestoValor);
        if (imp == null) {
            Impuesto unImpuesto = new Impuesto(impuestoValor);
            servicio.insertarImpuesto(unImpuesto);
            this.jbModificar.setEnabled(false);
            this.jbEliminar.setEnabled(false);
            loadDataImpuesto();
        } else {
            JOptionPane.showMessageDialog(this, "El impuesto ingresa ya existe.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarImpuesto(String impuesto) {
        Double impuestoValor = -1.0;
        try {
            impuestoValor = Double.valueOf(impuesto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El impuesto debe ser un número válido", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (impuestoValor < 0) {
            JOptionPane.showMessageDialog(this, "El impuesto debe ser un número positivo", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Impuesto imp = servicio.obtenerImpuesto(impuestoValor);
        if (imp == null) {
            Long idImpuesto = Long.valueOf(String.valueOf(this.jtImpuestos.getValueAt(jtImpuestos.getSelectedRow(), 0)));
            Impuesto unImpuesto = new Impuesto(impuestoValor);
            unImpuesto.setId(idImpuesto);
            servicio.modificarImpuesto(unImpuesto);
            this.jbModificar.setEnabled(false);
            this.jbEliminar.setEnabled(false);
            loadDataImpuesto();
        } else {
            JOptionPane.showMessageDialog(this, "El impuesto ingresa ya existe.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarImpuesto() {
        int option = JOptionPane.showConfirmDialog(this, "¿Desea confirmar esta operación?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            try {
                Double valor = Double.valueOf(String.valueOf(this.jtImpuestos.getValueAt(jtImpuestos.getSelectedRow(), 1)));
                List<Producto> listaProducto = servicio.obtenerProductosPorImpuesto(valor);
                if (listaProducto.isEmpty()) {
                    Long idImpuesto = Long.valueOf(String.valueOf(this.jtImpuestos.getValueAt(jtImpuestos.getSelectedRow(), 0)));
                    servicio.eliminarImpuesto(idImpuesto);
                    this.jbModificar.setEnabled(false);
                    this.jbEliminar.setEnabled(false);
                    loadDataImpuesto();
                } else {
                    JOptionPane.showMessageDialog(this, "Existe productos que se encuentran utilizando el impuesto.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createButtonHandler() {
        if (this.jtpCenter.getSelectedComponent().equals(this.jspMarcas)) {
            String marca = JOptionPane.showInputDialog(this, "Inserte el nombre de la marca", "Insertar marca", JOptionPane.PLAIN_MESSAGE);
            if (marca != null) {
                if (!marca.isEmpty()) {
                    agregarMarca(marca);
                }
            }
        } else if (this.jtpCenter.getSelectedComponent().equals(this.jspCategorias)) {
            String rubro = JOptionPane.showInputDialog(this, "Inserte el nombre de la Categoría", "Insertar categoria", JOptionPane.PLAIN_MESSAGE);
            if (rubro != null) {
                if (!rubro.isEmpty()) {
                    agregarCategoria(rubro);
                }
            }
        } else if (this.jtpCenter.getSelectedComponent().equals(this.jspImpuestos)) {
            String impuesto = JOptionPane.showInputDialog(this, "Inserte el valor del Impuesto", "Insertar impuesto", JOptionPane.PLAIN_MESSAGE);
            if (impuesto != null) {
                if (!impuesto.isEmpty()) {
                    agregarImpuesto(impuesto);
                }
            }
        }
    }

    private void updateButtonHandler() {
        if (this.jtpCenter.getSelectedComponent().equals(this.jspMarcas)) {
            String marca = JOptionPane.showInputDialog(this, "Inserte el nombre de la marca", "Insertar marca", JOptionPane.PLAIN_MESSAGE);
            if (marca != null) {
                if (!marca.isEmpty()) {
                    modificarMarca(marca);
                }
            }
        } else if (this.jtpCenter.getSelectedComponent().equals(this.jspCategorias)) {
            String categoria = JOptionPane.showInputDialog(this, "Inserte el nombre de la Categoría", "Insertar categoria", JOptionPane.PLAIN_MESSAGE);
            if (categoria != null) {
                if (!categoria.isEmpty()) {
                    modificarCategoria(categoria);
                }
            }
        } else if (this.jtpCenter.getSelectedComponent().equals(this.jspImpuestos)) {
            String impuesto = JOptionPane.showInputDialog(this, "Inserte el valor del Impuesto", "Insertar categoria", JOptionPane.PLAIN_MESSAGE);
            if (impuesto != null) {
                if (!impuesto.isEmpty()) {
                    modificarImpuesto(impuesto);
                }
            }
        }
    }

    private void deleteButtonHandler() {
        if (this.jtpCenter.getSelectedComponent().equals(this.jspMarcas)) {
            eliminarMarca();
        } else if (this.jtpCenter.getSelectedComponent().equals(this.jspCategorias)) {
            eliminarCategoria();
        } else if (this.jtpCenter.getSelectedComponent().equals(this.jspImpuestos)) {
            eliminarImpuesto();
        }
    }

    private void cerrar() {
        System.runFinalization();
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(jbCrear)) {
            createButtonHandler();
        }
        if (e.getSource().equals(jbModificar)) {
            updateButtonHandler();
        }
        if (e.getSource().equals(jbEliminar)) {
            deleteButtonHandler();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(this.jtpCenter)) {
            this.jbModificar.setEnabled(false);
            this.jbEliminar.setEnabled(false);
        }
        if (e.getSource().equals(this.jtMarcas)) {
            int fila = this.jtMarcas.rowAtPoint(e.getPoint());
            int columna = this.jtMarcas.columnAtPoint(e.getPoint());
            if ((fila > -1) && (columna > -1)) {
                this.jbModificar.setEnabled(true);
                this.jbEliminar.setEnabled(true);
            } else {
                this.jbModificar.setEnabled(false);
                this.jbEliminar.setEnabled(false);
            }
        }
        if (e.getSource().equals(this.jtCategorias)) {
            int fila = this.jtCategorias.rowAtPoint(e.getPoint());
            int columna = this.jtCategorias.columnAtPoint(e.getPoint());
            if ((fila > -1) && (columna > -1)) {
                this.jbModificar.setEnabled(true);
                this.jbEliminar.setEnabled(true);
            } else {
                this.jbModificar.setEnabled(false);
                this.jbEliminar.setEnabled(false);
            }
        }
        if (e.getSource().equals(this.jtImpuestos)) {
            int fila = this.jtImpuestos.rowAtPoint(e.getPoint());
            int columna = this.jtImpuestos.columnAtPoint(e.getPoint());
            if ((fila > -1) && (columna > -1)) {
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
