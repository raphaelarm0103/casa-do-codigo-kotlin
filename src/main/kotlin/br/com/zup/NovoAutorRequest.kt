package br.com.zup

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected // no momento de compilação, o micronaut vai conseguir acessar esses atributos e métodos
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email @field:CampoUnico(field="email", Autor::class, message = "Email já cadastrado") val email: String,
    @field:NotBlank @field:Size(max=400) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String
) {
    fun toModel(enderecoConsulta: EnderecoResponse): Autor{

        val endereco = Endereco(enderecoConsulta, numero)

        return Autor(nome, email, descricao, endereco)
    }




}



