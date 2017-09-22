/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.serviceImp;

import com.ferrus.apisac.model.EntityHandler.PrecioQueryHandler;
import com.ferrus.apisac.model.Precio;
import com.ferrus.apisac.model.service.PrecioService;

/**
 *
 * @author Ramiro Ferreira
 */
public class PrecioServImpl implements PrecioService {

    private PrecioQueryHandler queryHandler;

    public PrecioServImpl() {
        this.queryHandler = new PrecioQueryHandler();
    }

    @Override
    public void insertarPrecio(Precio precio) {
        this.queryHandler.insertarPrecio(precio);
    }

    @Override
    public Precio obtenerPrecio(Long id) {
        return this.queryHandler.obtenerPrecio(id);
    }

    @Override
    public void modificarPrecio(Precio precio) {
        this.queryHandler.modificarPrecio(precio);
    }

    @Override
    public void eliminarPrecio(Precio precio) {
        this.queryHandler.eliminarPrecio(precio);
    }

}
