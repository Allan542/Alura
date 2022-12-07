package br.com.alura.gerenciador.servlet;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="EmpresasService", urlPatterns={"/empresas"})
public class EmpresasService extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        List<Empresa> empresas = new Banco().getEmpresas();
        
        String valor = request.getHeader("Accept");
        System.out.println(valor);
        
        if(valor.contains("xml")) {
            XStream xstream = new XStream();
            xstream.alias("empresa", Empresa.class);
            String xml = xstream.toXML(empresas);

            response.setContentType("application/xml");
            response.getWriter().print(xml);
        } else if(valor.contains("json")) {
            Gson gson = new Gson();
            String json = gson.toJson(empresas);

            response.setContentType("application/json");
            response.getWriter().print(json);
        } else {
            response.setContentType("application/json");
            response.getWriter().print("{'message':'no content'}");
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
    
}
