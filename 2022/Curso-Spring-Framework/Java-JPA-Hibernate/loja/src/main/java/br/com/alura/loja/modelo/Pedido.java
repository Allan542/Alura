package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
//	@JoinTable() cria a tabela join para associar many to many/outros
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // se não mapear o outro lado de uma relação bidirecional, ele criará outra tabela representando many to many. Cascade fará a inserção automática na tabela itens_pedidos quando um pedido for inserido (persist), sem a necessidade da criação de outra classe DAO especificamente para isso, porém, neste exemplo, o cascade executará o comando SQL automaticamente para todo tipo de ação, seja deletar, atualizar etc.
	// Propagar as operações realizadas em uma entidade em seu relacionamento. (REFERÊNCIA A UMA TABELA CHAMADA itens_pedidos ManyToMany)
	private List<ItemPedido> itens = new ArrayList<>();
	
	public Pedido() {
		
	}
	
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void adicionarItem(ItemPedido item) {
		//item.setPedido(this); // precisa setar o pedido, para que os dois lados da relação sejam validados corretamente. (Na verdade está incorreto caso você já coloque o pedido no construtor de ItemPedido/tabela n para n, porque teoricamente, vc já setou um pedido, então isso só faz sentido se vc não setar um pedido no construtor de ItemPedido (já que este método pertence a pedidos, aí faz sentido setar))
		this.itens.add(item);
		
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();//String.format("%s, %s, %s, %s", nome, descricao, valorTotal.toString(), categoria.getNome());
	}

	public List<ItemPedido> getItens() {
		return itens;
	}
}
