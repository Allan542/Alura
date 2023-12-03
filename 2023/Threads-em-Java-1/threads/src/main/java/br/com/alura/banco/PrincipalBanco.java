package br.com.alura.banco;

public class PrincipalBanco {

    public static void main(String[] args) {

        GerenciadorDeTransacao tx = new GerenciadorDeTransacao();
        PoolDeConexao pool = new PoolDeConexao();

        // O Deadlock acontece quando uma thread espera pegar a chave de um recurso e está bloqueada, pois tem outra thread utilizando este recurso que está aguardando o recurso que a primeira thread está utilizando, fazendo assim com que trave as duas threads e nunca vai parar.
        // Em um monitoramento de threads, vai mostrar
        new Thread(new TarefaAcessaBanco(pool, tx)).start();
        new Thread(new TarefaAcessaBancoProcedimento(pool, tx)).start();
    }
}
