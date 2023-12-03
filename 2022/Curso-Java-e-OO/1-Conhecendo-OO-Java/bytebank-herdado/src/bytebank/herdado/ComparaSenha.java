/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bytebank.herdado;

/**
 *
 * @author silva
 */
public class ComparaSenha {
    private int senha;

    public boolean autentica(int senha) {
        if(this.senha == senha){
            return true;
        }
        else {
            return false;
        }
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
}
