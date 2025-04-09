package es.prog2425.taskmanager.Modelo

import java.time.LocalDate

abstract class Actividad(private val descripcion: String) {

    // Al crear una nueva istancia se comprueba que la descripcion no este vacia sino lanza una execpcion y en caso correcta genera un nuevo ID

    init {
        require(descripcion.isNotBlank()) {throw IllegalArgumentException("La descripcion no puede estar vacia")}
        generarID()
    }

    companion object{

        var id = 0

        /**
         * Funcion que incrementa la ID de la Actividad
         */

        fun generarID(){
            id++
        }

    }

    private val fechaCreacion = LocalDate.now().toString()


    private val id = fechaCreacion.format("YYYYMMDD").replace("-","") + Companion.id

    /**
     * @return Devuelve un String con la descripcion basica de la actividad ID + Descripcion
     */

    open fun obtenerDetalle(): String{
        return  "$id - $descripcion -"
    }

}
