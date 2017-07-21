/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.service;

import com.ferrus.apisac.model.Preferencia;
import java.util.List;
import javax.swing.UIManager;

/**
 *
 * @author Ramiro Ferreira
 */
public interface PreferenciaService {

    public List<Preferencia> getAllPreferences();

    public Preferencia getCurrentPreference();

    public void setPreference(String preferenceName);

    public void setAllPreferences(List<UIManager.LookAndFeelInfo> lafInfo);

}
