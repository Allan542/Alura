package com.example.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

    private static final Set<Socket> users = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(12345);
        ExecutorService threadPool = Executors.newCachedThreadPool();

        while(true) {
            Socket socket = serverSocket.accept();
            Scanner scanner = new Scanner(socket.getInputStream());

            threadPool.execute(() -> {
                String usuario = null;
                if(scanner.hasNextLine()){
                    usuario = scanner.nextLine();
                }

                System.out.println(usuario + " entrou no chat da UOL. Divirta-se!");
                users.add(socket);

                try {
                    String mensagem = null;
                    if(scanner.hasNextLine()){
                        mensagem = scanner.nextLine();
                    }

                    while (mensagem != null && !mensagem.equals("bye")) {
                        for (Socket user : users) {
                            PrintStream printStream = new PrintStream(user.getOutputStream());
                            if(user != socket){
                                printStream.println(mensagem);
                            }
                        }
                        System.out.println(mensagem);
                        mensagem = scanner.hasNextLine() ? scanner.nextLine() : null;
                        System.out.println(users);
                    }

                    users.remove(socket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }
}