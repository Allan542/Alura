package br.com.alura.gerenciador.modelo;

import java.util.Date;

public class Empresa {
    
    private static Integer cont = 0;
    private Integer id;
    private String nome;
    private Date dataAbertura = new Date();
    
    public Empresa() {
        this.cont += 1;
        this.id = cont; 
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

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    
    
}
