/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.EntityHandler;

import com.ferrus.apisac.model.Impuesto;
import com.ferrus.apisac.model.Marca;
import com.ferrus.apisac.model.Precio;
import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.ProductoCategoria;
import com.ferrus.apisac.model.ProductoSubCategoria;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ramiro Ferreira
 */
public class ProductoParametrosQueryHandler extends AbstractQuery {

    public ProductoParametrosQueryHandler() {
    }

    public void insertarProducto(Producto producto) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(producto);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public Producto obtenerProducto(Long id) {
        Producto producto = EntityManagerHandler.INSTANCE.getEntityManager().find(Producto.class, id);
        return producto;
    }

    public List<Producto> obtenerProductos(String nombre, boolean inclusivo) {
        TypedQuery<Producto> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("producto.obtenerProductos", Producto.class);
        if (inclusivo) {
            typedQuery.setParameter("nombre", "%" + nombre.trim() + "%");
        } else {
            typedQuery.setParameter("nombre", nombre.trim());
        }
        return typedQuery.getResultList();
    }

    public List<Producto> obtenerProductosPorMarca(String marca) {
        TypedQuery<Producto> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("producto.obtenerProductosMarca", Producto.class);
        typedQuery.setParameter("marca", marca.trim());
        return typedQuery.getResultList();
    }

    public List<Producto> obtenerProductosPorCategoria(String categoria) {
        TypedQuery<Producto> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("producto.obtenerProductosCategoria", Producto.class);
        typedQuery.setParameter("categoria", categoria.trim());
        return typedQuery.getResultList();
    }

    public List<Producto> obtenerProductosPorSubCategoria(String subCategoria) {
        TypedQuery<Producto> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("producto.obtenerProductosSubCategoria", Producto.class);
        typedQuery.setParameter("subcategoria", subCategoria.trim());
        return typedQuery.getResultList();
    }

    public List<Producto> obtenerProductosPorImpuesto(Double valor) {
        TypedQuery<Producto> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("producto.obtenerProductosImpuesto", Producto.class);
        typedQuery.setParameter("valor", valor);
        return typedQuery.getResultList();
    }

    public List<Producto> obtenerProductosPorMateriaPrimaID(Long idMateriaPrima) {
        TypedQuery<Producto> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("producto.obtenerProductosMateriaPrima", Producto.class);
        typedQuery.setParameter("idMateriaPrima", idMateriaPrima);
        return typedQuery.getResultList();
    }

    public void modificarProducto(Producto producto) {
        open();
        Producto p = EntityManagerHandler.INSTANCE.getEntityManager().find(Producto.class, producto.getId());
        p.setDescripcion(producto.getDescripcion());
        p.setImpuesto(producto.getImpuesto());
        p.setNombre(producto.getNombre());
        p.setPrecio(producto.getPrecio());
        p.setProductoCategoria(producto.getProductoCategoria());
        p.setProductoSubCategoria(producto.getProductoSubCategoria());
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void eliminarProducto(Long idProducto) {
        open();
        Producto p = EntityManagerHandler.INSTANCE.getEntityManager().find(Producto.class, idProducto);
        p.setImpuesto(null);
        p.setProductoCategoria(null);
        p.setProductoSubCategoria(null);
        EntityManagerHandler.INSTANCE.getEntityManager().remove(p);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    //PRODUCTO CATEGORIA
    public void insertarProductoCategoria(ProductoCategoria productoCategoria) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(productoCategoria);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public ProductoCategoria obtenerProductoCategoria(Long id) {
        ProductoCategoria productoCategoria = EntityManagerHandler.INSTANCE.getEntityManager().find(ProductoCategoria.class, id);
        return productoCategoria;
    }

    public List<ProductoCategoria> obtenerProductosCategorias(String descripcion, boolean inclusivo) {
        TypedQuery<ProductoCategoria> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("productoCategoria.obtenerProductoCategoriaNombre", ProductoCategoria.class);
        if (inclusivo) {
            typedQuery.setParameter("descripcion", "%" + descripcion + "%");
        } else {
            typedQuery.setParameter("descripcion", descripcion);
        }
        return typedQuery.getResultList();
    }

    public void modificarProductoCategoria(ProductoCategoria productoCategoria) {
        open();
        ProductoCategoria pc = EntityManagerHandler.INSTANCE.getEntityManager().find(ProductoCategoria.class, productoCategoria.getId());
        pc.setDescripcion(productoCategoria.getDescripcion());
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void eliminarProductoCategoria(Long idProductoCategoria) {
        open();
        ProductoCategoria pc = EntityManagerHandler.INSTANCE.getEntityManager().find(ProductoCategoria.class, idProductoCategoria);
        EntityManagerHandler.INSTANCE.getEntityManager().remove(pc);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    //PRODUCTO SUB-CATEGORIA
    public void insertarProductoSubCategoria(ProductoSubCategoria productoSubCategoria) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(productoSubCategoria);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public ProductoSubCategoria obtenerProductoSubCategoria(Long id) {
        ProductoSubCategoria productoSubCategoria = EntityManagerHandler.INSTANCE.getEntityManager().find(ProductoSubCategoria.class, id);
        return productoSubCategoria;
    }

    public List<ProductoSubCategoria> obtenerProductosSubCategorias(String descripcion, boolean inclusivo) {
        TypedQuery<ProductoSubCategoria> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("productoSubCategoria.obtenerProductoSubCategoriaNombre", ProductoSubCategoria.class);
        if (inclusivo) {
            typedQuery.setParameter("descripcion", "%" + descripcion + "%");
        } else {
            typedQuery.setParameter("descripcion", descripcion);
        }
        return typedQuery.getResultList();
    }

    public List<ProductoSubCategoria> obtenerProductosSubCategorias(ProductoCategoria productoCategoria) {
        TypedQuery<ProductoSubCategoria> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("productoSubCategoria.obtenerProductoSubCategoriaPorCategoria", ProductoSubCategoria.class);
        typedQuery.setParameter("categoria", productoCategoria.getDescripcion());
        return typedQuery.getResultList();
    }

    public void modificarProductoSubCategoria(ProductoSubCategoria productoSubCategoria, ProductoCategoria productoCategoria) {
        open();
        ProductoSubCategoria psc = EntityManagerHandler.INSTANCE.getEntityManager().find(ProductoSubCategoria.class, productoSubCategoria.getId());
        psc.setProductoCategoria(productoCategoria);
        psc.setDescripcion(productoSubCategoria.getDescripcion());
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void eliminarProductoSubCategoria(Long idProductoSubCategoria) {
        open();
        ProductoSubCategoria psc = EntityManagerHandler.INSTANCE.getEntityManager().find(ProductoSubCategoria.class, idProductoSubCategoria);
        psc.setProductoCategoria(null);
        EntityManagerHandler.INSTANCE.getEntityManager().remove(psc);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void insertarImpuesto(Impuesto impuesto) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(impuesto);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public Impuesto obtenerImpuesto(Long id) {
        Impuesto impuesto = EntityManagerHandler.INSTANCE.getEntityManager().find(Impuesto.class, id);
        return impuesto;
    }

    public Impuesto obtenerImpuesto(Double valor) {
        open();
        Impuesto impuesto = null;
        try {
            TypedQuery<Impuesto> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("impuesto.obtenerImpuestoValor", Impuesto.class);
            typedQuery.setParameter("valor", valor);
            impuesto = typedQuery.getSingleResult();
        } catch (NoResultException e) {
        }
        return impuesto;
    }

    public List<Impuesto> obtenerImpuestos() {
        TypedQuery<Impuesto> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("impuesto.obtenerImpuestos", Impuesto.class);
        return typedQuery.getResultList();
    }

    public void modificarImpuesto(Impuesto impuesto) {
        open();
        Impuesto imp = EntityManagerHandler.INSTANCE.getEntityManager().find(Impuesto.class, impuesto.getId());
        imp.setValor(impuesto.getValor());
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void eliminarImpuesto(Long idImpuesto) {
        open();
        Impuesto imp = EntityManagerHandler.INSTANCE.getEntityManager().find(Impuesto.class, idImpuesto);
        EntityManagerHandler.INSTANCE.getEntityManager().remove(imp);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    /*
    MARCA
     */
    public void insertarMarca(Marca marca) {
        open();
        EntityManagerHandler.INSTANCE.getEntityManager().persist(marca);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public Marca obtenerMarca(Long id) {
        Marca marca = EntityManagerHandler.INSTANCE.getEntityManager().find(Marca.class, id);
        return marca;
    }

    public List<Marca> obtenerMarcas(String nombre, boolean inclusivo) {
        TypedQuery<Marca> typedQuery = EntityManagerHandler.INSTANCE.getEntityManager().createNamedQuery("marca.obtenerMarcas", Marca.class);
        if (inclusivo) {
            typedQuery.setParameter("descripcion", "%" + nombre + "%");
        } else {
            typedQuery.setParameter("descripcion", nombre);
        }
        return typedQuery.getResultList();
    }

    public void modificarMarca(Marca marca) {
        open();
        Marca marc = EntityManagerHandler.INSTANCE.getEntityManager().find(Marca.class, marca.getId());
        marc.setDescripcion(marca.getDescripcion());
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

    public void eliminarMarca(Long idMarca) {
        open();
        Marca marc = EntityManagerHandler.INSTANCE.getEntityManager().find(Marca.class, idMarca);
        EntityManagerHandler.INSTANCE.getEntityManager().remove(marc);
        EntityManagerHandler.INSTANCE.getEntityTransaction().commit();
    }

}
