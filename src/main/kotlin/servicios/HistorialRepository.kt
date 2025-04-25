package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.datos.IHistorialRepository
import es.prog2425.taskmanager.dominio.Estado
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.dominio.Usuario
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class HistorialRepository(override val historial: MutableList<String> = mutableListOf()) : IHistorialRepository {

    companion object{
        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:MM:SS")
    }

    override fun añadirModificacionEstado(estado: Estado, tarea: Tarea, contador1: Int, contador2: Int): Boolean {
        if (contador2 != 0) {
            historial.add("${LocalDateTime.now().format(formato)} - $contador1.$contador2. ${tarea.obtenerDetalle()} - Cambiado el estado a '$estado'")
        } else {
            historial.add("${LocalDateTime.now().format(formato)} - $contador1. ${tarea.obtenerDetalle()} - Cambiado el estado a '$estado'")
        }
        return true
    }

    override fun añadirModificacionAsignacion(usuario: Usuario, tarea: Tarea, contador1: Int): Boolean {
        historial.add("${LocalDateTime.now().format(formato)} - $contador1. ${tarea.obtenerDetalle()} - Tarea asignada a ID: ${usuario.obtenerDetalle()}")
        return true
    }

    override fun listarHistorial() {
        var contador = 0
        for (modificacion in historial){
            contador++
            println("$contador. $modificacion")
        }
    }
}