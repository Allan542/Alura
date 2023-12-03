<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        </c:if>   
         
    </body>
</html>