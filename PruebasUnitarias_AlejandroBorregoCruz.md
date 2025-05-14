# Diseño de Casos de Prueba - UsuarioService

**Servicio probado:** `UsuarioService`

**Repositorio mockeado:** `UsuarioRepository` (inyectado vía constructor)

## Método: `crearUsuario`

| Caso de Prueba       | Estado Inicial del Mock                          | Acción                          | Resultado Esperado                          |
|-----------------------|--------------------------------------------------|---------------------------------|---------------------------------------------|
| Nombre válido         | `mockRepo.crearUsuario("nombre")` no hace nada  | `servicio.crearUsuario("nombre")` | Retorna `true`; verifica llamada a `mockRepo.crearUsuario` |
| Nombre vacío          | `mockRepo.crearUsuario("")` lanza `IllegalArgumentException` | `servicio.crearUsuario("")` | Lanza `IllegalArgumentException` |

## Método: `eliminarUsuarioPorNombre`

| Caso de Prueba       | Estado Inicial del Mock                                  | Acción                                  | Resultado Esperado                          |
|-----------------------|----------------------------------------------------------|-----------------------------------------|---------------------------------------------|
| Nombre existente      | `mockRepo.eliminarUsuarioPorNombre("nombre")` no hace nada | `servicio.eliminarUsuarioPorNombre("nombre")` | Retorna `true`; verifica llamada a `mockRepo.eliminarUsuarioPorNombre` |
| Nombre inexistente    | `mockRepo.eliminarUsuarioPorNombre("nombre")` lanza `NoSuchElementException` | `servicio.eliminarUsuarioPorNombre("nombre")` | Lanza `NoSuchElementException` |

## Método: `mostrarTodos`

| Caso de Prueba           | Estado Inicial del Mock                                      | Acción                  | Resultado Esperado                  |
|---------------------------|--------------------------------------------------------------|-------------------------|-------------------------------------|
| Repositorio con usuarios  | `mockRepo.obtenerTodos()` retorna `[Usuario("A"), Usuario("B")]` | `servicio.mostrarTodos()` | Verifica llamada a `mockRepo.obtenerTodos()` |
| Repositorio vacío         | `mockRepo.obtenerTodos()` retorna lista vacía                | `servicio.mostrarTodos()` | Verifica llamada a `mockRepo.obtenerTodos()` |

## Método: `obtenerTodos`

| Caso de Prueba           | Estado Inicial del Mock                                      | Acción                  | Resultado Esperado                  |
|---------------------------|--------------------------------------------------------------|-------------------------|-------------------------------------|
| Repositorio con usuarios  | `mockRepo.obtenerTodos()` retorna `[Usuario("A"), Usuario("B")]` | `servicio.obtenerTodos()` | Retorna la misma lista de usuarios  |
| Repositorio vacío         | `mockRepo.obtenerTodos()` retorna lista vacía                | `servicio.obtenerTodos()` | Retorna lista vacía                 |

## Método: `asignarTarea`

| Caso de Prueba           | Estado Inicial del Mock                                  | Acción                              | Resultado Esperado                          |
|---------------------------|----------------------------------------------------------|-------------------------------------|---------------------------------------------|
| Usuario y tarea válidos   | `mockRepo.asignarTarea(usuario, tarea)` retorna `true`   | `servicio.asignarTarea(usuario, tarea)` | Retorna `true`; verifica llamada a `mockRepo.asignarTarea` |
| Usuario no existe         | `mockRepo.asignarTarea(usuario, tarea)` retorna `false`  | `servicio.asignarTarea(usuario, tarea)` | Retorna `false`                             |

---

### Ejecución de tests

**Total de tests:** 10
**Pasaron:** 10
**Fallaron:** 0
**Tiempo de ejecución:** 0.6s

---

### Conclusiones

* Todos los métodos públicos de UsuarioService fueron probados con casos positivos y negativos.
* Se verificó tanto el valor de retorno como las interacciones con el repositorio mockeado.
* El uso de MockK garantiza que no hay dependencias externas en las pruebas.
* La estructura DescribeSpec permite fácil expansión para nuevos casos de prueba.

---