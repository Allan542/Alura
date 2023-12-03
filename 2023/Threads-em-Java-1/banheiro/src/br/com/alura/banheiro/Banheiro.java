package br.com.alura.banheiro;

public class Banheiro {

    private boolean ehSujo = true;

    // Para sincronizar tudo, é necessário sincronizar ambos os métodos
    public void fazNumero1() {

        String nome = Thread.currentThread().getName();

        System.out.println(nome + " batendo na porta");

        // Mesmo com isso, a gente não tem controle sobre quem começa a thread. Os dois podem bater a porta ao mesmo tempo, mas só um de fato vai conseguir usar o banheiro
        // Os comandos dentro do bloco serão executados de maneira atômica. Sendo assim, Quando alguem sai do banheiro, ou seja, sai do bloco syncronized, ele devolve a chave. Então, a pessoa que ficou bloqueada de usar o banheiro, pode entrar nele e fazer as necessidades. A chave usada para sincronizar o acesso é chamada de Mutex e é utilizada para executar um bloco de código de maneira atômica. Cada classe do Java tem o seu Mutex
        // A chave que a thread obtém pode ser chamada de lock implícito ou intrínseco. Existem formas de obter essa chave explicitamente, com métodos para obter a chave (tryLock) e para devolver (unlock).
        // Uma execução atômica é uma execução que não pode ser interrompida na metade. Um banco de dados que usa transações, possui a execução atômica como características. Transações possuem a funcionalidade ACID que vão além de syncronized (A de Atômico (o que syncronized faz), C de Consistente, I de Isolado e D de Durável)
        synchronized (this) { // this para este caso, seria: "quem possui a chave do banheiro". Então neste caso, a classe entra como parâmetro do bloco para ser sincronizada e obter a "chave" de sincronização, para caso quem está no banheiro, possui a chave, fazendo com que o próximo espere o primeiro sair e dessa forma bloqueando o acesso a thread.
            System.out.println(nome + " entrando no banheiro");

            while(ehSujo) {
                esperaLaFora(nome);
            }


            System.out.println(nome + " fazendo coisa rápida");

            dormeUmPouco(5000);

            this.ehSujo = true;

            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando mão");
            System.out.println(nome + " saindo do banheiro");
        }
    }

    public void fazNumero2() {
        String nome = Thread.currentThread().getName();

        System.out.println(nome + " batendo na porta");

        synchronized (this) {
            System.out.println(nome + " entrando no banheiro");

            // Não pode ser um if, porque se alguém entrar no banheiro antes da limpeza, a limpeza vem depois para limpar e deixar a pessoa que já estava esperando.
            // Quando a limpeza sai, tem a chance de outra pessoa entrar no lugar e deixar o banheiro sujo, mas não vai ser detectado pelo primeiro indivíduo porque ele verificou na primeira e única vez.
            // Por isso o while funciona melhor nessa verificação
            while(ehSujo) {
                esperaLaFora(nome);
            }

            System.out.println(nome + " fazendo coisa demorada");

            dormeUmPouco(10000);

            this.ehSujo = true;

            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando mão");
            System.out.println(nome + " saindo do banheiro");
        }
    }

    private static void dormeUmPouco(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void limpa() {

        String nome = Thread.currentThread().getName();

        System.out.println(nome + " batendo na porta");

        if(!ehSujo) {
            System.out.println(nome + ", não está sujo, vou sair");
            return;
        }

        synchronized (this) {
            System.out.println(nome + " entrando no banheiro");
            System.out.println(nome + " limpando banheiro");
            this.ehSujo = false;

            dormeUmPouco(13000);

            // notifyAll notifica todas as threads, acorda todas as threads que estavam esperando. Precisa ser chamado dentro de um bloco synchronized porque você precisa da chave Mutex da classe com o método sincronizado.
            // Se não colocar o wait dentro do bloco synchronized, ele jogará a exceção IllegalMonitorStateException, porque este método precisa do "monitor" (ou chave) daquele objeto que está tentando ser colocado em espera (também vale para o notify/notifyAll)
            // Para este caso, ele irá notificar que o banheiro está limpo e então, o próximo convidado entrará no banheiro para fazer as necessidades
            // Quando este método inicializar primeiro, devido a concorrência das threads, ele não notificará ninguém porque o banheiro foi limpo antes de alguém entrar.
            // Os métodos wait e notify/notifyAll estão definidos na classe Object. Todos os objetos possuem esses métodos.
            this.notifyAll();

            System.out.println(nome + " saindo do banheiro");
        }
    }

    private void esperaLaFora(String nome) {
        System.out.println(nome + ", eca, banheiro tá sujo");
        try {
            // como a classe possui a chave Mutex, A classe banheiro espera até ser "limpo"
            // Curiosidade: Quando chamamos este método, a thread devolve a chave e fica aguardando. É uma condição chamada guarded block, significando que a thread fica bloqueada aguardando um sinal/notificação
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
