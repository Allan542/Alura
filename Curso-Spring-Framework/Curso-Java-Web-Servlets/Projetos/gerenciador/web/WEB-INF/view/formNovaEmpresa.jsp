<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/entrada" var="linkEntradaServlet"/> <%--Action Link do form para pegar certinho o caminho, já que pode ser mudado dinamicamente--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <c:import url="logout-parcial.jsp" />
        <form action="${linkEntradaServlet}" method="POST">
            Nome: <input type="text" name="nome">
            Data de abertura: <input type="text" name="data">
            <input type="hidden" name="acao" value="NovaEmpresa">
            <input type="submit">
        </form>
    </body>
</html>

