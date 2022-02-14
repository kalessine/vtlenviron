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
        <body>
        <%
            Long l = Long.parseLong(request.getParameter("id"));
            if (l == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            sdmx.vtlenviron.entities.ScriptInput si = sessionBean.findScriptInput(l);
            if (request.getMethod().equals("POST")) {
                si.setName(request.getParameter("name"));
                si.setDescription(request.getParameter("description"));
                sessionBean.mergeScriptInput(si);
                response.sendRedirect("edit.jsp?id=" + si.getScript().getId());
                return;
            }
            response.sendRedirect("edit.jsp?id=" + si.getScript().getId());%>
