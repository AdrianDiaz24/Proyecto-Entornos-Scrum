package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.datos.IActividadRepository
import es.prog2425.taskmanager.dominio.Actividad

class ActividadService: IActividadRepository<Actividad> {

    override val elementos: MutableList<Actividad> = mutableListOf()

}