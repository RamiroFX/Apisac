/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.serviceImp;

import com.ferrus.apisac.model.EntityHandler.UnidadMedidaQueryHandler;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.service.UnidadMedidaService;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public class UnidadMedidaServImpl implements UnidadMedidaService {

    private UnidadMedidaQueryHandler queryHandler;

    public UnidadMedidaServImpl() {
        this.queryHandler = new UnidadMedidaQueryHandler();
    }

    @Override
    public void insertarUnidadMedida(UnidadMedida unidadMedida) {
        queryHandler.insertarUnidadMedida(unidadMedida);
    }

    @Override
    public UnidadMedida obtenerUnidadMedida(Long id) {
        return queryHandler.obtenerUnidadMedida(id);
    }

    @Override
    public List<UnidadMedida> obtenerUnidadMedidas() {
        return queryHandler.obtenerUnidadMedidas();
    }

}
