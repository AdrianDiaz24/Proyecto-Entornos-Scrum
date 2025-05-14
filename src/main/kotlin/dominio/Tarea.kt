package es.prog2425.taskmanager.dominio

class Tarea(descripcion: String, var estado: Estado = Estado.ABIERTA): Actividad(descripcion) {

    val listaSubtareas = mutableListOf<Tarea>()

    companion object{

        /**
         * Funcion para instaciar Tareas nuevas
         * @param descripcion Descripccion de la tarea
         * @param estado Estado de la Tarea Abierta o Cerrada, Default esta Abierta
         */

        fun creaTarea(descripcion: String, estado: Estado = Estado.ABIERTA,etiquetas: String = ""): Tarea {
            val tarea = Tarea(descripcion, estado)
            if (etiquetas.isNotBlank()) {
                tarea.aniadirEtiquetas(etiquetas)
            }
            return tarea
        }
    }

    // Devuelve un String con la descripcion general de la tarea

    /**
     * @return Devuelve un String con todos los parametros de la Tarea
     */

    override fun obtenerDetalle(): String {
        return "Tarea " + super.obtenerDetalle() + " - Estado: $estado"
    }

    fun aniadirSubtarea(tarea : Tarea){
        listaSubtareas.add(tarea)
    }

}