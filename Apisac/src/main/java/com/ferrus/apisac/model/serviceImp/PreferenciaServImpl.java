/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.serviceImp;

import com.ferrus.apisac.model.EntityHandler.PreferenciaManagerQueryHandler;
import com.ferrus.apisac.model.Preferencia;
import com.ferrus.apisac.model.service.PreferenciaService;
import java.util.List;
import javax.swing.UIManager;

/**
 *
 * @author Ramiro Ferreira
 */
public class PreferenciaServImpl implements PreferenciaService {

    private PreferenciaManagerQueryHandler queryHandler;

    public PreferenciaServImpl() {
        this.queryHandler = new PreferenciaManagerQueryHandler();
    }

    @Override
    public List<Preferencia> getAllPreferences() {
        return queryHandler.getAllPreferences();
    }

    @Override
    public Preferencia getCurrentPreference() {
        return queryHandler.getCurrentPreference();
    }

    @Override
    public void setPreference(String preferenceName) {
        queryHandler.setPreference(preferenceName);
    }

    @Override
    public void setAllPreferences(List<UIManager.LookAndFeelInfo> lafInfo) {
        queryHandler.setAllPreferences(lafInfo);
    }

}
