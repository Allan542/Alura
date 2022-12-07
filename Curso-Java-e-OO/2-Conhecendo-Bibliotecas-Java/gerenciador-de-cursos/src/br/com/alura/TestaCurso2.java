package br.com.alura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestaCurso2 {

    public static void main(String[] args) {
        Curso javaColecoes = new Curso("Dominando as coleções do Java", "Paulo Silveira");

        javaColecoes.adiciona(new Aula("Trabalhando com ArrayList", 21));
        javaColecoes.adiciona(new Aula("Criando uma Aula", 20));
        javaColecoes.adiciona(new Aula("Modelando com coleções", 24));
        
        List<Aula> aulasImutaveis = javaColecoes.getAulas();
        System.out.println(aulasImutaveis);
        
        List<Aula> aulas = new ArrayList<>(aulasImutaveis); // Quando uma lista imutável for passada no construtor de outra lista, esta lista pode ser ordenada. Mesmo se adicionar um novo elemento, será adicionado apenas
        // nessa nova lista
        
        Collections.sort(aulas); // Obs: não é possível fazer nem mesmo um sort numa lista imutável

        
//        aulas.add(new Aula("Teste", 2));
        System.out.println(aulas);
        System.out.println(javaColecoes.getTempoTotal());
        System.out.println(javaColecoes);
        
        
        List novaListaImutavel = Collections.singletonList(aulas);
//        novaListaImutavel.add(new Aula("Criando nova Lista Imutável", 5)); // Joga uma UnsupportedOperationException por ser uma nova lista imutável
        System.out.println("Nova lista imutável: " + novaListaImutavel);
        
        List novaCopiaListaImutavel = Collections.nCopies(2, aulas); // Cria cópias de algo dentro dessa lista, neste caso, cria duas cópias da lista de Aulas em duas posições diferentes (listas dentro da lista)
//        novaCopiaListaImutavel.add(new Aula("Criando nova Lista Imutável", 5)); // Joga uma UnsupportedOperationException por ser uma nova lista imutável
        System.out.println("Nova cópia imutável: " + novaCopiaListaImutavel);
        
        List<Aula> list = new ArrayList<Aula>(Collections.nCopies(10, (Aula) null)); // Cria 10 cópias de null que sofre um type casting para ser convertido no tipo do Objeto Aula.
        System.out.println(list);
    }    
}