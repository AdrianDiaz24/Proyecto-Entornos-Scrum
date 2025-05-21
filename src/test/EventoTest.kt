import es.prog2425.taskmanager.dominio.Evento
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.string.shouldContain

class EventoTest : DescribeSpec({
    describe("Validación de fecha") {
        it("patronFecha no es constante") {
            Evento.patronFecha shouldContain "/"
        }
    }
})