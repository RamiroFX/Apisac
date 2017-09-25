/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.service;

import com.ferrus.apisac.model.MateriaPrima;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public interface MateriaPrimaService {

    public void insertarMateriaPrima(MateriaPrima materiaPrima);

    public MateriaPrima obtenerMateriaPrima(Long id);

    public List<MateriaPrima> obtenerMateriasPrimas(String nombre, boolean inclusivo);

    public void modificarMateriasPrimas(MateriaPrima materiaPrima);

    public void eliminarMateriaPrima(Long id);

}
