/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac;

import com.ferrus.apisac.model.MateriaPrima;
import com.ferrus.apisac.model.Preferencia;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.service.PreferenciaService;
import com.ferrus.apisac.model.serviceImp.PreferenciaServImpl;
import com.ferrus.apisac.ui.inicio.App;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ramiro Ferreira
 */
public class Main {

    public static void main(String[] args) {
        /*PreferenciaService prefServ = new PreferenciaServImpl();
        UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
        Preferencia preference = new Preferencia();
        preference.setNombre(lafInfo[0].getName());
        preference.setDescripcion(lafInfo[0].getClassName());
        preference.setSeleccionado("N");
        prefServ.setPreference(preference);*/
        loadPreferences();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                App app = new App();
            }
        });
    }

    private static void loadPreferences() {
        PreferenciaService prefServ = new PreferenciaServImpl();
        /*
        Cuando se ejecuta al app por primera vez se chequea que existan LAFs
        Si es que no hay ninguno, se extraen los LAFs del sistema y se los carga en 
        la app.
         */
        if (prefServ.getAllPreferences().isEmpty()) {
            System.out.println("com.ferrus.apisac.Main.loadPreferences()> no hay preferencia");
            List<UIManager.LookAndFeelInfo> lafList = new ArrayList<>();
            UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
            lafList.addAll(Arrays.asList(lafInfo));
            prefServ.setAllPreferences(lafList);
            try {
                for (LookAndFeelInfo info : lafList) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        prefServ.setPreference(info.getName());
                        break;
                    }
                }
            } catch (Exception e) {
            }
        } else {
            System.out.println("com.ferrus.apisac.Main.loadPreferences()> preferencia seleccionada");
            try {
                UIManager.setLookAndFeel(prefServ.getCurrentPreference().getDescripcion());
            } catch (Exception e) {
            }
        }
    }
    
    private static void test(){
        UnidadMedida um1 = new UnidadMedida();
        um1.setNombre("Kilogramo");
        um1.setSimbolo("Kg");
        um1.setValor(1000.0);
        MateriaPrima mp1 = new MateriaPrima();
        mp1.setNombre("Harina 000");
        mp1.setPrecio(3100.0);
        mp1.setUnidadMedida(um1);
    }
}
