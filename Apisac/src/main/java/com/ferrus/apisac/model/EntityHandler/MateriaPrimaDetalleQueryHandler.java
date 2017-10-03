/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.EntityHandler;

import com.ferrus.apisac.model.MateriaPrimaDetalle;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ramiro Ferreira
 */
public class MateriaPrimaDetalleQueryHandler extends AbstractQuery {

    public MateriaPrimaDetalleQueryHandler() {
    }

    public void insertarMateriaPrimaDetalle(MateriaPrimaDetalle mpd) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(mpd);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public MateriaPrimaDetalle obtenerMateriaPrimaDetalle(Long id) {
        open();
        MateriaPrimaDetalle mpd = EntityManagerHandler.INSTANCE.getEntityManager().find(MateriaPrimaDetalle.class, id);
        return mpd;
    }

    public List<MateriaPrimaDetalle> obtenerMateriasPrimasDetalles(Long idPrecioProducto) {
        open();
        TypedQuery<MateriaPrimaDetalle> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("materiaPrimaDetalle.obtenerMateriasPrimasDetalles", MateriaPrimaDetalle.class);
        typedQuery.setParameter("idPrecioProducto", idPrecioProducto);
        return typedQuery.getResultList();
    }

    public void modificarMateriaPrimaDetalle(MateriaPrimaDetalle mpd) {
        open();
        MateriaPrimaDetalle materiaPrimaDetalle = EntityManagerHandler.INSTANCE.getEntityManager().find(MateriaPrimaDetalle.class, mpd.getId());
        materiaPrimaDetalle.setCantidad(mpd.getCantidad());
        materiaPrimaDetalle.setMateriaPrima(mpd.getMateriaPrima());
        materiaPrimaDetalle.setPrecioMateriaPrima(mpd.getPrecioMateriaPrima());
        materiaPrimaDetalle.setPrecioProducto(mpd.getPrecioProducto());
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void eliminarMateriaPrimaDetalle(Long idMpd) {
        open();
        MateriaPrimaDetalle mpd = EntityManagerHandler.INSTANCE.getEntityManager().find(MateriaPrimaDetalle.class, idMpd);
        EntityManagerHandler.INSTANCE.getEntityManager().remove(mpd);
        EntityManagerHandler.INSTANCE.getEntityManager().clear();
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }
}
