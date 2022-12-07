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

public class NovaEmpresa implements Acao {
    
    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("acao nova empresa");
        
        String nomeEmpresa = request.getParameter("nome");
        String dataEmpresa = request.getParameter("data");
        
        Date dataAbertura = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataAbertura = sdf.parse(dataEmpresa);
        } catch (ParseException e) {
//            Logger.getLogger(NovaEmpresaServlet.class.getName()).log(Level.SEVERE, null, e); // Apenas joga o "log" da exceção e não a exceção de fato
            throw new ServletException(e);
        }
        
        Banco banco = new Banco();
        Empresa empresa = new Empresa();
        
        empresa.setNome(nomeEmpresa);
        empresa.setDataAbertura(dataAbertura);
        banco.adiciona(empresa);
        
        return "redirect:entrada?acao=ListaEmpresas";
        
        // chamar o JSP
//        RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas");
//        rd.forward(request, response);
    }
}
