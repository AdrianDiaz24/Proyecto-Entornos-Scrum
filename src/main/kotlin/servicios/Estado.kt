package es.prog2425.taskmanager.Modelo

enum class Estado(val descripcion: String) {
    ABIERTA ("Abierta"),
    CERRADA ("Cerrada");

    override fun toString(): String {
        return descripcion
    }
}