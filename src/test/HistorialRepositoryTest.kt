import es.prog2425.taskmanager.servicios.HistorialRepository
import io.kotest.core.spec.style.DescribeSpec

class HistorialRepositoryTest : DescribeSpec({

    describe("Métodos con caracteres no ASCII") {
        it("añadirModificationEstado funciona correctamente") {
            val repo = HistorialRepository()
            repo.añadirModificationEstado("Cambio")
        }
    }
})