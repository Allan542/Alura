/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author silva
 */
public class TestandoListas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String aula1 = "Conhecendo mais de listas";
        String aula2 = "Modelando a classe Aula";
        String aula3 = "Trabalhando com Cursos e Sets";
        
        ArrayList<String> aulas = new ArrayList<>();
        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);
        
        System.out.println(aulas);
        
        aulas.remove(0);
        
        System.out.println(aulas);
        
        for (String aula : aulas) {
            System.out.println("Aula: " + aula);
        }
        
        String primeiraAula = aulas.get(0);
        System.out.println("A primeira aula é: " + primeiraAula);
        
        System.out.println(aulas.size());
        
        for (int i = 0; i < aulas.size(); i++) {
            System.out.println("Aula: " + aulas.get(i));
        }
        
        aulas.forEach(aula -> System.out.println("Percorrendo:\nAula " + aula));
        
        aulas.add("Aumentando nosso conhecimento");
        System.out.println(aulas);
        
//        Collections.sort(aulas);
        aulas.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("Depois de ordenado\n" + aulas);
        
        
        // Arraylist.sort em números
//        ArrayList<Integer> nums = new ArrayList<>();
//        nums.add(2);
//        nums.add(1);
//        nums.add(3);
//        nums.sort((n1, n2) -> Integer.compare(n1, n2));
//        System.out.println(nums);


        String curso1 = "Java 8: Tire proveito dos novos recursos da linguagem";
        String curso2 = "Apache Camel";
        String curso3 = "Certificação Java SE 8 Programmer I";
        
        ArrayList<String> cursos = new ArrayList<>();
        
        cursos.add(curso1);
        cursos.add(curso2);
        cursos.add(curso3);
        
        cursos.remove(2);
        
        System.out.println(cursos);
        
        System.out.println("O primeiro curso da lista é o: " + cursos.get(0));
        
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println("Aula: " + cursos.get(i));
        }
        
        Collections.sort(cursos);
        System.out.println("cursos");
    }
    
}
