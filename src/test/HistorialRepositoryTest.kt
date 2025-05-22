import es.prog2425.taskmanager.servicios.HistorialRepository
import io.kotest.core.spec.style.DescribeSpec

class HistorialRepositoryTest : DescribeSpec({
    describe("Registro de cambios") {
        it("anadirModificacionEstado registra cambios correctamente") {
            val repo = HistorialRepository()
            val tarea = mockk<Tarea>() {
                every { obtenerDetalle() } returns "Tarea prueba"
            }

            repo.anadirModificacionEstado(Estado.EN_PROGRESO, tarea, 1, 0)

            repo.obtenerHistorial() shouldHaveSize 1
        }
    }
})