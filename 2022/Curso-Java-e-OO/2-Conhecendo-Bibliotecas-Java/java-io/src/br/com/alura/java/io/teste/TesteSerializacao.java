package br.com.alura.java.io.teste;

import java.io.*;

public class TesteSerializacao {

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        
        // Serialização com Classe 
        
        Cliente cliente = new Cliente();
        cliente.setNome("Allan");
        cliente.setProfissao("Dev");
        cliente.setCpf("1231231231");
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("objeto.bin"));
        oos.writeObject(cliente);
        oos.close();


        // Uma observação: se fizer uma leitura de uma classe serializada que foi escrita como binário sem escrevê-la de novo com a nova version ID da mesma por haver uma mudança incompatível, o compilador dará erro, porque
        // A versionID que está sendo lida é justamente a version da versão anterior.
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("objeto.bin"));
        cliente = (Cliente) ois.readObject();
        ois.close();
        System.out.println(cliente.getCpf());




        // Serialização com String

//        String nome = "Allan Carlos";
//
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("objeto.bin"));
//        oos.writeObject(nome);
//        oos.close();

//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("objeto.bin"));
//        nome = (String) ois.readObject();
//        ois.close();
//        System.out.println(nome);
    }
}
