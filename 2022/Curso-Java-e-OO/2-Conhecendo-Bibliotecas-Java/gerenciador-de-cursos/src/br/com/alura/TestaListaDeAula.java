package br.com.alura;

import java.util.*;

public class TestaListaDeAula {

    public static void main(String[] args) {
        Aula a1 = new Aula("Revisitando os ArrayLists", 21);
        Aula a2 = new Aula("Lista de objetos", 20);
        Aula a3 = new Aula("Relacionamento de listas e objetos", 15);
        
        List<Aula> aulas = new ArrayList<>();
        aulas.add(a1);
        aulas.add(a2);
        aulas.add(a3);
        
        System.out.println(aulas);
        
        Collections.sort(aulas);
        
        System.out.println(aulas);
        
        Collections.sort(aulas, Comparator.comparing(Aula::getTempo));
//        Collections.sort(aulas, (au1, au2) -> Integer.compare(au1.getTempo(), au2.getTempo()));
        System.out.println(aulas);
        
        aulas.sort(Comparator.comparing(Aula::getTempo));
    }
    
}
