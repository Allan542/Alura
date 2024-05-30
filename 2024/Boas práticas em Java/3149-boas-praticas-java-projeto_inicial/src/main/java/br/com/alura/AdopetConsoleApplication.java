package br.com.alura;

import java.util.Scanner;

public class AdopetConsoleApplication {

    // Neste momento, foi aplicada a letra S do SOLID que significa princípio da responsabilidade única, ou seja, a classe só deve ter um, e somente um, motivo para mudar.
    // Significa que no nosso código, as classes estão com uma única responsabilidade, evitando código duplicado.
    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                exibirMenu();

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                // O padrão de projetos Command permite que invoque um comando que será responsável por implementar a lógica de chamada dos métodos das classes AbrigoService e PetService, o que abstrai essa chamada de dentro da classe main
                // Diferença entre padrão Command e Strategy:
                // Padrão Command
                // O padrão Command é usado para encapsular uma solicitação como um objeto, permitindo parametrizar clientes com diferentes solicitações, enfileirar solicitações, registrar o log de solicitações e até mesmo desfazer as
                // operações. Ele separa o remetente (quem faz a solicitação) do receptor (quem executa a ação), permitindo flexibilidade em adicionar novos comandos e mantendo o acoplamento baixo.
                // Padrão Strategy:
                // O padrão Strategy é usado para definir uma família de algoritmos, encapsulá-los e torná-los intercambiáveis. Isso permite que os algoritmos variem independentemente dos clientes que os usam. Ele é particularmente útil
                // quando você tem várias estratégias ou formas de realizar uma tarefa e deseja escolher dinamicamente a estratégia correta.
                // Propósitos
                // O Command é usado para encapsular solicitações como objetos, permitindo controle e gerenciamento de ações.
                // O Strategy é usado para definir algoritmos intercambiáveis, permitindo escolher a estratégia certa em tempo de execução.
                // Usos
                // Command é frequentemente usado para históricos, filas de comandos e operações desfazer/refazer.
                // Strategy é usado para escolher entre diferentes algoritmos ou estratégias de execução.
                // Flexibilidade
                // Command tem flexibilidade na execução de ações e histórico de comandos.
                // Strategy tem flexibilidade na escolha de algoritmos.
                switch (opcaoEscolhida){ // Curiosidade: Switch antigo precisava da palavra-chave yield em cada case e default para retorno de valor através de switch
                    case 1 -> executor.executeCommand(new ListarAbrigoCommand());
                    case 2 -> executor.executeCommand(new CadastrarAbrigoCommand());
                    case 3 -> executor.executeCommand(new ListarPetsDoAbrigoCommand());
                    case 4 -> executor.executeCommand(new ImportarPetsDoAbrigoCommand());
                    case 5 -> System.exit(0);
                    default -> opcaoEscolhida = 0;
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exibirMenu() {
        System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
        System.out.println("1 -> Listar abrigos cadastrados");
        System.out.println("2 -> Cadastrar novo abrigo");
        System.out.println("3 -> Listar pets do abrigo");
        System.out.println("4 -> Importar pets do abrigo");
        System.out.println("5 -> Sair");
    }
}
