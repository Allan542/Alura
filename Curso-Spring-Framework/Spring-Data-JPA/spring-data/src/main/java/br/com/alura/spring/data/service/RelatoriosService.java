package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final FuncionarioRepository funcionarioRepository; // final é necesário
	// Essa etapa é o que a annotation autowired faz vulgo, criar um bean
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de cargo deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionário por nome");
			System.out.println("2 - Busca funcionário por nome, data de contratação e salário maior");
			System.out.println("3 - Busca funcionário por data de contratação");
			System.out.println("4 - Pesquisa funcionário por salário");

		
			int action = scanner.nextInt();
			switch(action) {
				case 1:
					buscaFuncionarioNome(scanner);
					break;
				case 2:
					buscaFuncionarioNomeSalarioMaiorData(scanner);
					break;
				case 3:
					buscaFuncionarioDataContratacao(scanner);
					break;
				case 4:
					pesquisaFuncionarioSalario();
					break;
				default:
					system = false;
					break;
			}
		}
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Qual nome deseja pesquisar: ");
		String nome = scanner.nextLine();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Nome: ");
		String nome = scanner.nextLine();
		
		System.out.println("Data de contratação(dd/MM/yyyy): ");
		String dataContratacao = scanner.nextLine();
		LocalDate formataDataContratacao;
		try {
			formataDataContratacao = LocalDate.parse(dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch(Exception e) {
			System.out.println("Data inválida. Inserindo padrão 01/01/2000");
			formataDataContratacao = LocalDate.parse("01/01/2000");
		}
		
		System.out.println("Salário: ");
		String salario = scanner.nextLine();
		String decimalPattern = "([0-9]*)\\.([0-9]*)";
		Boolean ehDouble = Pattern.matches(decimalPattern, salario);
		Double formataSalario = 0.0;
		if(salario.trim().length() != 0 && ehDouble) formataSalario = Double.parseDouble(salario);

		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, formataSalario, formataDataContratacao);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Data de contratação(dd/MM/yyyy): ");
		String dataContratacao = scanner.nextLine();
		LocalDate formataDataContratacao;
		try {
			formataDataContratacao = LocalDate.parse(dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch(Exception e) {
			System.out.println("Data inválida. Inserindo padrão 01/01/2000");
			formataDataContratacao = LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(formataDataContratacao);
		list.forEach(System.out::println);
	}

	private void pesquisaFuncionarioSalario() { 
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId() + 
			" | nome: " + f.getNome() + " | salario: " + f.getSalario()));
	}
}
