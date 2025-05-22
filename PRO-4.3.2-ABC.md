# **1. Análisis de Code Smells y Refactorizaciones**  

## **1.a) Code Smells Identificados y Patrones Aplicados**  

### **Smell 1: Nombres con caracteres no ASCII**  
- **Error:** `añadirModificacionEstado` (utiliza la `ñ`).  
- **Patrón:** *Rename Method* (cambiar el nombre a `anadirModificacionEstado`).  
- **Herramienta del IDE:** `Shift + F6` (Refactor → Rename).  
- **Código Antes/Después:**  
  Antes:

  https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/51a23c6e4d3191f29e85872bc7c5dd1912a7fb33/src/main/kotlin/servicios/HistorialRepository.kt#L26-L29

  Despues:

  https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/d6210ec6f1ac6a0b52d5b8a7ace854ec93f0c047/src/main/kotlin/servicios/HistorialRepository.kt#L26-L29

### **Smell 2: Métodos que siempre retornan `true`**  
- **Error:** `crearUsuario()` en `UsuarioService`.  
- **Patrón:** *Remove Redundancy* (eliminar retorno fijo).  
- **Código Antes/Después:**

  Antes:

  https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/51a23c6e4d3191f29e85872bc7c5dd1912a7fb33/src/main/kotlin/servicios/UsuarioService.kt#L9-L12
  ![image](https://github.com/user-attachments/assets/81c8b378-3bb2-499b-8036-de508ab531b1)

  Despues:

  https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/d6210ec6f1ac6a0b52d5b8a7ace854ec93f0c047/src/main/kotlin/servicios/UsuarioService.kt#L9-L11

### **Smell 3: Propiedades no usadas (`tareas` en `Usuario`)**  
- **Patrón:** *Safe Delete* (eliminar propiedad no usada).  
- **Herramienta IDE:** `Alt + Delete`.  
- **Código Antes/Después:**

  Antes:

  https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/51a23c6e4d3191f29e85872bc7c5dd1912a7fb33/src/main/kotlin/dominio/Usuario.kt#L5
  https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/51a23c6e4d3191f29e85872bc7c5dd1912a7fb33/src/main/kotlin/dominio/Usuario.kt#L18-L20
  ![image](https://github.com/user-attachments/assets/cfd5f3f5-5514-4742-99cb-b4a85c48e455)

  Despues:

  Aqui se han borrado de forma segura sin que afecte a ninguna otra función o clase.

### **Smell 4: Condición redundante (`filtro != 6`)**  
- **Patrón:** *Simplify Conditional* (reemplazar con `when`).  
- **Código Antes/Después:**

  Antes:

  https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/51a23c6e4d3191f29e85872bc7c5dd1912a7fb33/src/main/kotlin/presentacion/Consola.kt#L449-L463

  Despues:

  https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/d6210ec6f1ac6a0b52d5b8a7ace854ec93f0c047/src/main/kotlin/presentacion/Consola.kt#L449-L465

  En este paso aparte de arreglar la condición rebundante tambien e separado en funciones para mayor claridad.

### **Smell 5: Miembros públicos innecesarios**  
- **Error:** `actividades` en `Consola`.  
- **Patrón:** *Encapsulate Field* (hacer `private`).  
- **Herramienta IDE:** `Refactor → Change Signature`.  
- **Código Antes/Después:**

  Antes:

  https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/51a23c6e4d3191f29e85872bc7c5dd1912a7fb33/src/main/kotlin/presentacion/Consola.kt#L8

  Despues:

  https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/d6210ec6f1ac6a0b52d5b8a7ace854ec93f0c047/src/main/kotlin/presentacion/Consola.kt#L8
  
---

## **1.b) Patrón Cubierto por Test***  
**Método refactorizado:** `crearUsuario()`

**¿Por qué mejora el código?**  

Lo mejora porque antes siempre iba a devolver `true` sin importar el resultado de lo que tiene dentro de la función que en este caso es la llamada a una función de un UsuarioRepository que tambien devuelve un boolean. Y ahora devuelve en función de la resolución que haya tenido dentro la función del repositorio.
  
**Test Asociado:**  

Es en UsuarioServiceTest.kt
 https://github.com/AdrianDiaz24/Proyecto-Entornos-Scrum/blob/d6210ec6f1ac6a0b52d5b8a7ace854ec93f0c047/src/test/UsuarioServiceTest.kt#L16-L35

---

# **2. Proceso para Evitar Regresiones**  
1. **Ejecutar pruebas antes de refactorizar:**  
   ```bash
   ./gradlew test  # Verificar estado inicial
   ```  
2. **Refactorizar en pasos pequeños** (ej: un smell a la vez).  
3. **Usar herramientas del IDE:**  
   - *Rename*: `Shift + F6`.  
   - *Safe Delete*: `Alt + Delete`.  
4. **Ejecutar pruebas después de cada cambio:**  
   ```bash
   ./gradlew test --tests "*HistorialRepositoryTest*"
   ```  
5. **Commits frecuentes en Git** para reversiones rápidas.  

---

