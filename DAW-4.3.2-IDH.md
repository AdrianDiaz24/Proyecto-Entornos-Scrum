# Actividad: Aplicación de Code Smells y Patrones de Refactorización en el Código del Task Manager

**ID actividad:** P4.3.2  
**Agrupamiento de la actividad:** Individual

---

## Descripción

Esta actividad consiste en mejorar el código de la aplicación que estás desarrollando, identificando y corrigiendo los *code smells* mediante patrones de refactorización. Además, en al menos una de las refactorizaciones se desarrollarán pruebas unitarias que garanticen que la funcionalidad existente permanece intacta.

---

## Objetivos

- Identificar *code smells* en el código del proyecto que se está desarrollando y asociarlos a patrones de refactorización.
- Aplicar patrones de refactorización adecuados usando las herramientas del IDE.
- Desarrollar y ejecutar pruebas unitarias para cada refactorización realizada.
- Automatizar y documentar el proceso de refactorización y de pruebas.

---

## Trabajo a realizar

### Revisión del código

- Ejecutar el analizador de código del IDE (linter) y/o realizar una revisión manual para localizar *code smells*.
- Localizar al menos **5 code smells** y patrones de refactorización aplicables.

### Aplicación de refactorizaciones

- Seleccionar y aplicar al menos **tres patrones de refactorización distintos**, como por ejemplo:
  - **Extracción de Método**
  - **Introducir Parámetro Objeto**
  - **Simplificar Condicional**
- Usar la funcionalidad de refactorización del IDE: `Refactor > …` para cada cambio.

### Desarrollo de pruebas

- Para cada patrón aplicado, crear o actualizar **pruebas unitarias JUnit** que cubran la funcionalidad refactorizada.
- Al menos un cambio debe incluir pruebas **antes y después** de la refactorización.

### Documentación

- Crear una rama adicional: `P4.3.2-[Iniciales]` (reemplaza `[Iniciales]` por tus iniciales).
- Redactar el archivo `PRO-4.3.2-[Iniciales].md` en la raíz del proyecto que incluya:

  - Descripción de los *code smells* detectados y los patrones aplicados, con **enlaces a los commits correspondientes**.
  - Respuestas a las preguntas 1.a, 1.b, 2.a y 3.a con **capturas de pantalla del IDE**.
  - Lista de pruebas unitarias asociadas a cada refactorización (**clase y método de test**).

---

## Preguntas a responder

# Respuestas a las Preguntas

## [1]

### 1.a ¿Qué *code smell* y patrones de refactorización has aplicado?

He detectado varios *code smells*, entre ellos:

- **Long Parameter List**: solucionado aplicando el patrón *Introduce Parameter Object* en la creación de eventos.
- **Duplicated Code / Complex Conditionals**: refactorizado con el patrón *Extract Method* y simplificación de estructuras `when` en funciones como `cambiarEstado`.

![image](https://github.com/user-attachments/assets/e805427b-cee0-44ca-b6a4-e5c840beb17f)

### 1.b Selecciona un patrón de refactorización que esté cubierto por los tests unitarios. ¿Por qué mejora o no mejora tu código? Asegúrate de poner enlaces a tu código.

El patrón **Introduce Parameter Object** aplicado en `leerDatosEvento()` está cubierto por pruebas unitarias. 

Mejora el código al:

- Facilitar la lectura y mantenimiento.
- Agrupar lógicamente los datos relacionados.
- Simplificar las llamadas a métodos con muchos parámetros.

---

## [2]

### 2.a Describe el proceso que sigues para asegurarte que la refactorización no afecta a código que ya tenías desarrollado.

Sigo este proceso:

1. **Ejecuto todos los tests existentes** antes y después de la refactorización.
2. **Aplico la refactorización paso a paso**, verificando que los cambios no afectan otras partes del sistema.
3. **Uso control de versiones (Git)** para comparar fácilmente los cambios y revertir si es necesario.
4. **Compruebo manualmente los flujos críticos** para validar que el comportamiento sigue siendo el esperado.

---

## [3]

### 3.a ¿Qué funcionalidad del IDE has usado para aplicar la refactorización seleccionada?

He utilizado las siguientes funcionalidades de IntelliJ IDEA:

- **"Refactor > Introduce Parameter Object…"** para agrupar parámetros relacionados.
- **"Extract Method"** para simplificar bloques de código complejos.
- **"Rename" y "Change Signature"** para actualizar nombres y estructuras de métodos de forma segura.

Además, utilicé **"Analyze > Inspect Code"** para detectar automáticamente *code smells* y problemas de estilo.

![image](https://github.com/user-attachments/assets/5fc438bd-be41-4be9-bcc2-3d47e78d1fa7)

---


## 1: Análisis de Code Smells y Patrones de Refactorización en el Código del Task Manager

### Herramienta de Análisis

Se ha utilizado la función “Analyze > Inspect Code…” para realizar un escaneo completo del proyecto.

#### Análisis del Código con “Inspect Code…”

Se ha realizado un análisis completo del proyecto mediante la herramienta **Analyze > Inspect Code…** de IntelliJ IDEA. Este análisis ha permitido identificar advertencias, errores de estilo y problemas potenciales en diferentes partes del código.

### Resultados del Análisis

Se han encontrado los siguientes datos:

- **Total de advertencias:** 29
- **Advertencias leves:** 39
- **Errores de kotlin:** 6
- **Errores de Md:** 12

### Categoría de cada fallo

- **Internationalization:** 12 advertencias por caracteres no ASCII.
- **Java:** 11 advertencias, principalmente por declaraciones redundantes.
- **Kotlin:**
    - 6 advertencias por convenciones de nombres.
    - 5 advertencias por construcciones redundantes.
    - 1 posible bug detectado.
- **Markdown:** Problemas de numeración de listas.

![image](https://github.com/user-attachments/assets/1152a8a8-11b3-469d-bede-e4e956a67204)

![image](https://github.com/user-attachments/assets/68ac6826-26a3-46f4-9931-d8ed24c8b6e4)

![image](https://github.com/user-attachments/assets/cf030b52-7b1e-4d67-8f1f-6b7b92856d30)

## 2: Revisión de Problemas

## Resultados del análisis de código

Tras ejecutar la herramienta **"Inspect Code…"**, se identificaron diversos problemas tanto en el código fuente como en la documentación:

- **Presencia de caracteres no ASCII:** localizados en varias clases, lo cual podría dificultar la adaptación internacional del proyecto.
- **Redundancias en Java y Kotlin:** como métodos que retornan valores constantes o declaraciones superfluas.
- **Uso de construcciones no idiomáticas en Kotlin:** fragmentos de código que pueden reescribirse para una mayor legibilidad o eficiencia.
- **Problemas de estilo y nomenclatura:** especialmente en nombres de clases y funciones en Kotlin, que no siguen las convenciones recomendadas.
- **Errores en archivos Markdown:** incluyendo numeración incorrecta de listas y formato deficiente de tablas.
- **Faltas gramaticales en `.md`:** que afectan negativamente la presentación general del proyecto.
- **Advertencias en scripts Shell:** como uso indebido de comandos POSIX o variables declaradas pero no utilizadas.

Este análisis ha sido útil para detectar áreas con margen de mejora, tanto en términos de calidad del código como en la documentación asociada.

## 3: Aplicación de Refactorizaciones

Como se puede apreciar en las capturas anteriores, la consola es la clase de nuestro trabajo con más fallos, al ser esta la más grande y compleja del mismo, por lo que,
voy a refactorizar 3 funciones de la misma.

### **Refactorización 1: Introducir Objeto Parámetro en `crearActividad` para Eventos**

**Problema:**  
La función `crearActividad` maneja directamente múltiples variables dispersas para crear un `Evento` (`descripcion`, `fecha`, `ubicacion`, `etiquetas`), lo que complica la gestión y aumenta el riesgo de errores.

**Solución:**  
Crear un `data class DatosEvento` para agrupar la información y un método `leerDatosEvento()` para construir ese objeto, simplificando la llamada a `Evento.creaEvento`.

**Ubicación:**  
`Consola.kt`, línea 135.

---

**Código antes:**

```kotlin
    fun crearActividad(input: Int){
        when (input){
            1 -> try {
                actividades.agregarElemento(Tarea.creaTarea(pedirInfoTarea("Introduce la descripcion de la tarea: "), etiquetas = pedirInfoTarea("Introduce etiquetas (separadas por ;)")))
            } catch (e: IllegalArgumentException) {
                println("**ERROR** $e")
            }
            2 -> {
                val (descripcion, fecha, ubicacion, etiquetas) = pedirInfoEvento()
                try {
                    actividades.agregarElemento(Evento.creaEvento(descripcion, fecha, ubicacion, etiquetas))
                } catch (e: IllegalArgumentException) {
                    println("**ERROR** $e")
                }
            }
        }
    }
```

---

**Código después:**

```kotlin
    data class DatosEvento(
        val descripcion: String,
        val fecha: String,
        val ubicacion: String,
        val etiquetas: String
    )

    fun leerDatosEvento(): DatosEvento {
        println("Introduce la descripcion del evento:")
        val descripcion = readLine() ?: ""
        println("Introduce la fecha del evento:")
        val fecha = readLine() ?: ""
        println("Introduce la ubicacion del evento:")
        val ubicacion = readLine() ?: ""
        println("Introduce etiquetas (separadas por ;):")
        val etiquetas = readLine() ?: ""
        return DatosEvento(descripcion, fecha, ubicacion, etiquetas)
    }

    fun crearActividad(input: Int){
        when (input){
            1 -> try {
                actividades.agregarElemento(Tarea.creaTarea(pedirInfoTarea("Introduce la descripcion de la tarea: "), etiquetas = pedirInfoTarea("Introduce etiquetas (separadas por ;)")))
            } catch (e: IllegalArgumentException) {
                println("**ERROR** $e")
            }
            2 -> {
                val datosEvento = leerDatosEvento()
                try {
                    actividades.agregarElemento(Evento.creaEvento(datosEvento.descripcion, datosEvento.fecha, datosEvento.ubicacion, datosEvento.etiquetas))
                } catch (e: IllegalArgumentException) {
                    println("**ERROR** $e")
                }
            }
        }
    }
```

**Test unitario:**

```kotlin
@Test
    fun `leerDatosEvento devuelve el objeto correcto con entrada simulada`() {
        // Simular entradas de usuario como si se escribieran en consola
        val input = """
            Descripción de prueba
            2025-05-21
            Ubicación de prueba
            urgente;personal
        """.trimIndent()

        // Sustituir temporalmente System.in
        val originalIn: InputStream = System.`in`
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val consola = Consola()
        val resultado = consola.leerDatosEvento()

        // Restaurar System.in
        System.setIn(originalIn)

        // Asserts
        assertEquals("Descripción de prueba", resultado.descripcion)
        assertEquals("2025-05-21", resultado.fecha)
        assertEquals("Ubicación de prueba", resultado.ubicacion)
        assertEquals("urgente;personal", resultado.etiquetas)
    }
```

### Refactorización 2: Simplificar condicionales en `cambiarEstado()`

**Problema:**  
La función `cambiarEstado()` tenía múltiples condiciones anidadas y repetidas, lo que dificultaba su lectura y mantenimiento.

**Solución:**
- Uso de `return` temprano para evitar anidaciones innecesarias.
- Cálculo previo de la variable `puedeFinalizar` para no repetir la lógica dentro del `when`.
- Simplificación del `when` eliminando condicionales anidadas.
- Extracción de lógica repetida a una función `cambiarAEstado()`.

**Ubicación:**  
`Consola.kt`, línea ~314

**Código antes:**

```kotlin
private fun cambiarEstado(){
  if (!listarActividades()) return

  println("\nElige una tarea")
  val numActividad = pedirNum(1, actividades.elementos.size) - 1
  val actividad = actividades.elementos[numActividad]

  if (actividad !is Tarea) return

  val estado = pedirEstadoComoInt()

  val puedeFinalizar = actividad.listaSubtareas.isEmpty() ||
          actividad.listaSubtareas.all { it.estado == Estado.FINALIZADA }

  when (estado) {
    1 -> {
      historial.añadirModificacionEstado(Estado.ABIERTA, actividad, numActividad + 1)
      actividad.estado = Estado.ABIERTA
    }
    2 -> {
      historial.añadirModificacionEstado(Estado.EN_PROGRESO, actividad, numActividad + 1)
      actividad.estado = Estado.EN_PROGRESO
    }
    3 -> {
      if (puedeFinalizar) {
        historial.añadirModificacionEstado(Estado.FINALIZADA, actividad, numActividad + 1)
        actividad.estado = Estado.FINALIZADA
      } else {
        println("ERROR: Todas las subtareas tienen que estar marcadas como 'FINALIZADA' antes de finalizar la tarea.")
      }
    }
  }
}
```

**Código después:** 

```kotlin
private fun cambiarEstado() {
  if (!listarActividades()) return

  println("\nElige una tarea")
  val numActividad = pedirNum(1, actividades.elementos.size) - 1
  val actividad = actividades.elementos[numActividad] as? Tarea ?: return

  val estado = pedirEstadoComoInt()
  val puedeFinalizar = actividad.listaSubtareas.isEmpty() ||
          actividad.listaSubtareas.all { it.estado == Estado.FINALIZADA }

  when (estado) {
    1 -> cambiarAEstado(actividad, Estado.ABIERTA, numActividad)
    2 -> cambiarAEstado(actividad, Estado.EN_PROGRESO, numActividad)
    3 -> {
      if (puedeFinalizar) {
        cambiarAEstado(actividad, Estado.FINALIZADA, numActividad)
      } else {
        println("ERROR: Todas las subtareas tienen que estar marcadas como 'FINALIZADA' antes de finalizar la tarea.")
      }
    }
  }
}

private fun cambiarAEstado(tarea: Tarea, nuevoEstado: Estado, num: Int) {
  historial.añadirModificacionEstado(nuevoEstado, tarea, num + 1)
  tarea.estado = nuevoEstado
}
```
**Test unitario:**

```kotlin
class ConsolaTest {

    val consola = object : Consola() {
        override fun pedirNum(min: Int, max: Int): Int {
            return 1 // siempre devuelve 1 para seleccionar la primera opción
        }

        override fun listarActividades(): Boolean {
            return true // para que no retorne falso y no salga temprano
        }

        override fun pedirEstadoComoInt(): Int {
            return 1 // devolver estado abierto sin pedir input
        }
    }
  
    @Test
    fun testCambiarEstadoSimple() {
        consola.actividades.elementos.clear()
        consola.actividades.elementos.add(Tarea("Tarea test"))

        consola.cambiarEstado()

        val tarea = consola.actividades.elementos[0] as? Tarea
        val estado = tarea?.estado
        assertEquals(Estado.ABIERTA, estado)
    }
}
```

### Refactorización 1: Extracción de Método en `cambiarEstado` y `cambiarEstadoSubTarea`

**Problema:**  
Ambos métodos repetían la lógica para leer el nuevo estado de una tarea, rompiendo el principio DRY y dificultando el mantenimiento.

**Solución:**  
Extraer la lógica de lectura a un método `leerEstado()` que centraliza la entrada y mejora la claridad.

**Ubicación:**  
`Consola.kt`, líneas ~320 y ~350

**Código antes:**  

```kotlin
private fun cambiarEstado(){
    // [...]
    println("\n¿Que estado quieres ponerle a la Subtarea?")
    println("1. Abierta")
    println("2. En proceso")
    println("3. Finalizada")

    val estado = pedirNum(1, 3)
// lógica para cambiar estado...
}

fun cambiarEstadoSubTarea() {
// [...]
    println("\n¿Que estado quieres ponerle a la Subtarea?")
    println("1. Abierta")
    println("2. En proceso")
    println("3. Finalizada")

    val estado = pedirNum(1, 3)
// lógica para cambiar estado subtarea...
}
```

**Código después:**

```kotlin
fun pedirEstadoComoInt(): Int {
    println("\n¿Qué estado quieres poner?")
    println("1. Abierta")
    println("2. En proceso")
    println("3. Finalizada")

    return pedirNum(1, 3)
}

fun cambiarEstado() {
    val estado = pedirEstadoComoInt()
// lógica para cambiar estado...
}

fun cambiarEstadoSubTarea() {
    val estado = pedirEstadoComoInt()
// lógica para cambiar estado subtarea...
}
```

**Test unitario:**

```kotlin
    @Test
    fun `leerDatosEvento devuelve el objeto correcto con entrada simulada`() {
        // Simular entradas de usuario como si se escribieran en consola
        val input = """
            Descripción de prueba
            2025-05-21
            Ubicación de prueba
            urgente;personal
        """.trimIndent()

        // Sustituir temporalmente System.in
        val originalIn: InputStream = System.`in`
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val consola = Consola()
        val resultado = consola.leerDatosEvento()

        // Restaurar System.in
        System.setIn(originalIn)

        // Asserts
        assertEquals("Descripción de prueba", resultado.descripcion)
        assertEquals("2025-05-21", resultado.fecha)
        assertEquals("Ubicación de prueba", resultado.ubicacion)
        assertEquals("urgente;personal", resultado.etiquetas)
    }
```
