# EDES-4.3.1 - Análisis Estático de Código con Detekt y Ktlint

## Descripción de la Actividad

Esta actividad consiste en instalar y usar un analizador de código estático en el proyecto actual (Detekt o Ktlint), capturar evidencias gráficas, detectar y clasificar errores, aplicar soluciones y explorar configuraciones.

---

## Paso 1: Instalación del Analizador de Código

### Herramienta Elegida: Detekt y Ktlint

Ambas herramientas se han integrado en el proyecto para ampliar el análisis estático y la limpieza del código.

### Instalación

Para instalar ambos analizadores de código, añadí los plugins correspondientes en el archivo `build.gradle.kts`. Esto permite integrarlos de forma automática en el flujo de trabajo de Gradle.

**build.gradle.kts:**

```kotlin
plugins {
    kotlin("jvm") version "2.0.20"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

kotlin {
    jvmToolchain(17)
}
```

### Capturas realizadas:

**build.gradle.kts con plugins añadidos:**
![build.gradle.kts con plugins añadidos](docs/BuildGradleCaptura.png)

**terminal con instalación y sincronización exitosa de Gradle:**
![terminal con instalación y sincronización exitosa de Gradle](docs/BuildGradleSincroCaptura.png)

**estructura del proyecto:**
![estructura del proyecto](docs/EstructuraProyectoCaptura.png)

---

## Paso 2: Integración y Ejecución del Análisis

### Ejecución

Desde la terminal del proyecto se han ejecutado los comandos:

```bash
./gradlew detekt
./gradlew ktlintCheck
```

### Detekt

Se detectaron **85 problemas ponderados** que indican errores y malas prácticas.

Incluye problemas de complejidad, nombres incorrectos, funciones largas, imports no usados, falta de documentación y uso de números mágicos.

Es importante revisar el informe generado para corregirlos y mejorar la calidad del código.

```bash
PS C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum> ./gradlew detekt

> Task :detekt FAILED
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:450:17: The function buscarFiltro is too long (118). The maximum length is 60. [LongMethod]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:208:9: The function ejecutarPrograma appears to be too complex based on Cyclomatic Complexity (complexity: 15). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:450:17: The function buscarFiltro appears to be too complex based on Cyclomatic Complexity (complexity: 49). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:134:9: Function crearActividad is nested too deeply. [NestedBlockDepth]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:335:17: Function cambiarEstado is nested too deeply. [NestedBlockDepth]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:380:17: Function cambiarEstadoSubTarea is nested too deeply. [NestedBlockDepth]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:450:17: Function buscarFiltro is nested too deeply. [NestedBlockDepth]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:598:17: Function mostrarEstadisticasTareas is nested too deeply. [NestedBlockDepth]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:9:7: Class 'Consola' with '24' functions detected. Defined threshold inside classes is set to '11' [TooManyFunctions]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:100:22: The caught exception is swallowed. The original exception could be lost. [SwallowedException]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IActividadRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IHistorialRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IHistorialRepository.kt:9:9: Function names should match the pattern: [a-z][a-zA-Z0-9]* [FunctionNaming]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IHistorialRepository.kt:10:9: Function names should match the pattern: [a-z][a-zA-Z0-9]* [FunctionNaming]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IUsuarioRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Estado.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Evento.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Tarea.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Usuario.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\Main.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:654:13: Variable names should match the pattern: [a-z][A-Za-z0-9]* [VariableNaming]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:659:13: Variable names should match the pattern: [a-z][A-Za-z0-9]* [VariableNaming]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\Actividad.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\Actividad.kt:1:1: Package name should match the pattern: [a-z]+(\.[a-z][A-Za-z0-9]*)* [PackageNaming]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\ActividadService.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\HistorialRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\UsuarioRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\UsuarioService.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IActividadRepository.kt:18:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IActividadRepository.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IHistorialRepository.kt:12:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IHistorialRepository.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IUsuarioRepository.kt:12:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IUsuarioRepository.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Estado.kt:11:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Estado.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Evento.kt:39:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Evento.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Evento.kt:8:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Evento.kt:29:13: patronFecha can be a `const val`. [MayBeConst]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Tarea.kt:40:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Tarea.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Usuario.kt:19:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Usuario.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\Main.kt:7:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\Main.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:142:17: The destructuring declaration contains 4 but only 3 are allowed. [DestructuringDeclarationWithTooManyEntries]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:4:1: es.prog2425.taskmanager.servicios.* is a wildcard import. Replace it with fully qualified imports. [WildcardImport]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:5:1: es.prog2425.taskmanager.dominio.* is a wildcard import. Replace it with fully qualified imports. [WildcardImport]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:9:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:137:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:158:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:372:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:416:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:420:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:678:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:68:28: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:78:27: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:149:13: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:168:13: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:222:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:226:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:230:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:234:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:238:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:242:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:246:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:250:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:254:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:258:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:262:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:348:38: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:364:21: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:407:42: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:419:21: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:454:41: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:464:34: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:504:50: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:518:29: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:527:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:552:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:568:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\Actividad.kt:59:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\Actividad.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\Actividad.kt:8:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\ActividadService.kt:10:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\ActividadService.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\HistorialRepository.kt:38:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\HistorialRepository.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\HistorialRepository.kt:19:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\HistorialRepository.kt:21:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\HistorialRepository.kt:27:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\UsuarioRepository.kt:35:2: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\UsuarioRepository.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\test\kotlin\UsuarioServiceTest.kt:128:3: The file C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\test\kotlin\UsuarioServiceTest.kt is not ending with a new line. [NewLineAtEndOfFile]
C:\Users\Fran\Documents\ReposGit\Proyecto-Entornos-Scrum\src\test\kotlin\UsuarioServiceTest.kt:7:1: io.mockk.* is a wildcard import. Replace it with fully qualified imports. [WildcardImport]


FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':detekt'.
> Analysis failed with 85 weighted issues.

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

BUILD FAILED in 3s
1 actionable task: 1 executed
```

### Ktlint

Se encontraron violaciones en el estilo de código:

- Falta de salto de línea al final del archivo.
- Líneas en blanco innecesarias.

Estas violaciones provocaron fallos en la ejecución de ktlint.

Ajustar el formato es necesario para pasar el control de calidad.

* Resultado del análisis Detekt en terminal (muestra de errores).
* Resultado del análisis Ktlint en terminal (muestra de errores).

---

## Paso 3: Identificación y Documentación de Errores

Se han detectado los siguientes errores:

---

### Error 1: Método Demasiado Largo (LongMethod)

**Descripción:**  
El método `buscarFiltro` tiene 118 líneas, excediendo el límite recomendado de 60.

**Solución:**  
Dividir el método en funciones más pequeñas y manejables.

**Ubicación:**  
`Consola.kt` línea 450

**Enlaces:**  
Antes: [commit link]  
Después: [commit link]

---

### Error 2: Complejidad Ciclomática Alta (CyclomaticComplexMethod)

**Descripción:**  
El método `buscarFiltro` tiene una complejidad ciclomática de 49, mucho mayor que el límite recomendado de 15.

**Solución:**  
Refactorizar el método para reducir la cantidad de caminos condicionales, extraer lógica compleja.

**Ubicación:**  
`Consola.kt` línea 450

**Enlaces:**  
Antes: [commit link]  
Después: [commit link]

---

### Error 3: Profundidad de Bloques Anidados Excesiva (NestedBlockDepth)

**Descripción:**  
El método `buscarFiltro` presenta una profundidad de bloque anidado de 7, sobrepasando el máximo permitido de 4.

**Solución:**  
Simplificar el código dividiendo la lógica anidada en funciones auxiliares.

**Ubicación:**  
`Consola.kt` línea 450

**Enlaces:**  
Antes: [commit link]  
Después: [commit link]

---

### Error 4: Demasiadas Funciones en la Clase (TooManyFunctions)

**Descripción:**  
La clase `Consola` tiene 24 funciones, excediendo el límite recomendado de 11.

**Solución:**  
Modularizar la clase, separando responsabilidades en clases o archivos diferentes.

**Ubicación:**  
`Consola.kt` línea 9

**Enlaces:**  
Antes: [commit link]  
Después: [commit link]

---

### Error 5: Excepciones Capturadas y Silenciadas (SwallowedException)

**Descripción:**  
Captura de excepción `IllegalArgumentException` sin tratamiento, ocultando errores reales.

**Solución:**  
Manejar la excepción adecuadamente, añadiendo logging o re-lanzando la excepción.

**Ubicación:**  
`Consola.kt` línea 100

**Enlaces:**  
Antes: [commit link]  
Después: [commit link]

### Capturas recomendadas:

* Informe de errores Detekt.
* Código antes y después de aplicar soluciones (puedes mostrarlo en VSCode o IntelliJ con los cambios marcados).

---

## Paso 4: Exploración de Configuraciones

### Opción modificada: `maxLineLength`

* Se ha cambiado de 120 a 80 caracteres.

```yaml
style:
  MaxLineLength:
    active: true
    maxLineLength: 80
```

### Impacto:

Obliga a reformatear código largo en varias líneas, mejorando la legibilidad.

### Enlaces:

* Antes del cambio: \[commit link]
* Después del cambio: \[commit link]

### Capturas recomendadas:

* Fragmento de archivo `detekt-config.yml` con el cambio.
* Código afectado antes y después del cambio.

---

## Paso 5: Respuestas a las Preguntas

### \[1]

**1.a** He usado Detekt y Ktlint. Sirven para analizar estáticamente el código Kotlin y asegurar buenas prácticas.
**1.b** Revisión de estilo, convenciones de nombres, estructura, comentarios, etc.
**1.c** Beneficios: código más limpio, mantenible y menos propenso a errores.

### \[2]

**2.a** El que más ha mejorado mi código ha sido extraer funciones largas.
**2.b** Sí, me ha parecido lógico y ha facilitado la comprensión.
**2.c** Se produce por acumulación de lógica sin modularizar.

### \[3]

**3.a** Se puede configurar el estilo, longitud de línea, comentarios obligatorios, nombres, etc.
**3.b** He configurado `maxLineLength`.
**3.c** Enlace antes: \[link], enlace después: \[link]. El código es ahora más legible.

### \[4]

Estas herramientas ayudan a mantener el código profesional, homogéneo y preparado para trabajar en equipo o escalar el proyecto.

---

## Paso 6: Conclusiones y Entrega

### Conclusiones

* El uso de analizadores estáticos es muy útil y me ha hecho detectar errores invisibles a simple vista.
* Además, me ha obligado a aplicar buenas prácticas que mejoran la calidad del proyecto.

### Entrega

* URL del repositorio: \[enlace al repo]
* Rama específica: `analisis-codigo`
* Archivo: `EDES-4.3.1-FR.md` (este documento)

---
