### Documentación de pruebas unitarias - UsuarioService - Adrian Diaz

**Servicio probado:** `UsuarioService`

**Repositorio mockeado:** `UsuarioRepository`

**Src:** [UsuarioServiceTest.kt](https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/PruebaUnitaria_FranAlbaMu%C3%B1oz/src/test/kotlin/UsuarioServiceTest.kt)

---

### Métodos públicos del servicio

1. `crearUsuario(nombre: String): Boolean`
2. `eliminarUsuarioPorNombre(nombre: String): Boolean`
3. `mostrarTodos(): Unit`
4. `obtenerTodos(): List<Usuario>`
5. `asignarTarea(usuario: Usuario, tarea: Actividad): Boolean`

---

### Tabla de diseño de pruebas

| Método                       | Caso de prueba     | Estado inicial del mock                    | Acción                                   | Resultado esperado                          |
| ---------------------------- | ------------------ |--------------------------------------------|------------------------------------------|---------------------------------------------|
| crearUsuario(nombre)         | Nombre válido      | `crearUsuario("Adrian") -> true`           | Llamar `crearUsuario("Adrian")`          | Retorna `true`                              |
| eliminarUsuarioPorNombre     | Nombre existe      | `eliminarUsuarioPorNombre("Ana") -> true`  | Llamar `eliminarUsuarioPorNombre("Ana")` | Retorna `true`                              |
| eliminarUsuarioPorNombre     | Fallo al eliminar  | `eliminarUsuarioPorNombre("Ana") -> false` | Llamar `eliminarUsuarioPorNombre("Ana")` | Retorna `false`                             |
| mostrarTodos()               | Hay usuarios       | `obtenerTodos() -> lista con usuarios`     | Llamar `mostrarTodos()`                  | Llama `obtenerDetalle()` sobre cada usuario |
| mostrarTodos()               | Lista vacía        | `obtenerTodos() -> lista vacía`            | Llamar `mostrarTodos()`                  | No hace los `println`                       |
| obtenerTodos()               | Hay usuarios       | `obtenerTodos() -> lista con usuarios`     | Llamar `obtenerTodos()`                  | Retorna la lista esperada                   |
| obtenerTodos()               | Lista vacía        | `obtenerTodos() -> lista vacía`            | Llamar `obtenerTodos()`                  | Retorna lista vacía                         |
| asignarTarea(usuario, tarea) | Asignación exitosa | `asignarTarea(...) -> true`                | Llamar `asignarTarea(usuario, tarea)`    | Retorna `true`                              |
| asignarTarea(usuario, tarea) | Fallo al asignar   | `asignarTarea(...) -> false`               | Llamar `asignarTarea(usuario, tarea)`    | Retorna `false`                             |

---

### Ejecución de tests

- **Total de tests:** 9
- **Pasaron:** 9
- **Fallaron:** 0
- **Tiempo de ejecución:** test: \~0.5 s build: \~3 s

---

### Conclusiones

* Todos los métodos del servicio están cubiertos.
* Se han probado tanto los casos esperados como escenarios alternativos.
* El uso de `MockK` y `Spyck` ha permitido aislar completamente el servicio del repositorio.
* El estilo `DescribeSpec` de Kotest ha facilitado la organización clara y legible de los tests.

---

