package es.prog2425.taskmanager.dominio

/**
 * Enum que representa los posibles estados de una tarea.
 *
 * @property descripcion Descripción legible del estado.
 */
enum class Estado(val descripcion: String) {
    ABIERTA ("Abierta"),
    EN_PROGRESO ("En proceso"),
    FINALIZADA ("Finalizada");

    /**
     * Devuelve la descripción legible del estado.
     */
    override fun toString(): String {
        return descripcion
    }
}