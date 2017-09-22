/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.EntityHandler;

import com.ferrus.apisac.model.CostoOperativoDetalle;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ramiro Ferreira
 */
public class CostoOperativoDetalleQueryHandler extends AbstractQuery {

    public CostoOperativoDetalleQueryHandler() {
    }

    public void insertarCostoOperativoDetalle(CostoOperativoDetalle cod) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(cod);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public CostoOperativoDetalle obtenerCostoOperativoDetalle(Long id) {
        CostoOperativoDetalle cod = EntityManagerHandler.INSTANCE.getEntityManager().find(CostoOperativoDetalle.class, id);
        return cod;
    }

    public List<CostoOperativoDetalle> obtenerCostosOperativosDetalles(Long idPrecioProducto) {
        TypedQuery<CostoOperativoDetalle> typedQuery
                = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("costoOperativoDetalle.obtenerCostosOperativosDetalles", CostoOperativoDetalle.class);
        typedQuery.setParameter("idPrecioProducto", idPrecioProducto);
        return typedQuery.getResultList();
    }

    public void modificarCostoOperativoDetalle(CostoOperativoDetalle cod) {
        open();
        CostoOperativoDetalle codTemp = EntityManagerHandler.INSTANCE.getEntityManager().find(CostoOperativoDetalle.class, cod.getId());
        codTemp.setCantidad(cod.getCantidad());
        codTemp.setCostoOperativo(cod.getCostoOperativo());
        codTemp.setPrecioCostoOperativo(cod.getPrecioCostoOperativo());
        codTemp.setPrecioProducto(cod.getPrecioProducto());
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void eliminarCostoOperativoDetalle(CostoOperativoDetalle cod) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().remove(cod);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }
}
