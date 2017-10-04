package com.ferrus.apisac.ui.inicio;

import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.service.ProductoParametrosService;
import com.ferrus.apisac.model.serviceImp.ProductoParametrosServImpl;
import com.ferrus.apisac.tablemodel.ProductoTableModel;
import com.ferrus.apisac.ui.costoOperativo.GestionCostoOperativo;
import com.ferrus.apisac.ui.materiaPrima.GestionMateriaPrima;
import com.ferrus.apisac.ui.producto.CrearProducto;
import com.ferrus.apisac.util.AppUIConstants;
import static com.ferrus.apisac.util.AppUIConstants.ALERT_MESSAGE;
import static com.ferrus.apisac.util.AppUIConstants.CONFIRM_MESSAGE;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

public class App extends JFrame implements ActionListener, MouseListener {

    private PanelPrincipal jpPrincipal;
    private MostrarInfoProducto jpMostrarInfoProd;
    private JToolBar jtbBarraHerramientas = null;
    private ImageIcon icono;
    private BarraMenu barraMenu;
    private JLabel timeLabel;
    private Timer timer;
    private ProductoParametrosService productoServicio;
    private ProductoTableModel productoTableModel;

    public App() {
        super(AppUIConstants.APP_TITLE);
        setName(AppUIConstants.APP_NAME);
        initializeVariables();
        addListeners();
        setJMenuBar(barraMenu);
        constructLayout();
        setIconImage(icono.getImage());
        constructAppWindow();
        startTimer();
        loadData();
    }

    private void initializeVariables() {
        try {
            icono = new ImageIcon("Assets/Icono.png");
        } catch (Exception e) {
            icono = new ImageIcon();
        }
        this.productoServicio = new ProductoParametrosServImpl();
        this.productoTableModel = new ProductoTableModel();
        this.jpMostrarInfoProd = new MostrarInfoProducto();
        this.jpPrincipal = new PanelPrincipal(jpMostrarInfoProd);
        this.barraMenu = new BarraMenu(this);

        this.timeLabel = new JLabel();
        this.timer = new Timer(timeLabel);
        jtbBarraHerramientas = new JToolBar(AppUIConstants.TOOL_BAR_NAME, JToolBar.HORIZONTAL);
        jtbBarraHerramientas.setPreferredSize(new Dimension(this.getWidth(), 30));
        jtbBarraHerramientas.setFloatable(false);
        jtbBarraHerramientas.add(new JLabel(AppUIConstants.TOOL_BAR_TIME_NAME));
        jtbBarraHerramientas.add(timeLabel);
    }

    private void addListeners() {
        this.jpPrincipal.jbCrear.addActionListener(this);
        this.jpPrincipal.jbModificar.addActionListener(this);
        this.jpPrincipal.jbBorrar.addActionListener(this);
        this.jpPrincipal.jbExportar.addActionListener(this);
        this.jpPrincipal.jbParametros.addActionListener(this);
        this.jpPrincipal.jbMateriaPrima.addActionListener(this);
        this.jpPrincipal.jbCostoOperativo.addActionListener(this);
        this.jpPrincipal.jbBuscar.addActionListener(this);
        this.jpPrincipal.jbLimpiar.addActionListener(this);
        //MOUSE LISTENERS
        this.jpPrincipal.jtProductos.addMouseListener(this);
    }

    private void constructAppWindow() {
        setLocation(0, 0);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void constructLayout() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(this.jpPrincipal, BorderLayout.CENTER);
        getContentPane().add(this.jtbBarraHerramientas, BorderLayout.SOUTH);
    }

    public Point centrarPantalla(JInternalFrame i) {
        /*con este codigo centramos el panel en el centro del contenedor
         la anchura del contenedor menos la anchura de nuestro componente divido a 2
         lo mismo con la altura.*/
        return new Point((this.getWidth() - i.getWidth()) / 2, (this.getHeight() - i.getHeight() - 45) / 2);
    }

    public void startTimer() {
        this.timer.start();
    }

    public void stopTimer() {
        this.timer.setRunning(false);
    }

    private void loadData() {
        buscar();
    }

    private void buscar() {
        String nombreProducto = jpPrincipal.jtfBuscar.getText().trim();
        List<Producto> productos = productoServicio.obtenerProductos(nombreProducto, true);
        this.productoTableModel.setProductoList(productos);
        this.jpPrincipal.jtProductos.setModel(productoTableModel);
        this.productoTableModel.updateTable();
    }

    private void borrarCampo() {
        this.jpPrincipal.jtfBuscar.setText("");
    }

    private void modificarProducto() {
        int row = this.jpPrincipal.jtProductos.getSelectedRow();
        if (row > -1) {
            Long idProd = (Long) this.jpPrincipal.jtProductos.getValueAt(row, 0);
            Producto prod = productoServicio.obtenerProducto(idProd);
            CrearProducto modProd = new CrearProducto(this, CrearProducto.UPDATE_PRODUCT);
            modProd.setProducto(prod);
            modProd.loadData(prod);
            modProd.setVisible(true);
            this.jpPrincipal.jbModificar.setEnabled(false);
            this.jpPrincipal.jbBorrar.setEnabled(false);
            this.jpPrincipal.jbExportar.setEnabled(false);
            this.jpPrincipal.jpInfoProd.cleanFields();
        }
    }

    private void eliminarProducto() {
        int row = this.jpPrincipal.jtProductos.getSelectedRow();
        if (row > -1) {
            int opcion = JOptionPane.showConfirmDialog(this, CONFIRM_MESSAGE, ALERT_MESSAGE, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION) {
                Long idProd = (Long) this.jpPrincipal.jtProductos.getValueAt(row, 0);
                productoServicio.eliminarProducto(idProd);
                jpPrincipal.jpInfoProd.cleanFields();
                buscar();
                this.jpPrincipal.jbModificar.setEnabled(false);
                this.jpPrincipal.jbBorrar.setEnabled(false);
                this.jpPrincipal.jbExportar.setEnabled(false);
                this.jpPrincipal.jpInfoProd.cleanFields();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src.equals(this.jpPrincipal.jbCrear)) {
            CrearProducto cp = new CrearProducto(this, CrearProducto.CREATE_PRODUCT);
            cp.setVisible(true);
        } else if (src.equals(this.jpPrincipal.jbModificar)) {
            modificarProducto();
        } else if (src.equals(this.jpPrincipal.jbBorrar)) {
            eliminarProducto();
        } else if (src.equals(this.jpPrincipal.jbBuscar)) {
            buscar();
        } else if (src.equals(this.jpPrincipal.jbLimpiar)) {
            borrarCampo();
        } else if (src.equals(this.jpPrincipal.jbParametros)) {
            Parametros param = new Parametros(this);
            param.setVisible(true);
        } else if (src.equals(this.jpPrincipal.jbMateriaPrima)) {
            GestionMateriaPrima gmp = new GestionMateriaPrima(this, GestionMateriaPrima.GESTIONAR);
            gmp.setVisible(true);
        } else if (src.equals(this.jpPrincipal.jbCostoOperativo)) {
            GestionCostoOperativo gco = new GestionCostoOperativo(this, GestionCostoOperativo.GESTIONAR);
            gco.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(this.jpPrincipal.jtProductos)) {
            int fila = this.jpPrincipal.jtProductos.rowAtPoint(e.getPoint());
            int columna = this.jpPrincipal.jtProductos.columnAtPoint(e.getPoint());
            if ((fila > -1) && (columna > -1)) {
                Long idProd = (Long) this.jpPrincipal.jtProductos.getValueAt(fila, 0);
                Producto prod = productoServicio.obtenerProducto(idProd);
                this.jpPrincipal.jpInfoProd.loadData(prod);
                this.jpPrincipal.jbModificar.setEnabled(true);
                this.jpPrincipal.jbBorrar.setEnabled(true);
                this.jpPrincipal.jbExportar.setEnabled(true);
                if (e.getClickCount() == 2) {
                    modificarProducto();
                }
            } else {
                this.jpPrincipal.jbModificar.setEnabled(false);
                this.jpPrincipal.jbBorrar.setEnabled(false);
                this.jpPrincipal.jbExportar.setEnabled(false);
                this.jpPrincipal.jpInfoProd.cleanFields();
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
