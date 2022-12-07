package br.com.bytebank.banco.test.util;

import java.util.ArrayList;

import br.com.bytebank.banco.modelo.Conta;
import br.com.bytebank.banco.modelo.ContaCorrente;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class TesteArrayListEquals {

	public static void main(String[] args) {
		
//		Conta cc1 = new ContaCorrente(22, 22);
//		Conta cc2 = new ContaCorrente(22, 22);
//		
//		boolean igual = cc1.ehIgual(cc2);
//		System.out.println(igual);
		

		//Generics
		List<Conta> lista = new ArrayList<Conta>();
//		List<Conta> lista = new Vector<Conta>();
//		Collections<Conta> lista = new Vector<Conta>(); // Não funciona alguns métodos de list, pois ela (Collections) não tem estes métodos implementados
                
		
		Conta cc = new ContaCorrente(22, 11);
		lista.add(cc);
		
		Conta cc2 = new ContaCorrente(22, 22);
		lista.add(cc2);
		
		Conta cc3 = new ContaCorrente(22, 22);
		boolean existe = lista.contains(cc3);
		
		System.out.println("Já existe? " + existe);
                
                for(int i=0; i< lista.size(); i++){
                    Object ORef = lista.get(i);
                    System.out.println(ORef);
                }
		
                System.out.println("----------");
                
		for(Conta conta : lista) {
			System.out.println(conta);
		}
		
	}

}
