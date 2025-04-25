package es.prog2425.taskmanager.datos

import es.prog2425.taskmanager.dominio.Estado
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Usuario

interface IHistorialRepository {
    val historial: MutableList<String>
    fun añadirModificacionEstado(estado: Estado, tarea: Tarea, contador1: Int, contador2: Int = 0): Boolean
    fun añadirModificacionAsignacion(usuario: Usuario, tarea: Tarea, contador1: Int): Boolean
    fun listarHistorial()
}