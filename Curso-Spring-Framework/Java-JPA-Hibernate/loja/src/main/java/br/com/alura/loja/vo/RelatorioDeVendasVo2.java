package br.com.alura.loja.vo;

import java.math.BigDecimal;

public class RelatorioDeVendasVo2 {
	
	private String nomeCliente;
	private String cpfCliente;
	private String nomeProduto;
	private String descProduto;
	private String catProduto;
	private BigDecimal precoProduto;
	private BigDecimal ìtemPrecoUnitario;
	private Long idItemPedido;
	private Long idPedido;
	
	public RelatorioDeVendasVo2() {
		
	}
	
	public RelatorioDeVendasVo2(Long idItemPedido, String nomeCliente, String cpfCliente, String nomeProduto,
			String descProduto, String catProduto, BigDecimal precoProduto, BigDecimal ìtemPrecoUnitario, Long idPedido) {
		this.idItemPedido = idItemPedido;
		this.nomeCliente = nomeCliente;
		this.cpfCliente = cpfCliente;
		this.nomeProduto = nomeProduto;
		this.descProduto = descProduto;
		this.catProduto = catProduto;
		this.precoProduto = precoProduto;
		this.ìtemPrecoUnitario = ìtemPrecoUnitario;
		this.idPedido = idPedido;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public BigDecimal getPrecoProduto() {
		return precoProduto;
	}

	public String getDescProduto() {
		return descProduto;
	}

	public String getCatProduto() {
		return catProduto;
	}

	public BigDecimal getÌtemPrecoUnitario() {
		return ìtemPrecoUnitario;
	}


	@Override
	public String toString() {
		return centralizaString(idItemPedido.toString(), 20) +
				"|" + centralizaString(nomeCliente, 30) +
				"|" + centralizaString(cpfCliente, 30) +
				"|" + centralizaString(nomeProduto, 30) +
				"|" + centralizaString(descProduto, 100) +
				"|" + centralizaString(catProduto, 30) +
				"|" + centralizaString(precoProduto.toString(), 30) +
				"|" + centralizaString(ìtemPrecoUnitario.toString(), 30) +
				"|" + centralizaString(idPedido.toString(), 20) + "|";
	}
	
	public StringBuilder centralizaString(String s, Integer maxTam) {
		StringBuilder centro = new StringBuilder(s);
		while(centro.length() < maxTam) {
			centro.insert(0, " ");
			if (centro.length() == maxTam) break;
			centro.append(" ");
		}
		return centro;
	}
}
