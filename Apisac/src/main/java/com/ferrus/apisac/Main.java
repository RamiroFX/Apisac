package com.ferrus.apisac;

import com.ferrus.apisac.model.service.PreferenciaService;
import com.ferrus.apisac.model.serviceImp.PreferenciaServImpl;
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                App app = new App();
            }
        });
    }

    private static void loadPreferences() {
        PreferenciaService prefServ = new PreferenciaServImpl();
        List<UIManager.LookAndFeelInfo> lafList = new ArrayList<>();
        UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
        lafList.addAll(Arrays.asList(lafInfo));
        /*
        Cuando se ejecuta al app por primera vez se chequea que existan LAFs(Look and Feels)
        Si es que no hay ninguno, se extraen los LAFs del sistema y se los carga en 
        la app.
         */
        if (prefServ.getAllPreferences().isEmpty()) {
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
}
