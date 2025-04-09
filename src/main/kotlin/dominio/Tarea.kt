package es.prog2425.taskmanager.Servicios

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.dominio.Estado

class Tarea(descripcion: String, var estado: Estado = Estado.ABIERTA): Actividad(descripcion) {


    companion object{

        /**
         * Funcion para instaciar Tareas nuevas
         * @param descripcion Descripccion de la tarea
         * @param estado Estado de la Tarea Abierta o Cerrada, Default esta Abierta
         */

        fun creaTarea(descripcion: String, estado: Estado = Estado.ABIERTA): Tarea = Tarea(descripcion, estado)

    }

    // Devuelve un String con la descripcion general de la tarea

    /**
     * @return Devuelve un String con todos los parametros de la Tarea
     */

    override fun obtenerDetalle(): String {
        return "Tarea " + super.obtenerDetalle() + " Estado: $estado"
    }

}