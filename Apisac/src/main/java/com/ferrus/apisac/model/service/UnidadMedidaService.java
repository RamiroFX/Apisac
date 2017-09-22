/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.service;

import com.ferrus.apisac.model.UnidadMedida;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public interface UnidadMedidaService {

    public void insertarUnidadMedida(UnidadMedida unidadMedida);

    public UnidadMedida obtenerUnidadMedida(Long id);

    public List<UnidadMedida> obtenerUnidadMedidas();

}
