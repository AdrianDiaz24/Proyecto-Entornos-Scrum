import es.prog2425.taskmanager.Modelo.Actividad
import es.prog2425.taskmanager.dominio.Usuario
import es.prog2425.taskmanager.servicios.UsuarioRepository
import es.prog2425.taskmanager.servicios.UsuarioService
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class UsuarioServiceTest : DescribeSpec({

    val mockRepositorio = mockk<UsuarioRepository>(relaxed = true)
    val servicio = UsuarioService(mockRepositorio)

    describe("UsuarioService") {

        describe("crearUsuario") {
            it("debería crear correctamente un usuario llamado Inda") {
                every { mockRepositorio.crearUsuario("Inda") } returns true

                val resultado = servicio.crearUsuario("Inda")

                verify { mockRepositorio.crearUsuario("Inda") }
                resultado shouldBe true
            }

            it("debería fallar al intentar crear un usuario llamado Inda") {
                every { mockRepositorio.crearUsuario("Inda") } returns false

                val resultado = servicio.crearUsuario("Inda")

                verify { mockRepositorio.crearUsuario("Inda") }
                resultado shouldBe false
            }
        }

        describe("eliminarUsuarioPorNombre") {
            it("debería eliminar correctamente a Adrián") {
                every { mockRepositorio.eliminarUsuarioPorNombre("Adrián") } returns true

                val resultado = servicio.eliminarUsuarioPorNombre("Adrián")

                verify { mockRepositorio.eliminarUsuarioPorNombre("Adrián") }
                resultado shouldBe true
            }

            it("debería fallar al intentar eliminar a Adrián") {
                every { mockRepositorio.eliminarUsuarioPorNombre("Adrián") } returns false

                val resultado = servicio.eliminarUsuarioPorNombre("Adrián")

                verify { mockRepositorio.eliminarUsuarioPorNombre("Adrián") }
                resultado shouldBe false
            }
        }

        describe("mostrarTodos") {
            it("debería mostrar los detalles de todos los usuarios disponibles") {
                val usuario1 = mockk<Usuario>()
                val usuario2 = mockk<Usuario>()

                every { usuario1.obtenerDetalle() } returns "Usuario: Fran"
                every { usuario2.obtenerDetalle() } returns "Usuario: Alejandro"
                every { mockRepositorio.obtenerTodos() } returns listOf(usuario1, usuario2)

                servicio.mostrarTodos()

                verify { usuario1.obtenerDetalle() }
                verify { usuario2.obtenerDetalle() }
            }

            it("no debería fallar si no hay usuarios registrados") {
                every { mockRepositorio.obtenerTodos() } returns emptyList()

                servicio.mostrarTodos()

                // No se espera ninguna excepción ni llamadas a obtenerDetalle
                verify(exactly = 0) { mockRepositorio.obtenerTodos().firstOrNull()?.obtenerDetalle() }
            }
        }

        describe("obtenerTodos") {
            it("debería devolver una lista con los usuarios actuales") {
                val usuarios = listOf<Usuario>(mockk(), mockk())
                every { mockRepositorio.obtenerTodos() } returns usuarios

                val resultado = servicio.obtenerTodos()

                resultado shouldBe usuarios
            }

            it("debería devolver una lista vacía si no existen usuarios") {
                every { mockRepositorio.obtenerTodos() } returns emptyList()

                val resultado = servicio.obtenerTodos()

                resultado shouldBe emptyList()
            }
        }

        describe("asignarTarea") {
            it("debería asignar correctamente una tarea a Fran") {
                val usuario = mockk<Usuario>()
                val tarea = mockk<Actividad>()

                every { mockRepositorio.asignarTarea(usuario, tarea) } returns true

                val resultado = servicio.asignarTarea(usuario, tarea)

                verify { mockRepositorio.asignarTarea(usuario, tarea) }
                resultado shouldBe true
            }

            it("debería fallar al intentar asignar una tarea a Fran") {
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
