package br.com.alura.lista;

public class Lista {
    private String[] elementos = new String[1000];
    private int indice = 0;

    // Sem o synchronized, vários threads trabalham em cima do mesmo objeto. Ou seja, quando um thread coloca o valor dentro do objeto, mas aí vem outra thread colocar outro valor numa outra posição mais a frente, antes da primeira incrementar. O valor que foi colocada pela primeira thread se perde pela falta da sincronia e é sobreescrevido, deixando o campo que estava preenchido nulo.
    // Com o synchronized, as threads vão inserindo os valores pela ordem de chegada. Ou seja, a primeira que conseguir chamar o método de adicionar, consegue colocar o valor e as próximas threads esperam a atual terminar e incrementar. Quando o valor é inserido, a próxima thread aparece para inserir o próximo valor, e assim sucessivamente.
    // syncronized para este caso, precisa retornar um método void
    public synchronized void adicionaElementos(String elemento) {
        this.elementos[indice] = elemento;
        this.indice++;

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (this.indice == this.elementos.length){
            System.out.println("lista tá cheia, notificando");
            this.notify();
        }
    }

    public int tamanho() {
        return this.elementos.length;
    }

    public String pegaElemento(int posicao) {
        return this.elementos[posicao];
    }

    public boolean estaCheia() {
        return this.indice == this.tamanho();
    }
}
