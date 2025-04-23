package es.prog2425.taskmanager.presentacion

import es.prog2425.taskmanager.servicios.*
import es.prog2425.taskmanager.dominio.*

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
        println("3. Cambiar estado de la Tarea")
        println("4. Cambiar estado de la SubTarea")
        println("5. Salir")
    }

    /**
     * Muestra el Submenu
     */

    fun mostrarSubmenu(){
        println("\n1. Crear Tarea")
        println("2. Crear Evento")
        println("3. Crear Subtarea")
        println("4. Cancelar")
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
        return pedirNum(1, 5)
    }

    /**
     * Muestra el submenu y pide que se seleccione una opcion
     * @return Devuelve un Nº con la eleccion seleccionada
     */

    fun submenu(): Int{
        mostrarSubmenu()
        return pedirNum(1,4)
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
            3 -> { //ToDo Arreglar que cuando no hay tareas no te deje crear subtareas.
                listarActividades()
                println("\nElige una tarea")
                val numActividad = pedirNum(1,actividades.elementos.size) - 1
                val tarea = actividades.elementos[numActividad]

                if (tarea is Tarea) {
                    try {
                        tarea.aniadirSubtarea(Tarea.creaTarea(pedirInfoTarea()))
                    } catch (e: IllegalArgumentException) {
                        println("**ERROR** $e")
                    }
                }
                else{
                    println("Tienes que elegir una Tarea!!")
                }
            }
            4 -> println("Volviendo al menu principal")
        }
    }

    /**
     * Lista las actividades almacenadas
     */

    fun listarActividades(){
        println("\n")
        if (actividades.elementos.isNotEmpty()) {
            var contador = 0
            for (actividad in actividades.elementos){
                contador++
                println(contador.toString() + ". " + actividad.obtenerDetalle())
                if (actividad is Tarea) {
                    listarSubTareas(actividad, contador)
                }
            }
        } else salida("Aún no existen actividades.")
    }

    fun listarSubTareas(tarea: Tarea, contador: Int){
        if (tarea.listaSubtareas.isNotEmpty()){
            var contador1 = 0
            for (subtarea in tarea.listaSubtareas){
                contador1++
                println("\t$contador.${contador1}. ${subtarea.obtenerDetalle()}")
            }
        }
    }

    /**
     * Funcion que realiza la ejecucion general del programa
     */

    fun ejecutarPrograma(){
        var salida = false
        while (!salida){
            val input = menu()
            if (input == 5) {
                salida = true
            } else if (input == 2){
                listarActividades()
            } else  if (input == 3) {
                cambiarEstado()
            } else if (input == 4){
                cambiarEstadoSubTarea()
            } else {
                val input1 = submenu()
                crearActividad(input1)
            }
        }
    }

    private fun cambiarEstado(){
        listarActividades()

        println("\nElige una tarea")
        val numActividad = pedirNum(1,actividades.elementos.size) - 1

        println("\n¿Que estado quieres ponerle a la tarea?")
        println("1. Abierta")
        println("2. En proceso")
        println("3. Finalizada")

        val estado = pedirNum(1,3)

        val actividad = actividades.elementos[numActividad]

        if(actividad is Tarea) {
            when (estado) {
                1 -> actividad.estado = Estado.ABIERTA
                2 -> actividad.estado = Estado.EN_PROGRESO
                3 -> {
                    if (actividad.listaSubtareas.isEmpty()){
                        actividad.estado = Estado.FINALIZADA
                    } else {
                        if (actividad.listaSubtareas.all { it.estado == Estado.FINALIZADA }) {
                            actividad.estado = Estado.FINALIZADA
                        } else println("ERROR: Todas las subtareas tienen que estar marcadas como 'FINALIZADA' antes de finalizar la tarea.")
                    }
                }
            }
        }
    }


    private fun cambiarEstadoSubTarea(){
        listarActividades()

        println("\nElige una tarea")
        val numActividad = pedirNum(1,actividades.elementos.size) - 1
        val tarea = actividades.elementos[numActividad] as Tarea

        if (tarea.listaSubtareas.isNotEmpty()) {

            println(tarea.obtenerDetalle())
            var contador = 0
            for (subtarea in tarea.listaSubtareas) {
                contador++
                println("\t$contador. " + subtarea.obtenerDetalle())
            }


            println("\nElige una Subtarea")
            val numSubTarea = pedirNum(1, tarea.listaSubtareas.size) - 1

            println("\n¿Que estado quieres ponerle a la Subtarea?")
            println("1. Abierta")
            println("2. En proceso")
            println("3. Finalizada")

            val estado = pedirNum(1, 3)

            val actividad = tarea.listaSubtareas[numSubTarea]
            when (estado) {
                1 -> actividad.estado = Estado.ABIERTA
                2 -> actividad.estado = Estado.EN_PROGRESO
                3 -> {
                    actividad.estado = Estado.FINALIZADA

                    if (actividad.listaSubtareas.all { it.estado == Estado.FINALIZADA }) {
                        tarea.estado = Estado.FINALIZADA
                    }
                }
            }

        } else {
            println("Esta tarea no tiene subtareas.")
        }
    }
}