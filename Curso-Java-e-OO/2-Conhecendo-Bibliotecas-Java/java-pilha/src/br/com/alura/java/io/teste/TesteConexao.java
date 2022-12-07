package br.com.alura.java.io.teste;

public class TesteConexao {

    public static void main(String[] args) {
        
        try (Conexao conexao = new Conexao() ) {
            conexao.leDados();
        } catch(IllegalStateException ex){
            System.out.println("Deu erro na conexão");
        }
        
        // ---------------------------------
//        Conexao con = new Conexao();
//        try {
//            con.leDados();
//        } catch(IllegalStateException ex) {
//            System.out.println("Deu erro na conexão");
//        } finally {
//            System.out.println("Finally");
//            if(con == null) {
//                con.close();
//            }
//        }
        
    }
    
}
