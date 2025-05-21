package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.datos.IUsuarioRepository
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Usuario

/**
 * Repositorio para gestionar usuarios y sus tareas.
 * Implementa las operaciones básicas sobre usuarios y asignación de tareas.
 */
class UsuarioRepository : IUsuarioRepository {

    /** Lista mutable que almacena todos los usuarios creados */
    val usuarios = mutableListOf<Usuario>()

    /**
     * Crea un nuevo usuario con el nombre dado y lo añade a la lista.
     * @param nombre Nombre del usuario a crear.
     * @return true si la creación fue exitosa.
     */
    override fun crearUsuario(nombre: String): Boolean {
        usuarios.add(Usuario.instanciarUsusario(nombre))
        return true
    }

    /**
     * Elimina un usuario de la lista por su nombre.
     * @param nombre Nombre del usuario a eliminar.
     * @return true si se encontró y eliminó el usuario, false si no.
     */
    override fun eliminarUsuarioPorNombre(nombre: String): Boolean {
        val usuarioEliminar: Usuario? = usuarios.find { it.nombre == nombre }

        if (usuarioEliminar != null ) {
            usuarios.remove(usuarioEliminar)
            return true
        } else return false
    }

    /**
     * Devuelve la lista completa de usuarios.
     * @return Lista inmutable de usuarios.
     */
    override fun obtenerTodos(): List<Usuario> {
        return usuarios
    }

    /**
     * Asigna una tarea a un usuario específico.
     * @param usuario Usuario al que se le asigna la tarea.
     * @param tarea Actividad (tarea) que se asigna.
     * @return true si la tarea fue asignada correctamente.
     */
    override fun asignarTarea(usuario: Usuario, tarea: Actividad): Boolean {
        usuario.listaTareas.add(tarea)
        return true
    }
}