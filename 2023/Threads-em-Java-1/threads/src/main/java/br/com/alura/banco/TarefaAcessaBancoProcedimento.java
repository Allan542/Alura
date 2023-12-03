package br.com.alura.banco;

public class TarefaAcessaBancoProcedimento implements Runnable {
    private PoolDeConexao pool;
    private GerenciadorDeTransacao tx;

    public TarefaAcessaBancoProcedimento(PoolDeConexao pool, GerenciadorDeTransacao tx) {
        this.pool = pool;
        this.tx = tx;
    }

    @Override
    public void run() { // O correto é alinhar as ordens de procedimento, para que quando um estiver usando a chave de um recurso, o outro aguarde o primeiro terminar
        synchronized (pool/*tx*/) {
            System.out.println("começando a tx");
            tx.begin();

            synchronized (tx/*pool*/) {
                System.out.println("peguei a conexão");
                pool.getConnection();
            }
        }
    }
}
