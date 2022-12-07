package br.com.alura;

import java.util.Iterator;
import java.util.Set;

public class TestaCursoComAluno {

    public static void main(String[] args) {
        Curso javaColecoes = new Curso("Dominando as coleções do Java", "Paulo Silveira");

        javaColecoes.adiciona(new Aula("Trabalhando com ArrayList", 21));
        javaColecoes.adiciona(new Aula("Criando uma Aula", 20));
        javaColecoes.adiciona(new Aula("Modelando com coleções", 24));
        
        Aluno a1 = new Aluno("Rodrigo Turini", 34672);
        Aluno a2 = new Aluno("Guilherme Silveira", 5617);
        Aluno a3 = new Aluno("Mauricio Aniche", 17645);
        
        javaColecoes.matricula(a1);
        javaColecoes.matricula(a2);
        javaColecoes.matricula(a3);
        
        Set<Aluno> alunos = javaColecoes.getAlunos();
        
        alunos.forEach(aluno -> System.out.println(aluno));
        
        Iterator<Aluno> iterador = alunos.iterator();
        while(iterador.hasNext()) {
            Aluno proximo = iterador.next();
            System.out.println(proximo);
        }
        
        Aluno depoisDoUltimo = iterador.next(); // NoSuchElementException
        
//        System.out.println("Todos os alunos matriculados: ");
//        javaColecoes.getAlunos().forEach(a -> System.out.println(a));
        
//        System.out.println("O aluno " + a1 + " está matriculado?");
//        System.out.println(javaColecoes.estaMatriculado(a1));
//        
//        Aluno turini = new Aluno("Rodrigo Turini", 34672);
//        System.out.println("E esse Turini, está matriculado?");
//        System.out.println(javaColecoes.estaMatriculado(turini));
//        
//        System.out.println("O a1 é equals ao turini?");
//        System.out.println(a1.equals(turini));
//        
//        // obrigatoriamente o seguinte é true
//        System.out.println(a1.hashCode() == turini.hashCode());
        
//        Set<Aluno> alunos = javaColecoes.getAlunos(); // Se passasse via construtor, então daria certo
//        alunos.add(new Aluno("Maicon Souza", 22315)); 
        
//        Set<Aluno> alunosSincronizados = Collections.synchronizedSet(alunos); // Set sincronizado (threadsafe)
//        alunosSincronizados.add(new Aluno("Maicon Souza", 22315)); // Não funciona também
        
//        System.out.println(alunosSincronizados);
    }

}
