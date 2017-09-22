/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.service;

import com.ferrus.apisac.model.MateriaPrimaDetalle;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public interface MateriaPrimaDetalleService {

    public void insertarMateriaPrimaDetalle(MateriaPrimaDetalle mpd);

    public MateriaPrimaDetalle obtenerMateriaPrimaDetalle(Long id);

    public List<MateriaPrimaDetalle> obtenerMateriasPrimasDetalles(Long idPrecioProducto);

    public void modificarMateriaPrimaDetalle(MateriaPrimaDetalle mpd);

    public void eliminarMateriaPrimaDetalle(Long id);
}
