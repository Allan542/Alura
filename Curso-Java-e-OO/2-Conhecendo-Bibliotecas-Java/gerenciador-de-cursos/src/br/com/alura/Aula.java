package br.com.alura;

public class Aula implements Comparable<Aula> {
    private String titulo;
    private int tempo;

    public Aula(String titulo, int tempo) {
        this.titulo = titulo;
        this.tempo = tempo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getTempo() {
        return tempo;
    }

    // Observação: Este método está sendo reescrevido, pois a classe mãe dessa classe (Object) faz um toString próprio que exibe o nome do pacote + @ + hashcode, sendo que eu preciso exibir o conteúdo desta classe atual.
    @Override
    public String toString() {
        return "Aula: " + this.titulo + ", " + this.tempo + " minutos";
    }

    @Override
    public int compareTo(Aula outraAula) {
        return this.titulo.compareTo(outraAula.titulo);
    }
    
    
    
}
