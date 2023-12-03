package br.com.alura.threads;

import java.lang.Thread.UncaughtExceptionHandler;

public class TratadorDeExcecao implements UncaughtExceptionHandler {

    // Tratando a exceção pegando a thread que deu erro e a exceção
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Deu exceção na thread " + t.getName() + ", " + e.getMessage());
    }
}
