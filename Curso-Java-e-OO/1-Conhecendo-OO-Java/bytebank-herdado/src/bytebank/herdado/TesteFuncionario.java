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
public class TesteFuncionario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Cliente c = new Cliente();

        
        Funcionario nico = new Gerente();
        nico.setNome("Nico Steppat");
        nico.setCpf("223355646-9");
        nico.setSalario(2600.00);
        
        System.out.println(nico.getNome());
        System.out.println(nico.getBonificacao());
    }
    
}
