package br.com.bytebank.banco.test;

import br.com.bytebank.banco.modelo.Cliente;
import br.com.bytebank.banco.modelo.ContaCorrente;
import java.io.*;

public class TesteSerializacao {

    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
        cliente.setNome("Allan");
        cliente.setProfissao("Dev");
        cliente.setCpf("1231231231");
        
        ContaCorrente cc = new ContaCorrente(222, 333);
        cc.setTitular(cliente);
        cc.deposita(222.3);
        
        
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cc.bin"));
        oos.writeObject(cc);
        oos.close();
    }
    
}
