package br.com.alura.spring.data.specification;

import java.time.LocalDate;

import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;
import org.springframework.data.jpa.domain.Specification;

import br.com.alura.spring.data.orm.Funcionario;

public class SpecificationFuncionario {
    
    public static Specification<Funcionario> nome(String nome) { // Uma Specification recebe o(s) parametro(s) que deseja buscar e retorna um Predicate/Expression Lambda
        // que recebe como parametros: um root(from), uma criteriaQuery (SELECT * FROM funcionarios, já que a Entity está sendo passada no tipo da Specification)
        // e uma criteriaBuilder(parâmetros para consulta (equals, like etc.))
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }

    public static Specification<Funcionario> cpf(String cpf) { 
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("cpf"), cpf);
    }

    public static Specification<Funcionario> salario(Double salario) { 
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.greaterThan(root.get("salario"), salario);
    }

    public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) { 
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao);
    }
}
