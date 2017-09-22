/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.EntityHandler;

import com.ferrus.apisac.model.MateriaPrima;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ramiro Ferreira
 */
public class MateriaPrimaQueryHandler extends AbstractQuery {

    public MateriaPrimaQueryHandler() {
    }

    public void insertarMateriaPrima(MateriaPrima materiaPrima) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(materiaPrima);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public MateriaPrima obtenerMateriaPrima(Long id) {
        open();
        MateriaPrima mp = EntityManagerHandler.INSTANCE.getEntityManager().find(MateriaPrima.class, id);
        return mp;
    }

    public List<MateriaPrima> obtenerMateriasPrimas(String nombre) {
        open();
        TypedQuery<MateriaPrima> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("materiaPrima.obtenerMateriasPrimasNombre", MateriaPrima.class);
        typedQuery.setParameter("nombre", nombre);
        return typedQuery.getResultList();
    }

    public void modificarMateriasPrimas(MateriaPrima materiaPrima) {
        open();
        MateriaPrima mp = EntityManagerHandler.INSTANCE.getEntityManager().find(MateriaPrima.class, materiaPrima.getId());
        mp.setNombre(materiaPrima.getNombre());
        mp.setDescripcion(materiaPrima.getDescripcion());
        mp.setPrecio(materiaPrima.getPrecio());
        mp.setUnidadMedida(materiaPrima.getUnidadMedida());
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void eliminarMateriaPrima(Long id) {
        open();
        MateriaPrima mp = EntityManagerHandler.INSTANCE.getEntityManager().find(MateriaPrima.class, id);
        mp.setUnidadMedida(null);
        EntityManagerHandler.INSTANCE.getEntityManager().remove(mp);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }
}
