### Documentación de pruebas unitarias - UsuarioService - Francisco Alba

**Servicio probado:** `UsuarioService`

**Repositorio mockeado:** `UsuarioRepository` (inyectado vía constructor)

**Src:** [UsuarioServiceTest.kt](https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/PruebaUnitaria_IndalecioDominguezHita/src/test/kotlin/UsuarioServiceTest.kt)

---

### Métodos públicos del servicio

1. `crearUsuario(nombre: String): Boolean`
2. `eliminarUsuarioPorNombre(nombre: String): Boolean`
3. `mostrarTodos(): Unit`
4. `obtenerTodos(): List<Usuario>`
5. `asignarTarea(usuario: Usuario, tarea: Actividad): Boolean`

---

### Tabla de diseño de pruebas

| Método                       | Caso de prueba     | Estado inicial del mock                              | Acción                                      | Resultado esperado                          |
| ---------------------------- | ------------------ | ------------------------------------------           | ----------------------------------------    | ------------------------------------------- |
| crearUsuario(nombre)         | Nombre válido      | `crearUsuario("Inda") -> true`                       | Llamar `crearUsuario("Inda")`               | Retorna `true`                              |
| crearUsuario(nombre)         | Fallo al crear     | `crearUsuario("Inda") -> false`                      | Llamar `crearUsuario("Inda")`               | Retorna `false`                             |
| eliminarUsuarioPorNombre     | Nombre existe      | `eliminarUsuarioPorNombre("Adrian") -> true`         | Llamar `eliminarUsuarioPorNombre("Adrian")` | Retorna `true`                              |
| eliminarUsuarioPorNombre     | Fallo al eliminar  | `eliminarUsuarioPorNombre("Adrian") -> false`        | Llamar `eliminarUsuarioPorNombre("Adrian")` | Retorna `false`                             |
| mostrarTodos()               | Hay usuarios       | `obtenerTodos(Fran, Alejandro) -> lista con usuarios`| Llamar `mostrarTodos(Fran, Alejandro)`      | Llama `obtenerDetalle()`sobre cada usuario  |
| mostrarTodos()               | Lista vacía        | `obtenerTodos() -> lista vacía`                      | Llamar `mostrarTodos()`                     | No llama a `println`                        |
| obtenerTodos()               | Hay usuarios       | `obtenerTodos() -> lista con usuarios`               | Llamar `obtenerTodos()`                     | Retorna la lista esperada                   |
| obtenerTodos()               | Lista vacía        | `obtenerTodos() -> lista vacía`                      | Llamar `obtenerTodos()`                     | Retorna lista vacía                         |
| asignarTarea(usuario, tarea) | Asignación exitosa | `asignarTarea(...) -> true`                          | Llamar `asignarTarea(Fran, tarea)`          | Retorna `true`                              |
| asignarTarea(usuario, tarea) | Fallo al asignar   | `asignarTarea(...) -> false`                         | Llamar `asignarTarea(Fran, tarea)`          | Retorna `false`                             |

---

### Ejecución de tests
En este apartado no puedo verificar que los test pasen ya que el error de IJ no me permite poder realizar las pruebas, pero debería de verse de la siguiente manera:

- **Total de tests:** 10

- **Pasaron:** 10

- **Fallaron:** 0

- **Tiempo de ejecución:** \tiempo en segundos.


---

### Conclusiones

* Gracias a MockK, el servicio fue evaluado de forma aislada sin depender del repositorio real.
* Se organizaron las pruebas de forma clara y comprensible utilizando la sintaxis DescribeSpec de Kotest.
* Los tests contemplan tanto los comportamientos esperados como situaciones en las que algo puede fallar.
* Cada función del servicio ha sido verificada mediante pruebas unitarias específicas.

---

**Observación:** Esta práctica tiene bastante dificultad a la hora de saber como hacer Las Pruebas Unitarias, ya que gradle no es nada intuitivo en este caso y el uso del mockk ha sido un gran problema en mi caso a la hora de mockkear UsuarioRepository.
