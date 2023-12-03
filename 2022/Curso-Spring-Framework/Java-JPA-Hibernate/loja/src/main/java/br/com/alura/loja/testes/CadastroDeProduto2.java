package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto2 {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(celulares);
		celulares.setNome("Categoria"); // como a JPA está persistindo, ou seja, está gerenciando esta entidade no banco de dados, ela fará o update e commita/descarrega(flush) a alteração
		// para o banco de dados.

		em.getTransaction().commit();
		em.close(); // destacado, mesmo quando fecha o banco de dados, ele ainda sabe que a entidade persistida existe
		celulares.setNome("1234"); // ignora porque o banco de dados já foi fechado, então não ocorre mudanças
	}

}
