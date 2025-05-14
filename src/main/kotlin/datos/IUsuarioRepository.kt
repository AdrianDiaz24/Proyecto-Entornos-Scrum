package es.prog2425.taskmanager.datos


import es.prog2425.taskmanager.dominio.Usuario
import es.prog2425.taskmanager.dominio.Actividad

interface IUsuarioRepository {
    fun crearUsuario(nombre: String): Boolean
    fun eliminarUsuarioPorNombre(nombre: String): Boolean
    fun obtenerTodos() : List<Usuario>
    fun asignarTarea(usuario: Usuario, tarea: Actividad): Boolean
}