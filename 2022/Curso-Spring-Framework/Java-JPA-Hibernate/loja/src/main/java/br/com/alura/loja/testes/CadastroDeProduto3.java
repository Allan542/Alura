package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto3 {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(celulares);
		celulares.setNome("Categoria"); 
		

		em.flush();
		em.clear();
		
		celulares = em.merge(celulares); // ele não muda o estado da entidade para managed, ele devolve uma nova referência que está no managed e a entidade continua detached
		celulares.setNome("1234"); // Reclama se não tiver um construtor padrão (vazio), porque ele faz um select
		em.flush();
		em.remove(celulares); // Passa do estado de managed para removed. Precisa necessariamente estar managed e não detached
		em.flush();
	}

}
