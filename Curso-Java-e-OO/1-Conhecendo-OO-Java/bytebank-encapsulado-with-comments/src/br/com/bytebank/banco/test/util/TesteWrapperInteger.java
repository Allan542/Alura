package br.com.bytebank.banco.test.util;

import java.util.ArrayList;
import java.util.List;

public class TesteWrapperInteger {

	public static void main(String[] args) {

		int idade = 29; // Integer
                
		Integer idadeRef = Integer.valueOf(29); // Autoboxing
                
		System.out.println(idadeRef.doubleValue()); // Unboxing

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
		System.out.println(Integer.SIZE);
		System.out.println(Integer.BYTES);
		
		int valor = idadeRef.intValue(); //unboxing
		String s = args[0];//"10" se colocar arguments
                
		//Integer numero = Integer.valueOf(s);
		int numero = Integer.parseInt(s);
		System.out.println(numero);
		
                // Obs: Lista só aceita wrappers e não o tipo primitivo
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(29); //Autoboxing
		
	}

}
