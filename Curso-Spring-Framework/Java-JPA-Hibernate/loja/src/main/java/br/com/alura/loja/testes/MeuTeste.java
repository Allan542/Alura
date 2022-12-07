package br.com.alura.loja.testes;

import java.math.BigDecimal;
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
import br.com.alura.loja.vo.RelatorioDeVendasVo2;

public class MeuTeste {

	public static void main(String[] args) {
		insereNoBanco();
		fazPedido();
	}

	private static void fazPedido() {
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDAO pDao = new ProdutoDAO(em);
		ClienteDAO cliDao = new ClienteDAO(em);
		PedidoDAO pedDao = new PedidoDAO(em);
		
		em.getTransaction().begin();
		
		Cliente cliente = cliDao.buscarPorId(1l);
		Cliente cliente2 = cliDao.buscarPorId(2l);
		
		Produto p1 = pDao.buscarPorId(1l);
		Produto p2 = pDao.buscarPorId(2l);
		Produto p3 = pDao.buscarPorId(3l);
		
		Pedido ped = new Pedido(cliente);
		Pedido ped2 = new Pedido(cliente2);
		
		ped.adicionarItem(new ItemPedido(90, ped, p1));
		ped.adicionarItem(new ItemPedido(10, ped, p2));
		ped.adicionarItem(new ItemPedido(40, ped, p3));
		
		p1.setPreco(new BigDecimal("1600"));
		p2.setPreco(new BigDecimal("4000"));
		p3.setPreco(new BigDecimal("7000"));
		
		ped2.adicionarItem(new ItemPedido(45, ped2, p1));
		ped2.adicionarItem(new ItemPedido(5, ped2, p2));
		ped2.adicionarItem(new ItemPedido(20, ped2, p3));
		
		pedDao.cadastrar(ped);
		pedDao.cadastrar(ped2);
		
		em.getTransaction().commit();
		
		RelatorioDeVendasVo2 vo2 = new RelatorioDeVendasVo2();
		List<RelatorioDeVendasVo2> relatorio = pedDao.relatorioDeVendas2();
		
		System.out.println(vo2.centralizaString("ID do Item do Pedido", 20) +
				"|" + vo2.centralizaString("Nome do Cliente", 30) +
				"|" + vo2.centralizaString("CPF do Cliente", 30) +
				"|" + vo2.centralizaString("Nome do Produto", 30) +
				"|" + vo2.centralizaString("Descrição do Produto", 100) +
				"|" + vo2.centralizaString("Categoria do Produto", 30) +
				"|" + vo2.centralizaString("Preço do Produto".toString(), 30) +
				"|" + vo2.centralizaString("Preço Unitário do Pedido".toString(), 30) + 
				"|" + vo2.centralizaString("Id do Pedido", 20) + "|");
		
		int max = 400;
		for(int i = 0; i <= max; i++) {
			System.out.print(i == max ? "\n" : "-");
		}
		
		relatorio.forEach(System.out::println);
	}

	public static void insereNoBanco() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria moveis = new Categoria("MOVEIS");
		Categoria computadores = new Categoria("COMPUTADORES");
		
		Produto celular = new Produto("Samsung Galaxy M21S", "Celular Samsung com 2Ghz de Processador e 4GB de RAM", new BigDecimal("1300"), celulares);
		Produto movel = new Produto("Guarda-Roupa", "Guarda-Roupa de madeira boa", new BigDecimal("3300"), moveis);
		Produto computador = new Produto("Notebook Dell", "Notebook com Intel a 13a geração gamer", new BigDecimal("5000"), computadores);
		
		Cliente cliente = new Cliente("Allan", "1234");
		Cliente cliente2 = new Cliente("Allan 2", "12345");
		
		EntityManager em = JPAUtil.getEntityManager();
		
		CategoriaDAO cDao = new CategoriaDAO(em);
		ProdutoDAO pDao = new ProdutoDAO(em);
		ClienteDAO cliDao = new ClienteDAO(em);
		
		
		em.getTransaction().begin();
		
		cDao.cadastrar(celulares);
		cDao.cadastrar(moveis);
		cDao.cadastrar(computadores);
		
		pDao.cadastrar(celular);
		pDao.cadastrar(movel);
		pDao.cadastrar(computador);
		
		cliDao.cadastrar(cliente);
		cliDao.cadastrar(cliente2);
		
		em.getTransaction().commit();
		em.close();
	}
}
