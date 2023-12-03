package br.com.alura.java.io.teste;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author silva
 */
public class TestaContaComExcecaoChecked {
    public static void main(String[] args){
        
        
        Conta c = new Conta();
        try{
            c.deposita();
        } catch(MinhaExcecao ex){
            System.out.println("tratamento ..... ");
        }
    }
}
