package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.Modelo.Actividad

class Evento(descripcion: String, val fecha: String, val ubicacion: String): Actividad(descripcion) {

    init {
        require(fecha.matches(Regex(patronFecha))) { throw IllegalArgumentException("La fecha no cumple el formato DD/MM/AAAA")}
        require(ubicacion.isNotBlank()) {throw IllegalArgumentException("La ubicacion no puede estar vacia")}
    }

    companion object{

        /**
         * Crear una nueva istancia de un evento
         * @param descripcion Descripcion del Evento
         * @param fecha Fecha de cuando trascurre el evento
         * @param ubicacion Ubiaccion donded trascurre el evento
         */

        fun creaEvento(descripcion: String, fecha: String, ubicacion: String): Evento = Evento(descripcion, fecha, ubicacion)


        val patronFecha = "^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})\$"

    }

    /**
     * @return Devuelve un String con todos los parametros del Evento
     */

    override fun obtenerDetalle(): String {
        return "Evento " + super.obtenerDetalle() + " Fecha: $fecha, Ubicacion: $ubicacion"
    }

}