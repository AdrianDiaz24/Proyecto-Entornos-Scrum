package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.datos.IUsuarioRepository
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Usuario

class UsuarioRepository : IUsuarioRepository {
    val usuarios = mutableListOf<Usuario>()

    override fun crearUsuario(nombre: String): Boolean {
        usuarios.add(Usuario.instanciarUsusario(nombre))
        return true
    }

    override fun eliminarUsuarioPorNombre(nombre: String): Boolean {
        val usuarioEliminar: Usuario? = usuarios.find { it.nombre == nombre }

        if (usuarioEliminar != null ) {
            usuarios.remove(usuarioEliminar)
            return true
        } else return false
    }

    override fun obtenerTodos(): List<Usuario> {
        return usuarios
    }

    override fun asignarTarea(usuario: Usuario, tarea: Actividad): Boolean {
        usuario.listaTareas.add(tarea)
        return true
    }


}