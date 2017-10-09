insert into unidad_medida_categoria (descripcion) values ('Unidad');
insert into unidad_medida_categoria (descripcion) values ('Masa');
insert into unidad_medida_categoria (descripcion) values ('Volumen');
insert into unidad_medida_categoria (descripcion) values ('Tiempo');
insert into unidad_medida_categoria (descripcion) values ('Longitud');
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (1, 'Unidad', 'Unid', 1.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (2, 'Gramo', 'gr', 1.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (2, 'Kilogramo', 'kg', 1000.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (2, 'Tonelada', 'tn', 1000000.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (3, 'Mililitro', 'ml', 1.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (3, 'Litro', 'l', 1000.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (4, 'Segundo', 'seg', 1.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (4, 'Minuto', 'min', 60.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (4, 'Hora', 'hr', 3600.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (5, 'Milímetro', 'mm', 1.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (5, 'Centímetro', 'cm', 10.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (5, 'Metro', 'mt, 100.0);
insert into unidad_medida (id_um_categoria, nombre, simbolo, valor) values (5, 'Kilometro', 'km', 1000.0);
insert into materia_prima (descripcion, nombre, precio, unidadMedida_id) values ('Harina 000', null, 2500.0, 3);
insert into materia_prima (descripcion, nombre, precio, unidadMedida_id) values ('Agua', null, 2000.0, 6);
insert into materia_prima (descripcion, nombre, precio, unidadMedida_id) values ('Azucar', null, 5500.0, 3);
insert into materia_prima (descripcion, nombre, precio, unidadMedida_id) values ('Sal', null, 2050.0, 3);
insert into materia_prima (descripcion, nombre, precio, unidadMedida_id) values ('Polvo de hornear', null, 2000.0, 3);
insert into materia_prima (descripcion, nombre, precio, unidadMedida_id) values ('Levadura en pan', null, 9000.0, 3);
insert into materia_prima (descripcion, nombre, precio, unidadMedida_id) values ('Huevo', null, 500.0, 1);
insert into costo_operativo ('Jornales y comisiones', 'Jornales y comisiones de comercialización', 11000.0, 1);
insert into costo_operativo ('Gastos de publicidad', null, 1000.0, 1);
insert into costo_operativo ('Panfletos', null, 1000.0, 1);
insert into costo_operativo ('Distribución', null, 1000.0, 1);
insert into costo_operativo ('Encuestas', null, 1000.0, 1);
insert into costo_operativo ('Documentos comerciales', null, 1000.0, 1);
insert into costo_operativo ('Diseño e impresión de logos', null, 1000.0, 1);
insert into costo_operativo ('Mano de obra', null, 11000.0, 9);
insert into costo_operativo ('Alquiler de local', null, 1000.0, 9);
insert into costo_operativo ('Alquiler de maquinarias', null, 1000.0, 9);
insert into costo_operativo ('Energía eléctrica', null, 1000.0,9);
insert into costo_operativo ('Servicio telefónico', null, 1000.0, 9);
insert into costo_operativo ('Servicio de agua', null, 1000.0, 9);
insert into costo_operativo ('Servicio de gas', null, 1000.0, 9);
insert into costo_operativo ('Servicio de internet', null, 1000.0, 9);
insert into productocategoria (descripcion) values ('Sin clasificar');
insert into productocategoria (descripcion) values ('Minuta');
insert into productocategoria (descripcion) values ('Panificado');
insert into productocategoria (descripcion) values ('Almuerzo');
insert into productocategoria (descripcion) values ('Bebida');
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Sin sub-clasificar', 1);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Empanada', 2);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Tarta', 2);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Sandwich', 2);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Salvado', 3);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Seco', 3);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Sandwich', 3);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Sopa', 4);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Ensalada', 4);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Carne', 4);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Jugo', 5);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Café', 5);
insert into productoSubCategoria (descripcion, id_producto_categoria) values ('Té', 5);