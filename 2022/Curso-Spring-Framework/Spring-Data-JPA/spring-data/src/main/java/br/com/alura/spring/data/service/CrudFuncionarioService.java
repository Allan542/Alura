package br.com.alura.spring.data.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService  {
	
	private Boolean system = true;
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeTrabalhoRepository unidadeRepository;
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
			CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de funcionário deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			switch(action) {
				case 1:
					salvar(scanner);
					break; 
				case 2:
					atualizar(scanner);
					break;
				case 3:
					visualizar(scanner);
					break;
				case 4:
					deletar(scanner);
					break;
				default:
					system = false;
					break;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Nome: ");
		String nome = scanner.nextLine();
		
		System.out.println("CPF: ");
		String cpf = scanner.nextLine();
		
		System.out.println("Salário(Se não seguir o padrão 0000.0, fica 0.0): ");
		String salario = scanner.nextLine();
		String decimalPattern = "([0-9]*)\\.([0-9]*)";
		Boolean ehDouble = Pattern.matches(decimalPattern, salario);
		Double formataSalario = 0.0;
		if(salario != null && ehDouble) formataSalario = Double.parseDouble(salario);
		
		System.out.println("Data de contratação(dd/MM/yyyy): ");
		String dataContratacao = scanner.nextLine();
		LocalDate formataDataContratacao;
		try {
			formataDataContratacao = LocalDate.parse(dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch(Exception e) {
			System.out.println("Data inválida. Inserindo padrão 01/01/2000");
			formataDataContratacao = LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		
		Iterable<Cargo> cargos = cargoRepository.findAll();
		
		System.out.println("Escolha dentre os cargos abaixo pelo seu ID (0 para não escolher): ");
		cargos.forEach(c -> System.out.println(c.getId() + " - " + c.getDescricao()));
		Integer cargoId = scanner.nextInt();
		Cargo cargo;
		try {			
			cargo = cargoRepository.findById(cargoId).get();
		} catch(Exception e) {
			cargo = null;
		}
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(formataSalario);		
		funcionario.setDataContratacao(formataDataContratacao);
		funcionario.setCargo(cargo);
		
		
		Iterable<UnidadeTrabalho> unidades = unidadeRepository.findAll();
		
		Boolean naoContem = true;
		while(naoContem) {
			unidades.forEach(u -> System.out.println(u.getId() + " - " + u.getDescricao()));
			System.out.println("Digite um ID que exista na tabela ou 0 para terminar: ");
			Integer idUnidade = scanner.nextInt();
			UnidadeTrabalho unidade = null;
			try {
				unidade = unidadeRepository.findById(idUnidade).get();
				
				if(!funcionario.getUnidadesTrabalho().contains(unidade)) {
					funcionario.adicionaUnidadadeDeTrabalho(unidade);
					System.out.println("Funcionário adicionado a uma unidade com sucesso.");
				} else {
					break;
				}
			} catch(Exception e) {
				System.out.println("Unidade de trabalho não encontrada.");
				break;
			}
			
		}
		
		funcionarioRepository.save(funcionario);

		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scanner) {
		
		System.out.println("Id: ");
		Integer id = scanner.nextInt();
		Funcionario encontraFunc;
		
		try {			
			encontraFunc = funcionarioRepository.findById(id).get();
		} catch(Exception e) {
			System.out.println("Funcionário não encontrado.");
			return;
		}
		
		System.out.println("Se vazio, não alterará!");
		scanner.nextLine();
		System.out.println("Nome: ");
		String nome = scanner.nextLine();
		if(nome.trim().isEmpty()) nome = encontraFunc.getNome();
		
		System.out.println("CPF: ");
		String cpf = scanner.nextLine();
		if(cpf.trim().isEmpty()) cpf = encontraFunc.getCpf();
		
		System.out.println("Salário(Se não seguir o padrão 10.0, 0.0): ");
		String salario = scanner.nextLine();
		String decimalPattern = "([0-9]*)\\.([0-9]{2})";
		Boolean ehDouble = Pattern.matches(decimalPattern, salario);
		Double formataSalario = 0.0;
		if(!salario.trim().isEmpty() && ehDouble) formataSalario = Double.parseDouble(salario);
		else formataSalario = encontraFunc.getSalario();
		
		System.out.println("Data de contratação(dd/MM/yyyy): ");
		String dataContratacao = scanner.nextLine();
		LocalDate formataDataContratacao;
		try {
			formataDataContratacao = LocalDate.parse(dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch(Exception e) {
			formataDataContratacao = encontraFunc.getDataContratacao();
		}
		
		Iterable<Cargo> cargos = cargoRepository.findAll();
		
		System.out.println("Escolha dentre os cargos abaixo pelo seu ID (0 para não alterar): ");
		cargos.forEach(c -> System.out.println(c.getId() + " - " + c.getDescricao()));
		Integer cargoId = scanner.nextInt();
		Cargo cargo;
		try {
			cargo = cargoRepository.findById(cargoId).get();
		} catch(Exception e) {
			cargo = encontraFunc.getCargo();
		}
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(formataSalario);		
		funcionario.setDataContratacao(formataDataContratacao);
		funcionario.setCargo(cargo);
		
		Iterable<UnidadeTrabalho> unidades = unidadeRepository.findAll();
		
		Boolean naoValido = true;
		funcionario.setUnidadesTrabalho(encontraFunc.getUnidadesTrabalho());
		while(naoValido) {
			unidades.forEach(u -> System.out.println(u.getId() + " - " + u.getDescricao()));
			System.out.println("Digite um ID que exista na tabela ou 0 para terminar: ");
			Integer idUnidade = scanner.nextInt();

			UnidadeTrabalho unidade = null;
			try {
				unidade = unidadeRepository.findById(idUnidade).get();
				
				System.out.println("Deseja adicionar ou remover? ");
				System.out.println("1 - Adicionar");
				System.out.println("2 - Remover");
				Integer opcao = scanner.nextInt();
				
				if(!funcionario.getUnidadesTrabalho().contains(unidade) && opcao == 1) {
					funcionario.adicionaUnidadadeDeTrabalho(unidade);
					System.out.println("Funcionário adicionado a uma unidade com sucesso.");
				} else if(funcionario.getUnidadesTrabalho().contains(unidade) && opcao == 2) {
					funcionario.removeUnidadeDeTrabalho(unidade);
					System.out.println("Funcionário removido desta unidade com sucesso.");
				} else if(opcao < 1 || opcao > 2) {
					System.out.println("Opcão Inválida!");
				} else {
					naoValido = false;
					System.out.println("Funcionário já existe nesta unidade para ser adicionado, ou não existe para ser removido.");
				}
			} catch(Exception e) {
				System.out.println("Unidade de trabalho não encontrada.");
				naoValido = false;
			}
			
		}
		
		funcionarioRepository.save(funcionario);

		System.out.println("Salvo");
	}
	
	private void visualizar(Scanner scanner) {
		System.out.println("Qual página você deseja visualizar: ");
		Integer page = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println("/----------------------------------------\\");		
		System.out.println(funcionarios);
		System.out.println("Pagina atual: " + funcionarios.getNumber());
		System.out.println("Total de elementos: " + funcionarios.getNumberOfElements());
		funcionarios.forEach(System.out::println);
		System.out.println("/----------------------------------------\\");
		
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id: ");
		Integer id = scanner.nextInt();
		
		funcionarioRepository.deleteById(id);
	}
}
