package br.com.alura.java.io.teste;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TesteUnicodeEEncoding {
    public static void main(String[] args) throws UnsupportedEncodingException {
        
        
        Charset charset = Charset.defaultCharset();
        String s = "13º Órgão Oficial";
        
        // System.out.println(s.codePointAt(0)); Só faz sentido para um caractere e não uma string inteira // unicode
        System.out.println(charset.displayName());
        
        byte[] bytes = s.getBytes("windows-1252");
        System.out.print(bytes.length + ", windows-1252, ");
        String sNovo = new String(bytes, "windows-1252");
        System.out.println(sNovo);
        
        bytes = s.getBytes("UTF-16");
        System.out.print(bytes.length + ", UTF-16, ");
        sNovo = new String(bytes, "UTF16");
        System.out.println(sNovo);
        
        bytes = s.getBytes(StandardCharsets.US_ASCII);
        System.out.print(bytes.length + ", US-ASCII, ");
        sNovo = new String(bytes, "windows-1252");
        System.out.println(sNovo);
        
        bytes = s.getBytes("UTF-8");
        System.out.print(bytes.length + ", UTF-8, ");
        sNovo = new String(bytes, "UTF-8");
        System.out.println(sNovo);
    }
}
