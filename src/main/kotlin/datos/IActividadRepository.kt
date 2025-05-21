package es.prog2425.taskmanager.datos

/**
 * Interfaz genérica para repositorios de actividades.
 * Define la estructura básica para almacenar y gestionar elementos de tipo T.
 */
interface IActividadRepository<T> {

    // almacena una lista del elemento especificado

    /**
     * Lista mutable que almacena los elementos del repositorio.
     */
    val elementos: MutableList<T>

    /***
     * Funcion para agregar el elemento a la lista
     * @param elemento Elemento que se va a añadir a la lista
     */
    fun agregarElemento(elemento: T){
        elementos.add(elemento)
    }

}