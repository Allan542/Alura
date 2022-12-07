package br.com.alura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TestaPerformance {

    public static void main(String[] args) {

        //Collection<Integer> numerosArrayList = new ArrayList<>();
        //Collection<Integer> numerosHashSet = new HashSet<>();
        
        Collection<Integer> numeros = new ArrayList<>();
        Set<Integer> numerosSet = new HashSet<>();
        Set<Integer> numerosSincronizados = Collections.synchronizedSet(numerosSet);
        
        System.out.println("List Tempo gasto: " + identador(calculaTempo(numeros)));
        System.out.println("Set Tempo gasto: " + identador(calculaTempo(numerosSet)));
        System.out.println("SynchronizedSet Tempo gasto: " + identador(calculaTempo(numerosSincronizados)));
        
//        System.out.println("Tempo gasto: " + calculaTempoDeExecucao(numerosArrayList));
//        System.out.println("Tempo gasto: " + calculaTempoDeExecucao(numerosHashSet));
    }

    
    public static long[] calculaTempo(Collection<Integer> c){
        long inicio = System.currentTimeMillis();

        for (int i = 1; i <= 50000; i++) {
            c.add(i);
        }
        
        long meio = System.currentTimeMillis();

        for (Integer numero : c) {
            c.contains(numero);
        }

        long fim = System.currentTimeMillis();

        return new long[]{fim - meio, meio - inicio};

        
    }
    
    public static StringBuilder identador(long[] c) {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            msg.append(c[i]).append(i == 0 ? " - " : ".");
        }
        return msg;
    }
}