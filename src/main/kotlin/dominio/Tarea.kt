package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.Modelo.Actividad

/**
 * Representa una tarea con estado y subtareas.
 * @property estado Estado actual de la tarea ([Estado.ABIERTA] por defecto).
 * @property listaSubtareas Lista de subtareas asociadas.
 * @constructor Crea una tarea con [descripcion] y [estado].
 */
class Tarea(descripcion: String, var estado: Estado = Estado.ABIERTA): Actividad(descripcion) {

    /**
     * Lista de subtareas dependientes de esta tarea.
     */
    val listaSubtareas = mutableListOf<Tarea>()

    companion object{

        /**
         * Funcion para instaciar Tareas nuevas
         * @param descripcion Descripccion de la tarea
         * @param estado Estado de la Tarea Abierta o Cerrada, Default esta Abierta
         * @param etiquetas Etiquetas opcionales separadas por comas.
         * @return Nueva instancia de [Tarea].
         */
        fun creaTarea(descripcion: String, estado: Estado = Estado.ABIERTA,etiquetas: String = ""): Tarea {
            val tarea = Tarea(descripcion, estado)
            if (etiquetas.isNotBlank()) {
                tarea.aniadirEtiquetas(etiquetas)
            }
            return tarea
        }
    }


    /**
     * Devuelve los detalles completos de la tarea.
     * @return String con formato: "Tarea [descripción] - Estado: [estado]".
     */
    override fun obtenerDetalle(): String {
        return "Tarea " + super.obtenerDetalle() + " - Estado: $estado"
    }

    /**
     * Añade una subtarea a la lista.
     * @param tarea Subtarea a agregar.
     */
    fun aniadirSubtarea(tarea : Tarea){
        listaSubtareas.add(tarea)
    }

}