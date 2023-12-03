package br.com.bytebank.banco.modelo;

import java.io.Serializable;


/**
 * Classe que representa um cliente no Bytebank.
 *
 * @author Nico Steppat
 * @version 0.1
 */

// Se na classe-objeto que está criando um atributo do tipo cliente existe Serializable e esta não, ou, a mesma classe-objeto não colocou Cliente como transient, esta classe deve implementar Serializable também.
public class Cliente {

    private String nome;
    private String cpf;
    private String profissao;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getProfissao() {
        return profissao;
    }
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

}
