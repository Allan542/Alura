package br.com.alura;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class TesteOptionalEStreamParaLista {

    public static void main(String[] args) {
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso("Python", 45));
        cursos.add(new Curso("Javascript", 150));
        cursos.add(new Curso("Java 8", 113));
        cursos.add(new Curso("C", 55));
        
         Optional<String> cursoOptional = cursos.stream()
                .filter(c -> c.getAlunos() >= 100)
                 .map(Curso::getNome)
                .findFirst();
         
         OptionalDouble mediaAlunos = cursos.stream()
                .mapToInt(Curso::getAlunos)
                .average();
         
         System.out.println(mediaAlunos.orElse(0));
         System.out.println(cursoOptional.orElse(""));
         
         List<Curso> lista = cursos.stream()
                 .filter(c -> c.getAlunos() > 50)
                 .collect(Collectors.toList());
         
         lista.forEach(curso -> System.out.println(curso.getNome()));
    }

}
