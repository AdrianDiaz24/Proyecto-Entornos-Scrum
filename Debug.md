## **ERROR FRAN:**

---

## **ERROR INDA:**

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

---

## **ERROR ADRIÁN:**

