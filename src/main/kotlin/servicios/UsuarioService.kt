package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.datos.IActividadRepository
import es.prog2425.taskmanager.datos.IUsuarioRepository
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Usuario

/**
 * Servicio para gestionar operaciones relacionadas con usuarios.
 * Trabaja como capa intermedia que utiliza un repositorio de usuarios.
 */
class UsuarioService(private val usuarios: IUsuarioRepository) {

    /**
     * Crea un nuevo usuario con el nombre dado.
     * @param nombre Nombre del usuario a crear.
     * @return true si la creación fue exitosa.
     */
    fun crearUsuario(nombre: String): Boolean {
        return usuarios.crearUsuario(nombre)
    }

    /**
     * Elimina un usuario identificado por su nombre.
     * @param nombre Nombre del usuario a eliminar.
     * @return true si el usuario fue eliminado, false si no se encontró.
     */
    fun eliminarUsuarioPorNombre(nombre: String): Boolean {
        return usuarios.eliminarUsuarioPorNombre(nombre)
    }

    /**
     * Muestra por consola todos los usuarios registrados.
     */
    fun mostrarTodos() {
        usuarios.obtenerTodos().forEach { usuario: Usuario -> println(usuario.obtenerDetalle()) }
    }

    /**
     * Obtiene una lista con todos los usuarios.
     * @return Lista de usuarios.
     */
    fun obtenerTodos(): List<Usuario> {
        return usuarios.obtenerTodos().toList()
    }

    /**
     * Asigna una tarea a un usuario.
     * @param usuario Usuario al que se le asigna la tarea.
     * @param tarea Actividad a asignar.
     * @return true si la asignación fue exitosa.
     */
    fun asignarTarea(usuario: Usuario, tarea: Actividad): Boolean {
        return usuarios.asignarTarea(usuario, tarea)
    }
}

