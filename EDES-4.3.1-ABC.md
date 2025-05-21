# Analizadores de código estático: Linting

## Intalación de Detekt

Instalamos/añadimos en el archivo `build.gradle.kts` las siguientes dependencias y plugins:
```kotlin
plugins{
  id("io.gitlab.arturbosch.detekt") version "1.23.0"
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.mockk:mockk:1.13.8")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}
```

## Ejecutamos Detekt y vemos los errores

Ejecutamos Detekt poniendo en la consola del proyecto el siguiente comando:
```bash
./gradlew detekt
```

Y nos muestra el siguiente mensaje donde podemos ver el analisis que a tenido Detekt sobre el proyecto, dando 69 errores o malas practicas dentro del trabajo.
```bash
> Task :detekt FAILED
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:454:17: The function buscarFiltro is too long (146). The maximum length is 60. [LongMethod]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:211:9: The function ejecutarPrograma appears to be too complex based on Cyclomatic Complexity (complexity: 15). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:454:17: The function buscarFiltro appears to be too complex based on Cyclomatic Complexity (complexity: 49). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:138:9: Function crearActividad is nested too deeply. [NestedBlockDepth]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:342:17: Function cambiarEstado is nested too deeply. [NestedBlockDepth]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:388:17: Function cambiarEstadoSubTarea is nested too deeply. [NestedBlockDepth]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:454:17: Function buscarFiltro is nested too deeply. [NestedBlockDepth]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:625:17: Function mostrarEstadisticasTareas is nested too deeply. [NestedBlockDepth]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:13:7: Class 'Consola' with '24' functions detected. Defined threshold inside classes is set to '11' [TooManyFunctions]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:104:22: The caught exception is swallowed. The original exception could be lost. [SwallowedException]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IActividadRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IHistorialRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IHistorialRepository.kt:9:9: Function names should match the pattern: [a-z][a-zA-Z0-9]* [FunctionNaming]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IHistorialRepository.kt:10:9: Function names should match the pattern: [a-z][a-zA-Z0-9]* [FunctionNaming]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\datos\IUsuarioRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Estado.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Evento.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Tarea.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Usuario.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\Main.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:678:13: Variable names should match the pattern: [a-z][A-Za-z0-9]* [VariableNaming]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:683:13: Variable names should match the pattern: [a-z][A-Za-z0-9]* [VariableNaming]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\Actividad.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\Actividad.kt:1:1: Package name should match the pattern: [a-z]+(\.[a-z][A-Za-z0-9]*)* [PackageNaming]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\ActividadService.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\HistorialRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]        
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\UsuarioRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\UsuarioService.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Evento.kt:8:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\dominio\Evento.kt:29:13: patronFecha can be a `const val`. [MayBeConst]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:146:17: The destructuring declaration contains 4 but only 3 are allowed. [DestructuringDeclarationWithTooManyEntries]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:13:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]      
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:141:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:162:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:379:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:421:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:425:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:702:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:72:28: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]      
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:82:28: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]      
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:153:13: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:171:13: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:225:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:229:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:233:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:237:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:241:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:245:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:249:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:253:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:257:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:261:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:265:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:354:38: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:370:21: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:412:42: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:424:21: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:457:42: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:469:34: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:516:50: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:534:29: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:547:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:573:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\presentacion\Consola.kt:592:17: This expression contains a magic number. Consider defining it to a well named constant. [MagicNumber]     
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\Actividad.kt:8:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]        
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\HistorialRepository.kt:18:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\HistorialRepository.kt:20:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]
C:\Users\UsuarioT\Documents\ReposGit\IntelliJ IDEA\Proyecto-Entornos-Scrum\src\main\kotlin\servicios\HistorialRepository.kt:26:1: Line detected, which is longer than the defined maximum line length in the code style. [MaxLineLength]


FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':detekt'.
> Analysis failed with 69 weighted issues.

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

BUILD FAILED in 1s
1 actionable task: 1 executed
```

## Arreglo y Documentación de Errores

### Primer Error: `InvalidPackageDeclaration`

Este error se debe a que hay paquetes que estas mal declarados dentro del proyecto ya que el paquete no coincide con la localización del archivo.
Para solucionarlo arreglamos la ubicacion del archivo o el paquete que tiene designado:

**Antes**



**Despues**



### Segundo Error: `MayBeConst`

Este error indica que hay una variable que podría ser constante ya que no cambia en ninguna parte del proceso.

**Antes**



**Despues**



### Tercer Error: `FunctionNaming`

Este error se debe a que hay una o varias funciones que tienen un nombre que podria dar problemas o errores, seguramente se deba a que el nombre de la función no esta en formato ASCII.

**Antes**



**Despues**



### Cuarto Error: `MaxLineLength`

Este error indica que hay funciones que superan el maximo que tiene por defecto en Detekt, el maximo indica el numero de lineas maximas que deberia de haber en una misma función.

**Antes**



**Despues**



### Quinto Error: `Magic Number`

Este error indica que hay numeros hardcodeados (escritos directamente en el código) que aparecen sin explicación. Esto hace el código menos legible y más difícil de mantener.

**Antes**



**Despues**



## Respuestas a las preguntas

**[1]**
**1.a ¿Que herramienta has usado, y para que sirve?**



**1.b ¿Cuales son sus características principales?**



**1.c ¿Qué beneficios obtengo al utilizar dicha herramienta?**



**[2]**
**2.a De los errores/problemas que la herramienta ha detectado y te ha ayudado a solucionar, ¿cual es el que te ha parecido que ha mejorado más tu código? **



**2.b ¿La solución que se le ha dado al error/problema la has entendido y te ha parecido correcta?**



**2.c ¿Por qué se ha producido ese error/problema?**



**[3]**
**3.a ¿Que posibilidades de configuración tiene la herramienta? **



**3.b De esas posibilidades de configuración, ¿cuál has configurado para que sea distinta a la que viene por defecto?**



**3.c Pon un ejemplo de como ha impactado en tu código, enlazando al código anterior al cambio, y al posterior al cambio,**



**[4]**
**4 ¿Qué conclusiones sacas después del uso de estas herramientas?**


