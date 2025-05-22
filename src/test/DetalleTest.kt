import es.prog2425.taskmanager.dominio.Evento
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.string.shouldContain

class DetalleTest : DescribeSpec({
    describe("Formato de Evento") {
        it("DEBE incluir la fecha proporcionada") {
            val fechaTest = "07/05/2025"
            val evento = Evento("Reunión", fechaTest, "Oficina")
            evento.obtenerDetalle() shouldContain "Fecha: $fechaTest"
        }
    }
})