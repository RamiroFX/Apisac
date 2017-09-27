/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.util;

/**
 *
 * @author Ramiro
 */
public class AppUIConstants {

    public static final String APP_TITLE = "App";
    public static final String APP_NAME = "app";
    public static final int APP_WINDOWS_SIZE_WIDTH = 800;
    public static final int APP_WINDOWS_SIZE_HEIGHT = 600;
    public static final String TOOL_BAR_NAME = "Barra de Herramientas";
    public static final String TOOL_BAR_TIME_NAME = "Hora actual: ";
    public static final String PREFERENCE_FORM_TITLE = "Preferencias";
    public static String PREFERENCE_LNF = "Look and Feel";
    public static String PREFERENCE_CANCEL_BTN = "Cerrar";
    public static String PREFERENCE_SELECT_BTN = "Seleccionar";
    public static final int PREFERENCE_WINDOWS_SIZE_WIDTH = 400;
    public static final int PREFERENCE_WINDOWS_SIZE_HEIGHT = 200;
    public static final String ALERT_MESSAGE = "Alerta";
    public static final String CONFIRM_MESSAGE = "¿Desea confirmar esta operación?";

    //PANEL PRINCIPAL
    public static final String CREATE_BUTTON_NAME = "Crear producto";
    public static final String MODIFY_BUTTON_NAME = "Modificar producto";
    public static final String DELETE_BUTTON_NAME = "Eliminar producto";
    public static final String EXPORT_BUTTON_NAME = "Exportar producto";
    public static final String PARAM_BUTTON_NAME = "Parametros";
    public static final String RAW_MATERIAL_BUTTON_NAME = "Materia prima";
    public static final String OPERATIVE_COST_BUTTON_NAME = "Costo operativo";
    public static final String SEARCH_BUTTON_NAME = "Buscar";

    //GESTION MATERIA PRIMA
    public static final int RAW_MATERIAL_WINDOWS_SIZE_WIDTH = 500;
    public static final int RAW_MATERIAL_WINDOWS_SIZE_HEIGHT = 600;
    public static final String SEARCH_RAW_MATERIAL_BUTTON_NAME = "Buscar";
    public static final String CREATE_RAW_MATERIAL_BUTTON_NAME = "Crear";
    public static final String UPDATE_RAW_MATERIAL_BUTTON_NAME = "Modificar";
    public static final String DELETE_RAW_MATERIAL_BUTTON_NAME = "Eliminar";
    public static final String SELECT_RAW_MATERIAL_BUTTON_NAME = "Seleccionar";
    public static final String RAW_MATERIAL_TITLE = "Gestión de materia prima";
    public static final String DESCRIPTION_RAW_MATERIAL_PANEL_NAME = "Descripción de materia prima";

    //CREAR /MODIFICAR MATERIA PRIMA
    public static final String RAW_MATERIAL_EXIST_MESSAGE = "Materia prima existente.";
    public static final String RAW_MATERIAL_VALID_POSITIVE_MESSAGE = "Inserte un número válido y positivo en el campo Precio";
    public static final String RAW_MATERIAL_NUMBER_VALID_MESSAGE = "Inserte un número válido en el campo Precio";
    public static final String RAW_MATERIAL_MIN_CHAR_PRICE_MESSAGE = "Inserte 1 caracter por lo menos en el campo Precio";
    public static final String RAW_MATERIAL_MAX_CHAR_DESC_MESSAGE = "Máximo permitido 150 caracteres en el campo Descripción";
    public static final String RAW_MATERIAL_MAX_CHAR_NAME_MESSAGE = "Máximo permitido 30 caracteres en el campo Nombre";
    public static final String RAW_MATERIAL_MIN_CHAR_NAME_MESSAGE = "Inserte 1 caracter por lo menos en el campo Nombre";

    public static final String CREATE_RAW_MATERIAl_FORM_TITLE = "Crear materia prima";
    public static final String UPDATE_RAW_MATERIAl_FORM_TITLE = "Modificar materia prima";
    public static final String ACEPT_RAW_MATERIAl_BUTTON_NAME = "Aceptar";
    public static final String CANCEL_RAW_MATERIAl_BUTTON_NAME = "Cancelar";
    public static final String NAME_RAW_MATERIAL_LABEL = "Nombre";
    public static final String DESCRIPTION_RAW_MATERIAl_BUTTON_NAME = "Descripción";
    public static final String PRECIO_RAW_MATERIAl_BUTTON_NAME = "Precio";
    public static final String UNIT_RAW_MATERIAl_BUTTON_NAME = "Unidad de medida";

    //GESTION COSTO OPERATIVO
    public static final int OPERATIVE_COST_WINDOWS_SIZE_WIDTH = 500;
    public static final int OPERATIVE_COST_WINDOWS_SIZE_HEIGHT = 600;
    public static final String SEARCH_OPERATIVE_COST_BUTTON_NAME = "Buscar";
    public static final String CREATE_OPERATIVE_COST_BUTTON_NAME = "Crear";
    public static final String UPDATE_OPERATIVE_COST_BUTTON_NAME = "Modificar";
    public static final String DELETE_OPERATIVE_COST_BUTTON_NAME = "Eliminar";
    public static final String OPERATIVE_COST_TITLE = "Gestión de costos operativos";
    public static final String DESCRIPTION_OPERATIVE_COST_PANEL_NAME = "Descripción de costos operativos";
    public static final String OPERATIVE_COST_EXIST_MESSAGE = "Existe productos que se encuentran utilizando el costo operativo.";

    //CREAR /MODIFICAR COSTO OPERATIVO
    public static final String OPERATIVE_COST_VALID_POSITIVE_MESSAGE = "Inserte un número válido y positivo en el campo Precio";
    public static final String OPERATIVE_COST_NUMBER_VALID_MESSAGE = "Inserte un número válido en el campo Precio";
    public static final String OPERATIVE_COST_MIN_CHAR_PRICE_MESSAGE = "Inserte 1 caracter por lo menos en el campo Precio";
    public static final String OPERATIVE_COST_MAX_CHAR_DESC_MESSAGE = "Máximo permitido 150 caracteres en el campo Descripción";
    public static final String OPERATIVE_COST_MAX_CHAR_NAME_MESSAGE = "Máximo permitido 30 caracteres en el campo Nombre";
    public static final String OPERATIVE_COST_MIN_CHAR_NAME_MESSAGE = "Inserte 1 caracter por lo menos en el campo Nombre";

    public static final String CREATE_OPERATIVE_COST_FORM_TITLE = "Crear costo operativo";
    public static final String UPDATE_OPERATIVE_COST_FORM_TITLE = "Modificar costo operativo";
    public static final String ACEPT_OPERATIVE_COST_BUTTON_NAME = "Aceptar";
    public static final String CANCEL_OPERATIVE_COST_BUTTON_NAME = "Cancelar";
    public static final String NAME_OPERATIVE_COST_LABEL = "Nombre";
    public static final String DESCRIPTION_OPERATIVE_COST_BUTTON_NAME = "Descripción";
    public static final String PRECIO_OPERATIVE_COST_BUTTON_NAME = "Precio";
    public static final String UNIT_OPERATIVE_COST_BUTTON_NAME = "Unidad de medida";

    //SELECIONAR CANTIDAD MATERIA PRIMA
    public static final int SELECT_RAW_MATERIAL_WINDOWS_SIZE_WIDTH = 500;
    public static final int SELECT_RAW_MATERIAL_WINDOWS_SIZE_HEIGHT = 300;
    public static final String SELECT_RAW_MATERIAl_FORM_TITLE = "Seleccionar cantidad de materia prima";
    public static final String CANT_RAW_MATERIAl_LABEL_NAME = "Cantidad";
    public static final String TOTAL_RAW_MATERIAl_LABEL_NAME = "Total";
    public static final String RAW_MATERIAL_NUMBER_VALID_CANT_MESSAGE = "Inserte un número válido en el campo Cantidad";
    public static final String SELECT_MATERIAL_VALID_POSITIVE_CANT_MESSAGE = "Inserte un número válido y positivo en el campo Cantidad";
    public static final String SELECT_MATERIAL_MIN_CHAR_CANT_MESSAGE = "Inserte 1 caracter por lo menos en el campo Cantidad";
}
