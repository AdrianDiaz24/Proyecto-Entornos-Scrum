package es.prog2425.taskmanager.datos // Como se puede ver el package espieza por es.prog2425.taskmanager que es el del Main.kt y despues se le añade el .datos que es en la carpeta que se encuentra no hay error

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
// Se añade una linea al final del archivo, aunque en GitHub no se esta ultima linea vacia
