package es.prog2425.taskmanager.dominio

/**
 * Representa los datos básicos de un evento.
 *
 * @property descripcion Texto que describe el evento.
 * @property fecha Fecha en formato String (ej. "dd/mm/yyyy") del evento.
 * @property ubicacion Lugar donde se realizará el evento.
 * @property etiquetas Etiquetas o palabras clave asociadas al evento.
 */
data class DatosEvento(
    val descripcion: String,
    val fecha: String,
    val ubicacion: String,
    val etiquetas: String
)
