package br.com.alura.gerenciador.servlet;

import br.com.alura.gerenciador.acao.Acao;
import br.com.alura.gerenciador.acao.AlteraEmpresa;
import br.com.alura.gerenciador.acao.ListaEmpresas;
import br.com.alura.gerenciador.acao.RemoveEmpresa;
import br.com.alura.gerenciador.acao.MostraEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresaForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet(name="UnicaEntradaServlet", urlPatterns={"/entrada"})
public class UnicaEntradaServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String paramAcao = request.getParameter("acao");
        
//        HttpSession sessao = request.getSession();
//        boolean usuarioNaoEstaLogado = sessao.getAttribute("usuarioLogado") == null;
//        boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm"));
//        if(ehUmaAcaoProtegida && usuarioNaoEstaLogado) {
//            response.sendRedirect("entrada?acao=LoginForm");
//            return;
//        }
        
        
        
        String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao;
        
        //        paramAcao.executa(req,res); isso exemplifica o código abaixo
        String nome;
        try {
            Class classe = Class.forName(nomeDaClasse); // carrega a classe com o nome
            Acao acao = (Acao) classe.newInstance();
            nome = acao.executa(request, response);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
        
//        String nome = "";
//        if(paramAcao.equals("ListaEmpresas")) {
//            ListaEmpresas acao = new ListaEmpresas();
//            nome = acao.executa(request, response);
//        } else if(paramAcao.equals("RemoveEmpresa")) {
//            RemoveEmpresa acao = new RemoveEmpresa();
//            nome = acao.executa(request, response);
//        } else if (paramAcao.equals("MostraEmpresa")) {
//            MostraEmpresa acao = new MostraEmpresa();
//            nome = acao.executa(request, response);
//        } else if (paramAcao.equals("AlteraEmpresa")) {
//            AlteraEmpresa acao = new AlteraEmpresa();
//            nome = acao.executa(request, response);
//        } else if (paramAcao.equals("NovaEmpresa")) {
//            NovaEmpresa acao = new NovaEmpresa();
//            nome = acao.executa(request, response);
//        } else if (paramAcao.equals("NovaEmpresaForm")) {
//            NovaEmpresaForm acao = new NovaEmpresaForm();
//            nome = acao.executa(request, response);
//        }
        
        String[] tipoEEndereco = nome.split(":");
        if(tipoEEndereco[0].equals("forward")) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
            rd.forward(request, response);
        } else {
            response.sendRedirect(tipoEEndereco[1]);
        }
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
