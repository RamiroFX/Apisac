/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.EntityHandler;

import com.ferrus.apisac.model.Preferencia;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ramiro Ferreira
 */
public class PreferenciaManagerHandler extends AbstractQuery {

    public PreferenciaManagerHandler() {
    }

    public List<Preferencia> getAllRoles() {
        open();
        TypedQuery<Preferencia> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("preferencia.getAll", Preferencia.class);
        return typedQuery.getResultList();
    }

}
