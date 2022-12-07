/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotations;

/**
 *
 * @author silva
 */
public class ContaCorrente extends Conta {
    
    @Override
     public void deposita(double valor) {
        this.saldo += valor - 1;
    }
}
