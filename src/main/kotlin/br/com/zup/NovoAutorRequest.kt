package br.com.zup

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected // no momento de compilação, o micronaut vai conseguir acessar esses atributos e métodos
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max=400) val descricao: String
) {
    fun toModel(): Autor{
        return Autor(nome, email, descricao)
    }
}



