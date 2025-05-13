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

            it("debe devolver false si no se puede crear el usuario") {
                every { mockRepositorio.crearUsuario("Juan") } returns false

                val resultado = servicio.crearUsuario("Juan")

                verify { mockRepositorio.crearUsuario("Juan") }
                resultado shouldBe false
            }
        }

        describe("eliminarUsuarioPorNombre") {
            it("debe llamar al método eliminarUsuarioPorNombre del repositorio y devolver true") {
                every { mockRepositorio.eliminarUsuarioPorNombre("Ana") } returns true

                val resultado = servicio.eliminarUsuarioPorNombre("Ana")

                verify { mockRepositorio.eliminarUsuarioPorNombre("Ana") }
                resultado shouldBe true
            }

            it("debe devolver false si no se puede eliminar el usuario") {
                every { mockRepositorio.eliminarUsuarioPorNombre("Ana") } returns false

                val resultado = servicio.eliminarUsuarioPorNombre("Ana")

                verify { mockRepositorio.eliminarUsuarioPorNombre("Ana") }
                resultado shouldBe false
            }
        }

        describe("mostrarTodos") {
            it("debe llamar a obtenerDetalle de todos los usuarios") {
                val usuario1 = mockk<Usuario>()
                val usuario2 = mockk<Usuario>()

                every { usuario1.obtenerDetalle() } returns "Usuario 1"
                every { usuario2.obtenerDetalle() } returns "Usuario 2"
                every { mockRepositorio.obtenerTodos() } returns listOf(usuario1, usuario2)

                servicio.mostrarTodos()

                verify { usuario1.obtenerDetalle() }
                verify { usuario2.obtenerDetalle() }
            }

            it("no debe imprimir nada si no hay usuarios") {
                every { mockRepositorio.obtenerTodos() } returns emptyList()

                mockkStatic(::println)
                every { println(any()) } just Runs

                servicio.mostrarTodos()

                verify(exactly = 0) { println(any()) }
            }
        }

        describe("obtenerTodos") {
            it("debe devolver una lista de usuarios desde el repositorio") {
                val usuarios = listOf<Usuario>(mockk(), mockk())
                every { mockRepositorio.obtenerTodos() } returns usuarios

                val resultado = servicio.obtenerTodos()

                resultado shouldBe usuarios
            }

            it("debe devolver una lista vacía si no hay usuarios") {
                every { mockRepositorio.obtenerTodos() } returns emptyList()

                val resultado = servicio.obtenerTodos()

                resultado shouldBe emptyList()
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

            it("debe devolver false si no se puede asignar la tarea") {
                val usuario = mockk<Usuario>()
                val tarea = mockk<Actividad>()

                every { mockRepositorio.asignarTarea(usuario, tarea) } returns false

                val resultado = servicio.asignarTarea(usuario, tarea)

                verify { mockRepositorio.asignarTarea(usuario, tarea) }
                resultado shouldBe false
            }
        }
    }
})