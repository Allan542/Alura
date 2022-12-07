/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
public class AutorizacaoFilter implements Filter {

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
        System.out.println("AutorizacaoFilter");
        
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        String paramAcao = request.getParameter("acao");
        
        HttpSession sessao = request.getSession();
        boolean usuarioNaoEstaLogado = sessao.getAttribute("usuarioLogado") == null;
        boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm"));
        if(ehUmaAcaoProtegida && usuarioNaoEstaLogado) {
            response.sendRedirect("entrada?acao=LoginForm");
            System.out.println("Fim AutorizacaoFilter");
            return;
        }
        
        System.out.println("Fim AutorizacaoFilter");
        chain.doFilter(request, response);
    }
    
    

    
    
}
