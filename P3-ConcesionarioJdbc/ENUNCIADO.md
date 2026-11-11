# P3 · AutoNova — RETO 2 (consulta nueva + página nueva)

**Tiempo objetivo:** 30 minutos. Apunta la hora de inicio.
**Puedes usar:** la guía, `MAPA.md`, las plantillas `dw…`, y como consulta `Tasca-Reto2-PaginaNueva`
o `ProductosJdbc-retos-resueltos` (busca `#R2`).
**Sin pedirme ayuda** hasta terminar (salvo 10 minutos atascado en el mismo error).

## Qué se pide

Los comerciales quieren ver rápido qué coches tiene cada marca. En el listado de marcas,
el nombre de cada marca pasa a ser un enlace. Al pulsarlo se abre una página propia con el
título **"Coches de &lt;nombre de la marca&gt;"** y una tabla con sus coches (nombre y precio),
**del más barato al más caro**.

Si la marca no tiene coches, en la tabla aparece *"Esta marca no tiene coches"*.
La página tiene un botón para volver al listado de marcas. Si alguien escribe a mano la
dirección de una marca que no existe, se muestra la página de error "No encontrado".

## Comprobaciones (TODAS a mano, con la app arrancada, leyendo cada línea entera)

- [ ] En Marcas, los nombres son enlaces.
- [ ] Pulsar *Toyota* → título "Coches de Toyota" y, **en este orden**: Yaris 19500.00 €, Corolla 27900.00 €, C-HR 31200.00 €.
- [ ] Pulsar *Lancia* → título "Coches de Lancia" y el mensaje "Esta marca no tiene coches".
- [ ] Botón Volver → listado de marcas.
- [ ] Escribir `localhost:8080/marcas/99/coches` → página "No encontrado".
- [ ] Lo que ya funcionaba sigue igual: listado de coches, editar un coche, crear un coche.

## Extra (solo si te sobra tiempo)

En la página de la marca, cada coche tiene un enlace **Editar** que lleva a su formulario
de edición (el mismo que el del listado de coches).

## Al terminar, apunta

- Cuánto has tardado (reto + comprobaciones).
- Qué archivos has tocado y en qué orden.
- Dónde te has atascado.
