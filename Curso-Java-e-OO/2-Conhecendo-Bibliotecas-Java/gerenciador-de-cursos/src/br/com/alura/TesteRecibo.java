package br.com.alura;

import java.util.TreeSet;

public class TesteRecibo {

    public static void main(String[] args) {
        Recibo rec1 = new Recibo(5421, "Asinha de frango: R$10");
        Recibo rec2 = new Recibo(7654, "File Mignon: R$60");
        Recibo rec3 = new Recibo(4528, "Paçoca: R$20");
        
        TreeSet<Recibo> recibos = new TreeSet<>();
        
        recibos.add(rec1);
        recibos.add(rec2);
        recibos.add(rec3);
    
        System.out.println(recibos); // Não funciona pq recibo não é Comparable
    }
}
