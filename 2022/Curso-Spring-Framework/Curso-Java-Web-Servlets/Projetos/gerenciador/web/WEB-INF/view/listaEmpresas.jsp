<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; chars-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="logout-parcial.jsp" />
        
        Usuario Logado: ${ usuarioLogado.login }
        <br>
        <br>
        <br>
        
        <a href="/gerenciador/entrada?acao=NovaEmpresaForm">Criar Empresa</a><br>
        
        Lista de empresas: <br>
        <ul>
            <c:forEach items="${empresas}" var="empresa">
                <fmt:formatDate value="${empresa.dataAbertura}" var="dataFormatada" pattern="dd/MM/yyyy" />
                <li>${empresa.id}: ${empresa.nome} - ${dataFormatada}</li>
                <a href="/gerenciador/entrada?acao=MostraEmpresa&id=${empresa.id}">edita</a>
                <a href="/gerenciador/entrada?acao=RemoveEmpresa&id=${empresa.id}">remove</a>
            </c:forEach>
        </ul>
        
        
<!--        O mesmo que acima, porém, com scriplets-->
<!--        <ul>
             
                List<Empresa> lista = (List<Empresa>) request.getAttribute("empresas");
                for (Empresa empresa : lista) {
            
                    <lia.getNome()</li>
            }
        </ul>-->
    </body>
</html>
