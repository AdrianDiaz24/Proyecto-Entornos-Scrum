package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.Modelo.Actividad


/**
 * Representa una tarea, que es una actividad con estado y posible lista de subtareas.
 * Implementa la interfaz Detallable para obtener su descripción completa.
 *
 * @property estado Estado actual de la tarea, por defecto ABIERTA.
 * @property listaSubtareas Lista mutable de subtareas asociadas.
 */
class Tarea(descripcion: String, var estado: Estado = Estado.ABIERTA) : Actividad(descripcion), Detallable {

    val listaSubtareas = mutableListOf<Tarea>()

    companion object {

        /**
         * Crea una nueva instancia de Tarea, opcionalmente con estado y etiquetas.
         *
         * @param descripcion Descripción de la tarea.
         * @param estado Estado inicial de la tarea, por defecto ABIERTA.
         * @param etiquetas Etiquetas opcionales para la tarea.
         * @return Nueva instancia de Tarea creada.
         */
        fun creaTarea(descripcion: String, estado: Estado = Estado.ABIERTA, etiquetas: String = ""): Tarea {
            val tarea = Tarea(descripcion, estado)
            if (etiquetas.isNotBlank()) {
                tarea.aniadirEtiquetas(etiquetas)
            }
            return tarea
        }
    }

    // Devuelve un String con la descripcion general de la tarea

    /**
     * Devuelve una cadena con los detalles completos de la tarea, incluyendo descripción y estado.
     *
     * @return Detalle completo de la tarea.
     */
    override fun obtenerDetalle(): String {
        return "Tarea " + super.obtenerDetalle() + " - Estado: $estado"
    }

    /**
     * Añade una subtarea a la lista de subtareas de esta tarea.
     *
     * @param tarea Subtarea a añadir.
     */
    fun aniadirSubtarea(tarea: Tarea) {
        listaSubtareas.add(tarea)
    }
}