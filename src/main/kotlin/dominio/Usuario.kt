package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.Modelo.Actividad

/**
 * Representa un usuario del gestor de tareas.
 *
 * Cada [Usuario] tiene un identificador único y un nombre, además de una lista de actividades
 * (tareas o eventos) asociadas. El constructor es privado para garantizar el control
 * de la creación mediante el método [instanciarUsusario].
 *
 * @property id Identificador único del usuario.
 * @property nombre Nombre del usuario.
 * @property listaTareas Lista de actividades asignadas al usuario.
 */
class Usuario private constructor(val id: Int, val nombre: String) {

    /**
     * Lista mutable de actividades (tareas o eventos) asociadas al usuario.
     */
    val listaTareas: MutableList<Actividad> = mutableListOf()

    companion object {
        /**
         * Contador interno para asignar IDs únicos a cada usuario instanciado.
         */
        var contID = 1

        /**
         * Instancia un nuevo [Usuario] con un nombre y un ID autogenerado.
         *
         * @param nombre Nombre del nuevo usuario.
         * @return Objeto [Usuario] con ID único e incrementado automáticamente.
         */
        fun instanciarUsusario(nombre: String) = Usuario(contID++, nombre)
    }

    /**
     * Devuelve una representación textual del usuario.
     *
     * @return Cadena con el ID y nombre del usuario.
     */
    open fun obtenerDetalle(): String {
        return "$id - $nombre"
    }
}
