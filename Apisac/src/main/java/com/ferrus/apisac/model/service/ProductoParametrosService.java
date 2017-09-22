/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.service;

import com.ferrus.apisac.model.Impuesto;
import com.ferrus.apisac.model.Marca;
import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.ProductoCategoria;
import com.ferrus.apisac.model.ProductoSubCategoria;
import java.util.List;

/**
 *
 * @author Ramiro Ferreira
 */
public interface ProductoParametrosService {

    /*
    PRODUCTO
     */
    public void insertarProducto(Producto producto);

    public Producto obtenerProducto(Long id);

    public List<Producto> obtenerProductos(String nombre);

    public List<Producto> obtenerProductosPorMarca(String marca);

    public List<Producto> obtenerProductosPorCategoria(String categoria);

    public List<Producto> obtenerProductosPorImpuesto(Double valor);

    public void modificarProducto(Producto producto);

    public void eliminarProducto(Producto producto);

    /*
    PRODUCTO CATEGORIA
     */
    public void insertarProductoCategoria(ProductoCategoria productoCategoria);

    public ProductoCategoria obtenerProductoCategoria(Long id);

    public List<ProductoCategoria> obtenerProductosCategorias(String descripcion, boolean inclusivo);

    public void modificarProductoCategoria(ProductoCategoria productoCategoria);

    public void eliminarProductoCategoria(Long idProductoCategoria);

    /*
    PRODUCTO SUB CATEGORIA
     */
    public void insertarProductoSubCategoria(ProductoSubCategoria productoSubCategoria);

    public ProductoSubCategoria obtenerProductoSubCategoria(Long id);

    public List<ProductoSubCategoria> obtenerProductosSubCategorias(String descripcion);

    public void modificarProductoSubCategoria(ProductoSubCategoria productoSubCategoria);

    public void eliminarProductoSubCategoria(ProductoSubCategoria productoSubCategoria);

    /*
    IMPUESTO
     */
    public void insertarImpuesto(Impuesto impuesto);

    public Impuesto obtenerImpuesto(Long id);

    public Impuesto obtenerImpuesto(Double valor);

    public List<Impuesto> obtenerImpuestos();

    public void modificarImpuesto(Impuesto impuesto);

    public void eliminarImpuesto(Long idImpuesto);

    /*
    MARCA
     */
    public void insertarMarca(Marca marca);

    public Marca obtenerMarca(Long id);

    public List<Marca> obtenerMarcas(String nombre, boolean inclusivo);

    public void modificarMarca(Marca marca);

    public void eliminarMarca(Long idMarca);
}
