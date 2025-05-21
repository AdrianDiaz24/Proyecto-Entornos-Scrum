package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.datos.IActividadRepository

/**
 * Servicio que implementa el repositorio de actividades.
 * Gestiona una lista mutable de actividades.
 */
class ActividadService: IActividadRepository<Actividad> {

    /**
     * Lista mutable que almacena las actividades.
     */
    override val elementos: MutableList<Actividad> = mutableListOf()

}