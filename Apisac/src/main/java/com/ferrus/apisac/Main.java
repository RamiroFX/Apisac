/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac;

import com.ferrus.apisac.model.CostoOperativo;
import com.ferrus.apisac.model.CostoOperativoDetalle;
import com.ferrus.apisac.model.Impuesto;
import com.ferrus.apisac.model.Marca;
import com.ferrus.apisac.model.MateriaPrima;
import com.ferrus.apisac.model.MateriaPrimaDetalle;
import com.ferrus.apisac.model.Precio;
import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.model.ProductoCategoria;
import com.ferrus.apisac.model.ProductoSubCategoria;
import com.ferrus.apisac.model.UnidadMedida;
import com.ferrus.apisac.model.UnidadMedidaCategoria;
import com.ferrus.apisac.model.service.CostoOperativoDetalleService;
import com.ferrus.apisac.model.service.CostoOperativoService;
import com.ferrus.apisac.model.service.MateriaPrimaDetalleService;
import com.ferrus.apisac.model.service.MateriaPrimaService;
import com.ferrus.apisac.model.service.PrecioService;
import com.ferrus.apisac.model.service.PreferenciaService;
import com.ferrus.apisac.model.service.ProductoParametrosService;
import com.ferrus.apisac.model.service.UnidadMedidaService;
import com.ferrus.apisac.model.serviceImp.CostoOperativoDetalleServImpl;
import com.ferrus.apisac.model.serviceImp.CostoOperativoServImpl;
import com.ferrus.apisac.model.serviceImp.MateriaPrimaDetalleServImpl;
import com.ferrus.apisac.model.serviceImp.MateriaPrimaServImpl;
import com.ferrus.apisac.model.serviceImp.PrecioServImpl;
import com.ferrus.apisac.model.serviceImp.PreferenciaServImpl;
import com.ferrus.apisac.model.serviceImp.ProductoParametrosServImpl;
import com.ferrus.apisac.model.serviceImp.UnidadMedidaServImpl;
import com.ferrus.apisac.ui.inicio.App;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Ramiro Ferreira
 */
public class Main {

    public static void main(String[] args) {
        loadPreferences();
        loadData();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                App app = new App();
            }
        });
    }

    private static void loadPreferences() {
        PreferenciaService prefServ = new PreferenciaServImpl();
        /*
        Cuando se ejecuta al app por primera vez se chequea que existan LAFs
        Si es que no hay ninguno, se extraen los LAFs del sistema y se los carga en 
        la app.
         */
        if (prefServ.getAllPreferences().isEmpty()) {
            List<UIManager.LookAndFeelInfo> lafList = new ArrayList<>();
            UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
            lafList.addAll(Arrays.asList(lafInfo));
            prefServ.setAllPreferences(lafList);
            try {
                for (LookAndFeelInfo info : lafList) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        prefServ.setPreference(info.getName());
                        break;
                    }
                }
            } catch (Exception e) {
            }
        } else {
            try {
                UIManager.setLookAndFeel(prefServ.getCurrentPreference().getDescripcion());
            } catch (Exception e) {
            }
        }
    }

    private static void loadData() {
        UnidadMedidaCategoria umcUnidad = new UnidadMedidaCategoria("Unidad");
        UnidadMedidaCategoria umcTiempo = new UnidadMedidaCategoria("Tiempo");
        UnidadMedidaCategoria umcLongitud = new UnidadMedidaCategoria("Longitud");
        UnidadMedidaCategoria umcMasa = new UnidadMedidaCategoria("Masa");
        UnidadMedidaCategoria umcVolumen = new UnidadMedidaCategoria("Volumen");
        //UNIDAD
        UnidadMedida umUnidad = new UnidadMedida("Unidad", "Unid", 1.0, umcUnidad);
        //MASA
        UnidadMedida umGr = new UnidadMedida("Gramo", "Gr", 1.0, umcMasa);
        UnidadMedida umKG = new UnidadMedida("Kilogramo", "Kg", 1000.0, umcMasa);
        UnidadMedida umTonelada = new UnidadMedida("Tonelada", "Ton", 1000000.0, umcMasa);
        //VOLUMEN
        UnidadMedida umMl = new UnidadMedida("Mililitro", "ml", 1.0, umcVolumen);
        UnidadMedida umL = new UnidadMedida("Litro", "L", 1000.0, umcVolumen);
        //TIEMPO
        UnidadMedida umSegundo = new UnidadMedida("Segundo", "Seg", 1.0, umcTiempo);
        UnidadMedida umMinuto = new UnidadMedida("Minuto", "Min", 60.0, umcTiempo);
        UnidadMedida umHora = new UnidadMedida("Hora", "Hr", 3600.0, umcTiempo);
        //LONGITUD
        UnidadMedida umMm = new UnidadMedida("Milímetro", "Mm", 1.0, umcLongitud);
        UnidadMedida umCm = new UnidadMedida("Centímetro", "Cm", 10.0, umcLongitud);
        UnidadMedida umMt = new UnidadMedida("Metro", "Mt", 100.0, umcLongitud);
        UnidadMedida umKm = new UnidadMedida("Kilómetro", "Km", 1000.0, umcLongitud);
        UnidadMedidaService medidaService = new UnidadMedidaServImpl();
        medidaService.insertarUnidadMedidaCategoria(umcUnidad);
        medidaService.insertarUnidadMedidaCategoria(umcLongitud);
        medidaService.insertarUnidadMedidaCategoria(umcMasa);
        medidaService.insertarUnidadMedidaCategoria(umcTiempo);
        medidaService.insertarUnidadMedidaCategoria(umcVolumen);
        medidaService.insertarUnidadMedida(umUnidad);
        medidaService.insertarUnidadMedida(umGr);
        medidaService.insertarUnidadMedida(umKG);
        medidaService.insertarUnidadMedida(umTonelada);
        medidaService.insertarUnidadMedida(umSegundo);
        medidaService.insertarUnidadMedida(umMinuto);
        medidaService.insertarUnidadMedida(umHora);
        medidaService.insertarUnidadMedida(umMm);
        medidaService.insertarUnidadMedida(umCm);
        medidaService.insertarUnidadMedida(umMt);
        medidaService.insertarUnidadMedida(umKm);
        medidaService.insertarUnidadMedida(umMl);
        medidaService.insertarUnidadMedida(umL);

        MateriaPrima mpHarina = new MateriaPrima("Harina 000", null, 2500.0, umKG);
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServImpl();
        materiaPrimaService.insertarMateriaPrima(mpHarina);

        CostoOperativo co1 = new CostoOperativo("Jornales y comisiones", "Jornales y comisiones de comercialización", 11000.0, umUnidad, 24);
        CostoOperativo co2 = new CostoOperativo("Gastos de publicidad", null, 1000.0, umUnidad, 24);
        CostoOperativo co3 = new CostoOperativo("Panfletos", null, 1000.0, umUnidad, 24);
        CostoOperativo co4 = new CostoOperativo("Distribución", null, 1000.0, umUnidad, 24);
        CostoOperativo co5 = new CostoOperativo("Encuestas", null, 1000.0, umUnidad, 24);
        CostoOperativo co6 = new CostoOperativo("Documentos comerciales", null, 1000.0, umUnidad, 24);
        CostoOperativo co7 = new CostoOperativo("Diseño e impresión de logos", null, 1000.0, umUnidad, 24);
        CostoOperativo co8 = new CostoOperativo("Mano de obra", null, 11000.0, umHora, 24);
        CostoOperativo co9 = new CostoOperativo("Alquiler de local", null, 1000.0, umHora, 24);
        CostoOperativo co10 = new CostoOperativo("Alquiler de maquinarias", null, 1000.0, umHora, 24);
        CostoOperativo co11 = new CostoOperativo("Energía eléctrica", null, 1000.0, umHora, 24);
        CostoOperativo co12 = new CostoOperativo("Servicio telefónico", null, 1000.0, umHora, 24);
        CostoOperativo co13 = new CostoOperativo("Servicio de agua", null, 1000.0, umHora, 24);
        CostoOperativo co14 = new CostoOperativo("Servicio de gas", null, 1000.0, umHora, 24);
        CostoOperativo co15 = new CostoOperativo("Servicio de internet", null, 1000.0, umHora, 24);
        CostoOperativoService costoOperativoService = new CostoOperativoServImpl();
        costoOperativoService.insertarCostoOperativo(co1);
        costoOperativoService.insertarCostoOperativo(co2);
        costoOperativoService.insertarCostoOperativo(co3);
        costoOperativoService.insertarCostoOperativo(co4);
        costoOperativoService.insertarCostoOperativo(co5);
        costoOperativoService.insertarCostoOperativo(co6);
        costoOperativoService.insertarCostoOperativo(co7);
        costoOperativoService.insertarCostoOperativo(co8);
        costoOperativoService.insertarCostoOperativo(co9);
        costoOperativoService.insertarCostoOperativo(co10);
        costoOperativoService.insertarCostoOperativo(co11);
        costoOperativoService.insertarCostoOperativo(co12);
        costoOperativoService.insertarCostoOperativo(co13);
        costoOperativoService.insertarCostoOperativo(co14);
        costoOperativoService.insertarCostoOperativo(co15);

        Marca marca1 = new Marca("Reina");
        Marca marca2 = new Marca("Ersa");
        Marca marca3 = new Marca("Hilagro");
        Marca marca4 = new Marca("OK");
        ProductoCategoria categoria1 = new ProductoCategoria("Sin clasificar");
        ProductoCategoria categoria2 = new ProductoCategoria("Minuta");
        ProductoCategoria categoria3 = new ProductoCategoria("Panificado");
        ProductoCategoria categoria4 = new ProductoCategoria("Almuerzo");
        ProductoCategoria categoria5 = new ProductoCategoria("Bebida");
        ProductoSubCategoria productoSubCategoria1 = new ProductoSubCategoria("Sin sub-clasificar", categoria1);
        ProductoSubCategoria productoSubCategoria5 = new ProductoSubCategoria("Empanada", categoria2);
        ProductoSubCategoria productoSubCategoria6 = new ProductoSubCategoria("Tarta", categoria2);
        ProductoSubCategoria productoSubCategoria7 = new ProductoSubCategoria("Sandwich", categoria2);
        ProductoSubCategoria productoSubCategoria8 = new ProductoSubCategoria("Salvado", categoria3);
        ProductoSubCategoria productoSubCategoria9 = new ProductoSubCategoria("Seco", categoria3);
        ProductoSubCategoria productoSubCategoria10 = new ProductoSubCategoria("Sandwich", categoria3);
        ProductoSubCategoria productoSubCategoria11 = new ProductoSubCategoria("Sopa", categoria4);
        ProductoSubCategoria productoSubCategoria12 = new ProductoSubCategoria("Ensalada", categoria4);
        ProductoSubCategoria productoSubCategoria13 = new ProductoSubCategoria("Carne", categoria4);
        ProductoSubCategoria productoSubCategoria2 = new ProductoSubCategoria("Jugo", categoria5);
        ProductoSubCategoria productoSubCategoria3 = new ProductoSubCategoria("Café", categoria5);
        ProductoSubCategoria productoSubCategoria4 = new ProductoSubCategoria("Té", categoria5);
        Impuesto impuesto1 = new Impuesto(0.0);
        Impuesto impuesto2 = new Impuesto(5.0);
        Impuesto impuesto3 = new Impuesto(10.0);
        ProductoParametrosService parametrosService = new ProductoParametrosServImpl();
        parametrosService.insertarMarca(marca1);
        parametrosService.insertarMarca(marca2);
        parametrosService.insertarMarca(marca3);
        parametrosService.insertarMarca(marca4);
        parametrosService.insertarProductoCategoria(categoria1);
        parametrosService.insertarProductoCategoria(categoria2);
        parametrosService.insertarProductoCategoria(categoria3);
        parametrosService.insertarProductoCategoria(categoria4);
        parametrosService.insertarProductoCategoria(categoria5);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria1);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria2);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria3);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria4);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria5);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria6);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria7);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria8);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria9);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria10);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria11);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria12);
        parametrosService.insertarProductoSubCategoria(productoSubCategoria13);
        parametrosService.insertarImpuesto(impuesto1);
        parametrosService.insertarImpuesto(impuesto2);
        parametrosService.insertarImpuesto(impuesto3);

        //PRUEBA
        List<MateriaPrimaDetalle> materiPrimaDetalles = new ArrayList<>();
        MateriaPrimaDetalle mpd = new MateriaPrimaDetalle();
        mpd.setCantidad(1.0);
        //mpd.setMateriaPrima(new MateriaPrima(mpHarina.getNombre(), mpHarina.getDescripcion(), mpHarina.getPrecio(), mpHarina.getUnidadMedida()));
        mpd.setMateriaPrima(mpHarina);
        mpd.setPrecioMateriaPrima(15000.0);
        materiPrimaDetalles.add(mpd);

        List<CostoOperativoDetalle> costoOperativoDetalles = new ArrayList<>();
        CostoOperativoDetalle costoOperativoDetalle1 = new CostoOperativoDetalle();
        costoOperativoDetalle1.setCantidad(1.0);
        //costoOperativoDetalle1.setCostoOperativo(new CostoOperativo(co1.getNombre(), co1.getDescripcion(), co1.getPrecio(), co1.getUnidadMedida(), 24));
        costoOperativoDetalle1.setCostoOperativo(co1);
        costoOperativoDetalle1.setPrecioCostoOperativo(15000.0);
        costoOperativoDetalles.add(costoOperativoDetalle1);

        Precio precio = new Precio();
        precio.setUnidadesProducidas(10.0);
        precio.setUtilidad(30.0);
        precio.setMateriaPrimaDetalles(materiPrimaDetalles);
        precio.setCostoOperativoDetalles(costoOperativoDetalles);
        mpd.setPrecioProducto(precio);
        costoOperativoDetalle1.setPrecioProducto(precio);
        PrecioService precioService = new PrecioServImpl();
        precioService.insertarPrecio(precio);

        MateriaPrimaDetalleService materiaPrimaDetalleService = new MateriaPrimaDetalleServImpl();
        materiaPrimaDetalleService.insertarMateriaPrimaDetalle(mpd);

        CostoOperativoDetalleService costoOperativoDetalleService = new CostoOperativoDetalleServImpl();
        costoOperativoDetalleService.insertarCostoOperativoDetalle(costoOperativoDetalle1);

        Producto producto = new Producto();
        producto.setDescripcion("");
        producto.setImpuesto(10.0);
        producto.setNombre("Empanada de carne");
        producto.setPrecio(precio);
        producto.setProductoCategoria(categoria1);
        producto.setProductoSubCategoria(productoSubCategoria1);
        producto.setUnidadMedida(umUnidad);
        parametrosService.insertarProducto(producto);
    }
}
