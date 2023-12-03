package br.com.alura.threads;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ImprimeString());
        thread.start();

//        System.out.println("É uma thread main");
//        Thread.sleep(30000);
    }
}