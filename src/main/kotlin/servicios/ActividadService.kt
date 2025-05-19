package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.servicios.Actividad
import es.prog2425.taskmanager.datos.IActividadRepository

class ActividadService: IActividadRepository<Actividad> {

    override val elementos: MutableList<Actividad> = mutableListOf()

}
