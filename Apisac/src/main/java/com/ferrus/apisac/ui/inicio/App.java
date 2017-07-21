package com.ferrus.apisac.ui.inicio;

import com.ferrus.apisac.util.AppUIConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class App extends JFrame implements ActionListener {

    private JDesktopPane desktop = null;
    private JInternalFrame currentJIF = null;
    private JToolBar jtbBarraHerramientas = null;
    private ImageIcon icono;
    private BarraMenu barraMenu;
    private JLabel timeLabel;
    private Timer timer;

    public App() {
        super(AppUIConstants.APP_TITLE);
        setName(AppUIConstants.APP_NAME);
        initializeVariables();
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
        this.barraMenu = new BarraMenu(this);
        this.desktop = new JDesktopPane();

        this.timeLabel = new JLabel();
        this.timer = new Timer(timeLabel);
        jtbBarraHerramientas = new JToolBar(AppUIConstants.TOOL_BAR_NAME, JToolBar.HORIZONTAL);
        jtbBarraHerramientas.setPreferredSize(new Dimension(this.getWidth(), 30));
        jtbBarraHerramientas.setFloatable(false);
        jtbBarraHerramientas.add(new JLabel(AppUIConstants.TOOL_BAR_TIME_NAME));
        jtbBarraHerramientas.add(timeLabel);
    }

    private void constructAppWindow() {
        setLocation(0, 0);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void constructLayout() {
        this.desktop.setLayout(null);
        this.desktop.setBackground(Color.WHITE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(this.desktop, BorderLayout.CENTER);
        getContentPane().add(this.jtbBarraHerramientas, BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        add(this.desktop, BorderLayout.CENTER);
        add(this.jtbBarraHerramientas, BorderLayout.SOUTH);
    }

    public JInternalFrame getCurrentJIF() {
        return currentJIF;
    }

    public void setCurrentJIF(JInternalFrame currentJIF) {
        this.currentJIF = currentJIF;
    }

    public void agregarVentana(JInternalFrame mdi) {
        desktop.add(mdi);
        setCurrentJIF(mdi);
        mdi.setVisible(true);
        mdi.moveToFront();
        mdi.setLocation(centrarPantalla(mdi));
        try {
            mdi.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
