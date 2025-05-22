import es.prog2425.taskmanager.dominio.Evento
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.string.shouldContain

class EventoTest : DescribeSpec({
    describe("Validación de fecha") {
        it("debe usar el formato correcto DD/MM/AAAA") {
            val evento = Evento("Reunión", "07/05/2025", "Oficina")
            evento.fecha shouldMatch Regex(Evento.PATRON_FECHA)
        }
    }
})