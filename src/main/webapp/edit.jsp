<%@page import="sdmx.vtlenviron.entities.ScriptOutput"%>
<%@page import="sdmx.vtlenviron.entities.ScriptInput"%>
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
        <body><a href="index.jsp">Go Back to Index</a>
        <%
            Long l = Long.parseLong(request.getParameter("id"));
            if (l == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            sdmx.vtlenviron.entities.Script s = sessionBean.findScript(l);
        %>
        <form action="edit.jsp" enctype="" method="post">
            <a href="invoke.jsp?id=<%=s.getId()%>">Invoke</a>
            <table>
                <%
                    if (request.getMethod().equals("POST")) {
                        s.setName(request.getParameter("name"));
                        s.setName(request.getParameter("name"));
                        s.setDescription(request.getParameter("desc"));
                        s.setScript(request.getParameter("script"));
                        sessionBean.mergeScript(s);
                        response.sendRedirect("edit.jsp?id=" + s.getId());
                        return;
                    }
                %>
                <tr><td><input id="id" name="id" type="hidden" value="<%=s.getId()%>"/>Name:<input id="name" name="name" type="text" value="<%=s.getName()%>"/></td><td>Desc:<input id="desc" name="desc" type="text" value="<%=s.getDescription()%>"/></td></tr><tr><td colspan="2">Script:<textarea id="script" name="script"><%=s.getScript()%></textarea></td></tr>
            </table>
            <input type="submit" name="submit" text="submit"/>
        </form>
        Inputs
        <%
            for (int i = 0; s.getScriptInputList() != null && i < s.getScriptInputList().size(); i++) {
                ScriptInput si = s.getScriptInputList().get(i);
        %>
        <form action="editsi.jsp?id=<%=si.getId()%>" enctype="" method="post">
            <table>

                <tr><td>Id:<input name="id" type="text" value="<%=si.getId()%>"/><a href="deletesi.jsp?id=<%=si.getId()%>">Delete</a></td></tr>
                <tr><td>Name:<input name="name" type="text" value="<%=si.getName()%>"/></td></tr>
                <tr><td>Desc:<input name="description" type="text" value="<%=si.getDescription()%>"/></td></tr>

            </table>
            <input type="submit" name="submit" text="submit"/>
        </form>
        <% }%>
        <a href="createsi.jsp?id=<%=s.getId()%>">Create Input</a><br/>
        Outputs
        <%
            for (int i = 0; s.getScriptOutputList() != null && i < s.getScriptOutputList().size(); i++) {
                ScriptOutput so = s.getScriptOutputList().get(i);
        %>
        <form action="editso.jsp?id=<%=so.getId()%>" enctype="" method="post">
            <table>

                <tr><td>Id:<input name="id"  type="text" value="<%=so.getId()%>"/><a href="deleteso.jsp?id=<%=so.getId()%>">Delete</a></td></tr>
                <tr><td>Name:<input name="name" type="text" value="<%=so.getName()%>"/></td></tr>
                <tr><td>Desc:<input name="description" type="text" value="<%=so.getDescription()%>"/></td></tr>
                <tr><td>Action:<input name="action" type="text" value="<%=so.getAction()%>"/></td></tr>

            </table>
            <input type="submit" name="submit" text="submit"/>
        </form>
        <% }%>
        <a href="createso.jsp?id=<%=s.getId()%>">Create Output</a>
    </body>
</html>
