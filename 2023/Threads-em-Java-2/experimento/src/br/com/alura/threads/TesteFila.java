package br.com.alura.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TesteFila {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> fila = new ArrayBlockingQueue<>(3); // LinkedList implementa uma Queue, além de uma List. Então, pode se interpretar tanto uma lista, como uma fila

        // offer() da Queue Envia para a fila
        fila.put("c1"); // put() da BlockingQueue também funciona parecidamente com a implementação do offer da Queue, porém ele bloqueia quando o número de valores colocados na fila ultrapassa a capacidade máxima e espera até que um seja consumido
        fila.put("c2");
        fila.put("c3");
        fila.put("c4");

        // poll() da Queue Consome da fila. peek() da Queue apenas visualiza da fila o primeiro valor, mas não o consome (não o remove da fila)
        System.out.println(fila.take()); // take() da BlockingQueue funciona parecidamente com a implementação do poll da Queue, porém ele bloqueia o thread até alguém disponibilizar um novo elemento. Como não tem uma thread separada para fazer isso, com 4 valores, a thread main ficará bloqueada para sempre
        System.out.println(fila.take());
        System.out.println(fila.take());
        System.out.println(fila.take());

        System.out.println(fila.size());
    }
}
