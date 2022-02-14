<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="sessionBean" class="sdmx.vtlenviron.session.ScriptSessionBean"
                     scope="session"></jsp:useBean>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
        <table>
        <c:forEach var="s" items="${sessionBean.scripts}">
            <tr><td>Id:${s.id}</td><td>Name:${s.name}</td><td>Desc:${s.description}</td><td><a href="invoke.jsp?id=${s.id}">Invoke</a></td><td><a href="edit.jsp?id=${s.id}">Edit</a></td><td><a href="delete.jsp?id=${s.id}">Delete</a></td></tr>
        </c:forEach>
        </table>
            <a href="create.jsp">Create</a>
    </body>
</html>
