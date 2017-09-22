/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.serviceImp;

import com.ferrus.apisac.model.CostoOperativoDetalle;
import com.ferrus.apisac.model.EntityHandler.CostoOperativoDetalleQueryHandler;
import com.ferrus.apisac.model.service.CostoOperativoDetalleService;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public class CostoOperativoDetalleServImpl implements CostoOperativoDetalleService {

    private CostoOperativoDetalleQueryHandler queryHandler;

    public CostoOperativoDetalleServImpl() {
        this.queryHandler = new CostoOperativoDetalleQueryHandler();
    }

    @Override
    public void insertarCostoOperativoDetalle(CostoOperativoDetalle cod) {
        this.queryHandler.insertarCostoOperativoDetalle(cod);
    }

    @Override
    public CostoOperativoDetalle obtenerCostoOperativoDetalle(Long id) {
        return this.queryHandler.obtenerCostoOperativoDetalle(id);
    }

    @Override
    public List<CostoOperativoDetalle> obtenerCostosOperativosDetalles(Long idPrecioProducto) {
        return this.queryHandler.obtenerCostosOperativosDetalles(idPrecioProducto);
    }

    @Override
    public void modificarCostoOperativoDetalle(CostoOperativoDetalle cod) {
        this.queryHandler.modificarCostoOperativoDetalle(cod);
    }

    @Override
    public void eliminarCostoOperativoDetalle(CostoOperativoDetalle cod) {
        this.queryHandler.eliminarCostoOperativoDetalle(cod);
    }

}
