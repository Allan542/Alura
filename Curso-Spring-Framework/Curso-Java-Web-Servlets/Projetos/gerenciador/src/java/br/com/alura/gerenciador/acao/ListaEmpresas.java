package br.com.alura.gerenciador.acao;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListaEmpresas implements Acao {
    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
//        HttpSession sessao = request.getSession();
//        if(sessao.getAttribute("usuarioLogado") == null) {
//            return "redirect:entrada?acao=LoginForm";
//        }
        
        System.out.println("acao listando empresas");
        
        Banco banco = new Banco();
        List<Empresa> lista = banco.getEmpresas();
        
        request.setAttribute("empresas", lista);
        
        return "forward:listaEmpresas.jsp";
    }
}
