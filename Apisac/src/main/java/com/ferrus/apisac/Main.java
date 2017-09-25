/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac;

import com.ferrus.apisac.model.MateriaPrima;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.service.MateriaPrimaService;
import com.ferrus.apisac.model.service.PreferenciaService;
import com.ferrus.apisac.model.service.UnidadMedidaService;
import com.ferrus.apisac.model.serviceImp.MateriaPrimaServImpl;
import com.ferrus.apisac.model.serviceImp.PreferenciaServImpl;
import com.ferrus.apisac.model.serviceImp.UnidadMedidaServImpl;
import com.ferrus.apisac.ui.inicio.App;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Ramiro Ferreira
 */
public class Main {

    public static void main(String[] args) {
        loadPreferences();
        loadData();
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
            try {
                UIManager.setLookAndFeel(prefServ.getCurrentPreference().getDescripcion());
            } catch (Exception e) {
            }
        }
    }

    private static void loadData() {
        UnidadMedida umGr = new UnidadMedida("Gramo", "Gr", 1.0);
        UnidadMedida umKG = new UnidadMedida("Kilogramo", "Kg", 1000.0);
        UnidadMedida umTonelada = new UnidadMedida("Tonelada", "Ton", 10000.0);
        UnidadMedidaService medidaService = new UnidadMedidaServImpl();
        medidaService.insertarUnidadMedida(umGr);
        medidaService.insertarUnidadMedida(umKG);
        medidaService.insertarUnidadMedida(umTonelada);

        MateriaPrima mpHarina = new MateriaPrima("Harina 000", null, 2500.0, umKG);
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServImpl();
        materiaPrimaService.insertarMateriaPrima(mpHarina);
    }
}
