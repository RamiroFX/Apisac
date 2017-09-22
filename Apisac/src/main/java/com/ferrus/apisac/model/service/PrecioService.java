/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.service;

import com.ferrus.apisac.model.EntityHandler.EntityManagerHandler;
import com.ferrus.apisac.model.Precio;

/**
 *
 * @author Ramiro Ferreira
 */
public interface PrecioService {

    public void insertarPrecio(Precio precio);

    public Precio obtenerPrecio(Long id);

    public void modificarPrecio(Precio precio);

    public void eliminarPrecio(Precio precio);
}
