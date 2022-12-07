package br.com.alura.spring.data.service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService  {
	
	private Boolean system = true;
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
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
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.nextLine();
		
		Cargo cargo = new Cargo();	
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id: ");
		Integer id = scanner.nextInt();
		Cargo encontraCargo;
		try {
			encontraCargo = cargoRepository.findById(id).get();
		} catch (Exception e) {
			System.out.println("Cargo não encontrado!");
			return;
		}
		
		System.out.println("Se vazio, não alterará!");
		scanner.nextLine();
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.nextLine();
		if(descricao.trim().length() == 0) descricao = encontraCargo.getDescricao();
		
		Cargo cargo = new Cargo();
		if(encontraCargo.getId() == id) {			
			cargo.setId(id);
			if(descricao != null) cargo.setDescricao(descricao);
			cargoRepository.save(cargo);
			
			System.out.println("Atualizado");
		} else {
			System.out.println("ID Informado não existe!");
		}
	}
	
	private void visualizar() {
		System.out.println("/----------------------------------------\\");
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(System.out::println);
		System.out.println("/----------------------------------------\\");
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		Integer id = scanner.nextInt();
		
		cargoRepository.deleteById(id);
	}
}
