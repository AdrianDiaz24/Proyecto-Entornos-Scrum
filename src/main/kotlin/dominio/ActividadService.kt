package es.prog2425.taskmanager.Servicios

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.datos.IActividadRepository

class ActividadService: IActividadRepository<Actividad> {

    override val elementos: MutableList<Actividad> = mutableListOf()

}