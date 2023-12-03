package br.com.alura.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DistribuirTarefas implements Runnable {

    private ExecutorService threadPool;
    private BlockingQueue<String> filaComandos;
    private Socket socket;
    private ServidorTarefas servidor;

    public DistribuirTarefas(ExecutorService threadPool, BlockingQueue<String> filaComandos, Socket socket, ServidorTarefas servidor) {
        this.threadPool = threadPool;
        this.filaComandos = filaComandos;
        this.socket = socket;
        this.servidor = servidor;
    }

    @Override
    public void run() {

        try {
            System.out.println("Distribuindo tarefas para " + socket);

            // Basicamente, PrintStream vai enviar e Scanner vai receber as mensagens do cliente
            Scanner entradaCliente = new Scanner(socket.getInputStream()); // Pega o System.in (InputStream) através do socket, que recebe as mensagens enviadas pelo cliente
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream()); // Manda uma mensagem de confirmação ou erro para o cliente, pegando o System.out do mesmo através da socket

            // lê e imprime no servidor, o comando recebido do cliente
            while (entradaCliente.hasNextLine()){
                String comando = entradaCliente.nextLine();
                System.out.println("Comando recebido " + comando);

                switch (comando) {
                    case "c1":
                        saidaCliente.println("Confirmação comando c1");
                        ComandoC1 c1 = new ComandoC1(saidaCliente);
                        this.threadPool.execute(c1); // Pega a threadPool do servidor para executar um comando, que é a mesma threadPool usada para distribuir tarefas a um cliente
                        break;
                    case "c2":
                        saidaCliente.println("Confirmação comando c2");
                        // Detalhe importante: Callable só pode ser usado em um pool de threads, já que a Thread raíz não permite Callables e sim apenas Runnables
                        ComandoC2ChamaWS c2 = new ComandoC2ChamaWS(saidaCliente);
                        ComandoC2AcessaBanco c2Banco = new ComandoC2AcessaBanco(saidaCliente);
                        Future<String> futureWS = this.threadPool.submit(c2);// submit envia uma tarefa parecida com execute, mas com a diferença que ele permite o envio de um Callable, enquanto execute só permite Runnable
                        Future<String> futureBanco = this.threadPool.submit(c2Banco);
                        // Future representa o resultado no futuro, desde que você use o método get, que te retorna o resultado quando ficar pronto, causando um bloqueio nesta thread (provavelmente um waiting para esperar o resultado)

                        // Pequena curiosidade: Se vc colocar no callable do submit algo retornável, o Future esperará pela chamada do método get para poder retornar este resultado. Se o generics do submit for um void e o retorno for nulo, ele executa imediatamente sem a necessidade de um get
                        // Outra pequena curiosidade: Se tiver algum get do Future declarado aqui, ele travará esta thread, porém a execução dos Callables anteriores continuará acontecendo enquanto esta thread estiver travada. Quando o comando abaixo for chamado nessa situação, ele já vai retornar os valores depois de uns 5 segundos porque o processo já estava executando, matando o timeout que existe dentro da tarefa abaixo
                        // Um "Thread.sleep(25000);" também funciona da mesma forma que a forma acima se colocada o mesmo tempo de processamento das threads, o que de novo, anula os timeouts que existem dentro da tarefa abaixo.
                        this.threadPool.submit(new JuntaResultadosFutureWSFutureBanco(futureWS, futureBanco, saidaCliente));

                        break;
                    case "c3":
                        this.filaComandos.put(comando); //blocks
                        saidaCliente.println("Comando c3 adicionado na fila");
                        break;
                    case "fim":
                        saidaCliente.println("Desligando o servidor");
                        servidor.parar();
                        return; // return para sair do método e não o break que apenas saíra do switch, assim liberando a thread
                    default:
                        saidaCliente.println("comando não encontrado");
                        break;
                }

//                System.out.println(comando);
            }

            entradaCliente.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
