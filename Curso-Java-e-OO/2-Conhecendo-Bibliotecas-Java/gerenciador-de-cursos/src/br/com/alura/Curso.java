package br.com.alura;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class Curso {
    
    private String nome;
    private String instrutor;
    private List<Aula> aulas = new LinkedList<Aula>();
    private Set<Aluno> alunos = new HashSet<>();
    private Map<Integer, Aluno> matriculaParaAluno = new HashMap<>();

    public Curso(String nome, String instrutor) {
        this.nome = nome;
        this.instrutor = instrutor;
    }

    public List<Aula> getAulas() {
        return Collections.unmodifiableList(aulas);
    }

    public String getNome() {
        return nome;
    }

    public String getInstrutor() {
        return instrutor;
    }
    
    public void adiciona(Aula aula){
        this.aulas.add(aula);
    }
    
    public int getTempoTotal() {
//        int tempoTotal = 0;
//        for (Aula aula : aulas) {
//            tempoTotal += aula.getTempo();
//        }
//        return tempoTotal;
        // ou
        return this.aulas.stream().mapToInt(Aula::getTempo).sum(); // a mesma proposta do código acima
    }
    
    @Override
    public String toString() {
        return "[Curso: " + nome + ", tempo total: " + this.getTempoTotal() + ", " + "Aulas:" + this.aulas +"]";
    }

    void matricula(Aluno aluno) {
        this.alunos.add(aluno);
        this.matriculaParaAluno.put(aluno.getNumeroMatricula(), aluno);
    }

    public Set<Aluno> getAlunos() {
        return Collections.unmodifiableSet(alunos);
    }

    boolean estaMatriculado(Aluno aluno) {
        return this.alunos.contains(aluno);
    }
    
    public Aluno buscaMatriculado(int numero) {
        if(!matriculaParaAluno.containsKey(numero))
            throw new NoSuchElementException();
        return matriculaParaAluno.get(numero); // HashMap e seus métodos são úteis para performance
        // Retorna o valor ao qual essa chave foi mapeada
    }
}
