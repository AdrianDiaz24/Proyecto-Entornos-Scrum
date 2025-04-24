package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.Modelo.Actividad

class Usuario private constructor(val id: Int, val nombre: String) {

    val listaTareas: MutableList<Actividad> = mutableListOf()

    companion object {
        var contID = 1

        fun instanciarUsusario(nombre: String) = Usuario(contID, nombre)
    }

    open fun obtenerDetalle(): String{
        return  "$id - $nombre"
    }

}