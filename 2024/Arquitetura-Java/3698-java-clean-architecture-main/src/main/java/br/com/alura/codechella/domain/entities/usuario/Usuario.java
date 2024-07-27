package br.com.alura.codechella.domain.entities.usuario;

import br.com.alura.codechella.domain.Endereco;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

// Entidade na Clean Architecture é uma classe que possui um ID (Identificador único). Regras de domínio tem que ficar no domínio (Ex.: Não pode cadastrar um usuário com menos de 18 anos).
public class Usuario {
    private String cpf;
    private String nome;
    private LocalDate nascimento;
    private String email;
    private Endereco endereco;

    public Usuario(String cpf, String nome, LocalDate nascimento, String email) {

        if(cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")){
            throw new IllegalArgumentException("Cpf no padrão incorreto!");
        }

        if(Period.between(nascimento, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("Usuário não pode ter menos que 18 anos");
        }

        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Usuario usuario)) {
            return false;
        }
        return Objects.equals(cpf, usuario.getCpf());
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "cpf='" + cpf + '\'' +
            ", nome='" + nome + '\'' +
            ", nascimento=" + nascimento +
            ", email='" + email + '\'' +
            ", endereco=" + endereco +
            '}';
    }
}
