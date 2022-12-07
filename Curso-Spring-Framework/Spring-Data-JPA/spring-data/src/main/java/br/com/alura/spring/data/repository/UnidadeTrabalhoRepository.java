package br.com.alura.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.UnidadeTrabalho;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<UnidadeTrabalho, Integer> { // <Qual é o tipo do objeto que você quer que seja criado um Repository para ele, O tipo do Id deste>
	
}
