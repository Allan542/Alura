package br.com.alura.threads;

public class ServidorDeTeste {

    // volatile: palavra chave do java para fazer com que o valor deste atributo seja manipulado na memória principal e não em cache por threads distintas
    private volatile boolean estaRodando = false;

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTeste servidor = new ServidorDeTeste();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        Thread thread = new Thread(new Runnable() {

            public void run() {
                System.out.println("Servidor começando, estaRodando = " + estaRodando );

                // Ele não vai sair deste while porque o atributo está sendo mudado pela thread main, por estarem em threads diferentes e cada thread guarda em cache, o valor desse atributo que eles pegam da memória principal. Basicamente, cada thread nativamente vai ter sua memória e copia os dados para otimizar o acesso. Mas, neste caso, a gente quer o valor que vem da memória principal. Ou seja, para este caso, a gente precisa pegar o valor que vem da memória principal e não do cache de cada thread.
                while(!estaRodando) {}

                if(estaRodando) {
                    throw new RuntimeException("Deu erro na thread ....");
                }

                System.out.println("Servidor rodando, estaRodando = " + estaRodando );

                while(estaRodando) {}

                System.out.println("Servidor terminando, estaRodando = " + estaRodando );
            }
        });
        // Por não adiantar o uso de um bloco try-catch em volta de uma criação de thread e runnable, pelo motivo de rodar em um processo separado e que cada thread possui a sua própria pilha de métodos (no caso, a main possui uma e a thread criada possui outra pilha),
        // a classe Thread tem seu próprio ExceptionHandler, chamado UncaughtExceptionHandler, que consegue tratar a exceção inesperada que ocorreu na thread e faz o tratamento específico da thread de uma forma mais centralizada.
        // O bom que pode ser criada uma classe que implementa esse Handler, mas o problema é que toda thread criada precisa setar esse handler se forem em processos separados.
        thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
        thread.start();
    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = true");
        estaRodando = true;

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = false");
        estaRodando = false;
    }
}