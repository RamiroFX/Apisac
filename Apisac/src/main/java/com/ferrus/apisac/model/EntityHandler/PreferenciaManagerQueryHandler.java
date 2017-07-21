package com.ferrus.apisac.model.EntityHandler;

import com.ferrus.apisac.model.Preferencia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.UIManager;

/**
 *
 * @author Ramiro Ferreira
 */
public class PreferenciaManagerQueryHandler extends AbstractQuery {

    public PreferenciaManagerQueryHandler() {
    }

    public void insertPreference(Preferencia preference) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(preference);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public List<Preferencia> getAllPreferences() {
        open();
        TypedQuery<Preferencia> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("preferencia.getAll", Preferencia.class);
        return typedQuery.getResultList();
    }

    public Preferencia getCurrentPreference() {
        open();
        TypedQuery<Preferencia> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("preferencia.getPreferenciaBySeleccionado", Preferencia.class);
        return typedQuery.getSingleResult();
    }

    public void setAllPreferences(List<UIManager.LookAndFeelInfo> lafInfo) {
        open();
        for (UIManager.LookAndFeelInfo lookAndFeelInfo : lafInfo) {
            Preferencia preferencia = new Preferencia(lookAndFeelInfo.getName(), lookAndFeelInfo.getClassName());
            preferencia.setSeleccionado("N");
            EntityManagerHandler.INSTANCE.getEntityManager().persist(preferencia);
        }
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void setPreference(String preferenceName) {
        open();
        List<Preferencia> allPreferences = getAllPreferences();
        for (Preferencia preferencia : allPreferences) {
            if (preferenceName.equals(preferencia.getNombre())) {
                preferencia.setSeleccionado("S");
            } else {
                preferencia.setSeleccionado("N");
            }
        }
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

}
