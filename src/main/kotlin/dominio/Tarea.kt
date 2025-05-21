package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.Modelo.Actividad

/**
 * Representa una tarea dentro del gestor de actividades.
 *
 * Una [Tarea] puede tener subtareas y un estado (abierta o cerrada). Hereda de [Actividad].
 *
 * @constructor Crea una nueva instancia de [Tarea] con una descripción y un estado.
 * @param descripcion Descripción de la tarea.
 * @param estado Estado inicial de la tarea (por defecto, [Estado.ABIERTA]).
 */
class Tarea(descripcion: String, var estado: Estado = Estado.ABIERTA): Actividad(descripcion) {

    /**
     * Lista mutable de subtareas asociadas a esta tarea.
     */
    val listaSubtareas = mutableListOf<Tarea>()

    companion object {

        /**
         * Crea una nueva instancia de [Tarea].
         *
         * @param descripcion Descripción de la tarea.
         * @param estado Estado de la tarea (por defecto, [Estado.ABIERTA]).
         * @param etiquetas Etiquetas opcionales separadas por comas para clasificar la tarea.
         * @return Una nueva instancia de [Tarea] con o sin etiquetas añadidas.
         */
        fun creaTarea(descripcion: String, estado: Estado = Estado.ABIERTA, etiquetas: String = ""): Tarea {
            val tarea = Tarea(descripcion, estado)
            if (etiquetas.isNotBlank()) {
                tarea.aniadirEtiquetas(etiquetas)
            }
            return tarea
        }
    }

    /**
     * Devuelve un resumen detallado de la tarea, incluyendo su descripción y estado.
     *
     * @return Cadena con la información completa de la tarea.
     */
    override fun obtenerDetalle(): String {
        return "Tarea " + super.obtenerDetalle() + " - Estado: $estado"
    }

    /**
     * Añade una subtarea a la lista de subtareas de esta tarea.
     *
     * @param tarea Subtarea que se desea añadir.
     */
    fun aniadirSubtarea(tarea: Tarea) {
        listaSubtareas.add(tarea)
    }
}
