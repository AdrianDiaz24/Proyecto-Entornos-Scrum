package es.prog2425.taskmanager.datos

interface IActividadRepository<T> {

    // almacena una lista del elemento especificado

    val elementos: MutableList<T>

    /***
     * Funcion para agregar el elemento a la lista
     * @param elemento Elemento que se va a añadir a la lista
     */

    fun agregarElemento(elemento: T){
        elementos.add(elemento)
    }

}