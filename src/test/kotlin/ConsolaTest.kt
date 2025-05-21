import es.prog2425.taskmanager.dominio.Tarea
import es.prog2425.taskmanager.presentacion.Consola
import es.prog2425.taskmanager.servicios.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import java.io.ByteArrayInputStream

class ConsolaTest : DescribeSpec({

    val mockHistorial = mockk<HistorialRepository>(relaxed = true)
    val mockActividades = mockk<ActividadService>(relaxed = true)
    val mockUsuarios = mockk<UsuarioService>(relaxed = true)
    val consola = Consola(mockHistorial, mockActividades, mockUsuarios)

    describe("Consola") {

        beforeTest {
            clearMocks(mockHistorial, mockActividades, mockUsuarios)
        }

        it("menu devuelve la opción seleccionada por el usuario") {
            System.setIn(ByteArrayInputStream("3\n".toByteArray()))
            val opcion = consola.menu()
            opcion shouldBe 3
        }

        it("listarActividades devuelve false si no hay actividades") {
            every { mockActividades.elementos } returns mutableListOf()
            val resultado = consola.listarActividades()
            resultado shouldBe false
        }

        it("listarActividades devuelve true si hay actividades") {
            val tarea = mockk<Tarea>(relaxed = true)
            every { tarea.obtenerDetalle() } returns "Tarea de ejemplo"
            every { mockActividades.elementos } returns mutableListOf(tarea)

            val resultado = consola.listarActividades()
            resultado shouldBe true
        }
    }
})
