package br.com.bytebank.banco.test;

import br.com.bytebank.banco.modelo.ContaCorrente;
import java.io.*;

public class TesteDesserializacao {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cc.bin"));
        ContaCorrente cc = (ContaCorrente) ois.readObject();
        ois.close();
        System.out.println(cc.getSaldo());
        System.out.println(cc.getTitular());
    }
    
}
