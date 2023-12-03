package br.com.alura;

public class Recibo {
    private int numeroRecibo;
    private String textoRecibo;

    public Recibo(int numeroRecibo, String textoRecibo) {
        this.numeroRecibo = numeroRecibo;
        this.textoRecibo = textoRecibo;
    }

    public int getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(int numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getTextoRecibo() {
        return textoRecibo;
    }

    public void setTextoRecibo(String textoRecibo) {
        this.textoRecibo = textoRecibo;
    }
    
    
}
