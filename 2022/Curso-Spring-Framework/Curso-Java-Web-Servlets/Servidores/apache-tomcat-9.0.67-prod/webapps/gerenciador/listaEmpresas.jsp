<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${not empty empresa}">
            Empresa ${ empresa } cadastrada com sucesso! <%-- ${}} Expression Language: pega o atributo enviado através da requisição sem precisar atrbuí-lo a uma variável --%>
        </c:if>
            
        <c:if test="${empty empresa}">
            Nenhuma empresa cadastrada!
        </c:if><br>
        
        <a href="/gerenciador/formNovaEmpresa.jsp">Criar Empresa</a><br>
        
        Lista de empresas: <br>
        <ul>
            <c:forEach items="${empresas}" var="empresa">
                <fmt:formatDate value="${empresa.dataAbertura}" var="dataFormatada" pattern="dd/MM/yyyy" />
                <li>${empresa.id}: ${empresa.nome} - ${dataFormatada}</li>
                <a href="/gerenciador/mostraEmpresa?id=${empresa.id}">edita</a>
                <a href="/gerenciador/removeEmpresa?id=${empresa.id}">remove</a>
            </c:forEach>
        </ul>
        
        
<!--        O mesmo que acima, porém, com scriplets-->
<!--        <ul>
             
                List<Empresa> lista = (List<Empresa>) request.getAttribute("empresas");
                for (Empresa empresa : lista) {
            
                    <li>empresa.getNome()</li>
            }
        </ul>-->
    </body>
</html>
