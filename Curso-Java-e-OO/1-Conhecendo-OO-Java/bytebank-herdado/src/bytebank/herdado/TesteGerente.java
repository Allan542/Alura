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
public class TesteGerente {
    
    public static void main(String[] args){
        
        Autenticavel gerente = new Gerente();// Lado esquerdo sofre polimorfismo, lado direito nasce e morre o mesmo tipo.
        gerente.setSenha(0);
        
        
        Gerente g1 = new Gerente();
        g1.setNome("Marco");
        g1.setCpf("37627423");
        g1.setSalario(5000.0);
        
        System.out.println(g1.getNome());
        System.out.println(g1.getCpf());
        System.out.println(g1.getSalario());
        System.out.println(g1.getBonificacao());
        
        
        g1.setSenha(222);
        boolean autenticou = g1.autentica(222);
        System.out.println(autenticou);
    }
    
}
