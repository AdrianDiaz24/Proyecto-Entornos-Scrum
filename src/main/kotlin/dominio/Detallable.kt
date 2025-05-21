package es.prog2425.taskmanager.dominio

/**
 * Interfaz para objetos que pueden proporcionar un detalle descriptivo.
 */
interface Detallable {
    fun obtenerDetalle(): String
}