package br.com.alura.lista;

public class TarefaImprimir implements Runnable {

    private Lista lista;

    public TarefaImprimir(Lista lista) {
        this.lista = lista;
    }

    @Override
    public void run() {

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        synchronized (lista){ // Neste caso, a chave que precisa ser pega para ser sincronizada, é da lista e não do objeto atual, já que a lista possui um método sincronizado que está sendo manipulado por threads
            if(!lista.estaCheia()){
                try {
                    System.out.println("indo esperar, aguardando notificação");
                    lista.wait(); // precisa estar dentro do bloco synchronized. Se acontecer algum problema e esta thread for executada depois do notify, ela deixa a execução infinitamente rodando.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < lista.tamanho(); i++) {
                System.out.println(i + " - " + lista.pegaElemento(i));
            }
        }
    }
}
