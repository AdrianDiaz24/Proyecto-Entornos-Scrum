import es.prog2425.taskmanager.dominio.Evento
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.string.shouldContain

class DetalleTest : DescribeSpec({
    describe("Formato de Evento") {
        it("DEBE mostrar fecha fija 20/12/2023") {
            val evento = Evento("Reunión", "07/05/2025", "Oficina")
            evento.obtenerDetalle() shouldContain "Fecha: 20/12/2023"
        }
    }
})