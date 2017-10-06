/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.ui.inicio;

import com.ferrus.apisac.model.CostoOperativoDetalle;
import com.ferrus.apisac.model.MateriaPrimaDetalle;
import com.ferrus.apisac.model.Producto;
import com.ferrus.apisac.util.AppUIConstants;
import static com.ferrus.apisac.util.AppUIConstants.TABLE_MODEL_CANT;
import static com.ferrus.apisac.util.AppUIConstants.TABLE_MODEL_OPERATIVE_COST;
import static com.ferrus.apisac.util.AppUIConstants.TABLE_MODEL_PRICE;
import static com.ferrus.apisac.util.AppUIConstants.TABLE_MODEL_RAW_MATERIAL;
import static com.ferrus.apisac.util.AppUIConstants.TABLE_MODEL_SUB_TOTAL;
import static com.ferrus.apisac.util.AppUIConstants.TABLE_MODEL_UNIT_MEASURE;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Ramiro Ferreira
 */
public class Exportar {

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private CellStyle styleBold, stylePercentage, styleDouble;
    private File directory;
    private Producto producto;

    public Exportar(Producto producto) {
        this.producto = producto;
        createWorkBook();
        createCellStyles();
    }

    private void createWorkBook() {
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet();
    }

    private void createCellStyles() {
        // FORMAT STYLE
        Font font = workbook.createFont();//Create font
        font.setBold(true);
        styleBold = workbook.createCellStyle();
        styleBold.setAlignment(HorizontalAlignment.CENTER);
        styleBold.setFont(font);//set it to bold

        DataFormat format = workbook.createDataFormat();
        stylePercentage = workbook.createCellStyle();
        stylePercentage.setDataFormat(format.getFormat("#,###.00%"));

        styleDouble = workbook.createCellStyle();
        styleDouble.setDataFormat(format.getFormat("#,###.00"));
    }

    public void exportar() {
        //PREPARAR CONTENIDO
        int fila = 0;//En preparar cuerpo empieza en cero (0).
        String desktop = System.getProperty("user.home") + "\\Desktop";
        JFileChooser chooser = new JFileChooser(desktop);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            directory = chooser.getSelectedFile();
            directory.setWritable(true);
            directory.setExecutable(true);
            directory.setReadable(true);
        } else {
            return;
        }
        //PRODUCT NAME
        Row rowNombreProducto = sheet.createRow(fila);
        rowNombreProducto.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.NAME_LABEL));
        rowNombreProducto.createCell(1).setCellValue(producto.getNombre());
        rowNombreProducto.getCell(0).setCellStyle(styleBold);
        fila++;

        //CATEGORY - SUBCATEGORY
        Row rowCategory = sheet.createRow(fila);
        rowCategory.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_CATEGORY_LABEL));
        rowCategory.createCell(1).setCellValue(producto.getProductoCategoria().getDescripcion());
        rowCategory.getCell(0).setCellStyle(styleBold);
        rowCategory.createCell(2).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_SUB_CATEGORY_LABEL));
        rowCategory.createCell(3).setCellValue(producto.getProductoSubCategoria().getDescripcion());
        rowCategory.getCell(2).setCellStyle(styleBold);
        fila++;

        //DESCRIPTIONM
        Row rowDescription = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 1, 5));
        rowDescription.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.DESCRIPTION_LABEL));
        rowDescription.createCell(1).setCellValue(producto.getDescripcion());
        rowDescription.getCell(0).setCellStyle(styleBold);
        fila++;
        fila++;//SPACE

        /*
        UNIT PRODUCED
         */
        Row rowUnitProduced = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowUnitProduced.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_UNIT_PROD_NAME));
        rowUnitProduced.createCell(2).setCellValue(producto.getPrecio().getUnidadesProducidas());
        rowUnitProduced.createCell(3).setCellValue(new HSSFRichTextString(AppUIConstants.UNIT_MEASURE_LABEL));
        rowUnitProduced.createCell(4).setCellValue(producto.getUnidadMedida().getNombre() + "-" + producto.getUnidadMedida().getSimbolo());
        rowUnitProduced.getCell(0).setCellStyle(styleBold);
        rowUnitProduced.getCell(2).setCellStyle(styleDouble);
        rowUnitProduced.getCell(3).setCellStyle(styleBold);
        fila++;

        /*
        UTILITY
         */
        Row rowUtility = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowUtility.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_UTILITY_PERCENT_LABEL));
        rowUtility.createCell(2).setCellValue(producto.getPrecio().getUtilidad() * 0.01);
        rowUtility.getCell(0).setCellStyle(styleBold);
        rowUtility.getCell(2).setCellStyle(stylePercentage);
        fila++;

        /*
        TAX        
         */
        Row rowTax = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowTax.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_TAX_LABEL));
        rowTax.createCell(2).setCellValue(producto.getImpuesto() * 0.01);
        rowTax.getCell(0).setCellStyle(styleBold);
        rowTax.getCell(2).setCellStyle(stylePercentage);
        fila++;

        /*
        TOTAL PRODUCTION COST
         */
        Row rowTotalProdCost = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowTotalProdCost.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_TOTAL_VAR_COST_PROD_NAME));
        rowTotalProdCost.createCell(2).setCellValue(producto.getPrecio().costoVariableTotal());
        rowTotalProdCost.getCell(0).setCellStyle(styleBold);
        rowTotalProdCost.getCell(2).setCellStyle(styleDouble);
        fila++;

        /*
        TOTAL UNIT PRODUCTION COST
         */
        Row rowTotalUnitProdCost = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowTotalUnitProdCost.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_VAR_COST_UNIT_PROD_NAME));
        rowTotalUnitProdCost.createCell(2).setCellValue(producto.getPrecio().costoProduccionUnitario());
        rowTotalUnitProdCost.getCell(0).setCellStyle(styleBold);
        rowTotalUnitProdCost.getCell(2).setCellStyle(styleDouble);
        fila++;

        /*
        TOTAL OPERATIVE COST
         */
        Row rowTotalOperCost = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowTotalOperCost.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_TOTAL_FIXED_COST_PROD_NAME));
        rowTotalOperCost.createCell(2).setCellValue(producto.getPrecio().costoFijoTotal());
        rowTotalOperCost.getCell(0).setCellStyle(styleBold);
        rowTotalOperCost.getCell(2).setCellStyle(styleDouble);
        fila++;

        /*
        TOTAL UNIT OPERATIVE COST
         */
        Row rowUnitOperCost = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowUnitOperCost.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_FIXED_COST_UNIT_PROD_NAME));
        rowUnitOperCost.createCell(2).setCellValue(producto.getPrecio().costoOperativoUnitario());
        rowUnitOperCost.getCell(0).setCellStyle(styleBold);
        rowUnitOperCost.getCell(2).setCellStyle(styleDouble);
        fila++;

        /*
        TOTAL COST
         */
        Row rowTotalCost = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowTotalCost.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_TOTAL_COST_PROD_NAME));
        rowTotalCost.createCell(2).setCellValue(producto.getPrecio().costoTotal());
        rowTotalCost.getCell(0).setCellStyle(styleBold);
        rowTotalCost.getCell(2).setCellStyle(styleDouble);
        fila++;

        /*
        TOTAL UNIT COST
         */
        Row rowTotalUnitCost = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowTotalUnitCost.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_TOTAL_UNIT_COST_PROD_NAME));
        rowTotalUnitCost.createCell(2).setCellValue(producto.getPrecio().costoTotalUnitario());
        rowTotalUnitCost.getCell(0).setCellStyle(styleBold);
        rowTotalUnitCost.getCell(2).setCellStyle(styleDouble);
        fila++;

        /*
        SELL PRICE WITHOUT TAX
         */
        Row rowSellPriceNoTax = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowSellPriceNoTax.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_SELL_PRICE_LABEL));
        rowSellPriceNoTax.createCell(2).setCellValue(producto.getPrecio().precioVentaSinImpuesto());
        rowSellPriceNoTax.getCell(0).setCellStyle(styleBold);
        rowSellPriceNoTax.getCell(2).setCellStyle(styleDouble);
        fila++;

        /*
        SELL PRICE
         */
        Row rowSellPrice = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, 1));
        rowSellPrice.createCell(0).setCellValue(new HSSFRichTextString(AppUIConstants.CREATE_PRODUCT_SELL_PRICE_LABEL));
        rowSellPrice.createCell(2).setCellValue(producto.getPrecio().precioVentaConImpuesto(producto.getImpuesto()));
        rowSellPrice.getCell(0).setCellStyle(styleBold);
        rowSellPrice.getCell(2).setCellStyle(styleDouble);
        fila++;
        fila++;

        /*
        RAW MATERIAL HEADER        
         */
        Row rowRawMaterialHeader = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 1, 3));
        rowRawMaterialHeader.createCell(1).setCellValue(new HSSFRichTextString(AppUIConstants.RAW_MATERIAL_BUTTON_NAME));
        rowRawMaterialHeader.createCell(2).setCellValue("");
        rowRawMaterialHeader.createCell(3).setCellValue("");
        rowRawMaterialHeader.getCell(1).setCellStyle(styleBold);
        fila++;
        //SUB CABECERA DE ARQUEOS
        Row rowRawMaterialSubHeader = sheet.createRow(fila);
        rowRawMaterialSubHeader.createCell(0).setCellValue(new HSSFRichTextString(TABLE_MODEL_CANT));
        rowRawMaterialSubHeader.createCell(1).setCellValue(new HSSFRichTextString(TABLE_MODEL_UNIT_MEASURE));
        rowRawMaterialSubHeader.createCell(2).setCellValue(new HSSFRichTextString(TABLE_MODEL_RAW_MATERIAL));
        rowRawMaterialSubHeader.createCell(3).setCellValue(new HSSFRichTextString(TABLE_MODEL_PRICE));
        rowRawMaterialSubHeader.createCell(4).setCellValue(new HSSFRichTextString(TABLE_MODEL_SUB_TOTAL));
        fila++;

        for (MateriaPrimaDetalle mpd : producto.getPrecio().getMateriaPrimaDetalles()) {
            Row arqueoCajas = sheet.createRow(fila);
            Double cant = mpd.getCantidad();
            Double price = mpd.getPrecioMateriaPrima();
            Double subTotal = cant * price;
            arqueoCajas.createCell(0).setCellValue(cant);
            arqueoCajas.createCell(1).setCellValue(mpd.getUnidadMedida().getSimbolo());
            arqueoCajas.createCell(2).setCellValue(mpd.getMateriaPrima().getNombre());
            arqueoCajas.createCell(3).setCellValue(price);
            arqueoCajas.createCell(4).setCellValue(subTotal);
        }
        fila++;
        fila++;

        //OPERATIVE COST HEADER
        Row rowOperCostHeader = sheet.createRow(fila);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 1, 3));
        rowOperCostHeader.createCell(1).setCellValue(new HSSFRichTextString(AppUIConstants.OPERATIVE_COST_BUTTON_NAME));
        rowOperCostHeader.createCell(2).setCellValue("");
        rowOperCostHeader.createCell(3).setCellValue("");
        rowOperCostHeader.getCell(1).setCellStyle(styleBold);
        fila++;

        //SUB CABECERA DE ARQUEOS
        Row rowOperCostSubHeader = sheet.createRow(fila);
        rowOperCostSubHeader.createCell(0).setCellValue(new HSSFRichTextString(TABLE_MODEL_CANT));
        rowOperCostSubHeader.createCell(1).setCellValue(new HSSFRichTextString(TABLE_MODEL_UNIT_MEASURE));
        rowOperCostSubHeader.createCell(2).setCellValue(new HSSFRichTextString(TABLE_MODEL_OPERATIVE_COST));
        rowOperCostSubHeader.createCell(3).setCellValue(new HSSFRichTextString(TABLE_MODEL_PRICE));
        rowOperCostSubHeader.createCell(4).setCellValue(new HSSFRichTextString(TABLE_MODEL_SUB_TOTAL));
        fila++;

        for (CostoOperativoDetalle cod : producto.getPrecio().getCostoOperativoDetalles()) {
            Row arqueoCajas = sheet.createRow(fila);
            Double cant = cod.getCantidad();
            Double price = cod.getPrecioCostoOperativo();
            Double subTotal = cant * price;
            arqueoCajas.createCell(0).setCellValue(cant);
            arqueoCajas.createCell(1).setCellValue(cod.getUnidadMedida().getSimbolo());
            arqueoCajas.createCell(2).setCellValue(cod.getCostoOperativo().getNombre());
            arqueoCajas.createCell(3).setCellValue(price);
            arqueoCajas.createCell(4).setCellValue(subTotal);
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);

        //PREPARE DOCUMENT
        try {
            FileOutputStream out = new FileOutputStream(directory.getPath() + ".xls");
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
