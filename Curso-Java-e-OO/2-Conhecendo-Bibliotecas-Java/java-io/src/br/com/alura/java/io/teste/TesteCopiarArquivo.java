package br.com.alura.java.io.teste;

import java.io.*;
import java.net.Socket;

public class TesteCopiarArquivo {

	public static void main(String[] args) throws IOException {
		
//		Socket s = new Socket(); // Apenas um exemplo, não vai funcionar sem uma conexão

                // Fluxo de Entrada com Arquivo
//                InputStream fis = new FileInputStream("lorem.txt"); // Neste caso, faz uma cópia do primeiro lorem para o segundo lorem
		InputStream fis = System.in; // s.getInputStream(}; // Neste caso, é mais dinâmico, é possível colocar o texto em tempo de excecução
		Reader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		OutputStream fos = System.out;  //new FileOutputStream("lorem2.txt");
		Writer osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		
		String linha = br.readLine();
		
		while( !(linha == null || linha.isEmpty()) ) { // Enquanto linha tiver algo na buffer ou enquanto linha tiver algo escrito
			bw.write(linha);
			bw.newLine();
			bw.flush(); // Limpa a buffer do teclado para que mostre o texto na hora de escrever e não no final
			linha = br.readLine();
		}
	
		br.close();
		bw.close();
		
	}
}
