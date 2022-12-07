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
        <form action="${linkEntradaServlet}" method="POST">
            Login: <input type="text" name="login">
            Senha: <input type="password" name="senha">
            <input type="hidden" name="acao" value="Login">
            <input type="submit">
        </form>
    </body>
</html>

