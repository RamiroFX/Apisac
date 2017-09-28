/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.callback;

import com.ferrus.apisac.model.CostoOperativoDetalle;
import com.ferrus.apisac.model.MateriaPrimaDetalle;

/**
 *
 * @author Ramiro Ferreira
 */
public interface CrearProductoCallback {

    public void recibirMateriaPrimaDetalle(MateriaPrimaDetalle mpd);

    public void modificarMateriaPrimaDetalle(MateriaPrimaDetalle mpd);

    public void recibirCostoOperativoDetalle(CostoOperativoDetalle cod);

    public void modificarCostoOperativoDetalle(CostoOperativoDetalle cod);
}
