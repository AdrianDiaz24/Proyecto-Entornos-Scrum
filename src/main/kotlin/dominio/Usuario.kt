package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.Modelo.Actividad

/**
 * Representa un usuario del sistema con tareas asignadas.
 * @property id Identificador único autoincremental.
 * @property nombre Nombre del usuario.
 * @property listaTareas Lista mutable de actividades asignadas.
 * @constructor Privado para forzar el uso de [instanciarUsuario].
 */
class Usuario private constructor(val id: Int, val nombre: String) {

    /**
     * Lista de tareas/eventos asignados al usuario.
     */
    val listaTareas: MutableList<Actividad> = mutableListOf()

    companion object {
        /**
         * Contador interno para IDs autoincrementales.
         */
        var contID = 1

        /**
         * Crea una instancia de [Usuario] con ID autoincremental.
         * @param nombre Nombre del usuario (no vacío).
         * @return Nueva instancia de [Usuario].
         */
        fun instanciarUsusario(nombre: String) = Usuario(contID++, nombre)
    }

    /**
     * Devuelve información básica del usuario.
     * @return String con formato: "[id] - [nombre]".
     */
    open fun obtenerDetalle(): String{
        return  "$id - $nombre"
    }
}