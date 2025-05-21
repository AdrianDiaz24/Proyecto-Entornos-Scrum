# Actividad: Analizadores de código estático: Linting (RA4.cd)  
**ID actividad:** 4.3.1.

---

## Agrupamiento de la actividad  
Individual

---

## Descripción  
La actividad consiste en instalar y usar un analizador de código estático (Detekt o Ktlint) en el proyecto que vienes desarrollando, capturar evidencias gráficas, detectar y clasificar errores, aplicar soluciones y explorar las posibilidades de configuración de la herramienta elegida.

Instala y usa los analizadores de código comentados en clase: Detekt, Ktlint.

---

## Objetivo  
- Conocer qué es un analizador de código y su propósito.  
- Familiarizarse con herramientas Detekt o Ktlint.  
- Usar las herramientas y estudiar y aplicar configuraciones de la herramienta seleccionada.

---

## Trabajo a realizar  

Haciendo uso de las herramientas descritas en el punto 4.3 Analizador de código:

1. Instalar la herramienta elegida (Detekt o Ktlint) e incluir capturas de pantalla del proceso.  
2. Integrar el analizador en el proyecto que se está desarrollando y ejecutar el análisis.  
3. Identificar al menos 5 tipos de errores detectados.  
4. Para cada tipo de error, documentar:  
   - Descripción del error.  
   - Solución aplicada (antes y después, con enlaces a commits específicos).  
5. Explorar y modificar al menos una opción de configuración del analizador distinta de la predeterminada; describir cómo afecta al código y por tanto al informe de errores.

---

## Responde a las preguntas

### [1]  
**1.a** ¿Qué herramienta has usado, y para qué sirve?  
**1.b** ¿Cuáles son sus características principales?  
**1.c** ¿Qué beneficios obtengo al utilizar dicha herramienta?

### [2]  
**2.a** De los errores/problemas que la herramienta ha detectado y te ha ayudado a solucionar, ¿cuál es el que te ha parecido que ha mejorado más tu código?  
**2.b** ¿La solución que se le ha dado al error/problema la has entendido y te ha parecido correcta?  
**2.c** ¿Por qué se ha producido ese error/problema?

### [3]  
**3.a** ¿Qué posibilidades de configuración tiene la herramienta?  
**3.b** De esas posibilidades de configuración, ¿cuál has configurado para que sea distinta a la que viene por defecto?  
**3.c** Pon un ejemplo de cómo ha impactado en tu código, enlazando al código anterior al cambio, y al posterior al cambio.

### [4]  
**4** ¿Qué conclusiones sacas después del uso de estas herramientas?












# CAPTURAS DE DESCARGA DE kTLINT
![image](https://github.com/user-attachments/assets/e835eeaa-e2de-4dc7-94b6-bc5d15131bee)

## **Proceso de analisis de KTLINT**




![image](https://github.com/user-attachments/assets/ca7d13e1-6ab5-4d03-a43d-f00b3457d1d8)



## **Analisis exitoso y elección de errores a corregir**



![image](https://github.com/user-attachments/assets/040de8d7-f1bf-435d-b4e1-a8372a583954)



## Informe de errores de ktlint en Proyecto-Entornos-Scrum

**1. Tipo de error:** Parámetro debe iniciar en una nueva línea en la firma de función

* Descripción:
La convención estándar pide que en firmas de funciones con varios parámetros, cada parámetro comience en una línea nueva para mejorar la legibilidad.
* Antes y después

### Código antes 

```kotlin
interface IHistorialRepository {
    val historial: MutableList<String>
    fun añadirModificacionEstado(estado: Estado, tarea: Tarea, contador1: Int, contador2: Int = 0): Boolean
    fun añadirModificacionAsignacion(usuario: Usuario, tarea: Tarea, contador1: Int): Boolean
    fun listarHistorial()
}

```

### Código después
```kotlin
interface IHistorialRepository {
    val historial: MutableList<String>
    fun añadirModificacionEstado(
        estado: Estado,
        tarea: Tarea,
        contador1: Int,
        contador2: Int = 0
    ): Boolean

    fun añadirModificacionAsignacion(
        usuario: Usuario,
        tarea: Tarea,
        contador1: Int
    ): Boolean

    fun listarHistorial()
}

```

Se separa cada parámetro en una línea nueva para mejorar la claridad y cumplir con buena legibilidad.  

Commit importante https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/commit/bb84bb1a9dcc919aef2725818a7e64ebeac7cfb4

**2. Tipo de error:** Importación no usada

* Descripción:
es.prog2425.taskmanager.dominio.Tarea no se usa en la interfaz, por lo que es innecesario y debe eliminarse para mantener el código limpio y evitar advertencias.
* Antes y después

### Código antes

```kotlin
package es.prog2425.taskmanager.datos

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Usuario

interface IUsuarioRepository {
    fun crearUsuario(nombre: String): Boolean
    fun eliminarUsuarioPorNombre(nombre: String): Boolean
    fun obtenerTodos() : List<Usuario>
    fun asignarTarea(usuario: Usuario, tarea: Actividad): Boolean
}

```

### Código después

```kotlin
package es.prog2425.taskmanager.datos

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.dominio.Usuario

interface IUsuarioRepository {

    fun crearUsuario(nombre: String): Boolean

    fun eliminarUsuarioPorNombre(nombre: String): Boolean

    fun obtenerTodos(): List<Usuario>

    fun asignarTarea(
        usuario: Usuario,
        tarea: Actividad
    ): Boolean

}

```
Commit importante https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/commit/09e9383a90abb7d6d04bfc640ae9d7169f69cdda

**3. Tipo de error:** Falta de coma final en la declaración

* Descripción
En Kotlin, cuando una enum class declara múltiples constantes en líneas separadas, es buena práctica (y requerido por Ktlint si se configura así) usar una coma final después del último elemento.

   - Facilita modificaciones futuras (agregar nuevos elementos).
   
   - Reduce errores de sintaxis.
   
   - Mantiene consistencia y legibilidad.
* Antes y después

### Código antes  
```kotlin
enum class Estado(val descripcion: String) {
    ABIERTA("Abierta"),
    EN_PROGRESO("En proceso"),
    FINALIZADA("Finalizada");

    override fun toString(): String {
        return descripcion
    }
}
```
### Código después
```kotlin
enum class Estado(val descripcion: String) {
    ABIERTA("Abierta"),
    EN_PROGRESO("En proceso"),
    FINALIZADA("Finalizada"),  

    ;

    override fun toString(): String {
        return descripcion
    }
}
```
Commit importante
