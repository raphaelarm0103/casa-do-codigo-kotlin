package br.com.zup

import javax.persistence.Embeddable

@Embeddable
class Endereco( enderecoResponse: EnderecoResponse,
                val numero: String
) {
    val cep = enderecoResponse.cep
    val logradouro = enderecoResponse.logradouro
    val complemento = enderecoResponse.complemento
    val bairro = enderecoResponse.bairro
    val localidade = enderecoResponse.localidade
    val uf =enderecoResponse.uf

}
