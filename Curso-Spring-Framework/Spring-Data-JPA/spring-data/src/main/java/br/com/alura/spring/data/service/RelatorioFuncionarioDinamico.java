package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
    
    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Digite o nome: ");
        String nome = scanner.nextLine();
        if(nome.trim().isEmpty()) nome = null;

        System.out.println("Digite o cpf: ");
        String cpf = scanner.nextLine();
        if(cpf.trim().isEmpty()) cpf = null;

        System.out.println("Digite o salário: ");
        Double salario = scanner.nextDouble();
        if(salario == 0) salario = null;

        scanner.nextLine();
        System.out.println("Data de contratação(dd/MM/yyyy): ");
		String dataContratacao = scanner.nextLine();
		LocalDate formataDataContratacao;
		try {
			formataDataContratacao = LocalDate.parse(dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch(Exception e) {
			System.out.println("Data inválida. Inserindo nulo");
			formataDataContratacao = null;
		}

        List<Funcionario> funcionarios = funcionarioRepository
                .findAll(Specification.where(SpecificationFuncionario.nome(nome))
                .or(SpecificationFuncionario.cpf(cpf))
                .or(SpecificationFuncionario.salario(salario))
                .or(SpecificationFuncionario.dataContratacao(formataDataContratacao))
                ); // Faz a busca dinâmica do nome no SQL usando a interface que utiliza a CriteriaAPI
        funcionarios.forEach(System.out::println);
    }
}
