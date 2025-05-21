package es.prog2425.taskmanager.Modelo

import java.time.LocalDate

/**
 * Clase abstracta que representa una actividad genérica con descripción, etiquetas e ID único.
 *
 * @property descripcion Descripción de la actividad (no puede estar vacía).
 */
abstract class Actividad(private val descripcion: String) {
    private val etiquetas: MutableSet<String> = mutableSetOf()

    // Al crear una nueva istancia se comprueba que la descripcion no este vacia sino lanza una execpcion y en caso correcta genera un nuevo ID

    init {
        require(descripcion.isNotBlank()) {throw IllegalArgumentException("La descripcion no puede estar vacia")}
        generarID()
    }

    companion object{

        var id = 0

        /**
         * Incrementa el contador de ID para asignar a nuevas actividades.
         */
        fun generarID(){
            id++
        }
    }

    private val fechaCreacion = LocalDate.now().toString()
    private val id = fechaCreacion.format("YYYYMMDD").replace("-","") + Companion.id

    /**
     * Devuelve un detalle básico de la actividad: ID, descripción y etiquetas.
     * @return String con el detalle de la actividad.
     */
    open fun obtenerDetalle(): String{
        return  "$id - $descripcion - Etiquetas: ${obtenerEtiquetas()}"
    }

    /**
     * Añade etiquetas a la actividad separadas por punto y coma.
     * Las etiquetas vacías o con espacios no se añaden.
     * @param etiquetas1 Cadena con etiquetas separadas por ';'.
     */
    fun aniadirEtiquetas(etiquetas1: String) {
        if (etiquetas1.isNotBlank()) {
            etiquetas1.split(";").forEach {
                val etiqueta = it.trim()
                if (etiqueta.isNotBlank()) {
                    etiquetas.add(etiqueta)
                }
            }
        }
    }

    /**
     * Devuelve el conjunto de etiquetas asignadas.
     * @return Conjunto de etiquetas.
     */
    fun adquirirEtiquetas() = etiquetas

    /**
     * Obtiene las etiquetas en formato cadena, o un texto indicativo si no hay etiquetas.
     * @return String con las etiquetas o mensaje "Ninguna etiqueta asignada".
     */
    private fun obtenerEtiquetas(): String {

        return if (etiquetas.isNotEmpty()) {
            etiquetas.joinToString(", ")
        } else{
            "Ninguna etiqueta asignada"
        }
    }
}