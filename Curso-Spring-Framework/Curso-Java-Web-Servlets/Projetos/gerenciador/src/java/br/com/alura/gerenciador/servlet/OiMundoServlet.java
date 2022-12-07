package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/oi", loadOnStartup = 1) // instancia o servlet quando o servidor é iniciado, mostrando a mensagem definida no construtor
public class OiMundoServlet extends HttpServlet {
    
    public OiMundoServlet() {
        System.out.println("Criando Oi Mundo Servlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Olá mundo");
        out.println("</body>");
        out.println("</html>");
        
        System.out.println("O servlet foi chamado");
    }
}
