import es.prog2425.taskmanager.dominio.Usuario
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class UsuarioTest : DescribeSpec({
    describe("Usuario") {
        it("debe instanciarse correctamente con nombre") {
            val usuario = Usuario.instanciarUsuario("Ana")
            usuario.nombre shouldBe "Ana"
            usuario.listaTareas shouldBe emptyList()
        }
    }
})