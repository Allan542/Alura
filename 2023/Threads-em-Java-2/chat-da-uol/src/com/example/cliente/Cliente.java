package com.example.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

// Não fez muito sentido o cliente receber a mensagem de outros usuários conectados, mas vou deixar como aprendizado
public class Cliente {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);

        PrintStream mensagemEnviada = new PrintStream(socket.getOutputStream());
        Scanner mensagemRecebida = new Scanner(socket.getInputStream());
        Scanner mensagemEscrita = new Scanner(System.in);

        boolean isNotValidName = true;
        String nome = null;
        while(isNotValidName){
            System.out.println("Digite seu nome: ");
            nome = mensagemEscrita.nextLine();
            if(nome.trim().length() == 0){
                System.out.println("Insira um nome válido para prosseguir!");
                continue;
            }
            isNotValidName = false;
            mensagemEnviada.println(nome);

        }

        final String finalNome = nome;
        Thread mandarSaida = new Thread(() -> {
            while (true){
                System.out.print(finalNome + ": ");
                String enviarMensagem = mensagemEscrita.nextLine();

                if(enviarMensagem.trim().equals("bye")){
                    mensagemEnviada.println(finalNome + " saiu do chat.");
                    break;
                }

                mensagemEnviada.println(finalNome + ": " + enviarMensagem);
            }
        });

        Thread lerEntrada = new Thread(() -> {
            while (mensagemRecebida.hasNextLine()){
                System.out.println();
                String mensagem = mensagemRecebida.nextLine();
                System.out.println(mensagem);
                System.out.print(finalNome + ": ");
            }
        });

        mandarSaida.start();
        lerEntrada.start();

        mandarSaida.join();


        mensagemEnviada.close();
        mensagemRecebida.close();
        mensagemEscrita.close();
        socket.close();
    }
}
