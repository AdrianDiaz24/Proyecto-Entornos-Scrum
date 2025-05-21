package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.Modelo.Actividad

/**
 * Representa un evento, que es un tipo de actividad con fecha y ubicación.
 * Implementa la interfaz Detallable para obtener su descripción completa.
 *
 * @property fecha Fecha del evento en formato DD/MM/AAAA o similar.
 * @property ubicacion Lugar donde se realiza el evento.
 *
 * @throws IllegalArgumentException Si la fecha no cumple el formato esperado o la ubicación está vacía.
 */
class Evento(descripcion: String, val fecha: String, val ubicacion: String): Actividad(descripcion), Detallable {

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
        fun creaEvento(descripcion: String, fecha: String, ubicacion: String, etiquetas: String = ""): Evento {
            val evento = Evento(descripcion, fecha, ubicacion)
            if (etiquetas.isNotBlank()) {
                evento.aniadirEtiquetas(etiquetas)
            }
            return evento
        }

        /**
         * Patrón Regex para validar la fecha con formato DD/MM/AAAA o DD-MM-AAAA.
         */
        val patronFecha = "^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})\$"
    }

    /**
     * @return Devuelve un String con todos los parametros del Evento
     */
    override fun obtenerDetalle(): String {
        return "Evento " + super.obtenerDetalle() + " - Fecha: $fecha - Ubicacion: $ubicacion"
    }
}