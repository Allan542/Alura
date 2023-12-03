package br.com.alura.threads;

public class Main {
    public static void main(String[] args) {

        // Não existe uma ordem de execução, eles executam paralelamente. As threads que foram criadas no Java estão sendo mapeadas para threads do SO. Quando o Java executa a implementação, depende do SO por baixo e depende de quantas CPUs que eu tenho, depende do ambiente.
        // Isso é uma coisa que depende do escalonador de threads (scheduler), mas a gente pode dar umas "dicas"  para ele.
        String nome = "da";

        Thread threadAssinaturas1 = new Thread(new TarefaBuscaTextual("assinaturas1.txt", nome));
        Thread threadAssinaturas2 = new Thread(new TarefaBuscaTextual("assinaturas2.txt", nome));
        Thread threadAutores = new Thread(new TarefaBuscaTextual("autores.txt", nome));

        threadAssinaturas1.start();
        threadAssinaturas2.start();
        threadAutores.start();
    }
}