package es.prog2425.taskmanager.presentacion

import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.servicios.*
import es.prog2425.taskmanager.dominio.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Clase principal de presentación que gestiona la interacción por consola
 * con el usuario para administrar actividades, usuarios e historial.
 *
 * @property historial Repositorio para gestionar el historial de cambios
 * @property actividades Servicio para manejar actividades (tareas, eventos)
 * @property usuarios Servicio para gestionar usuarios y sus tareas asignadas
 */
open class Consola(val historial: HistorialRepository = HistorialRepository(), val actividades: ActividadService = ActividadService(), val usuarios: UsuarioService = UsuarioService(UsuarioRepository())) {

    /**
     * Funcion que pinta por patalla el mensaje que recibe
     * @param msj String con el mensaje que desea sacar por pantalla
     */

    fun salida(msj: String){
        println(msj)
    }

    /**
     * Muestra el menú principal con las opciones disponibles para el usuario.
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
     * Muestra el submenú para crear actividades específicas.
     */
    fun mostrarSubmenu(){
        println("\n1. Crear Tarea")
        println("2. Crear Evento")
        println("3. Crear Subtarea")
        println("4. Cancelar")
    }

    /**
     * Solicita al usuario la descripción de una tarea.
     * @param msg Mensaje que se muestra para pedir la descripción
     * @return Descripción introducida por el usuario
     */
    fun pedirInfoTarea(msg: String): String{
        print("\n${msg}")
        return readln()
    }

    /**
     * Muestra el menú principal y solicita al usuario seleccionar una opción válida.
     * @return Número de opción seleccionada (1 a 13)
     */
    fun menu(): Int {
        mostrarMenu()
        return pedirNum(1, 13)
    }

    /**
     * Muestra el submenú de creación de actividades y pide una opción válida.
     * @return Número de opción seleccionada (1 a 4)
     */
    fun submenu(): Int{
        mostrarSubmenu()
        return pedirNum(1,4)
    }

    /**
     * Pide un número entero dentro del rango indicado.
     * @param min Número mínimo permitido
     * @param max Número máximo permitido
     * @return Número válido introducido por el usuario
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
     * Solicita la información necesaria para crear un evento.
     * @return Lista con la descripción, fecha, ubicación y etiquetas
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
     * Crea una actividad según la opción seleccionada: tarea, evento o subtarea.
     * @param input Opción que indica qué actividad crear
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

    /**
     * Lee datos completos para la creación de un evento.
     * @return DatosEvento con descripción, fecha, ubicación y etiquetas
     */
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
     * Lista todas las actividades con detalle.
     * @return true si existen actividades, false si no hay ninguna
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

    /**
     * Lista las subtareas de una tarea dada.
     * @param tarea Tarea de la que se listan las subtareas
     * @param contador Índice de la tarea principal para mostrar jerarquía
     */
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
     * Bucle principal que mantiene la ejecución del programa hasta que se elige salir.
     */
    fun ejecutarPrograma() {
        var opcion: Int
        do {
            opcion = menu()
            procesarOpcion(opcion)
        } while (opcion != 13)
    }

    /**
     * Procesa la opción seleccionada en el menú principal ejecutando la acción correspondiente.
     * @param opcion Número de opción elegida
     */
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

    /**
     * Crea un nuevo usuario pidiendo un nombre válido.
     * @return true si el usuario se creó correctamente.
     */
    private fun crearUsuario():  Boolean {
        val nombreUsuario: String = pedirNombreUsuario()
        usuarios.crearUsuario(nombreUsuario)
        return true
    }

    /**
     * Lista todos los usuarios existentes.
     * Si no hay usuarios, muestra un mensaje indicándolo.
     */
    private fun listarUsuarios() {
        println("\n")
        if (usuarios.obtenerTodos().isNotEmpty()) {
            usuarios.mostrarTodos()
        } else salida("Aún no existen usuarios creados")
    }

    /**
     * Solicita al usuario que introduzca un nombre válido para crear un nuevo usuario.
     * Repite la petición hasta que el nombre no esté vacío ni sea nulo.
     * @return El nombre válido introducido.
     */
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

    /**
     * Asigna una tarea a un usuario.
     * Muestra las tareas y usuarios disponibles para seleccionar.
     * Añade un registro de la asignación en el historial.
     */
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

    /**
     * Muestra las tareas asignadas a un usuario seleccionado.
     * Si el usuario no tiene tareas, muestra un mensaje indicándolo.
     */
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

    /**
     * Cambia el estado de una tarea seleccionada por el usuario.
     */
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

    /**
     * Cambia el estado de una subtarea dentro de una tarea.
     */
    private fun cambiarAEstado(tarea: Tarea, nuevoEstado: Estado, num: Int) {
        historial.añadirModificacionEstado(nuevoEstado, tarea, num + 1)
        tarea.estado = nuevoEstado
    }

    /**
     * Cambia el estado de una subtarea dentro de una tarea.
     */
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

    /**
     * Pide al usuario que seleccione un estado para una tarea.
     * @return Int correspondiente al estado elegido:
     * 1 -> Abierta, 2 -> En proceso, 3 -> Finalizada.
     */
    open fun pedirEstadoComoInt(): Int {
        println("\n¿Qué estado quieres poner?")
        println("1. Abierta")
        println("2. En proceso")
        println("3. Finalizada")

        return pedirNum(1, 3)
    }

    /**
     * Añade etiquetas a una actividad seleccionada por el usuario.
     * Solicita las etiquetas separadas por ';' y las añade a la actividad.
     */
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

    /**
     * Muestra un menú para que el usuario seleccione un filtro para buscar actividades.
     * Los filtros disponibles son: Tipo, Estado, Etiquetas, Usuario, Fecha, o salir.
     * Ejecuta la función correspondiente al filtro seleccionado.
     */
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

    /**
     * Muestra el menú para pedir al usuario el filtro deseado.
     * @return Int entre 1 y 6 correspondiente a la opción seleccionada.
     */
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

    /**
     * Filtra y muestra actividades según el tipo seleccionado: Tarea o Evento.
     * Si no hay actividades muestra un mensaje de aviso.
     */
    private fun filtrarPorTipo() {
        if (actividades.elementos.isNotEmpty()) {
            println("Tipo: \n\t1. Tarea\n\t2. Evento")
            when(pedirNum(1, 2)) {
                1 -> mostrarElementosDeTipo<Tarea>("tareas")
                2 -> mostrarElementosDeTipo<Evento>("eventos")
            }
        } else salida("Aún no existen actividades.")
    }

    /**
     * Función genérica para mostrar actividades filtradas por tipo.
     * @param tipoNombre Nombre en plural del tipo para mensajes (ej: "tareas").
     */
    private inline fun <reified T : Detallable> mostrarElementosDeTipo(tipoNombre: String) {
        val elementosTipo = actividades.elementos.filterIsInstance<T>()
        if (elementosTipo.isNotEmpty()) {
            elementosTipo.forEach { println(it.obtenerDetalle()) }
        } else print("No existen $tipoNombre creadas.")
    }

    /**
     * Filtra y muestra tareas según el estado seleccionado (Abierta, En progreso, Finalizada).
     * Muestra mensaje si no hay tareas.
     */
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

    /**
     * Muestra las tareas filtradas por el estado especificado.
     * @param tareas Lista de tareas a filtrar.
     * @param estado Estado a filtrar.
     */
    private fun mostrarTareasPorEstado(tareas: List<Tarea>, estado: Estado) {
        val filtradas = tareas.filter { it.estado == estado }
        if (filtradas.isNotEmpty()) {
            filtradas.forEach { println(it.obtenerDetalle()) }
        } else salida("No existen tareas con estado '${estado.name.lowercase()}'.")
    }

    /**
     * Filtra tareas que contengan una etiqueta especificada por el usuario.
     * Muestra mensaje de error si no se encuentra ninguna tarea con la etiqueta.
     */
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

    /**
     * Filtra y muestra tareas asignadas a un usuario indicado por ID.
     * Muestra mensajes de error si no existen usuarios o el ID no es válido.
     */
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

    /**
     * Filtra eventos por fecha introducida por el usuario.
     * Muestra mensaje si no hay eventos o no se encuentra ninguno con la fecha dada.
     */
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

    /**
     * Muestra un panel de control con estadísticas de tareas y eventos programados.
     */
    private fun paneldeControl(){
        println("\n--- Panel de Control ---")

        mostrarEstadisticasTareas()
        mostrarEventosProgramados()

        println("\n--------------------------")
    }

    /**
     * Muestra estadísticas generales de las tareas y sus subtareas,
     * incluyendo el total y distribución por estado.
     */
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

    /**
     * Muestra estadísticas de eventos próximos organizados por hoy, mañana,
     * esta semana y este mes.
     */
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
