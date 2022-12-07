package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService  {
	
	private Boolean system = true;
	private final UnidadeTrabalhoRepository unidadeRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de cargo deseja executar");
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
					visualizar();
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
		System.out.println("Descrição: ");
		String descricao = scanner.nextLine();
		
		System.out.println("Endereço: ");
		String endereco = scanner.nextLine();
		
		UnidadeTrabalho unidade = new UnidadeTrabalho();
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		unidadeRepository.save(unidade);
		
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id: ");
		Integer id = scanner.nextInt();
		UnidadeTrabalho unidade = new UnidadeTrabalho();
		UnidadeTrabalho encontraUnidade;
		try {
			encontraUnidade = unidadeRepository.findById(id).get();
		} catch (Exception e) {
			System.out.println("Unidade de trabalho não encontrada!");
			return;
		}
		
		System.out.println("Se vazio, não alterará!");
		scanner.nextLine();
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.nextLine();
		if(descricao.trim().length() == 0) descricao = encontraUnidade.getDescricao();
		
		System.out.println("Endereço: ");
		String endereco = scanner.nextLine();
		if(endereco.trim().length() == 0) endereco = encontraUnidade.getEndereco();
		
		unidade.setId(id); 		
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		if(descricao.length() > 0) unidadeRepository.save(unidade);
		
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
		System.out.println("/----------------------------------------\\");
		Iterable<UnidadeTrabalho> unidades = unidadeRepository.findAll();
		unidades.forEach(System.out::println);
		System.out.println("/----------------------------------------\\");
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		Integer id = scanner.nextInt();
		
		unidadeRepository.deleteById(id);
	}
}
