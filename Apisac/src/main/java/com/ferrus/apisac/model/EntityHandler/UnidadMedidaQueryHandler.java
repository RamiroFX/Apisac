/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.EntityHandler;

import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.UnidadMedidaCategoria;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ramiro Ferreira
 */
public class UnidadMedidaQueryHandler extends AbstractQuery {

    public UnidadMedidaQueryHandler() {
    }

    public void insertarUnidadMedida(UnidadMedida unidadMedida) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(unidadMedida);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public UnidadMedida obtenerUnidadMedida(Long id) {
        open();
        UnidadMedida unidadMedida = EntityManagerHandler.INSTANCE.getEntityManager().find(UnidadMedida.class, id);
        return unidadMedida;
    }

    public List<UnidadMedida> obtenerUnidadMedidas() {
        open();
        TypedQuery<UnidadMedida> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("unidadMedida.getUnidadesMedida", UnidadMedida.class);
        return typedQuery.getResultList();
    }

    public List<UnidadMedida> obtenerUnidadMedidasPorCategoria(UnidadMedidaCategoria umc) {
        open();
        TypedQuery<UnidadMedida> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("unidadMedida.getUnidadesMedidaPorCategoria", UnidadMedida.class);
        typedQuery.setParameter("descripcion", umc.getDescripcion());
        return typedQuery.getResultList();
    }

    public void insertarUnidadMedidaCategoria(UnidadMedidaCategoria unidadMedidaCategoria) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(unidadMedidaCategoria);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public UnidadMedidaCategoria obtenerUnidadMedidaCategoria(Long id) {
        open();
        UnidadMedidaCategoria unidadMedida = EntityManagerHandler.INSTANCE.getEntityManager().find(UnidadMedidaCategoria.class, id);
        return unidadMedida;
    }

    public List<UnidadMedidaCategoria> obtenerUnidadMedidaCategorias() {
        open();
        TypedQuery<UnidadMedidaCategoria> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("UnidadMedidaCategoria.obtenerCategorias", UnidadMedidaCategoria.class);
        return typedQuery.getResultList();
    }

}
