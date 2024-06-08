package br.com.alura.codechella.domain;

// Endereco é uma classe que vai constituir um ValueObject. A diferença de uma Entity para um ValueObject é que um ValueObject é uma classe onde todos os seus atributos vão compor se é um objeto igual ao outro.
// Ex.: Se tiver um objeto com um determinado endereço e outro objeto com mesmo endereço, significa que eles são iguais. Então a composição dos 3 campos diz se um objeto é igual ao outro.
// No caso de Usuário, que pode ter um usuário com informações únicas, porém ele possui um Identificador Único que é o cpf, o que não o torna um ValueObject
public class Endereco {
    private String cep;
    private Integer numero;
    private String complemento;

    public Endereco(String cep, Integer numero, String complemento) {
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
