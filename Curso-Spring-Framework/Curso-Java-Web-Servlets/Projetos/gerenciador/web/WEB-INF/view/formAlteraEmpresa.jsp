<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
            <fmt:formatDate value="${empresa.dataAbertura}" var="dataFormatada" pattern="dd/MM/yyyy" />
            Nome: <input type="text" name="nome" value="${empresa.nome}">
            Data de abertura: <input type="text" name="data" value="${dataFormatada}">
            <input type="hidden" name="id" value="${empresa.id}">
            <input type="hidden" name="acao" value="AlteraEmpresa">
            <input type="submit">
        </form>
    </body>
</html>

