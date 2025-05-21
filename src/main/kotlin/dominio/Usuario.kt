package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.Modelo.Actividad

/**
 * Representa un usuario con un identificador único y nombre.
 *
 * @property id Identificador único del usuario.
 * @property nombre Nombre del usuario.
 * @property listaTareas Lista mutable de actividades (tareas, eventos, etc.) asignadas al usuario.
 */
class Usuario private constructor(val id: Int, val nombre: String) {

    val listaTareas: MutableList<Actividad> = mutableListOf()

    companion object {
        var contID = 1

        /**
         * Crea una nueva instancia de Usuario con un ID autoincremental.
         *
         * @param nombre Nombre del usuario a crear.
         * @return Nueva instancia de Usuario.
         */
        fun instanciarUsusario(nombre: String) = Usuario(contID++, nombre)
    }

    /**
     * Devuelve una cadena con el detalle básico del usuario.
     *
     * @return Detalle con ID y nombre.
     */
    open fun obtenerDetalle(): String{
        return  "$id - $nombre"
    }

}