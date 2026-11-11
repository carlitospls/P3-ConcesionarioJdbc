# MAPA · Moverse con Ctrl+Shift+F

Tiene dos partes:

- **A. En tus proyectos de consulta**, que llevan etiquetas.
- **B. En el proyecto del examen**, que no llevará ninguna etiqueta. Esta parte es la que más importa.

---

## A. Etiquetas de los proyectos de consulta

Todas empiezan por `#`. Si buscas `#R3`, salen **todos los pasos del Reto 3, y solo esos**.

| Etiqueta | Reto | Proyectos que la tienen |
|---|---|---|
| `#R4-COLUMNA` | Dato nuevo de punta a punta | ProductosJdbc (stock), BibliotecaJdbc (año del autor y huecos en libro), Tasca-Reto4 (calorías) |
| `#R3-DUPLICADO` | Regla: no repetir | ProductosJdbc (fabricante), Tasca-Reto3 (plato) |
| `#R2-PAGINA` | Pulsar algo → página nueva con sus hijos | ProductosJdbc (productos de un fabricante), Tasca-Reto2 (platos de una categoría) |
| `#R1-CONTAR` | Columna calculada (cuántos) | ProductosJdbc (nº de productos), Tasca-Reto1 (nº de platos) |
| `#X-FILTRO` `#X-ORDEN` `#X-MENSAJE` `#X-EXCEPCION` `#X-REGLA` `#X-CONSULTA` | Huecos sueltos | BibliotecaJdbc (tus marcas `>>> EXAMEN`) |

Si buscas solo `#R`, te salen todos los retos a la vez.

### Del enunciado a la etiqueta

Busca en Ctrl+Shift+F una palabra del enunciado. Te llevará a la línea correspondiente de esta tabla, que te dice qué etiqueta buscar después.

| Si el enunciado dice algo como... | Es | Busca |
|---|---|---|
| "ahora también se guarda / registra", "nuevo dato", "nuevo campo", "nuevo atributo", "cada X tiene ahora", "indicar al crear y al modificar", "obligatorio", "entre N y M", "no negativo", "mostrar en el listado" | Reto 4 | `#R4` |
| "no puede haber dos", "no se puede repetir", "repetido", "duplicado", "único", "ya existe", "mismo nombre", "sin distinguir mayúsculas", "avisar junto al campo", "sin perder lo escrito" | Reto 3 | `#R3` |
| "no se puede borrar si tiene...", "impedir", "solo si" | Reto 3, variante borrado | `#X-REGLA`, y en ProductosJdbc `ConProductos` |
| "al pulsar / hacer clic en", "ver los X de un Y", "sus productos / libros / platos", "detalle", "página propia", "nueva vista", "enlace", "volver" | Reto 2 | `#R2` |
| "cuántos", "número de", "nº de", "total", "contador", "resumen", "incluidos los que no tienen ninguno", "aunque sea 0" | Reto 1 | `#R1` |
| "buscar por", "filtrar", "solo los que" | Filtro | `#X-FILTRO` |
| "ordenado por", "de mayor a menor" | Orden | `#X-ORDEN` |

---

## B. En el proyecto limpio del examen (sin etiquetas)

### El truco principal: busca un dato parecido que ya exista

Para el **Reto 4**, busca con Ctrl+Shift+F el nombre de una columna parecida que ya esté en el proyecto. Por ejemplo, para un número entero busca `precio` o `stock`; para un texto, `nombre`. **Cada resultado es un sitio donde el dato nuevo también tiene que estar**: schema, data, modelo, SELECT, mapRow, DTO, toDto/toModelEntity o mapper, formulario, listado, INSERT y UPDATE. Hasta que no hayas puesto el dato nuevo junto a cada resultado, no has terminado.

Complemento: pon el cursor sobre el campo en el modelo y pulsa **Alt+F7** (Find Usages). Te lista sus usos en Java. Ojo: no ve el SQL ni el HTML, para eso está Ctrl+Shift+F.

### Anclas que hay en cualquier proyecto de la profe

| Busco... | Ctrl+Shift+F |
|---|---|
| La tabla | `CREATE TABLE` |
| Los datos de ejemplo | `INSERT INTO` (en `data.sql`) |
| El SELECT del listado | `SELECT_BASE` (en Biblioteca no existe: busca `SELECT` en `*RepositoryJdbc`) |
| De fila a objeto | `mapRow` |
| Guardar y modificar | `INSERT INTO` y `UPDATE` en los `*Jdbc.java` |
| Del modelo al formulario y vuelta | `toDto` y `toModelEntity` (o `Mapper`) |
| Las validaciones | `message =` |
| Mensajes en fichero aparte | `messages.properties` |
| El formulario | `th:field` |
| El listado | `th:each` |
| Las URLs | `@GetMapping`, `@PostMapping`, `@RequestMapping` |
| Una consulta "¿existe?" para copiar | `SELECT 1` |
| Una excepción para copiar | `extends RuntimeException` |
| Cómo pegar un error a un campo | `rejectValue` |
| Constructores que se rompen al añadir un campo | `new Producto(` (o el modelo que sea) |

**Trucos de la ventana de búsqueda:**

- **File mask**: limita la búsqueda a `*.java`, `*.html` o `*.sql`.
- **Cc**: distingue mayúsculas de minúsculas.
- **W**: busca solo palabras completas.
- **Ctrl+Shift+R**: busca y reemplaza en todo el proyecto. Úsalo con cuidado.
