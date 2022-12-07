package br.com.alura.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	// Para ENUM: CELULARES, INFORMATICA, LIVROS;
	
	@EmbeddedId
	private CategoriaId id;
	
	public Categoria() {
		
	}
	
	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "xpto");
	}
	
	public String getNome() {
		return this.id.getNome();
	}
	
	public void setNome(String id) {
		this.id.setNome(id);
	}
	
	public CategoriaId getId() {
		return id;
	}
}
