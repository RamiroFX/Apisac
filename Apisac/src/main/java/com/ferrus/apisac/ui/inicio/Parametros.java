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
import com.ferrus.apisac.model.ProductoSubCategoria;
import com.ferrus.apisac.model.service.ProductoParametrosService;
import com.ferrus.apisac.model.serviceImp.ProductoParametrosServImpl;
import com.ferrus.apisac.tablemodel.ImpuestoTableModel;
import com.ferrus.apisac.tablemodel.MarcaTableModel;
import com.ferrus.apisac.tablemodel.ProductoCategoriaTableModel;
import com.ferrus.apisac.tablemodel.ProductoSubCategoriaTableModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Ramiro Ferreira
 */
public class Parametros extends javax.swing.JDialog implements ActionListener, MouseListener, KeyListener {

    private javax.swing.JButton jbCrear, jbModificar, jbEliminar;
    private JTabbedPane jtpCenter;
    private JPanel jpSouth;
    private JScrollPane jspCategorias, jspSubCategorias;
    //private JScrollPane jspMarcas, jspImpuestos;
    private JTable jtCategorias, jtSubCategorias;
    //private JTable jtMarcas, jtImpuestos;
    private ProductoParametrosService servicio;
    private ProductoCategoriaTableModel productoCategoriaTableModel;
    private ProductoSubCategoriaTableModel productoSubCategoriaTableModel;
    //private MarcaTableModel marcaTableModel;
    //private ImpuestoTableModel impuestoTableModel;

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
        loadDataCategoria();
        loadDataSubCategoria();
        //loadDataMarca();
        //loadDataImpuesto();
    }

    /*
    private void loadDataMarca() {
        marcaTableModel.setMarcaList(servicio.obtenerMarcas("", true));
        jtMarcas.setModel(marcaTableModel);
        marcaTableModel.updateTable();
    }*/
    private void loadDataCategoria() {
        productoCategoriaTableModel.setProductoCategoriaList(servicio.obtenerProductosCategorias("", true));
        jtCategorias.setModel(productoCategoriaTableModel);
        productoCategoriaTableModel.updateTable();
    }

    private void loadDataSubCategoria() {
        productoSubCategoriaTableModel.setProductoCategoriaList(servicio.obtenerProductosSubCategorias("", true));
        jtSubCategorias.setModel(productoSubCategoriaTableModel);
        productoSubCategoriaTableModel.updateTable();
    }

    /*
    private void loadDataImpuesto() {
        impuestoTableModel.setImpuestoList(servicio.obtenerImpuestos());
        jtImpuestos.setModel(impuestoTableModel);
        impuestoTableModel.updateTable();
    }*/
    private void inicializarVista() {
        jbEliminar.setEnabled(false);
        jbModificar.setEnabled(false);
    }

    private void initComponents() {
        servicio = new ProductoParametrosServImpl();
        productoCategoriaTableModel = new ProductoCategoriaTableModel();
        productoSubCategoriaTableModel = new ProductoSubCategoriaTableModel();
        jtCategorias = new JTable();
        jtCategorias.getTableHeader().setReorderingAllowed(false);
        jspCategorias = new JScrollPane(jtCategorias);
        jtSubCategorias = new JTable();
        jtSubCategorias.getTableHeader().setReorderingAllowed(false);
        jspSubCategorias = new JScrollPane(jtSubCategorias);
        /*impuestoTableModel = new ImpuestoTableModel();
        marcaTableModel = new MarcaTableModel();
        jtMarcas = new JTable();
        jtMarcas.getTableHeader().setReorderingAllowed(false);
        jspMarcas = new JScrollPane(jtMarcas);
        jtImpuestos = new JTable();
        jtImpuestos.getTableHeader().setReorderingAllowed(false);
        jspImpuestos = new JScrollPane(jtImpuestos);*/

        jtpCenter = new JTabbedPane();
        jtpCenter.add("Categorías", jspCategorias);
        jtpCenter.add("Sub-Categorías", jspSubCategorias);
        //jtpCenter.add("Impuestos", jspImpuestos);
        //jtpCenter.add("Marcas", jspMarcas);
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
        jtSubCategorias.addMouseListener(this);
        //jtMarcas.addMouseListener(this);
        //jtImpuestos.addMouseListener(this);
        jbCrear.addActionListener(this);
        jbModificar.addActionListener(this);
        jbEliminar.addActionListener(this);
        /*
        KEYLISTENERS
         */
        jtpCenter.addKeyListener(this);
        jtCategorias.addKeyListener(this);
        jtSubCategorias.addKeyListener(this);
        //jtMarcas.addKeyListener(this);
        //jtImpuestos.addKeyListener(this);
        jbCrear.addKeyListener(this);
        jbModificar.addKeyListener(this);
        jbEliminar.addKeyListener(this);
    }

    /*
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
    }*/
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
                loadDataCategoria();
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
            loadDataCategoria();
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

    private void agregarSubCategoria(String subCategoria, ProductoCategoria categoria) {
        String c = subCategoria.trim();
        if (c.length() < 1 || c.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserte 1 caracter por lo menos.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (c.length() > 30) {
            JOptionPane.showMessageDialog(this, "Máximo permitido 30 caracteres.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<ProductoSubCategoria> listaSubCategoria = servicio.obtenerProductosSubCategorias(c, false);
        if (listaSubCategoria.isEmpty()) {
            ProductoSubCategoria psc = new ProductoSubCategoria();
            psc.setProductoCategoria(categoria);
            psc.setDescripcion(c);
            servicio.insertarProductoSubCategoria(psc);
            this.jbModificar.setEnabled(false);
            this.jbEliminar.setEnabled(false);
            loadDataSubCategoria();
        } else {
            JOptionPane.showMessageDialog(this, "Sub-Categoría existente.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarSubCategoria(String subCategoria, ProductoCategoria categoria) {
        String c = subCategoria.trim();
        if (c.length() < 1 || c.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserte 1 caracter por lo menos.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (c.length() > 30) {
            JOptionPane.showMessageDialog(this, "Máximo permitido 30 caracteres.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<ProductoSubCategoria> list = servicio.obtenerProductosSubCategorias(c, false);
        if (list.size() == 1) {
            Long idCate = Long.valueOf(String.valueOf(this.jtSubCategorias.getValueAt(jtSubCategorias.getSelectedRow(), 0)));
            Long idCateTemp = list.get(0).getId();
            if (Objects.equals(idCate, idCateTemp)) {
                ProductoSubCategoria psc = new ProductoSubCategoria();
                psc.setProductoCategoria(categoria);
                psc.setDescripcion(c);
                psc.setId(idCate);
                servicio.modificarProductoSubCategoria(psc, categoria);
                this.jbModificar.setEnabled(false);
                this.jbEliminar.setEnabled(false);
                loadDataSubCategoria();
            } else {
                JOptionPane.showMessageDialog(this, "Sub-Categoría existente.", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        } else if (list.isEmpty()) {
            Long idCate = Long.valueOf(String.valueOf(this.jtSubCategorias.getValueAt(jtSubCategorias.getSelectedRow(), 0)));
            ProductoSubCategoria psc = new ProductoSubCategoria();
            psc.setProductoCategoria(categoria);
            psc.setDescripcion(c);
            psc.setId(idCate);
            servicio.modificarProductoSubCategoria(psc, categoria);
            this.jbModificar.setEnabled(false);
            this.jbEliminar.setEnabled(false);
            loadDataSubCategoria();
        } else {
            JOptionPane.showMessageDialog(this, "Sub-Categoría existente.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarSubCategoria() {
        String nombreSubCategoria = String.valueOf(this.jtSubCategorias.getValueAt(jtSubCategorias.getSelectedRow(), 2));
        List<Producto> listaProducto = servicio.obtenerProductosPorSubCategoria(nombreSubCategoria);
        if (listaProducto.isEmpty()) {
            int option = JOptionPane.showConfirmDialog(this, "¿Desea confirmar esta operación?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    Long idSubCate = Long.valueOf(String.valueOf(this.jtSubCategorias.getValueAt(jtSubCategorias.getSelectedRow(), 0)));
                    servicio.eliminarProductoSubCategoria(idSubCate);
                    this.jbModificar.setEnabled(false);
                    this.jbEliminar.setEnabled(false);
                    loadDataSubCategoria();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Existe productos que se encuentran utilizando la sub-categoría seleccionada.", "Alerta", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
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
    }*/
    private void createButtonHandler() {
        if (this.jtpCenter.getSelectedComponent().equals(this.jspCategorias)) {
            String rubro = JOptionPane.showInputDialog(this, "Inserte el nombre de la Categoría", "Insertar categoria", JOptionPane.PLAIN_MESSAGE);
            if (rubro != null) {
                if (!rubro.isEmpty()) {
                    agregarCategoria(rubro);
                }
            }
        } else if (this.jtpCenter.getSelectedComponent().equals(this.jspSubCategorias)) {
            JComboBox<ProductoCategoria> jcb = new JComboBox();
            List<ProductoCategoria> listaCategorias = servicio.obtenerProductosCategorias("", true);
            if (listaCategorias.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No exiten categorías. Ingrese una categoría primero", "Alerta", JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (ProductoCategoria listaCategoria : listaCategorias) {
                jcb.addItem(listaCategoria);
            }
            JTextField jtfSubCategoria = new JTextField();
            JPanel jp = new JPanel(new GridLayout(2, 1));
            jp.add(jcb);
            jp.add(jtfSubCategoria);
            JOptionPane.showMessageDialog(null, jp, "Ingresar sub-categoría", JOptionPane.QUESTION_MESSAGE);
            String subCategoria = jtfSubCategoria.getText();
            ProductoCategoria pc = (ProductoCategoria) jcb.getSelectedItem();
            agregarSubCategoria(subCategoria, pc);
        }
        /*else if (this.jtpCenter.getSelectedComponent().equals(this.jspImpuestos)) {
            String impuesto = JOptionPane.showInputDialog(this, "Inserte el valor del Impuesto", "Insertar impuesto", JOptionPane.PLAIN_MESSAGE);
            if (impuesto != null) {
                if (!impuesto.isEmpty()) {
                    agregarImpuesto(impuesto);
                }
            }
        } else 
        if (this.jtpCenter.getSelectedComponent().equals(this.jspMarcas)) {
            String marca = JOptionPane.showInputDialog(this, "Inserte el nombre de la marca", "Insertar marca", JOptionPane.PLAIN_MESSAGE);
            if (marca != null) {
                if (!marca.isEmpty()) {
                    agregarMarca(marca);
                }
            }
        }*/
    }

    private void updateButtonHandler() {
        if (this.jtpCenter.getSelectedComponent().equals(this.jspCategorias)) {
            String categoria = JOptionPane.showInputDialog(this, "Inserte el nombre de la Categoría", "Insertar categoria", JOptionPane.PLAIN_MESSAGE);
            if (categoria != null) {
                if (!categoria.isEmpty()) {
                    modificarCategoria(categoria);
                }
            }
        } else if (this.jtpCenter.getSelectedComponent().equals(this.jspSubCategorias)) {
            JComboBox<ProductoCategoria> jcb = new JComboBox();
            List<ProductoCategoria> listaCategorias = servicio.obtenerProductosCategorias("", true);
            if (listaCategorias.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No exiten categorías. Ingrese una categoría primero", "Alerta", JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (ProductoCategoria listaCategoria : listaCategorias) {
                jcb.addItem(listaCategoria);
            }
            JTextField jtfSubCategoria = new JTextField();
            JPanel jp = new JPanel(new GridLayout(2, 1));
            jp.add(jcb);
            jp.add(jtfSubCategoria);
            JOptionPane.showMessageDialog(null, jp, "Modificar sub-categoría", JOptionPane.QUESTION_MESSAGE);
            String subCategoria = jtfSubCategoria.getText();
            ProductoCategoria pc = (ProductoCategoria) jcb.getSelectedItem();
            modificarSubCategoria(subCategoria, pc);
        }
        /*else if (this.jtpCenter.getSelectedComponent().equals(this.jspImpuestos)) {
            String impuesto = JOptionPane.showInputDialog(this, "Inserte el valor del Impuesto", "Insertar categoria", JOptionPane.PLAIN_MESSAGE);
            if (impuesto != null) {
                if (!impuesto.isEmpty()) {
                    modificarImpuesto(impuesto);
                }
            }
        }else 
        if (this.jtpCenter.getSelectedComponent().equals(this.jspMarcas)) {
            String marca = JOptionPane.showInputDialog(this, "Inserte el nombre de la marca", "Insertar marca", JOptionPane.PLAIN_MESSAGE);
            if (marca != null) {
                if (!marca.isEmpty()) {
                    modificarMarca(marca);
                }
            }
        } */
    }

    private void deleteButtonHandler() {
        if (this.jtpCenter.getSelectedComponent().equals(this.jspCategorias)) {
            eliminarCategoria();
        } else if (this.jtpCenter.getSelectedComponent().equals(this.jspSubCategorias)) {
            eliminarSubCategoria();
        }
        /*else if (this.jtpCenter.getSelectedComponent().equals(this.jspImpuestos)) {
            eliminarImpuesto();
        } else if (this.jtpCenter.getSelectedComponent().equals(this.jspMarcas)) {
            eliminarMarca();
        } */
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
        if (e.getSource().equals(this.jtSubCategorias)) {
            int fila = this.jtSubCategorias.rowAtPoint(e.getPoint());
            int columna = this.jtSubCategorias.columnAtPoint(e.getPoint());
            if ((fila > -1) && (columna > -1)) {
                this.jbModificar.setEnabled(true);
                this.jbEliminar.setEnabled(true);
            } else {
                this.jbModificar.setEnabled(false);
                this.jbEliminar.setEnabled(false);
            }
        }
        /*
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
        }*/
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
