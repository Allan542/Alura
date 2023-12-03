package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

    private final ServerSocket servidor;
    private final ExecutorService threadPool;
    private AtomicBoolean estaRodando; // garantir que não há um cache para este atributo e as threads acessam diretamente da memória. Classe atômica é a representação de uma classe volátil para o tipo especificado, que nesse caso, é Booleano e pode ser manipulado por várias threads.

    private BlockingQueue<String> filaComandos;

    public ServidorTarefas() throws IOException {
        System.out.println("--- Iniciando servidor ---");
        this.servidor = new ServerSocket(12345); // abre um servidor, ou seja, abre um socket de servidor para permitir conexões

        // Curiosidade sobre ambos executors abaixo: se uma thread não é utilizada em até 60s, ele elimina essas threads que não estão sendo utilizadas
        // Existe uma sobrecarga no método da criação de uma threadPool, que recebe um método do tipo ThreadFactory
//        this.threadPool = Executors.newFixedThreadPool(4, new FabricaDeThreads()); // Cria um número fixos de threads dentro dessa threadPool. Uma pool é um gerenciador de objetos no qual podemos definir seu tamanho e reaproveitar esses objetos. As threads dentro da thread pool nunca são perdidas, mas sim reaproveitadas quando não estiverem sendo utilizadas. No entanto, quando todas as threads estão sendo utilizadas por ser um número fixo, o próximo na fila de processamento fica bloqueado até que alguém libere uma thread.
        this.threadPool = Executors.newCachedThreadPool(new FabricaDeThreads()); // Cria uma thread pool que não possui um número fixo de threads. Neste caso para realmente simular um sistema de clientes que não se sabe quantos estão acessando o servidor. Esse caso também reaproveita threads que foram criadas, mas deixaram de ser usadas por outros clientes.
        this.estaRodando = new AtomicBoolean(true); // Atomic* também poupa a necessidade de ter que criar um método sincronizado.
        this.filaComandos = new ArrayBlockingQueue<>(2);
        iniciarConsumidores();
    }

    private void iniciarConsumidores() {

        int qtdConsumidores = 2;
        for (int i = 0; i < qtdConsumidores; i++) {
            TarefaConsumir tarefa = new TarefaConsumir(filaComandos);
            this.threadPool.execute(tarefa);
        }
    }

    // Toda comunicação TCP envolve dois pontos finais: um socket de cada lado com seu próprio endereço e porta:
    // [endereço_servidor:porta_servidor] <-> [endereço_cliente:porta_do_cliente]
    public static void main(String[] args) throws Exception {
        ServidorTarefas servidor = new ServidorTarefas();
        servidor.rodar();
        servidor.parar();
    }

    public void parar() throws IOException {
        this.estaRodando.set(false);
        servidor.close();
        threadPool.shutdown();
    }

    public void rodar() throws IOException {
        while(this.estaRodando.get()){
            try {
                Socket socket = servidor.accept(); // aceita conexões que são solicitadas ao servidor. É um método bloqueante e trava a thread principal, ou seja, a thread main fica parada até receber uma conexão através de um cliente. O retorno é um socket que abstrai os detalhes da conexão do lado do servidor
                System.out.println("Aceitando novo cliente na porta " + socket.getPort()); // a porta para cada cliente que conecta no servidor, é diferente. Pois a porta 12345 é apenas a porta inicial de conexão e as portas para o cliente fazer a comunicação com o servidor, são portas dedicadas

                DistribuirTarefas distribuirTarefas = new DistribuirTarefas(threadPool, filaComandos, socket, this);
                threadPool.execute(distribuirTarefas); // As threads dentro da threadPool executam esta tarefa. Nada muito diferente de criar uma thread única
//            Thread threadCliente = new Thread(distribuirTarefas);
//            threadCliente.start(); // criada thread para que possa aceitar mais de um cliente simultaneamente

//            Thread.sleep(20000); // Isso está travando o próximo cliente que quer acessar o servidor porque todos estão sendo aceitos e pegando a tarefa no mesmo thread main
            } catch (SocketException e) {
                System.out.println("SocketException, Está rodando? " + this.estaRodando);
            }
        }
    }

}


// Ver todas as threads da JVM
//            Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();
//            for (Thread thread : todasAsThreads) {
//                System.out.println(thread.getName());
//            }

// Podemos perguntar a quantidade de processadores usando a classe Runtime
//    Runtime runtime = Runtime.getRuntime();
//    int qtdProcessadores = runtime.availableProcessors();
//System.out.println("Qtd de processadores: " + qtdProcessadores);
