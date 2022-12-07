package br.com.alura.java.io.teste;

import java.io.Serializable;


/**
 * Classe que representa um cliente no Bytebank.
 *
 * @author Nico Steppat
 * @version 0.1
 */

// Com o serializable, é necessário colocar a serialVersionUID na classe. Recomendado manter este Version ID para que a classe não fique mudando a versão durante mudanças compatíveis, que são mudanças que não impactam diretamente na classe,
// como a adição de métodos.
// Para mudanças incompatíveis, como a mudança de um tipo de um atributo ou a adição do mesmo, o Version ID irá mudar ou deve ser mudado manualmente, aumentando a mesma.
public class Cliente implements Serializable {

    private static final long serialVersionUID = 9205117266306915548L;
    private String nome;
    private String cpf;
    private String profissao;
    
    public String getNomeCpf(){
        return nome + ", " + cpf;
    }
    
    public String getNomeCpfProfissao(){
        return nome + ", " + cpf + ", " + profissao;
    }

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
