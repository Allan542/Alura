package br.com.alura.java.io.teste;

public class FluxoComTratamento {

    public static void main(String[] args){
        System.out.println("Ini do main");
        try{
            metodo1();
        } catch(Exception ex) {
            String msg = ex.getMessage();
            System.out.println("Exception " + msg);
            ex.printStackTrace();
        }
        System.out.println("Fim do main");
    }

    private static void metodo1() throws MinhaExcecao {
        System.out.println("Ini do metodo1");
        metodo2();
        System.out.println("Fim do metodo1");
    }
    
    // Caso uma classe throwable criada extenda a classe Exception diretamente e não a RuntimeException, o compilador irá checar esta classe, fazendo dela uma classe checked, sendo necessário inserir um throws para que jogue a exceção
    // e o compilador entenda que essa exceção tenha que acontecer, ou seja, um try catch pode tratar esta exceção caso ela ocorra ou o erro é jogado diretamente para a saída
    private static void metodo2() throws MinhaExcecao {
        System.out.println("Ini do metodo2");
        Conta c = new Conta();
        throw new MinhaExcecao("deu errado"); // Precisa extender de throwable, direta ou indiretamente
        
//        System.out.println("Fim do metodo2");
    }
}
