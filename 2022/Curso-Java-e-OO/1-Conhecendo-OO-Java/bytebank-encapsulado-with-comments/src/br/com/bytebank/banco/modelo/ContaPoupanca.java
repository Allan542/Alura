package br.com.bytebank.banco.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author silva
 */
public class ContaPoupanca extends Conta {
    
    public ContaPoupanca(int agencia, int numero) {
        super(agencia, numero);
    }

    @Override
    public void deposita(double valor) {
        super.saldo += valor;
    }
    
    @Override
    public String toString() {
        return "ContaPoupanca, " + super.toString();
    }
    
}
