package br.com.alura.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

// Callable é a interface com a mesma finalidade de um Runnable que é rodar uma tarefa, mas a diferença é que o Callable permite o retorno de algo
public class ComandoC2AcessaBanco implements Callable<String> {
    private PrintStream saida;

    public ComandoC2AcessaBanco(PrintStream saida) {
        this.saida = saida;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Servidor recebeu comando c2 - Banco");

        saida.println("Processando comando c2 - Banco");

        Thread.sleep(25000);

        int numero = new Random().nextInt(100) + 1;

        System.out.println("Servidor finalizou comando c2 - Banco");
        return Integer.toString(numero);

//        throw new RuntimeException("Exception no comando c2");
//        saida.println("Comando c2 executado com sucesso!");
    }
}
