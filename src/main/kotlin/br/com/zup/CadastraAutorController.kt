package br.com.zup

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastra(@Body @Valid request: NovoAutorRequest){
        println("Requisição -> $request")

        val autor = request.toModel()

        autorRepository.save(autor)

        println("Criando Autor {${request.nome}}")

    }
}