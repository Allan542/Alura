package br.com.alura;

import java.util.Collections;
import java.util.Set;

public class TesteEmptySet {

    public static void main(String[] args) {
        Set<String> nomes = Collections.emptySet();
        
        nomes.add("Paulo"); // Não inseriu porque é uma lista vazia imutável, serializável e parametrizável
    }

}
