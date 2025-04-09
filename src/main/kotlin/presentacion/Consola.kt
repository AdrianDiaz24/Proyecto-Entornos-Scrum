package es.prog2425.taskmanager.presentacion

import es.prog2425.taskmanager.Servicios.ActividadService
import es.prog2425.taskmanager.Servicios.Evento
import es.prog2425.taskmanager.Servicios.Tarea

class Consola() {

    val actividades = ActividadService()

    /**
     * Funcion que pinta por patalla el mensaje que recibe
     * @param msj String con el mensaje que desea sacar por pantalla
     */

    fun salida(msj: String){
        println(msj)
    }

    /**
     * Muestra el menu principal
     */

    fun mostrarMenu(){
        println("\n1. Crear Actividad")
        println("2. Listar Actividades")
        println("3. Salir")
    }

    /**
     * Muestra el Submenu
     */

    fun mostrarSubmenu(){
        println("\n1. Crear Tarea")
        println("2. Crear Evento")
        println("3. Cancelar")
    }

    /**
     * Pided la informacion necesaria para crear una tarea
     * @return Devuelve un String con la descripcion de la tarea
     */

    fun pedirInfoTarea(): String{
        print("\nIntroduce la descripcion de la tarea: ")
        return readln()
    }

    /**
     * Muestra el menu y pide que se seleccione una opcion
     * @return Devuelve un Nº con la eleccion seleccionada
     */

    fun menu(): Int {
        mostrarMenu()
        return pedirNum(1, 3)
    }

    /**
     * Muestra el submenu y pide que se seleccione una opcion
     * @return Devuelve un Nº con la eleccion seleccionada
     */

    fun submenu(): Int{
        mostrarSubmenu()
        return pedirNum(1,3)
    }

    /**
     * Pide que se introduzca un Nº
     * @param min Nº minimo que se permite introducir
     * @param max Nº maximo que se permmite introducir
     * @return Devuelve el Nº Introducido por el usuario si se encuentra dentro de los parametros
     */

    fun pedirNum(min: Int, max: Int): Int{
        var valorValido = false
        var input = 0
        while (!valorValido){
            print(">> ")
            try {
                input = readln().toInt()
            } catch (e: IllegalArgumentException){
                println("Introduce un Nº")
            }
            if (input in min..max){
                valorValido = true
            } else {
                println("Introduce un Nº entre $min y $max")
            }
        }
        return input
    }

    /**
     * Pide la informacion necesaria  para crear un evento
     * @return Devuelve un triple co los 3 datos propocionados por consola
     */

    fun pedirInfoEvento(): Triple<String, String, String>{
        var descripcion = ""
        var fecha = ""
        var ubicacion = ""
        print("\nIntroduce la descripcion del Evento: ")
        descripcion = readln()
        print("\nIntroduce la fecha del evento: ")
        fecha = readln()
        print("\nIntroduce la ubicacion del evento: ")
        ubicacion = readln()
        val salida = Triple(descripcion, fecha, ubicacion)
        return salida

    }

    /**
     * Crear uno de los 2 tipos de actividades o vuelve a menu principal
     * @param input Nº que define que actividad crear 1 -> Tarea, 2 -> Evento
     */

    fun crearActividad(input: Int){
        when (input){
            1 -> try {
                actividades.agregarElemento(Tarea.creaTarea(pedirInfoTarea()))
            } catch (e: IllegalArgumentException) {
                println("**ERROR** $e")
            }
            2 -> {
                val (descripcion, fecha, ubicacion) = pedirInfoEvento()
                try {
                    actividades.agregarElemento(Evento.creaEvento(descripcion, fecha, ubicacion))
                } catch (e: IllegalArgumentException) {
                    println("**ERROR** $e")
                }
            }
            3 -> println("Volviendo al menu principal")
        }
    }

    /**
     * Lista las actividades almacenadas
     */

    fun listarActividades(){
        println("\n")
        if (actividades.elementos.isNotEmpty()) {
            actividades.elementos.forEach { salida(it.obtenerDetalle()) }
        } else salida("Aún no existen actividades.")
    }

    /**
     * Funcion que realiza la ejecucion general del programa
     */

    fun ejecutarPrograma(){
        var salida = false
        while (!salida){
            val input = menu()
            if (input == 3) {
                salida = true
            } else if (input == 2){
                listarActividades()
            } else {
                val input = submenu()
                crearActividad(input)
            }
        }
    }

}