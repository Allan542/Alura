package br.com.alura.lista;

public class Principal {

    public static void main(String[] args) throws InterruptedException {

        // Curiosidade: arrayList não é thread-safe, ou seja, não é sincronizada
        // Thread-safe significa algo que quando for manipulado por mais de uma thread, não perderá sua integridade. Exemplo: Se uma consulta ao banco de dados for feita por duas threads diferente,
//        List<String> lista = new Vector<>(); //Isso é o mesmo que um Vector: Collections.synchronizedList(new ArrayList<>());
        Lista lista = new Lista();

        for (int i = 0; i < 10; i++) {
            new Thread(new TarefaAdicionarElemento(lista, i)).start();
        }

        new Thread(new TarefaImprimir(lista)).start();
    }
}
