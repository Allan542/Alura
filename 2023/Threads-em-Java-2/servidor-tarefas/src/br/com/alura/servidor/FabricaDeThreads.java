package br.com.alura.servidor;

import java.util.concurrent.ThreadFactory;

public class FabricaDeThreads implements ThreadFactory {

    private static int numero = 1;

    // Classe que implementa a interface ThreadFactory criada para permitir a manipulação da criação de uma thread que irá para a threadPool. Antes de dar início a essa(s) thread(s), a threadPool chamará esse método passando a runnable que a gente passa para executar a tarefa através de uma thread.
    // Neste caso, foi usado para passar o TratadorDeExcecao criado no experimento, para tratar uma exceção jogada no comando c2
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, "Thread Servidor Tarefas " + numero);

        numero++;

        thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
        thread.setDaemon(true); // Se não colocar esta thread que deu exceção como daemon e tentar fechar o servidor, ele não encerrará e não deixará que você tente finalizar novamente
        return thread;
    }
}
