package br.com.alura.banheiro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BanheiroComLock {

    private Lock lock = new ReentrantLock();

    // ReetrantLock funciona da mesma forma que o bloco syncronized, com a diferença de ser um bloqueio de forma explícita (programaticamente). A desvantagem é que o programador tem a responsabilidade de liberar o lock (unlock)
    // Existe uma forma de colocar um timeout na hora de obter um lock (boolean locked = lock.tryLock(5, TimeUnit.SECONDS); neste caso, tentará dentro de 5 segundos travar a execução)
    public void fazNumero1() {

        String nome = Thread.currentThread().getName();

        System.out.println(nome + " batendo na porta");

        lock.lock();
            System.out.println(nome + " entrando no banheiro");
            System.out.println(nome + " fazendo coisa rápida");
            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando mão");
            System.out.println(nome + " saindo do banheiro");
        lock.unlock();
    }

    public void fazNumero2() {
        String nome = Thread.currentThread().getName();

        System.out.println(nome + " batendo na porta");

        lock.lock();
            System.out.println(nome + " entrando no banheiro");
            System.out.println(nome + " fazendo coisa demorada");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando mão");
            System.out.println(nome + " saindo do banheiro");
        lock.unlock();
    }
}
