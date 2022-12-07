package br.com.alura;

import java.util.HashSet;
import java.util.Set;

public class TesteAlunosSemAcento {

    public static void main(String[] args) {
        Set<String> alunos = new HashSet<>();
        alunos.add("Paulo");
        alunos.add("Alberto");
        alunos.add("Pedro");
        alunos.add("Nico");
        
        alunos.forEach(aluno -> System.out.println(aluno));
        System.out.println(alunos.size());
        alunos.add("Paulo");
        System.out.println(alunos.size());
    }

}
