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
public class ControleBonificacao {

    private double soma;
    
    
    public void registra(Funcionario f){
        double boni = f.getBonificacao();
        this.soma = this.getSoma() + boni;
    }

    public double getSoma() {
        return soma;
    }
    
    
}
