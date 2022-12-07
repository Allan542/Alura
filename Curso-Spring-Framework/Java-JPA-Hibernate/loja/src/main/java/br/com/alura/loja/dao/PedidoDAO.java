package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;
import br.com.alura.loja.vo.RelatorioDeVendasVo2;

public class PedidoDAO {
	
	private EntityManager em;

	public PedidoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}

	public List<RelatorioDeVendasVo> relatorioDeVendas() {
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo(" // cria a instancia dessa classe que não é uma entidade, precisa do caminho completo da package, apenas com os valores para o construtor, depois, cria uma lista
				+ " produto.nome,"
				+ " SUM(item.quantidade),"
				+ " MAX(pedido.data))"
				+ " FROM Pedido pedido"
				+ " JOIN pedido.itens item"
				+ " JOIN item.produto produto"
				+ " GROUP BY produto.nome"
				+ " ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioDeVendasVo.class)
				.getResultList();
	}
	
	public List<RelatorioDeVendasVo2> relatorioDeVendas2() {
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo2(" // cria a instancia dessa classe que não é uma entidade, precisa do caminho completo da package, apenas com os valores para o construtor, depois, cria uma lista
				+ " item.id,"
				+ " cliente.nome,"
				+ " cliente.cpf,"
				+ " produto.nome,"
				+ " produto.descricao,"
				+ " categoria.nome,"
				+ " produto.preco,"
				+ " item.precoUnitario,"
				+ " pedido.id)"
				+ " FROM Pedido pedido"
				+ " JOIN pedido.cliente cliente"
				+ " JOIN pedido.itens item"
				+ " JOIN item.produto produto"
				+ " JOIN produto.categoria categoria"
				+ " ORDER BY cliente.nome ASC";
		return em.createQuery(jpql, RelatorioDeVendasVo2.class)
				.getResultList();
	}
	
	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class) // JOIN FETCH vai transformar apenas esta consulta em uma consulta Eager em vez de Lazy.
				.setParameter("id", id)
				.getSingleResult();
	}
}



// Teste do TestandoSetDoPedido
//public ItemPedido busca(Pedido p) {
//String jpql = "SELECT p FROM ItemPedido p WHERE p.pedido.id = :id";
//return em.createQuery(jpql, ItemPedido.class)
//		.setParameter("id", p.getId())
//		.getSingleResult();
//}

//public Pedido busca(Pedido p) {
//String jpql = "SELECT p FROM Pedido p JOIN p.itens item JOIN item.pedido ip ON ip.id = :id";
//return em.createQuery(jpql, Pedido.class)
//		.setParameter("id", p.getId())
//		.getSingleResult();
//}
