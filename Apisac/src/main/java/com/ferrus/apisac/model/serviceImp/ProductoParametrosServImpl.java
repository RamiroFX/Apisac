/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.serviceImp;

import com.ferrus.apisac.model.EntityHandler.ProductoParametrosQueryHandler;
import com.ferrus.apisac.model.Impuesto;
import com.ferrus.apisac.model.Marca;
import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.ProductoCategoria;
import com.ferrus.apisac.model.ProductoSubCategoria;
import java.util.List;
import com.ferrus.apisac.model.service.ProductoParametrosService;

/**
 *
 * @author Ramiro Ferreira
 */
public class ProductoParametrosServImpl implements ProductoParametrosService {

    private ProductoParametrosQueryHandler queryHandler;

    public ProductoParametrosServImpl() {
        this.queryHandler = new ProductoParametrosQueryHandler();
    }

    @Override
    public void insertarProducto(Producto producto) {
        this.queryHandler.insertarProducto(producto);
    }

    @Override
    public Producto obtenerProducto(Long id) {
        return this.queryHandler.obtenerProducto(id);
    }

    @Override
    public List<Producto> obtenerProductos(String nombre, boolean inclusivo) {
        return this.queryHandler.obtenerProductos(nombre, inclusivo);
    }

    @Override
    public List<Producto> obtenerProductosPorMarca(String marca) {
        return this.queryHandler.obtenerProductosPorMarca(marca);
    }

    @Override
    public List<Producto> obtenerProductosPorCategoria(String categoria) {
        return this.queryHandler.obtenerProductosPorCategoria(categoria);
    }

    @Override
    public List<Producto> obtenerProductosPorSubCategoria(String subCategoria) {
        return this.queryHandler.obtenerProductosPorSubCategoria(subCategoria);
    }

    @Override
    public List<Producto> obtenerProductosPorImpuesto(Double valor) {
        return this.queryHandler.obtenerProductosPorImpuesto(valor);
    }

    @Override
    public List<Producto> obtenerProductosPorMateriaPrimaID(Long idMateriaPrima) {
        return this.queryHandler.obtenerProductosPorMateriaPrimaID(idMateriaPrima);
    }

    @Override
    public void modificarProducto(Producto producto) {
        this.queryHandler.eliminarProducto(producto);
    }

    @Override
    public void eliminarProducto(Producto producto) {

    }

    @Override
    public void insertarProductoCategoria(ProductoCategoria productoCategoria) {
        this.queryHandler.insertarProductoCategoria(productoCategoria);
    }

    @Override
    public ProductoCategoria obtenerProductoCategoria(Long id) {
        return this.queryHandler.obtenerProductoCategoria(id);
    }

    @Override
    public List<ProductoCategoria> obtenerProductosCategorias(String descripcion, boolean inclusivo) {
        return this.queryHandler.obtenerProductosCategorias(descripcion, inclusivo);
    }

    @Override
    public void modificarProductoCategoria(ProductoCategoria productoCategoria) {
        this.queryHandler.modificarProductoCategoria(productoCategoria);
    }

    @Override
    public void eliminarProductoCategoria(Long idProductoCategoria) {
        this.queryHandler.eliminarProductoCategoria(idProductoCategoria);
    }

    @Override
    public void insertarProductoSubCategoria(ProductoSubCategoria productoSubCategoria) {
        this.queryHandler.insertarProductoSubCategoria(productoSubCategoria);
    }

    @Override
    public ProductoSubCategoria obtenerProductoSubCategoria(Long id) {
        return this.queryHandler.obtenerProductoSubCategoria(id);
    }

    @Override
    public List<ProductoSubCategoria> obtenerProductosSubCategorias(String descripcion, boolean inclusivo) {
        return this.queryHandler.obtenerProductosSubCategorias(descripcion, inclusivo);
    }

    @Override
    public void modificarProductoSubCategoria(ProductoSubCategoria productoSubCategoria, ProductoCategoria productoCategoria) {
        this.queryHandler.modificarProductoSubCategoria(productoSubCategoria, productoCategoria);
    }

    @Override
    public void eliminarProductoSubCategoria(Long idProductoSubCategoria) {
        this.queryHandler.eliminarProductoSubCategoria(idProductoSubCategoria);
    }

    @Override
    public void insertarImpuesto(Impuesto impuesto) {
        this.queryHandler.insertarImpuesto(impuesto);
    }

    @Override
    public Impuesto obtenerImpuesto(Long id) {
        return this.queryHandler.obtenerImpuesto(id);
    }

    @Override
    public Impuesto obtenerImpuesto(Double valor) {
        return this.queryHandler.obtenerImpuesto(valor);
    }

    @Override
    public List<Impuesto> obtenerImpuestos() {
        return this.queryHandler.obtenerImpuestos();
    }

    @Override
    public void modificarImpuesto(Impuesto impuesto) {
        this.queryHandler.modificarImpuesto(impuesto);
    }

    @Override
    public void eliminarImpuesto(Long idImpuesto) {
        this.queryHandler.eliminarImpuesto(idImpuesto);
    }

    @Override
    public void insertarMarca(Marca marca) {
        this.queryHandler.insertarMarca(marca);
    }

    @Override
    public Marca obtenerMarca(Long id) {
        return this.queryHandler.obtenerMarca(id);
    }

    @Override
    public List<Marca> obtenerMarcas(String nombre, boolean inclusivo) {
        return this.queryHandler.obtenerMarcas(nombre, inclusivo);
    }

    @Override
    public void modificarMarca(Marca marca) {
        this.queryHandler.modificarMarca(marca);
    }

    @Override
    public void eliminarMarca(Long idMarca) {
        this.queryHandler.eliminarMarca(idMarca);
    }

}
