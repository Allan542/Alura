package br.com.alura;

import java.util.ArrayList;
import java.util.Comparator;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrdenaStrings {

    public static void main(String[] args) {
        List<String> palavras = new ArrayList<>();
        palavras.add("alura online");
        palavras.add("editora casa do código");
        palavras.add("caelum");
        
        
        //Collections.sort(palavras, comparador);
        
        // default methods: um método com a palavra reservada default dentro de uma interface, pode ter um corpo, ou seja, pode ser implementado
//        palavras.sort((s1, s2) -> {
//            if(s1.length() < s2.length()) {
//                return -1;
//            } if(s1.length() > s2.length()) {
//                return 1;
//            }
//            return 0;
//        });
        
        // O mesmo que acima
        // palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        
        palavras.sort(Comparator.comparing(s -> s.length()));
        palavras.sort(Comparator.comparing(String::length));
        palavras.sort(comparing(String::length)); // import estático
        // Method references: Útil para quando a expressão lambda chama apenas um método que recebe um argumento.
        // Não vai funcionar se precisa chamar mais de um método ou o único método tenha dois argumentos que sejam do mesmo tipo do que a list que está sendo ordenada guarda (Exemplo: palavras.sort(Comparator::comparing), que recebe dois argumentos do mesmo tipo que a lista)
        // Obs: não precisa colocar o argumento, apenas a classe e o método que deseja funcionar
        
        Function<String, Integer> func = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        
        func = String::length;
        Function<String, Integer> funcao2 = s -> s.length();
        
        Function<String, Integer> funcao = s -> s.length(); // Todos os functions acima são apenas funções que retornam o tamanho da String, porém não estão comparando com nada, por não estarem num Comparator
        Comparator<String> comparator = comparing(funcao); // O mesmo que acima
        // palavras.sort(comparator); // Funcionou porque o comparator recebeu a function acima
        System.out.println(palavras);
        
        for (String p : palavras) {
            System.out.println(p);
        }
                
        Consumer<String> impressor = System.out::println; // Também poderia ser: (s) -> System.out.println(s);
        palavras.forEach(impressor); // O mesmo que abaixo
        // Object não é uma interface funcional e não vai funcionar. Necessariamente, precisa ser uma interface funcional (implementação de métodos default) e não uma classe ou uma Interface não-funcional
        // neste caso, precisa ter uma implementação e ela precisa receber os parâmetros necessários do método
        
        // Classe anonima para implementações de poucas linhas, porém, em foreach, é substituível
        palavras.forEach(s -> System.out.println(s)); // forEach só consegue implementar interfaces que tem uma implementação de métodos, que é chamada de interface funcional
        palavras.forEach(System.out::println); // forEach só consegue implementar interfaces que tem uma implementação de métodos, que é chamada de interface funcional
    
        List<Integer> numeros = new ArrayList<>();
        
        numeros.add(5);
        numeros.add(2);
        numeros.add(3);
    
        numeros.sort(comparing(Integer::valueOf)); // Ordena pelo valor de cada número
        System.out.println(numeros);
    }

}

//Consumer<String> consumer = new Consumer<String>() {
//    @Override
//    public void accept(String t) {
//    }
//};
//
//Comparator comparator = new Comparator() {
//    @Override
//    public int compare(Object t, Object t1) {
//        return 0;
//    }
//};
