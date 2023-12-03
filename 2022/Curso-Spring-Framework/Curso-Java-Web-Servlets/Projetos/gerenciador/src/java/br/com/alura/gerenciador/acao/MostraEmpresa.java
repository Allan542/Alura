package br.com.alura.gerenciador.acao;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MostraEmpresa implements Acao {
    
    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("mostrando dados da empresa");
        
        String paramId = request.getParameter("id");
        Integer id = Integer.parseInt(paramId);
        
        Banco banco = new Banco();
        
        Empresa empresa = banco.buscaEmpresaPelaId(id);
        
        System.out.println(empresa.getNome());
        
        request.setAttribute("empresa", empresa);
        
        return "forward:formAlteraEmpresa.jsp";
    }
}
