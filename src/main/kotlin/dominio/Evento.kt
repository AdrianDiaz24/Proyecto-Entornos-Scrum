package es.prog2425.taskmanager.dominio
import es.prog2425.taskmanager.Modelo.Actividad

/**
 * Representa un evento en el gestor de tareas, con ubicación y fecha específica.
 * @property fecha Fecha del evento en formato DD/MM/AAAA. Valida el patrón [patronFecha].
 * @property ubicacion Lugar donde ocurre el evento (no puede estar vacío).
 * @constructor Crea un evento con [descripcion], [fecha] y [ubicacion].
 * @throws IllegalArgumentException Si la fecha no cumple el formato o la ubicación está vacía.
 */

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
         * @param etiquetas Etiquetas opcionales separadas por comas.
         * @return Nueva instancia de [Evento].
         */

        fun creaEvento(descripcion: String, fecha: String, ubicacion: String, etiquetas: String = ""): Evento {
            val evento = Evento(descripcion, fecha, ubicacion)
            if (etiquetas.isNotBlank()) {
                evento.aniadirEtiquetas(etiquetas)
            }
            return evento
        }

        /**
         * Patrón Regex para validar fechas (DD/MM/AAAA o DD-MM-AAAA).
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