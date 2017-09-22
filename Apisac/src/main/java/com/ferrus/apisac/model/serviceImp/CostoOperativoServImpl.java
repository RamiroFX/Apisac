/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.serviceImp;

import com.ferrus.apisac.model.CostoOperativo;
import com.ferrus.apisac.model.EntityHandler.CostoOperativoQueryHandler;
import com.ferrus.apisac.model.service.CostoOperativoService;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public class CostoOperativoServImpl implements CostoOperativoService {

    private CostoOperativoQueryHandler queryHandler;

    public CostoOperativoServImpl() {
        this.queryHandler = new CostoOperativoQueryHandler();
    }

    @Override
    public void insertarCostoOperativo(CostoOperativo costoOperativo) {
        this.queryHandler.insertarCostoOperativo(costoOperativo);
    }

    @Override
    public CostoOperativo obtenerMateriaPrima(Long id) {
        return this.queryHandler.obtenerMateriaPrima(id);
    }

    @Override
    public List<CostoOperativo> obtenerCostosOperativos(String nombre) {
        return this.queryHandler.obtenerCostosOperativos(nombre);
    }

    @Override
    public void modificarCostoOperativo(CostoOperativo costoOperativo) {
        this.queryHandler.modificarCostoOperativo(costoOperativo);
    }

    @Override
    public void eliminarCostoOperativo(Long id) {
        this.queryHandler.eliminarCostoOperativo(id);
    }

}
