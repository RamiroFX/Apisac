/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.serviceImp;

import com.ferrus.apisac.model.EntityHandler.MateriaPrimaQueryHandler;
import com.ferrus.apisac.model.MateriaPrima;
import com.ferrus.apisac.model.service.MateriaPrimaService;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public class MateriaPrimaServImpl implements MateriaPrimaService {

    private MateriaPrimaQueryHandler queryHandler;

    public MateriaPrimaServImpl() {
        this.queryHandler = new MateriaPrimaQueryHandler();
    }

    @Override
    public void insertarMateriaPrima(MateriaPrima materiaPrima) {
        this.queryHandler.insertarMateriaPrima(materiaPrima);
    }

    @Override
    public MateriaPrima obtenerMateriaPrima(Long id) {
        return this.queryHandler.obtenerMateriaPrima(id);
    }

    @Override
    public List<MateriaPrima> obtenerMateriasPrimas(String nombre, boolean inclusivo) {
        return this.queryHandler.obtenerMateriasPrimas(nombre, inclusivo);
    }

    @Override
    public void modificarMateriasPrimas(MateriaPrima materiaPrima) {
        this.queryHandler.modificarMateriasPrimas(materiaPrima);
    }

    @Override
    public void eliminarMateriaPrima(Long id) {
        this.queryHandler.eliminarMateriaPrima(id);
    }

}
