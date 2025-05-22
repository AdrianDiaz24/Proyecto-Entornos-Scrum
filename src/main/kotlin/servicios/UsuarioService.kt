package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.datos.IUsuarioRepository
import es.prog2425.taskmanager.dominio.Actividad
import es.prog2425.taskmanager.dominio.Usuario

class UsuarioService(private val usuarios: IUsuarioRepository) {

    fun crearUsuario(nombre: String): Boolean {
        return usuarios.crearUsuario(nombre)
    }

    fun eliminarUsuarioPorNombre(nombre: String): Boolean {
        return usuarios.eliminarUsuarioPorNombre(nombre)
    }

    fun mostrarTodos() {
        usuarios.obtenerTodos().forEach { usuario: Usuario -> println(usuario.obtenerDetalle()) }
    }

    fun obtenerTodos(): List<Usuario> {
        return usuarios.obtenerTodos().toList()
    }

    fun asignarTarea(usuario: Usuario, tarea: Actividad): Boolean {
        return usuarios.asignarTarea(usuario, tarea)
    }

}

