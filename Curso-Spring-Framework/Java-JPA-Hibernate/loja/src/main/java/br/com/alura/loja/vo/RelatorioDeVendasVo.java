package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {
	private String nomeProduto;
	private Long quantidadeVendida;
	private LocalDate dataUltimaVenda;
	
	public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}
	
	@Override
	public String toString() {
		return "|" + centralizaString(nomeProduto, 30) + "|" + centralizaString(quantidadeVendida.toString(), 30) + "|" + centralizaString(dataUltimaVenda.toString(), 30) + "|";
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
