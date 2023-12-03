package br.com.alura.cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class ClienteTarefas {

    public static void main(String[] args) throws Exception {
        // Socket é o ponto-final de um fluxo de comunicação entre duas aplicações, através de uma rede.
        Socket socket = new Socket("localhost", 12345); // Acessa o servidor, através das informações do host, que seria o ip/endereço do servidor e a porta

        System.out.println("conexao estabelecida");

        // Criando threads separadas para enviar e receber comando ao mesmo tempo/paralelamente
        Thread threadEnviaComando = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Pode enviar comandos!");
                    PrintStream saida = new PrintStream(socket.getOutputStream()); // Cria uma PrintStream que permite o cliente enviar mensagens ao servidor através do OutputStream

                    Scanner teclado = new Scanner(System.in);
                    while(teclado.hasNextLine()){
                        String linha = teclado.nextLine();

                        if(linha.trim().equals("")){
                            break;
                        }

                        saida.println(linha); // Manda o comando c1 para o servidor
                    }

                    saida.close();
                    teclado.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadRecebeResposta = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("recebendo dados do servidor");
                    Scanner respostaServidor = new Scanner(socket.getInputStream()); // Scanner para ler dados do servidor

                    while(respostaServidor.hasNextLine()) {
                        String linha = respostaServidor.nextLine();
                        System.out.println(linha);
                    }

                    respostaServidor.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadRecebeResposta.start();
        threadEnviaComando.start();

        // Obs.: existe um join que recebe o tempo em millisegundos como parâmetro e outro que recebe o tempo em millissegundos mais os nanossegundos
        threadEnviaComando.join(); // fala para a thread atual desta classe (Thread main) esperar esta thread de envio de comandos terminar para que a main também encerre, ou seja, junta a thread de envio de comandos na thread main. Basicamente, ele deixa a thread main em wait, que espera até um notify ou notifyAll ser mandado pelo encerramento desta thread que deu o join. Detalhe: Esse comando funciona o wait, mas o wait dentro de um método main não funciona por ser estático.

        socket.close(); // fecha a conexão com o servidor
    }
}
