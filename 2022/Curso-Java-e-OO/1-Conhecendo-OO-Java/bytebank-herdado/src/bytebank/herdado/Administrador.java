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
public class Administrador extends Funcionario implements Autenticavel{
    
    private int senha;
    
    private ComparaSenha autenticador;
    
    public Administrador(){
        this.autenticador = new ComparaSenha();
    }

    @Override
    public boolean autentica(int senha) {
        return this.autenticador.autentica(senha);
    }

    @Override
    public void setSenha(int senha) {
        this.autenticador.setSenha(senha);
    }

    @Override
    public double getBonificacao() {
        return 50;
    }
}
