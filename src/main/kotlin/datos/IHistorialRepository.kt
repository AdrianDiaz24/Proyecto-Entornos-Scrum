package es.prog2425.taskmanager.datos

import es.prog2425.taskmanager.dominio.Estado
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Usuario

interface IHistorialRepository {
    val historial: MutableList<String>
    fun añadirModificacionEstado(estado: Estado, tarea: Tarea, contador1: Int, contador2: Int = 0): Boolean // Como se puede ver esta escrito el nombre de la funcion en lowerCamelCase lo que da como falso positivo es la Ñ por lo cual durante el apartado de tocar la configuracion se tocara
    fun añadirModificacionAsignacion(usuario: Usuario, tarea: Tarea, contador1: Int): Boolean
    fun listarHistorial()
}