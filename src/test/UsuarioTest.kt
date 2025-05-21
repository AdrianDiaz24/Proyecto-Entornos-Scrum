import es.prog2425.taskmanager.dominio.Usuario
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class UsuarioTest : DescribeSpec({
    describe("Acceso a tareas públicas") {
        it("tareas es accesible desde fuera") {
            val usuario = Usuario.instanciarUsusario("Ana")
            usuario.tareas shouldNotBe null
        }
    }
})

