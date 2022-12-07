package br.com.alura.spring.data.orm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private Double salario;
	private LocalDate dataContratacao;

	@ManyToOne
	private Cargo cargo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<UnidadeTrabalho> unidadesTrabalho = new ArrayList();
	
	public Funcionario() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<UnidadeTrabalho> getUnidadesTrabalho() {
		return unidadesTrabalho;
	}
	
	public void setUnidadesTrabalho(List<UnidadeTrabalho> unidadesTrabalho) {
		this.unidadesTrabalho = unidadesTrabalho;
	}
	
	public void adicionaUnidadadeDeTrabalho(UnidadeTrabalho unidadeTrabalho) {
		this.unidadesTrabalho.add(unidadeTrabalho);
	}
	
	public void removeUnidadeDeTrabalho(UnidadeTrabalho unidade) {
		this.unidadesTrabalho.remove(unidade);
	}

	@Override
	public String toString() {
		String cargoDescricao;
		try {
			cargoDescricao = cargo.getDescricao();
		} catch(Exception e) {
			cargoDescricao = "";
		}
		return "id=" + id + " | nome=" + nome + " | cpf=" + cpf + " | salario=" + String.format("%.2f", salario)
				+ " | dataContratacao=" + dataContratacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | cargo=" + cargoDescricao + " | unidadesTrabalho=" + this.getUnidadesTrabalho();
	}

	
}
