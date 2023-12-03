package br.com.alura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TesteCollectionsThreadsafe {

    public static void main(String[] args) {
        Set<Integer> numeros = new HashSet<>();
        List<Integer> numeros2 = new ArrayList<>();
        
        for(int i = 0; i < 50000; i++) {
            numeros.add(i);
            numeros2.add(i);
        }
        
        long ini = System.currentTimeMillis();
        
        Set<Integer> numerosSincronizados = Collections.synchronizedSet(numeros);
        
        for (Integer numero : numerosSincronizados) {
            numerosSincronizados.contains(numero);
        }
        
        long fim = System.currentTimeMillis();
        
        System.out.println(fim-ini);
        
        
        
        ini = System.currentTimeMillis();
        
        for (Integer numero : numeros2) {
            numeros2.contains(numero);
        }
        
        fim = System.currentTimeMillis();
        
        
        System.out.println(fim-ini);
        
    }

}
