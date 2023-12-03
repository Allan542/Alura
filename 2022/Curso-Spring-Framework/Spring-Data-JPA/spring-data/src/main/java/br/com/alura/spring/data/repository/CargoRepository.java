package br.com.alura.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> { // <Qual é o tipo do objeto que você quer que seja criado um Repository para ele, O tipo do Id deste>
	
}
