package br.com.alura.gerenciador.acao;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlteraEmpresa implements Acao {
    
    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("acao altera empresa");
        
        String nomeEmpresa = request.getParameter("nome");
        String dataEmpresa = request.getParameter("data");
        String paramId = request.getParameter("id");
        Integer id = Integer.parseInt(paramId);
        
        Date dataAbertura = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataAbertura = sdf.parse(dataEmpresa);
        } catch (ParseException e) {
            throw new ServletException(e);
        }
        
        System.out.println(id);
        
        Banco banco = new Banco();
        Empresa empresa = banco.buscaEmpresaPelaId(id);
        empresa.setNome(nomeEmpresa);
        empresa.setDataAbertura(dataAbertura);

        
        return "redirect:entrada?acao=ListaEmpresas";
    }
}
