package br.com.alura.threads;

import Tarefa.Tarefa;

import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Principal {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> tarefa = new Tarefa(); //Tarefa implementa Callable
        FutureTask<String> futureTask = new FutureTask<>(tarefa);
        new Thread(futureTask).start(); //usando Thread puro!!
        String resultado = futureTask.get();
        System.out.println(resultado);
//        Properties properties = new Properties();
//        Thread thread = new Thread(new LeitorPropriedades(properties, "arquivo1.txt"));
//        thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
//        thread.start();
    }
}

class LeitorPropriedades implements Runnable {

    private Properties propriedades;
    private String nomeArquivo;

    public LeitorPropriedades(Properties propriedades, String nomeArquivo) {
        this.propriedades = propriedades;
        this.nomeArquivo = nomeArquivo;
    }

    public void run() {
        try {
            this.propriedades.load(new FileReader(nomeArquivo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}