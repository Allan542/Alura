/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura;

/**
 *
 * @author silva
 */
public class Aluno {
    
    String nome;
    private int numeroMatricula;
    
    public Aluno(String nome, int numeroMatricula) {
        if(nome == null) {
            throw new NullPointerException("Nome não pode ser null");
        }
        this.nome = nome;
        this.numeroMatricula = numeroMatricula;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    @Override
    public String toString() {
        return "[Aluno: " + this.nome + ", Matrícula: " + this.numeroMatricula + "]";
    }

    @Override
    public boolean equals(Object obj) {
        Aluno outro = (Aluno) obj;
        return this.nome.equals(outro.nome); 
    }

    @Override
    public int hashCode() { // Se reescrever o método equals, o hashCode também deve ser reescrito para que todas as comparações que envolva ==, equals, ou qualquer outra forma de comparação seja true caso possuam o mesmo valor e sejam do mesmo tipo e não o mesmo "clone"
        return this.nome.hashCode(); // pega o hashcode de um atributo para fazer a diferenciação
    }
    
    
    
}
