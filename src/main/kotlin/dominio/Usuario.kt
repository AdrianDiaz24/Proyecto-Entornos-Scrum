package es.prog2425.taskmanager.dominio

class Usuario private constructor(val id: Int, val nombre: String) {

    val tareas: Any = Any()
    val listaTareas: MutableList<Actividad> = mutableListOf()

    companion object {
        var contID = 1

        fun instanciarUsusario(nombre: String) = Usuario(contID++, nombre)
    }

    fun obtenerDetalle(): String{
        return  "$id - $nombre"
    }

    fun asignarTarea(tarea: Tarea) {

    }

}