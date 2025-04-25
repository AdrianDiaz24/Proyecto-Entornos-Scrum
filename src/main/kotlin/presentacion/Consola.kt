package es.prog2425.taskmanager.presentacion

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.servicios.*
import es.prog2425.taskmanager.dominio.*

class Consola(val historial: HistorialRepository = HistorialRepository(), val actividades: ActividadService = ActividadService(), val usuarios: UsuarioService = UsuarioService(UsuarioRepository())) {

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
        println("5. Añadir Etiquetas a una Actividad")
        println("6. Crear usuario")
        println("7. Listar usuarios")
        println("8. Asignar tarea a usuario")
        println("9. Mostrar tareas asignadas a un usuario")
        println("10. Buscar con filtro")
        println("11. Listar historial de cambios")
        println("12. Salir")
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

    fun pedirInfoTarea(msg: String): String{
        print("\n${msg}")
        return readln()
    }

    /**
     * Muestra el menu y pide que se seleccione una opcion
     * @return Devuelve un Nº con la eleccion seleccionada
     */

    fun menu(): Int {
        mostrarMenu()
        return pedirNum(1, 12)
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

    fun pedirInfoEvento(): MutableList<String>{
        var descripcion = ""
        var fecha = ""
        var ubicacion = ""
        var etiquetas = ""
        print("\nIntroduce la descripcion del Evento: ")
        descripcion = readln()
        print("\nIntroduce la fecha del evento: ")
        fecha = readln()
        print("\nIntroduce la ubicacion del evento: ")
        ubicacion = readln()
        print("\nIntroduce etiquetas (separadas por ;) -> ")
        etiquetas = readln()
        val salida = mutableListOf<String>(descripcion,fecha,ubicacion,etiquetas)
        return salida
    }

    /**
     * Crear uno de los 2 tipos de actividades o vuelve a menu principal
     * @param input Nº que define que actividad crear 1 -> Tarea, 2 -> Evento
     */

    fun crearActividad(input: Int){
        when (input){
            1 -> try {
                actividades.agregarElemento(Tarea.creaTarea(pedirInfoTarea("Introduce la descripcion de la tarea: "), etiquetas = pedirInfoTarea("Introduce etiquetas (separadas por ;)")))
            } catch (e: IllegalArgumentException) {
                println("**ERROR** $e")
            }
            2 -> {
                val (descripcion, fecha, ubicacion, etiquetas) = pedirInfoEvento()
                try {
                    actividades.agregarElemento(Evento.creaEvento(descripcion, fecha, ubicacion, etiquetas))
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
                        tarea.aniadirSubtarea(Tarea.creaTarea(pedirInfoTarea("Introduce la descripcion de la tarea: "), etiquetas = pedirInfoTarea("Introduce etiquetas (separadas por ;)")))
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

    fun listarActividades(): Boolean{
        println("\n")
        return if (actividades.elementos.isNotEmpty()) {
            var contador = 0
            for (actividad in actividades.elementos){
                contador++
                println(contador.toString() + ". " + actividad.obtenerDetalle())
                if (actividad is Tarea) {
                    listarSubTareas(actividad, contador)
                }
            }
            true
        } else {
            salida("Aún no existen actividades.")
            false
        }
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

    fun ejecutarPrograma() {
        var salida = false
        while (!salida) {
            val input = menu()

            when (input) {
                1 -> {
                    crearActividad(submenu())
                }

                2 -> {
                    listarActividades()
                }

                3 -> {
                    cambiarEstado()
                }

                4 -> {
                    cambiarEstadoSubTarea()
                }

                5 -> {
                    aniadirEtiquetasActividad()
                }

                6 -> {
                    crearUsuario()
                }

                7 -> {
                    listarUsuarios()
                }

                8 -> {
                    asignarTarea()
                }

                9 -> {
                    mostrarTareasAsignadasUsuario()
                }

                10 -> {
                    buscarFiltro()
                }

                11 -> {
                    historial.listarHistorial()
                }

                12 -> {
                    salida = true
                }
            }
        }
    }

    private fun crearUsuario():  Boolean {
        val nombreUsuario: String = pedirNombreUsuario()
        usuarios.crearUsuario(nombreUsuario)
        return true
    }

    private fun listarUsuarios() {
        println("\n")
        if (usuarios.obtenerTodos().isNotEmpty()) {
            usuarios.mostrarTodos()
        } else salida("Aún no existen usuarios creados")
    }

    private fun pedirNombreUsuario(): String {
        var nombre: String = ""

        while (nombre == null || nombre.isBlank()) {
            print("Introduce el nombre del usuario: ")
            nombre = readln()
            if (nombre == null || nombre.isBlank()) {
                println("ERROR: Introduce un nombre válido.")
            }
        }
        return nombre
    }

    private fun asignarTarea() {


        if (actividades.elementos.size == 0) salida("Aún no existen tareas creadas")
        else if (usuarios.obtenerTodos().isEmpty()) salida("Aún no existen usuarios creados")
        else {
            print("Elije una tarea: ")
            listarActividades()

            val numActividad = pedirNum(1,actividades.elementos.size) - 1
            val tarea: Tarea = actividades.elementos[numActividad] as Tarea

            print("Elije un usuario: ")
            listarUsuarios()
            val numUsuario = pedirNum(1, usuarios.obtenerTodos().size) - 1
            val usuario = usuarios.obtenerTodos()[numUsuario]

            usuarios.asignarTarea(usuario, tarea)
            historial.añadirModificacionAsignacion(usuario, tarea, numActividad + 1)
        }
    }

    private fun mostrarTareasAsignadasUsuario() {

        if (usuarios.obtenerTodos().isEmpty()) salida("Aún no existen usuarios creados")
        else {
            println("\nElige un usuario: ")
            listarUsuarios()

            val numUsuario = pedirNum(1, usuarios.obtenerTodos().size) - 1
            val usuario = usuarios.obtenerTodos()[numUsuario]

            if(usuario.listaTareas.isNotEmpty()) {
                println("Mostrando tareas del usuario #ID# ${usuario.obtenerDetalle()}")
                usuario.listaTareas.forEach { actividad: Actividad -> println("\t" + actividad.obtenerDetalle()) }
                println("\n")
            } else salida("El usuario no tiene tareas asignadas.")
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
                1 -> {
                    historial.añadirModificacionEstado(Estado.ABIERTA, actividad, numActividad + 1)
                    actividad.estado = Estado.ABIERTA
                }
                2 -> {
                    historial.añadirModificacionEstado(Estado.EN_PROGRESO, actividad, numActividad + 1)
                    actividad.estado = Estado.EN_PROGRESO
                }
                3 -> {
                    if (actividad.listaSubtareas.isEmpty()){
                        historial.añadirModificacionEstado(Estado.FINALIZADA, actividad, numActividad + 1)
                        actividad.estado = Estado.FINALIZADA
                    } else {
                        if (actividad.listaSubtareas.all { it.estado == Estado.FINALIZADA }) {
                            historial.añadirModificacionEstado(Estado.FINALIZADA, actividad, numActividad + 1)
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
                1 -> {
                    historial.añadirModificacionEstado(Estado.ABIERTA, actividad, numActividad + 1, numSubTarea + 1)
                    actividad.estado = Estado.ABIERTA
                }
                2 -> {
                    historial.añadirModificacionEstado(Estado.EN_PROGRESO, actividad, numActividad + 1, numSubTarea + 1)
                    actividad.estado = Estado.EN_PROGRESO
                }
                3 -> {
                    historial.añadirModificacionEstado(Estado.FINALIZADA, actividad, numActividad + 1, numSubTarea + 1)
                    actividad.estado = Estado.FINALIZADA

                    if (actividad.listaSubtareas.all { it.estado == Estado.FINALIZADA }) {
                        historial.añadirModificacionEstado(Estado.FINALIZADA, tarea, numActividad + 1)
                        tarea.estado = Estado.FINALIZADA
                    }
                }
            }

        } else {
            println("Esta tarea no tiene subtareas.")
        }
    }

    private fun aniadirEtiquetasActividad(){
        val hayActividades = listarActividades()

        if (hayActividades){
            println("\nElige una actividad")
            val numActividad = pedirNum(1,actividades.elementos.size) - 1

            print("\nIntroduce etiquetas (separadas por ;) -> ")
            val etiquetas = readln()

            actividades.elementos[numActividad].aniadirEtiquetas(etiquetas)
        }
    }

    private fun buscarFiltro() {

        var filtro = -1

        while(filtro == -1 && filtro != 6) {
            print("Introduce el filtro deseado: " +
                    "\n\t1. Tipo" +
                    "\n\t2. Estado" +
                    "\n\t3. Etiquetas" +
                    "\n\t4. Usuario" +
                    "\n\t5. Fecha" +
                    "\n\t6. Salir" +
                    "\n")

            filtro = pedirNum(1, 6)
            when(filtro) {
                1 -> {
                    if (actividades.elementos.isNotEmpty()) {
                        println("Tipo: " +
                                "\n\t1. Tarea" +
                                "\n\t2. Evento")
                        val tipo = pedirNum(1, 2)
                        when(tipo) {
                            1 -> {
                                if(actividades.elementos.any { it is Tarea }) {
                                    actividades.elementos.forEach { if(it is Tarea) println(it.obtenerDetalle()) }
                                } else print("No existen tareas creadas.")
                            }

                            2 -> {
                                if(actividades.elementos.any { it is Evento }) {
                                    actividades.elementos.forEach { if(it is Evento) println(it.obtenerDetalle()) }
                                } else print("No existen eventos creados.")
                            }
                        }
                    } else salida("Aún no existen actividades.")
                }

                2 -> {

                    val tareas = mutableListOf<Tarea>()
                    for(elemento in actividades.elementos) {
                        if (elemento is Tarea) {
                            tareas.add(elemento)
                        }
                    }

                    if (actividades.elementos.isNotEmpty()) {
                        println(
                            "Estado: " +
                                    "\n\t1. Abierta" +
                                    "\n\t2. En progreso" +
                                    "\n\t3. Finalizada"
                        )
                        val estado = pedirNum(1, 3)
                        when(estado) {
                            1 -> {
                                if(tareas.any { it.estado == Estado.ABIERTA }) {
                                    tareas.forEach { if(it.estado == Estado.ABIERTA) println(it.obtenerDetalle()) }
                                } else salida("No existen tareas con estado 'abierto'.")
                            }

                            2 -> {
                                if(tareas.any { it.estado == Estado.EN_PROGRESO }) {
                                    tareas.forEach { if(it.estado == Estado.EN_PROGRESO) println(it.obtenerDetalle()) }
                                } else salida("No existen tareas con estado 'en progreso'.")
                            }

                            3 -> {
                                if(tareas.any { it.estado == Estado.FINALIZADA }) {
                                    tareas.forEach { if(it.estado == Estado.FINALIZADA) println(it.obtenerDetalle()) }
                                } else salida("No existen tareas con estado 'finalizada'.")
                            }
                        }
                    } else salida("No existen actividades creadas.")
                }

                3 -> {
                    val tareas = mutableListOf<Tarea>()
                    for(elemento in actividades.elementos) {
                        if (elemento is Tarea) {
                            tareas.add(elemento)
                        }
                    }

                    if (actividades.elementos.any { it is Tarea }) {
                        print("Introduce la etiqueta: ")
                        val filtro = readln().lowercase()

                        for(elemento in actividades.elementos) {
                            if (elemento is Tarea && elemento.adquirirEtiquetas().any { it.lowercase() == filtro }) {
                                tareas.forEach { salida(it.obtenerDetalle()) }
                            } else salida("ERROR: No se encontró ninguna etiqueta.")
                        }
                    } else salida("No existen tareas creadas.")
                }

                4 -> {

                    if (usuarios.obtenerTodos().isNotEmpty()) {
                        println("Introduce el ID del usuario: ")
                        val usuariosFiltro = pedirNum(1, usuarios.obtenerTodos().size)

                        for(elemento in usuarios.obtenerTodos()) {
                            if (elemento.id == usuariosFiltro) {
                                for (actividad in elemento.listaTareas) {
                                    salida(actividad.obtenerDetalle())
                                }
                            } else salida("ERROR: No se encontró ninguna etiqueta.")
                        }
                    } else salida("No existen usuarios aún.")
                }

                5 -> {

                    val eventos = mutableListOf<Evento>()
                    for(elemento in actividades.elementos) {
                        if (elemento is Evento) {
                            eventos.add(elemento)
                        }
                    }

                    if (eventos.isNotEmpty()) {
                        print("Introduce la fecha: ")
                        val filtro = readln()
                        if (eventos.any { it.fecha == filtro }) {
                            eventos.forEach { evento -> if (evento.fecha == filtro) salida(evento.obtenerDetalle()) }
                        } else print("ERROR: No se encontró ningún elemento con la fecha indicada")
                    } else salida("No existen eventos creados.")
                }
            }
        }
    }
}