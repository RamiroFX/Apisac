/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.serviceImp;

import com.ferrus.apisac.model.EntityHandler.MateriaPrimaDetalleQueryHandler;
import com.ferrus.apisac.model.MateriaPrimaDetalle;
import com.ferrus.apisac.model.service.MateriaPrimaDetalleService;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public class MateriaPrimaDetalleServImpl implements MateriaPrimaDetalleService {

    private MateriaPrimaDetalleQueryHandler queryHandler;

    public MateriaPrimaDetalleServImpl() {
        this.queryHandler = new MateriaPrimaDetalleQueryHandler();
    }

    @Override
    public void insertarMateriaPrimaDetalle(MateriaPrimaDetalle mpd) {
        this.queryHandler.insertarMateriaPrimaDetalle(mpd);
    }

    @Override
    public MateriaPrimaDetalle obtenerMateriaPrimaDetalle(Long id) {
        return this.queryHandler.obtenerMateriaPrimaDetalle(id);
    }

    @Override
    public List<MateriaPrimaDetalle> obtenerMateriasPrimasDetalles(Long idPrecioProducto) {
        return this.queryHandler.obtenerMateriasPrimasDetalles(idPrecioProducto);
    }

    @Override
    public void modificarMateriaPrimaDetalle(MateriaPrimaDetalle mpd) {
        this.queryHandler.modificarMateriaPrimaDetalle(mpd);
    }

    @Override
    public void eliminarMateriaPrimaDetalle(Long id) {
        this.queryHandler.eliminarMateriaPrimaDetalle(id);
    }

}
