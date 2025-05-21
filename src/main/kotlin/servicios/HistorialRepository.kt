package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.datos.IHistorialRepository
import es.prog2425.taskmanager.dominio.Estado
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Usuario
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Implementación del repositorio de historial.
 * Guarda y gestiona las modificaciones realizadas sobre tareas y asignaciones.
 *
 * @property historial Lista mutable que almacena las entradas del historial como cadenas.
 */
class HistorialRepository(override val historial: MutableList<String> = mutableListOf()) : IHistorialRepository {

    companion object{
        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:MM:SS")
    }

    /**
     * Añade una entrada al historial cuando se cambia el estado de una tarea.
     *
     * @param estado Nuevo estado asignado a la tarea.
     * @param tarea Tarea modificada.
     * @param contador1 Índice o número principal de la tarea.
     * @param contador2 Índice secundario (por ejemplo, subtarea), 0 si no aplica.
     * @return true si la entrada fue añadida correctamente.
     */
    override fun añadirModificacionEstado(estado: Estado, tarea: Tarea, contador1: Int, contador2: Int): Boolean {
        if (contador2 != 0) {
            historial.add("${LocalDateTime.now().format(formato)} - $contador1.$contador2. ${tarea.obtenerDetalle()} - Cambiado el estado a '$estado'")
        } else {
            historial.add("${LocalDateTime.now().format(formato)} - $contador1. ${tarea.obtenerDetalle()} - Cambiado el estado a '$estado'")
        }
        return true
    }

    /**
     * Añade una entrada al historial cuando se asigna una tarea a un usuario.
     *
     * @param usuario Usuario al que se asigna la tarea.
     * @param tarea Tarea asignada.
     * @param contador1 Índice o número principal de la tarea.
     * @return true si la entrada fue añadida correctamente.
     */
    override fun añadirModificacionAsignacion(usuario: Usuario, tarea: Tarea, contador1: Int): Boolean {
        historial.add("${LocalDateTime.now().format(formato)} - $contador1. ${tarea.obtenerDetalle()} - Tarea asignada a ID: ${usuario.obtenerDetalle()}")
        return true
    }

    /**
     * Muestra por consola todas las entradas almacenadas en el historial.
     */
    override fun listarHistorial() {
        var contador = 0
        for (modificacion in historial){
            contador++
            println("$contador. $modificacion")
        }
    }
}