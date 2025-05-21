package es.prog2425.taskmanager.presentacion

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.servicios.*
import es.prog2425.taskmanager.dominio.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

open class Consola(val historial: HistorialRepository = HistorialRepository(), val actividades: ActividadService = ActividadService(), val usuarios: UsuarioService = UsuarioService(UsuarioRepository())) {

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
        println("\n1.  Crear Actividad")
        println("2.  Listar Actividades")
        println("3.  Cambiar estado de la Tarea")
        println("4.  Cambiar estado de la SubTarea")
        println("5.  Añadir Etiquetas a una Actividad")
        println("6.  Crear usuario")
        println("7.  Listar usuarios")
        println("8.  Asignar tarea a usuario")
        println("9.  Mostrar tareas asignadas a un usuario")
        println("10. Buscar con filtro")
        println("11. Listar historial de cambios")
        println("12. Panel de Control")
        println("13. Salir")
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
        return pedirNum(1, 13)
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

    open fun pedirNum(min: Int, max: Int): Int {
        var valorValido = false
        var input = 0
        while (!valorValido) {
            print(">> ")
            try {
                input = readln().toInt()
                if (input in min..max) {
                    valorValido = true
                } else {
                    println("Introduce un Nº entre $min y $max")
                }
            } catch (e: IllegalArgumentException) {
                println("Introduce un Nº válido")
                e.printStackTrace()
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
                val datosEvento = leerDatosEvento()
                try {
                    actividades.agregarElemento(Evento.creaEvento(datosEvento.descripcion, datosEvento.fecha, datosEvento.ubicacion, datosEvento.etiquetas))
                } catch (e: IllegalArgumentException) {
                    println("**ERROR** $e")
                }
            }
            3 -> {
                val hayActividades = listarActividades()
                if (hayActividades){
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
            }
            4 -> println("Volviendo al menu principal")
        }
    }

    fun leerDatosEvento(): DatosEvento {
        println("Introduce la descripcion del evento:")
        val descripcion = readLine() ?: ""
        println("Introduce la fecha del evento:")
        val fecha = readLine() ?: ""
        println("Introduce la ubicacion del evento:")
        val ubicacion = readLine() ?: ""
        println("Introduce etiquetas (separadas por ;):")
        val etiquetas = readLine() ?: ""
        return DatosEvento(descripcion, fecha, ubicacion, etiquetas)
    }

    /**
     * Lista las actividades almacenadas
     */

    open fun listarActividades(): Boolean{
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
        var opcion: Int
        do {
            opcion = menu()
            procesarOpcion(opcion)
        } while (opcion != 13)
    }

    fun procesarOpcion(opcion: Int) {
        when (opcion) {
            1 -> crearActividad(submenu())
            2 -> listarActividades()
            3 -> cambiarEstado()
            4 -> cambiarEstadoSubTarea()
            5 -> aniadirEtiquetasActividad()
            6 -> crearUsuario()
            7 -> listarUsuarios()
            8 -> asignarTarea()
            9 -> mostrarTareasAsignadasUsuario()
            10 -> buscarFiltro()
            11 -> historial.listarHistorial()
            12 -> paneldeControl()
            13 -> println("Saliendo...")
            else -> println("Opción no válida")
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

    fun cambiarEstado() {
        if (!listarActividades()) return

        println("\nElige una tarea")
        val numActividad = pedirNum(1, actividades.elementos.size) - 1
        val actividad = actividades.elementos[numActividad] as? Tarea ?: return

        val estado = pedirEstadoComoInt()
        val puedeFinalizar = actividad.listaSubtareas.isEmpty() ||
                actividad.listaSubtareas.all { it.estado == Estado.FINALIZADA }

        when (estado) {
            1 -> cambiarAEstado(actividad, Estado.ABIERTA, numActividad)
            2 -> cambiarAEstado(actividad, Estado.EN_PROGRESO, numActividad)
            3 -> {
                if (puedeFinalizar) {
                    cambiarAEstado(actividad, Estado.FINALIZADA, numActividad)
                } else {
                    println("ERROR: Todas las subtareas tienen que estar marcadas como 'FINALIZADA' antes de finalizar la tarea.")
                }
            }
        }
    }

    private fun cambiarAEstado(tarea: Tarea, nuevoEstado: Estado, num: Int) {
        historial.añadirModificacionEstado(nuevoEstado, tarea, num + 1)
        tarea.estado = nuevoEstado
    }

    private fun cambiarEstadoSubTarea(){
        val existenActividades = listarActividades()

        if (existenActividades){

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

                val estado = pedirEstadoComoInt()

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
    }

    open fun pedirEstadoComoInt(): Int {
        println("\n¿Qué estado quieres poner?")
        println("1. Abierta")
        println("2. En proceso")
        println("3. Finalizada")

        return pedirNum(1, 3)
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
        while (filtro == -1 && filtro != 6) {
            filtro = pedirFiltro()
            when(filtro) {
                1 -> filtrarPorTipo()
                2 -> filtrarPorEstado()
                3 -> filtrarPorEtiqueta()
                4 -> filtrarPorUsuario()
                5 -> filtrarPorFecha()
            }
        }
    }

    private fun pedirFiltro(): Int {
        print("Introduce el filtro deseado: " +
                "\n\t1. Tipo" +
                "\n\t2. Estado" +
                "\n\t3. Etiquetas" +
                "\n\t4. Usuario" +
                "\n\t5. Fecha" +
                "\n\t6. Salir" +
                "\n")
        return pedirNum(1, 6)
    }

    private fun filtrarPorTipo() {
        if (actividades.elementos.isNotEmpty()) {
            println("Tipo: \n\t1. Tarea\n\t2. Evento")
            when(pedirNum(1, 2)) {
                1 -> mostrarElementosDeTipo<Tarea>("tareas")
                2 -> mostrarElementosDeTipo<Evento>("eventos")
            }
        } else salida("Aún no existen actividades.")
    }

    private inline fun <reified T : Detallable> mostrarElementosDeTipo(tipoNombre: String) {
        val elementosTipo = actividades.elementos.filterIsInstance<T>()
        if (elementosTipo.isNotEmpty()) {
            elementosTipo.forEach { println(it.obtenerDetalle()) }
        } else print("No existen $tipoNombre creadas.")
    }

    private fun filtrarPorEstado() {
        val tareas = actividades.elementos.filterIsInstance<Tarea>()
        if (tareas.isNotEmpty()) {
            println(
                "Estado: " +
                        "\n\t1. Abierta" +
                        "\n\t2. En progreso" +
                        "\n\t3. Finalizada"
            )
            when(pedirNum(1, 3)) {
                1 -> mostrarTareasPorEstado(tareas, Estado.ABIERTA)
                2 -> mostrarTareasPorEstado(tareas, Estado.EN_PROGRESO)
                3 -> mostrarTareasPorEstado(tareas, Estado.FINALIZADA)
            }
        } else salida("No existen tareas creadas.")
    }

    private fun mostrarTareasPorEstado(tareas: List<Tarea>, estado: Estado) {
        val filtradas = tareas.filter { it.estado == estado }
        if (filtradas.isNotEmpty()) {
            filtradas.forEach { println(it.obtenerDetalle()) }
        } else salida("No existen tareas con estado '${estado.name.lowercase()}'.")
    }

    private fun filtrarPorEtiqueta() {
        val tareas = actividades.elementos.filterIsInstance<Tarea>()
        if (tareas.isNotEmpty()) {
            print("Introduce la etiqueta: ")
            val filtro = readln().lowercase()
            val filtradas = tareas.filter { tarea -> tarea.adquirirEtiquetas().any { it.lowercase() == filtro } }
            if (filtradas.isNotEmpty()) {
                filtradas.forEach { salida(it.obtenerDetalle()) }
            } else salida("ERROR: No se encontró ninguna etiqueta.")
        } else salida("No existen tareas creadas.")
    }

    private fun filtrarPorUsuario() {
        val todosUsuarios = usuarios.obtenerTodos()
        if (todosUsuarios.isNotEmpty()) {
            println("Introduce el ID del usuario: ")
            val idUsuario = pedirNum(1, todosUsuarios.size)
            val usuario = todosUsuarios.find { it.id == idUsuario }
            if (usuario != null) {
                usuario.listaTareas.forEach { salida(it.obtenerDetalle()) }
            } else salida("ERROR: No se encontró el usuario.")
        } else salida("No existen usuarios aún.")
    }

    private fun filtrarPorFecha() {
        val eventos = actividades.elementos.filterIsInstance<Evento>()
        if (eventos.isNotEmpty()) {
            print("Introduce la fecha: ")
            val filtro = readln()
            val filtrados = eventos.filter { it.fecha == filtro }
            if (filtrados.isNotEmpty()) {
                filtrados.forEach { salida(it.obtenerDetalle()) }
            } else print("ERROR: No se encontró ningún elemento con la fecha indicada")
        } else salida("No existen eventos creados.")
    }

    private fun paneldeControl(){
        println("\n--- Panel de Control ---")

        mostrarEstadisticasTareas()
        mostrarEventosProgramados()

        println("\n--------------------------")
    }

    private fun mostrarEstadisticasTareas() {

        val listaTareas = mutableListOf<Tarea>()

        for (actividad in actividades.elementos){
            if (actividad is Tarea){
                listaTareas.add(actividad)
            }
        }

        val totalTareas = listaTareas.size
        var totalSubtareas = 0

        for (tarea in listaTareas){
            totalSubtareas += tarea.listaSubtareas.size
        }

        println("\n--- Estadísticas de Tareas ---")
        println(" - Total de tareas principales: $totalTareas")
        println(" - Total de subtareas: $totalSubtareas")


        println("\n--- Distribución por estado ---")

        for (estadoActual in Estado.entries) {
            var contadorTareas = 0
            var contadorSubtareas = 0

            for (tarea in listaTareas) {
                if (tarea.estado == estadoActual) {
                    contadorTareas++
                }

                for (subtarea in tarea.listaSubtareas) {
                    if (subtarea.estado == estadoActual) {
                        contadorSubtareas++
                    }
                }
            }

            println(" - ${estadoActual.descripcion}: $contadorTareas tareas principales, $contadorSubtareas subtareas")
        }
    }

    private fun mostrarEventosProgramados() {

        val eventos = mutableListOf<Evento>()

        for (actividad in actividades.elementos){
            if (actividad is Evento) {
                eventos.add(actividad)
            }
        }

        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val hoy = LocalDate.now()
        val mañana = LocalDate.now().plusDays(1)
        val semana = LocalDate.now().plusWeeks(1)
        val mes = LocalDate.now().plusMonths(1)

        var contadorEventosHoy = 0
        var contadorEventosMañana = 0
        var contadorEventosEstaSemana = 0
        var contadorEventosEsteMes = 0

        for (evento in eventos){
            if (LocalDate.parse(evento.fecha, formato) in hoy..mes){
                contadorEventosEsteMes++
            }
            if (LocalDate.parse(evento.fecha, formato) in hoy..semana){
                contadorEventosEstaSemana++
            }
            if (LocalDate.parse(evento.fecha, formato) == mañana){
                contadorEventosMañana++
            }
            if (LocalDate.parse(evento.fecha, formato) == hoy){
                contadorEventosHoy++
            }
        }

        println("\n--- Estadisticas de Eventos --- \n - Eventos hoy: $contadorEventosHoy\n - Eventos mañana: $contadorEventosMañana \n - Eventos esta semana: $contadorEventosEstaSemana \n - Eventos este mes: $contadorEventosEsteMes")
    }
}
