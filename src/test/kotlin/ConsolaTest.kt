import es.prog2425.taskmanager.dominio.Estado
import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.presentacion.Consola
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.InputStream

class ConsolaTest {

    val consola = object : Consola() {
        override fun pedirNum(min: Int, max: Int): Int {
            return 1 // siempre devuelve 1 para seleccionar la primera opción
        }

        override fun listarActividades(): Boolean {
            return true // para que no retorne falso y no salga temprano
        }

        override fun pedirEstadoComoInt(): Int {
            return 1 // devolver estado abierto sin pedir input
        }
    }

    @Test
    fun `leerDatosEvento devuelve el objeto correcto con entrada simulada`() {
        // Simular entradas de usuario como si se escribieran en consola
        val input = """
            Descripción de prueba
            2025-05-21
            Ubicación de prueba
            urgente;personal
        """.trimIndent()

        // Sustituir temporalmente System.in
        val originalIn: InputStream = System.`in`
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val consola = Consola()
        val resultado = consola.leerDatosEvento()

        // Restaurar System.in
        System.setIn(originalIn)

        // Asserts
        assertEquals("Descripción de prueba", resultado.descripcion)
        assertEquals("2025-05-21", resultado.fecha)
        assertEquals("Ubicación de prueba", resultado.ubicacion)
        assertEquals("urgente;personal", resultado.etiquetas)
    }

    @Test
    fun `pedirEstadoComoInt devuelve el número correcto con entrada simulada`() {
        // Simulamos que el usuario introduce "2"
        val input = "2\n"
        val originalIn: InputStream = System.`in`
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val consola = Consola()
        val estado = consola.pedirEstadoComoInt()

        System.setIn(originalIn)

        assertEquals(2, estado)
    }

    @Test
    fun testCambiarEstadoSimple() {
        consola.actividades.elementos.clear()
        consola.actividades.elementos.add(Tarea("Tarea test"))

        consola.cambiarEstado()

        val tarea = consola.actividades.elementos[0] as? Tarea
        val estado = tarea?.estado
        assertEquals(Estado.ABIERTA, estado)
    }
}