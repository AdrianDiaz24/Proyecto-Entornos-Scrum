enum class Estado(val descripcion: String) {
    ABIERTA("Abierta"),
    EN_PROGRESO("En proceso"),
    FINALIZADA("Finalizada"),

    ;

    override fun toString(): String {
        return descripcion
    }
}