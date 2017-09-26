package com.ferrus.apisac.ui.inicio;

import com.ferrus.apisac.ui.costoOperativo.GestionCostoOperativo;
import com.ferrus.apisac.ui.materiaPrima.GestionMateriaPrima;
import com.ferrus.apisac.ui.producto.CrearProducto;
import com.ferrus.apisac.util.AppUIConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class App extends JFrame implements ActionListener {

    private PanelPrincipal jpPrincipal;
    private JToolBar jtbBarraHerramientas = null;
    private ImageIcon icono;
    private BarraMenu barraMenu;
    private JLabel timeLabel;
    private Timer timer;

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
    }

    private void initializeVariables() {
        try {
            icono = new ImageIcon("Assets/Icono.png");
        } catch (Exception e) {
            icono = new ImageIcon();
        }
        this.jpPrincipal = new PanelPrincipal();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src.equals(this.jpPrincipal.jbCrear)) {
            CrearProducto cp = new CrearProducto(this, CrearProducto.CREATE_PRODUCT);
        } else if (src.equals(this.jpPrincipal.jbParametros)) {
            Parametros param = new Parametros(this);
            param.setVisible(true);
        } else if (src.equals(this.jpPrincipal.jbMateriaPrima)) {
            GestionMateriaPrima gmp = new GestionMateriaPrima(this);
        } else if (src.equals(this.jpPrincipal.jbCostoOperativo)) {
            GestionCostoOperativo gco = new GestionCostoOperativo(this);
        }
    }
}
