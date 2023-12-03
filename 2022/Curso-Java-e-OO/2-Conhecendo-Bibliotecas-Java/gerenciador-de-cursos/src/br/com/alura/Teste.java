package br.com.alura;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Teste {

    public static void main(String[] args) {
        Funcionario f1 = new Funcionario("Barney", 12);
        Funcionario f2 = new Funcionario("Jonatan", 9);
        Funcionario f3 = new Funcionario("Guaraciara", 13);
        
        Set<Funcionario> funcionarios = new TreeSet<>(new OrdenaPorIdade());
        funcionarios.add(f1);
        funcionarios.add(f2);
        funcionarios.add(f3);
        
        Iterator<Funcionario> iterator = funcionarios.iterator();
        
        while(iterator.hasNext()) {
            System.out.println(iterator.next().getNome());
        }
    }
}

class OrdenaPorIdade implements Comparator<Funcionario> {

    @Override
    public int compare(Funcionario func1, Funcionario func2) {
        return Integer.compare(func1.getIdade(), func2.getIdade());
    }
    
}
