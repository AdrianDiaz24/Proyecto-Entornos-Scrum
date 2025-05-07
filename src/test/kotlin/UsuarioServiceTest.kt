import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.dominio.Usuario
import es.prog2425.taskmanager.servicios.UsuarioRepository
import es.prog2425.taskmanager.servicios.UsuarioService
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import java.sql.DriverManager.println

class UsuarioServiceTest : DescribeSpec({
    val mockRepositorio = mockk<UsuarioRepository>(relaxed = true)
    val servicio = UsuarioService(mockRepositorio)

    describe("UsuarioService") {

        describe("crearUsuario") {
            it("debe llamar al método crearUsuario del repositorio y devolver true") {
                every { mockRepositorio.crearUsuario("Juan") } returns true

                val resultado = servicio.crearUsuario("Juan")

                verify { mockRepositorio.crearUsuario("Juan") }
                resultado shouldBe true
            }
        }

        describe("eliminarUsuarioPorNombre") {
            it("debe llamar al método eliminarUsuarioPorNombre del repositorio y devolver true") {
                every { mockRepositorio.eliminarUsuarioPorNombre("Ana") } returns true

                val resultado = servicio.eliminarUsuarioPorNombre("Ana")

                verify { mockRepositorio.eliminarUsuarioPorNombre("Ana") }
                resultado shouldBe true
            }
        }

        describe("mostrarTodos") {
            it("debe imprimir los detalles de todos los usuarios") {
                val usuarioMock = mockk<Usuario>()
                every { usuarioMock.obtenerDetalle() } returns "Detalle del usuario"
                every { mockRepositorio.obtenerTodos() } returns listOf(usuarioMock)

                mockkStatic(::println)
                every { println(any()) } just Runs

                servicio.mostrarTodos()

                verify { println("Detalle del usuario") }
            }
        }

        describe("obtenerTodos") {
            it("debe devolver una lista de usuarios desde el repositorio") {
                val usuarios = listOf<Usuario>(mockk(), mockk())
                every { mockRepositorio.obtenerTodos() } returns usuarios

                val resultado = servicio.obtenerTodos()

                resultado shouldBe usuarios
            }
        }

        describe("asignarTarea") {
            it("debe delegar la asignación de tarea al repositorio y devolver el resultado") {
                val usuario = mockk<Usuario>()
                val tarea = mockk<Actividad>()

                every { mockRepositorio.asignarTarea(usuario, tarea) } returns true

                val resultado = servicio.asignarTarea(usuario, tarea)

                verify { mockRepositorio.asignarTarea(usuario, tarea) }
                resultado shouldBe true
            }
        }

    }
})