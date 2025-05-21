package es.prog2425.taskmanager.datos

import es.prog2425.taskmanager.dominio.Estado
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Usuario

/**
 * Interfaz para gestionar el historial de modificaciones en el sistema.
 * Permite almacenar y registrar cambios relacionados con tareas y usuarios.
 */
interface IHistorialRepository {

    /**
     * Lista mutable que almacena las entradas del historial como cadenas de texto.
     */
    val historial: MutableList<String>

    /**
     * Añade una entrada al historial cuando se modifica el estado de una tarea.
     *
     * @param estado Nuevo estado de la tarea.
     * @param tarea La tarea que ha cambiado de estado.
     * @param contador1 Contador auxiliar (puede ser usado para referencias internas).
     * @param contador2 Contador auxiliar opcional, con valor por defecto 0.
     * @return true si la modificación se añadió correctamente, false en caso contrario.
     */
    fun añadirModificacionEstado(estado: Estado, tarea: Tarea, contador1: Int, contador2: Int = 0): Boolean

    /**
     * Añade una entrada al historial cuando se asigna un usuario a una tarea.
     *
     * @param usuario Usuario asignado a la tarea.
     * @param tarea La tarea a la que se asigna el usuario.
     * @param contador1 Contador auxiliar (uso interno).
     * @return true si la asignación se añadió correctamente, false en caso contrario.
     */
    fun añadirModificacionAsignacion(usuario: Usuario, tarea: Tarea, contador1: Int): Boolean

    /**
     * Muestra o lista el historial completo de modificaciones registradas.
     */
    fun listarHistorial()
}