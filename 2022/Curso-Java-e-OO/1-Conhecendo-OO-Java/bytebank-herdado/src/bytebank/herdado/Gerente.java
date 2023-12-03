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

// Gerente é um funcionário, Gerente herda da class Funcionario, assina o contrato Autenticavel
public class Gerente extends Funcionario implements Autenticavel {
    
    private int senha;
    private ComparaSenha autenticador;
    
    public Gerente(){
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
    public double getBonificacao(){ // super vai chamar o método da classe mãe em vez de chamar o próprio metodo, que seria com this nesse caso
        System.out.println("Chamando o método bonificação do GERENTE");
        return super.getSalario();
    }
}
