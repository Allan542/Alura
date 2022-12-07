package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter(urlPatterns = "/entrada")
public class MonitoramentoFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        System.out.println("========================");
        System.out.println("MonitoramentoFilter");
        
        System.out.println("Entrando no filtro");
        long antes = System.currentTimeMillis();
        
        
        String acao = request.getParameter("acao");
        
        // Executa a ação e depois termina o restante do código, pegar o tempo de depois da execução e imprimir o que vai ser imprimido depois
        System.out.println("Executando a ação");
        chain.doFilter(request, response);
        System.out.println("Executou a ação");
        
        long depois = System.currentTimeMillis();
        
        System.out.println("Tempo de execução da ação: " + acao + " -> " + (depois - antes));
        System.out.println("Saindo do filtro");
    }

}
