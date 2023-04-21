/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.gerenciador.servlet;

import br.com.alura.gerenciador.acao.Acao;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author silva
 */
//@WebFilter(filterName = "AutorizacaoFilter", urlPatterns = {"/entrada"})
public class ControladorFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain chain)
            throws IOException, ServletException {
        
        System.out.println("==================");
        System.out.println("ControladorFilter");
        
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        String paramAcao = request.getParameter("acao");
        String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao;
        
        //        paramAcao.executa(req,res); isso exemplifica o código abaixo
        String nome;
        try { // ação que vai ser executada da package acao
            Class classe = Class.forName(nomeDaClasse); // carrega a classe com o nome
            Acao acao = (Acao) classe.newInstance();
            nome = acao.executa(request, response);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
        
        String[] tipoEEndereco = nome.split(":");
        if(tipoEEndereco[0].equals("forward")) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
            rd.forward(request, response);
        } else {
            response.sendRedirect(tipoEEndereco[1]);
        }
        // sem chain.doFilter pq esse é o último na cadeia
        
        System.out.println("Fim ControladorFilter");
    }
    
    

    
    
}
