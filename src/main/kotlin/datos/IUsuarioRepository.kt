package es.prog2425.taskmanager.datos

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Usuario

/**
 * Interfaz para la gestión de usuarios en el sistema.
 * Define operaciones básicas para crear, eliminar y gestionar usuarios y sus tareas.
 */
interface IUsuarioRepository {

    /**
     * Crea un nuevo usuario con el nombre especificado.
     *
     * @param nombre Nombre del usuario a crear.
     * @return true si el usuario se creó con éxito, false si ya existe o hay error.
     */
    fun crearUsuario(nombre: String): Boolean

    /**
     * Elimina un usuario basado en su nombre.
     *
     * @param nombre Nombre del usuario a eliminar.
     * @return true si se eliminó correctamente, false si no se encontró o hubo error.
     */
    fun eliminarUsuarioPorNombre(nombre: String): Boolean

    /**
     * Obtiene la lista completa de usuarios registrados.
     *
     * @return Lista inmutable con todos los usuarios.
     */
    fun obtenerTodos() : List<Usuario>

    /**
     * Asigna una tarea (actividad) a un usuario específico.
     *
     * @param usuario Usuario al que se le asigna la tarea.
     * @param tarea Actividad a asignar.
     * @return true si la asignación fue exitosa, false si no.
     */
    fun asignarTarea(usuario: Usuario, tarea: Actividad): Boolean

}