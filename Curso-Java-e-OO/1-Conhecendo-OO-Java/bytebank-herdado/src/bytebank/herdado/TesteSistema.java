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
public class TesteSistema {
    public static void main(String[] args){
        Gerente g = new Gerente();
        g.setSenha(2222);
        
        Administrador adm = new Administrador();
        adm.setSenha(3333);
        
        Cliente cliente = new Cliente();
        cliente.setSenha(2222);
        
        SistemaInterno si = new SistemaInterno();
        si.autentica(g);
        si.autentica(adm);
        si.autentica(cliente);
    }
}
