/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.EntityHandler;

import com.ferrus.apisac.model.Precio;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public class PrecioQueryHandler extends AbstractQuery {

    public PrecioQueryHandler() {
    }

    public void insertarPrecio(Precio precio) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(precio);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public Precio obtenerPrecio(Long id) {
        Precio p = EntityManagerHandler.INSTANCE.getEntityManager().find(Precio.class, id);
        return p;
    }

    public void modificarPrecio(Precio precio) {
        open();
        Precio p = EntityManagerHandler.INSTANCE.getEntityManager().find(Precio.class, precio.getId());
        p.setCostoOperativoDetalles(precio.getCostoOperativoDetalles());
        p.setMateriaPrimaDetalles(precio.getMateriaPrimaDetalles());
        p.setUnidadesProducidas(precio.getUnidadesProducidas());
        p.setUtilidad(p.getUtilidad());
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void eliminarPrecio(Precio precio) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().remove(precio);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }
}
