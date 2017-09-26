/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.EntityHandler;

import com.ferrus.apisac.model.CostoOperativo;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ramiro Ferreira
 */
public class CostoOperativoQueryHandler extends AbstractQuery {

    public CostoOperativoQueryHandler() {
    }

    public void insertarCostoOperativo(CostoOperativo costoOperativo) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(costoOperativo);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public CostoOperativo obtenerMateriaPrima(Long id) {
        open();
        CostoOperativo co = EntityManagerHandler.INSTANCE.getEntityManager().find(CostoOperativo.class, id);
        return co;
    }

    public List<CostoOperativo> obtenerCostosOperativos(String nombre, boolean inclusivo) {
        open();
        TypedQuery<CostoOperativo> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("costoOperativo.obtenerCostosOperativos", CostoOperativo.class);
        if (inclusivo) {
            typedQuery.setParameter("nombre", "%" + nombre + "%");
        } else {
            typedQuery.setParameter("nombre", nombre);
        }
        return typedQuery.getResultList();
    }

    public void modificarCostoOperativo(CostoOperativo costoOperativo) {
        open();
        CostoOperativo mp = EntityManagerHandler.INSTANCE.getEntityManager().find(CostoOperativo.class, costoOperativo.getId());
        mp.setNombre(costoOperativo.getNombre());
        mp.setDescripcion(costoOperativo.getDescripcion());
        mp.setPrecio(costoOperativo.getPrecio());
        mp.setDiasLaborales(costoOperativo.getDiasLaborales());
        mp.setUnidadMedida(costoOperativo.getUnidadMedida());
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void eliminarCostoOperativo(Long id) {
        open();
        CostoOperativo co = EntityManagerHandler.INSTANCE.getEntityManager().find(CostoOperativo.class, id);
        co.setUnidadMedida(null);
        EntityManagerHandler.INSTANCE.getEntityManager().remove(co);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }
}
