# Documentación con KDoc y Dokka  
## 1. Clases documentadas con Kdoc
- **Evento.kt**:

 ![image](https://github.com/user-attachments/assets/2b107da7-d138-464e-95a2-dfec8864f829)
 ![image](https://github.com/user-attachments/assets/c57d71ef-2736-41df-b9dd-5ffcf75dec34)
  
- **Usuario.kt**:

 ![image](https://github.com/user-attachments/assets/95372daf-5d9f-4725-b1d3-389ee6be9848)
  
- **Tarea.kt**:

 ![image](https://github.com/user-attachments/assets/ca16189e-fa92-454a-b2e9-d7ba7b9a830e)


## 2. Configuración de Dokka  
Para configurar Dokka en el entorno añadimos el siguiente elemento:
```kotlin
// En el archivo build.gradle.kts  
plugins {  
    id("org.jetbrains.dokka") version "1.9.10"  
}

tasks.dokkaHtml {
    outputDirectory.set(file("$buildDir/dokka"))
}
```

Despues de recargar gradle ejecutamos en la terminal el siguiente comando para que se active la tarea de documentacion html de Dokka:
```bash
./gradlew dokkaHtml
```

Al poner este comando se crea dentro de la carpeta build una carpeta llamada dokka donde se encuentra el html con la documentación del proyecto.
