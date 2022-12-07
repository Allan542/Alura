package br.com.alura.spring.data.orm;

// Interface based Projection (pode ser substituído por uma classe DTO (classe que recebe dados))
public interface FuncionarioProjecao {
    Integer getId();
    String getNome();
    Double getSalario();
}
