package br.com.alura;

public class TestaBuscaAlunosNoCurso {

    public static void main(String[] args) {
        Curso javaColecoes = new Curso("Dominando as coleções do Java", "Paulo Silveira");
        
        Aluno a1 = new Aluno("Rodrigo Turini", 34672);
        Aluno a2 = new Aluno("Guilherme Silveira", 5617);
        Aluno a3 = new Aluno("Mauricio Aniche", 17645);
        Aluno a4 = new Aluno("Paulo Silveira", 5617);
        
        javaColecoes.matricula(a1);
        javaColecoes.matricula(a2);
        javaColecoes.matricula(a3);
        javaColecoes.matricula(a4); // Se colocar um novo valor dentro de um map, porém com a mesma chave, ele irá sobrescrever o valor referente a última inserção
        
        javaColecoes.estaMatriculado(a1);
        
        int matricula = 5617;
        System.out.println(javaColecoes.getAlunos());
        System.out.println("Quem é o aluno com matrícula " + matricula + "?");
        Aluno aluno = javaColecoes.buscaMatriculado(matricula);
        System.out.println("Aluno: " + aluno);
        
    }

}
