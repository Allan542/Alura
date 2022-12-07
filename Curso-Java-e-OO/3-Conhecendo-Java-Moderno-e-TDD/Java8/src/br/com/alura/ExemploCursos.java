package br.com.alura;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.stream.Collectors;


class Curso {
    private String nome;
    private int alunos;

    public Curso(String nome, int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }

    public String getNome() {
        return nome;
    }

    public int getAlunos() {
        return alunos;
    }
}

public class ExemploCursos {

    public static void main(String[] args) {
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso("Python", 45));
        cursos.add(new Curso("Javascript", 150));
        cursos.add(new Curso("Java 8", 113));
        cursos.add(new Curso("C", 55));
        
        Consumer<Curso> cnsmr = (c) -> System.out.println(c.getNome());
        
        cursos.sort(Comparator.comparingInt(Curso::getAlunos));
        cursos.forEach(cnsmr);
        
        System.out.println("");
        
        // Métodos filter e map não são aplicados em Collections, Stream etc. por questões de´performance: seria necessário alterar ou criar outro
        cursos.stream()
                .filter(c -> c.getAlunos() >= 100) // não dá para inserir um method reference no forEach com print, por possuir comparação
                .map(Curso::getNome) // dá para usar method reference (tanto no print como aqui) porque o map está pegando o valor
                .forEach(System.out::println);
        
        int sum = cursos.stream()
                .filter(c -> c.getAlunos() >= 100)
                .mapToInt(Curso::getAlunos).sum();
        
        OptionalDouble media = cursos.stream()
                .filter(c -> c.getAlunos() >= 100)
                .mapToInt(Curso::getAlunos)
                .average();
        // Stream devolve uma corrente/fluxo de objetos, ou uma forma de se trabalhar com aqueles objetos dentro da lista
        // Stream não afeta a list/collection original, não gera impacto nenhum, não muda
        // Maneira lazy, lento
//        cursos.forEach(c -> System.out.println(c.getNome())); // Vai imprimir tudo por não ser afetado por stream
        System.out.println(sum);
        System.out.println(media);
        
        Optional<Curso> optionalCurso = cursos.stream()
                .filter(c -> c.getAlunos() >= 1000)
                .findAny(); // Optional permite o trabalho com null objects, que é opcional, pode ser que exista, pode ser que não exista
        System.out.println(optionalCurso);
        
        // curiosidade sobre Stream: ela não pode ser atribuida a mesma lista que está "sofrendo" stream, mesmo que devolva um valor,
        // porque Stream não é uma lista mesmo
        
        cursos.stream()
                .filter(c -> c.getAlunos() >= 100)
                .findAny()
                .ifPresent(cnsmr);
        
        Curso curso = optionalCurso.orElse(null);
        optionalCurso.ifPresent(cnsmr);
        
        
//        cursos = cursos.stream()
//                .filter(c -> c.getAlunos() >=100)
//                .collect(Collectors.toList()); // Transforma de volta dpara uma lista e pode ser atribuível para a lista inicial
//        cursos.stream()
//                .forEach(c -> System.out.println(c.getNome()));

          cursos.stream()
                  .filter(c -> c.getAlunos() >= 100)
                  .collect(Collectors.toMap(
                          c -> c.getNome(), // Key
                          c -> c.getAlunos())) // Value
                  .forEach((nome, alunos) -> System.out.println(nome + " tem " + alunos + " alunos"));
          
//          Curso curso = optionalCurso.orElse(null); // tanto orElse como get iriam jogar uma Exception
//          System.out.println(curso.getNome());
    }
}
