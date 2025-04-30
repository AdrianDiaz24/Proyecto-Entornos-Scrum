## ERROR FRAN #1: Uso incorrecto de `forEach`

### Descripción del fallo
Al filtrar por etiquetas, cuando una tarea tiene una etiqueta coincidente, se imprime toda la lista de tareas, no solo la coincidencia actual. El código que provoca esta situación es:

```kotlin
if (actividades.elementos.any { it is Tarea }) {
                        print("Introduce la etiqueta: ")
                        val filtro = readln().lowercase()

                        for(elemento in actividades.elementos) {
                            if (elemento is Tarea && elemento.adquirirEtiquetas().any { it.lowercase() == filtro }) {
                                tareas.forEach { salida(it.obtenerDetalle()) }
                            } else salida("ERROR: No se encontró ninguna etiqueta.")
                        }
} else salida("No existen tareas creadas.")
```

Realmente, ese fragmento de código lo que está evaluando es  “si encuentro una coincidencia, imprime todas las tareas”, cuando debería evaluar: “si esta tarea es una coincidencia, imprímela”.

### Técnica de depuración aplicada

**Herramienta utilizada**: Depurador de IntelliJ IDEA.

#### Breakpoints:

- Línea dentro del bucle `for`, justo antes del `if`.
  
![image](https://github.com/user-attachments/assets/a8c1930e-6ed5-48d8-b268-3b3913e24524)

- Dentro del bloque `if`, para ver cuándo se cumple la condición.

![image](https://github.com/user-attachments/assets/a58e488e-5cd4-4127-82e3-ff2caac571cf)

#### Inspección de variables:

- Se inspeccionó la colección `tareas` y se observó que se estaba imprimiendo completa.
  
![image](https://github.com/user-attachments/assets/8eb07816-ce5c-4da9-b4e6-321d7020e719)

- Se inspeccionó la variable `elemento` y el contenido de `elemento.adquirirEtiquetas()` para comprobar la coincidencia.

![image](https://github.com/user-attachments/assets/1ad5313b-4234-4967-ac7a-54b67ee665fe)

#### Sistema de registro (logging):

- **Uso de `tareas.forEach { salida(it.obtenerDetalle()) }` como método de logging**: Esta línea estaba causando que, cada vez que se encontraba una etiqueta coincidente, se imprimieran todas las tareas, en lugar de solo la tarea que coincidia con el filtro. Esto se convirtió en un método de logging para intentar entender por qué se estaban imprimiendo todas las tareas. De manera temporal, se imprimieron todas las tareas dentro del bloque `forEach` para observar qué estaba ocurriendo durante la ejecución.

### Solución dada

Este error puede ser fácilmente solventado modificando ese fragmento de código y sustituyéndolo por el siguiente:

```kotlin
var encontrada = false
for (elemento in actividades.elementos) {
    if (elemento is Tarea && elemento.adquirirEtiquetas().any { it.lowercase() == filtro }) {
        salida(elemento.obtenerDetalle())
        encontrada = true
    }
}

if (!encontrada) salida("ERROR: No se encontró ninguna etiqueta.")
```

De esta manera, se declara una variable 'encontrada' que se inicializa con un valor 'false'. Tras esto, se busca entre todas las tareas la que tiene la etiqueta que se usa como filtro y, si la encuentra, obtiene el detalle de esta y lo imprime.  Con este cambio en el código, el problema queda solucionado.

### ERROR FRAN #2

**ERROR INDA:**

![image](https://github.com/user-attachments/assets/88acae97-c785-431b-b757-64506e8defa9)

![image](https://github.com/user-attachments/assets/beeb19e7-caab-4d93-82ac-9c6f391d1e33)

![image](https://github.com/user-attachments/assets/b0d93132-b445-4d4f-96a6-31d6f7d5fdbb)

![image](https://github.com/user-attachments/assets/39c7fd29-0639-45ab-a52a-6ff31bc25215)

**ERROR ADRIÁN:**

---

## **ERROR ALEJANDRO:**
Planteamos el problema de nuestro código con la siguente imagen:

![image](https://github.com/user-attachments/assets/bb387c53-d404-4f1c-8ded-f97621f352ae)
- Aqui tenemos un error sin solucionar que hace que el programa funcione de una manera indebida a la que deberia.
- La manera en la que actua indebidamente es que al no tener actividades que mostrar dice que no hay actividades pero despues te mete en un bucle ya que te pide que metas el numero de una actividad de 1 a 0 y se queda en ese bucle infinito.


Al depurar nos damos cuenta de que en la función de listarActividades esta devuelve un valor booleano por si no tiene actividades.

![image](https://github.com/user-attachments/assets/215d8362-ae19-4160-a467-692850db14fe)

Ahora lo solucionamos dandole una variable al valor que devuelve la funcion y poniendo un if para que actue en consecuencia de esa variable.

![image](https://github.com/user-attachments/assets/cd4c29f8-59ca-4927-a06a-cba4a1decdcf)

- Para poder depurar utilizamos como se ve en las imágenes, utilizamos breakpoints para poder ir al grano en nuestro problema y atacar el error de manera directa. 

- Como se puede apreciar en la primera captura de pantalla, hacemos uso de ToDo. Este se añadió cuando se empezó a programar el código y ahora en la fase de debug gracias a él podemos entender directamente el error sin necesidad de tener que intrepretar de nuevo el código.

- Como resultado de este error, ahora usamos con mucha más frecuencia herramientas que nos ayuden a poder corregir nuestros fallos y agilicen nuestro trabajo.
