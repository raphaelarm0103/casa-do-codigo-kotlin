package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(val autorRepository: AutorRepository, val enderecoClient : EnderecoClient) {

    @Post
    fun cadastra(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {
        println("Requisição -> $request")
        try {
            val enderecoResponse = enderecoClient.consulta(request.cep)

            val autor = request.toModel(enderecoResponse.body()!!)

            autorRepository.save(autor)

            val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))

            println("Criando Autor {${request.nome}}")

            return HttpResponse.created(uri)
        }catch (e: Exception){
            return HttpResponse.notFound()
        }


    }
}