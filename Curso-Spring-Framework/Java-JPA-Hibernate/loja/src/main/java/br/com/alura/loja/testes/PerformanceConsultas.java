package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

// Existe dois tipos de carregamento na JPA: Eager e Lazy. O carregamento de relacionamento To One são Eager, faz o carregamento antecipado mesmo que não use.
// O carregamento de relacionamento To Many são Lazy, só carrega a lista na qual a annotation está referenciando se você acessar algum valor de dentro desta lista.
// Boa prática se todos os carregamentos forem lazy, setando o fetch como o mesmo de cada Annotation ToOne.
public class PerformanceConsultas {

	public static void main(String[] args) {
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDAO pedidoDao = new PedidoDAO(em);
		Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
		
		//System.out.println(pedido.getData());
//		System.out.println(pedido.getItens().size());
		em.close();
		System.out.println(pedido.getCliente().getNome()); // Joga uma Exception de Lazy porque o banco fechou antes de fazer uma consulta própria que traria o nome do cliente
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
		Produto macbook = new Produto("Mackbook", "Macbook pro retina", new BigDecimal("14000"), informatica);
		
		Cliente cliente = new Cliente("Rodrigo", "123456");
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(40, pedido, videogame));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(2, pedido2, macbook));
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		ClienteDAO clienteDao = new ClienteDAO(em);
		PedidoDAO pedidoDao = new PedidoDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares); // Como é uma relação ManyToOne, a persistencia da tabela que será o one, precisa vir primeiro, já que ela que manda o seu id como foreign key para outra tabela
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);
		
		clienteDao.cadastrar(cliente);
		
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		em.getTransaction().commit();
		em.close();
	}
}
