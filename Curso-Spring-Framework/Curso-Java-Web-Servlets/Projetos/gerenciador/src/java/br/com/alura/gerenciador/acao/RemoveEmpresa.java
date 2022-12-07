package br.com.alura.gerenciador.acao;

import br.com.alura.gerenciador.modelo.Banco;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveEmpresa implements Acao {
    
    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("acao removendo empresa");
        
        String paramId = request.getParameter("id");
        Integer id = Integer.parseInt(paramId);
        
        System.out.println(id);
        
        Banco banco = new Banco();
        banco.removeEmpresa(id);
        
        return "redirect:entrada?acao=ListaEmpresas";
    }
}
