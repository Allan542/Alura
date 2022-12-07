package br.com.alura;

import java.util.HashSet;
import java.util.Set;

public class TestaAlunos {

    public static void main(String[] args) {
        Set<String> alunos = new HashSet<>(); // Não insere na ordem que foi colocado determinado valor e nem tem como dar get. Também, não é iterável. Tem uma grande vantagem de velocidade e performance no uso desta.
        // HashSet: tabela de espalhamento. mais rápido na hora de fazer uma busca, seja o contains ou remove

        
        alunos.add("Rodrigo Turini");
        alunos.add("Alberto Souza");
        alunos.add("Nico Steppat");
        alunos.add("Sérgio Lopes");
        alunos.add("Renan Saggio");
        alunos.add("Mauricio Aniche");
        
        System.out.println(alunos.size()); // 6
        alunos.add("Alberto Souza"); // Não vai adicionar, pois o HashSet garante que não haverá elementos repetidos dentro dele
        
        System.out.println(alunos.size()); // 6 em vez de 7 porque não adiciona repetidos
        
        boolean pauloEstaMatriculado = alunos.contains("Paulo Silveira");
        alunos.remove("Sergio Lopes");
        System.out.println(pauloEstaMatriculado + "\n");
        
        for (String aluno : alunos) {
            System.out.println(aluno);
        }
        
        System.out.println();
        alunos.forEach(aluno -> System.out.println(aluno));
        
        System.out.println(alunos);
    }
    
}
