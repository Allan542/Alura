package br.com.alura;

import java.util.LinkedList;
import java.util.List;

public class TestaCurso {

    public static void main(String[] args) {
        Curso javaColecoes = new Curso("Dominando as coleções do Java", "Paulo Silveira");
        List<Aula> aulas = javaColecoes.getAulas();
        System.out.println(aulas); // Não queremos fazer dessa forma existindo uma lista dentro de curso
        
//        javaColecoes.getAulas().add(new Aula("Trabalhando com ArrayList", 21)); // Não funciona mais adicionar diretamente desta forma, pois a lista agora é uma lista não-modificável (imutável), só funciona apenas com o
        // o método adiciona criado na classe Curso
        javaColecoes.adiciona(new Aula("Trabalhando com ArrayList", 21));
        javaColecoes.adiciona(new Aula("Criando uma Aula", 20));
        javaColecoes.adiciona(new Aula("Modelando com coleções", 22));
        
        
//        aulas = javaColecoes.getAulas();
        
        System.out.println(javaColecoes.getAulas());
    }
    
}

// A diferença entre LinkedList e Arraylist é mais na questão de performance em alguns casos. Quando se tem listas muito longas e são necessários muitos for, a ArrayList vai usar um Array[] internamente
// Então, se pegar uma posição específica, como 15, ela vai diretamente na posição informada, sem rodeios. A LinkedList vai fazer uma identação(enésima) por toda a lista até achar essa posição, porém, de uma forma rápida.
// Arrays tem um problema grave de deletar elementos no meio ou no começo. Por exemplo: se deletar um elemento no começo e a sua lista tem 1000 elementos, ela vai pegar cada elemento e empurrar para frente 1 por 1
// até chegar no elemento 999 (ou seja, o 2º vira o 1º, o 3º vira o 2º, e assim sucessivamente até chegar no 1000º que vira o 999º). Isso também é valido para inserções no meio ou no começo. (Computação de consumo de tempo linear)
// LinkedList tem a desvantagem de quando você quer obter um valor dentro da lista. Por exemplo, pegar o último elemento de uma lista com 1000 elementos, ele vai percorrer toda a lista até chegar no valor desejado.
// Obs para alteração dentro de listas: Método  set pegando a posição que deseja alterar e o que deseja colocar no lugar