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
        <%
          sdmx.vtlenviron.entities.Script s = sessionBean.createScript();
          response.sendRedirect("index.jsp");
        %>
            <tr><td>Name:<input type="text" value="${s.name}"/></td><td>Desc:<input type="text" value="${s.description}"/></td></tr><tr><td colspan="2">Script:<textarea>${s.script}</textarea></td></tr>
        </table>
    </body>
</html>
