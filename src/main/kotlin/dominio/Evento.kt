package es.prog2425.taskmanager.dominio

import es.prog2425.taskmanager.Modelo.Actividad

/**
 * Clase que representa un Evento dentro del gestor de tareas.
 *
 * Un Evento es una subclase de [Actividad] que incluye una fecha y una ubicación específicas.
 * Se valida que la fecha tenga un formato correcto y que la ubicación no esté vacía.
 *
 * @constructor Crea un nuevo Evento con una descripción, fecha y ubicación.
 * @param descripcion Descripción del evento.
 * @param fecha Fecha en la que se realiza el evento, en formato DD/MM/AAAA.
 * @param ubicacion Lugar donde se lleva a cabo el evento.
 * @throws IllegalArgumentException si la fecha no cumple el formato o la ubicación está vacía.
 */
class Evento(descripcion: String, val fecha: String, val ubicacion: String): Actividad(descripcion) {

    init {
        require(fecha.matches(Regex(patronFecha))) {
            throw IllegalArgumentException("La fecha no cumple el formato DD/MM/AAAA")
        }
        require(ubicacion.isNotBlank()) {
            throw IllegalArgumentException("La ubicacion no puede estar vacía")
        }
    }

    companion object {

        /**
         * Expresión regular que define el formato de fecha aceptado: DD/MM/AAAA o DD-MM-AAAA.
         */
        val patronFecha = "^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$"

        /**
         * Crea una nueva instancia de [Evento].
         *
         * @param descripcion Descripción del evento.
         * @param fecha Fecha del evento en formato DD/MM/AAAA o DD-MM-AAAA.
         * @param ubicacion Ubicación donde ocurre el evento.
         * @param etiquetas Etiquetas opcionales separadas por comas para clasificar el evento.
         * @return Una nueva instancia de [Evento] con o sin etiquetas añadidas.
         */
        fun creaEvento(descripcion: String, fecha: String, ubicacion: String, etiquetas: String = ""): Evento {
            val evento = Evento(descripcion, fecha, ubicacion)
            if (etiquetas.isNotBlank()) {
                evento.aniadirEtiquetas(etiquetas)
            }
            return evento
        }
    }

    /**
     * Devuelve un resumen detallado del evento, incluyendo la descripción, fecha y ubicación.
     *
     * @return Cadena con la información completa del evento.
     */
    override fun obtenerDetalle(): String {
        return "Evento " + super.obtenerDetalle() + " - Fecha: $fecha - Ubicacion: $ubicacion"
    }
}
