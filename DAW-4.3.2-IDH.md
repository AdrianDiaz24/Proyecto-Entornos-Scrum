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

### [1]

**1.a** ¿Qué *code smell* y patrones de refactorización has aplicado?

**1.b** Teniendo en cuenta aquella funcionalidad que tiene pruebas unitarias, selecciona un patrón de refactorización de los que has aplicado y que están cubiertos por los test unitarios.  
¿Porque mejora o no mejora tu código?  
*Asegúrate de poner enlaces a tu código.*

---

### [2]

**2.a** Describe el proceso que sigues para asegurarte que la refactorización no afecta a código que ya tenías desarrollado.

---

### [3]

**3.a** ¿Qué funcionalidad del IDE has usado para aplicar la refactorización seleccionada?  
*Si es necesario, añade capturas de pantalla para identificar la funcionalidad.*

---


## 1: Análisis de Code Smells y Patrones de Refactorización en el Código del Task Manager

### Herramienta de Análisis

Se ha utilizado la función “Analyze > Inspect Code…” para realizar un escaneo completo del proyecto.

#### Análisis del Código con “Inspect Code…”

Se ha realizado un análisis completo del proyecto mediante la herramienta **Analyze > Inspect Code…** de IntelliJ IDEA. Este análisis ha permitido identificar advertencias, errores de estilo y problemas potenciales en diferentes partes del código.
