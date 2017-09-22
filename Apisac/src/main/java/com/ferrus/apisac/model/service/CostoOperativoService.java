/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.service;

import com.ferrus.apisac.model.CostoOperativo;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public interface CostoOperativoService {

    public void insertarCostoOperativo(CostoOperativo costoOperativo);

    public CostoOperativo obtenerMateriaPrima(Long id);

    public List<CostoOperativo> obtenerCostosOperativos(String nombre);

    public void modificarCostoOperativo(CostoOperativo costoOperativo);

    public void eliminarCostoOperativo(Long id);

}
