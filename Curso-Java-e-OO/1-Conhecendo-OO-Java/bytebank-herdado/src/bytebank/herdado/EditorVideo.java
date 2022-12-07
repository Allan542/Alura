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

// Gerente é um funcionário
public class EditorVideo extends Funcionario {

    
    public double getBonificacao(){
        System.out.println("Chamando o método bonificação do EDITOR DE VIDEO");
        return 100;
    }
}
