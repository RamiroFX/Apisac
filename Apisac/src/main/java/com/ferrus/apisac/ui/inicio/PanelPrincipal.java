/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.inicio;

import static com.ferrus.apisac.util.AppUIConstants.*;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Ramiro Ferreira
 */
public class PanelPrincipal extends JPanel {

    private JPanel jpProductos, jpBotones, jpPrecio;
    public JButton jbCrear, jbModificar, jbBorrar, jbExportar, jbParametros, 
            jbBuscar, jbMateriaPrima;
    public JTextField jtfBuscar;

    public PanelPrincipal() {
        setLayout(new BorderLayout());
        initializeVariables();
        constructLayout();
    }

    private void initializeVariables() {
        this.jpProductos = new JPanel(new BorderLayout());
        this.jpProductos.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        this.jpPrecio = new JPanel();
        this.jpBotones = new JPanel();
        this.jpBotones.setBorder(new EtchedBorder());
        this.jbCrear = new JButton(CREATE_BUTTON_NAME);
        this.jbModificar = new JButton(MODIFY_BUTTON_NAME);
        this.jbBorrar = new JButton(DELETE_BUTTON_NAME);
        this.jbExportar = new JButton(EXPORT_BUTTON_NAME);
        this.jbParametros = new JButton(PARAM_BUTTON_NAME);
        this.jbMateriaPrima = new JButton(RAW_MATERIAL_BUTTON_NAME);
        this.jbBuscar = new JButton(SEARCH_BUTTON_NAME);
        this.jtfBuscar = new JTextField(15);
    }

    private void constructLayout() {
        this.jpBotones.add(jbCrear);
        this.jpBotones.add(jbModificar);
        this.jpBotones.add(jbBorrar);
        this.jpBotones.add(jbExportar);
        this.jpBotones.add(jbParametros);
        this.jpBotones.add(jbMateriaPrima);
        JPanel jpBuscar = new JPanel();
        jpBuscar.setBorder(new BevelBorder(BevelBorder.RAISED));
        jpBuscar.add(this.jtfBuscar);
        jpBuscar.add(this.jbBuscar);
        this.jpProductos.add(jpBuscar, BorderLayout.NORTH);

        add(this.jpProductos, BorderLayout.WEST);
        add(this.jpPrecio, BorderLayout.CENTER);
        add(this.jpBotones, BorderLayout.SOUTH);
    }

}
