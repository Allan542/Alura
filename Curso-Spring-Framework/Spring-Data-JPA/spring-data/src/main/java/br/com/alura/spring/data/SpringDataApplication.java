package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.service.RelatorioFuncionarioDinamico;
import br.com.alura.spring.data.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private boolean system = true;
	
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamico relatorioDinamico;
	
	public SpringDataApplication(CrudCargoService cargoService, 
			CrudFuncionarioService funcionarioService,
			CrudUnidadeTrabalhoService unidadeService,
			RelatoriosService relatoriosService,
			RelatorioFuncionarioDinamico relatorioDinamico) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeService = unidadeService;
		this.relatoriosService = relatoriosService;
		this.relatorioDinamico = relatorioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual ação você quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionário");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatório dinâmico");
			
			int action = scanner.nextInt();
			switch (action) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2:
				funcionarioService.inicial(scanner);
				break;
			case 3:
				unidadeService.inicial(scanner);
				break;
			case 4:
				relatoriosService.inicial(scanner);
				break;
			case 5:
				relatorioDinamico.inicial(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}

}
