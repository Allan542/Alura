package br.com.alura.banheiro;

public class Main {
    public static void main(String[] args) {

        Banheiro banheiro = new Banheiro();

        Thread convidado1 = new Thread(new TarefaNumero1(banheiro), "João");
        Thread convidado2 = new Thread(new TarefaNumero2(banheiro), "Pedro");
        Thread limpeza = new Thread(new TarefaLimpeza(banheiro), "Limpeza");
        // Uma thread Daemon é uma thread que só executa quando há threads principais rodando, ou seja, a finalização da aplicação não depende mais dessa thread para ser finalizada, porque ela depende apenas da finalização das threads principais.
        // No contexto dessa aplicação, a thread de limpeza só limpará enquanto tiver convidados usando o banheiro, ou seja, enquanto tiver threads principais executando. Quando as threads principais encerram, a thread daemon também é encerrada
        // Em outras palavras, uma thread daemon é uma prestadora de serviços para outras threads. Ela só é usada enquanto as outras threads estão rodando.
        // A thread deve ser definida como Daemon antes de inicializá-la
        limpeza.setDaemon(true);
//        Thread convidado3 = new Thread(new TarefaNumero1(banheiro), "Maria");
//        Thread convidado4 = new Thread(new TarefaNumero2(banheiro), "Ana");

        convidado1.start();
        convidado2.start();
        limpeza.start();
//        convidado3.start();
//        convidado4.start();
    }
}