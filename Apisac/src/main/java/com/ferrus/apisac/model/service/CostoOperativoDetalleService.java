/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.service;

import com.ferrus.apisac.model.CostoOperativoDetalle;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public interface CostoOperativoDetalleService {

    public void insertarCostoOperativoDetalle(CostoOperativoDetalle cod);

    public CostoOperativoDetalle obtenerCostoOperativoDetalle(Long id);

    public List<CostoOperativoDetalle> obtenerCostosOperativosDetalles(Long idPrecioProducto);

    public void modificarCostoOperativoDetalle(CostoOperativoDetalle cod);

    public void eliminarCostoOperativoDetalle(Long idCod);
}
