<%@page import="sdmx.vtlenviron.util.ScriptInputUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="sessionBean" class="sdmx.vtlenviron.session.ScriptSessionBean"
                     scope="session"></jsp:useBean>        <%
            Long l = Long.parseLong(request.getParameter("id"));
            if (l == null) {
                response.sendRedirect("index.jsp");
                return;
            }
          sessionBean.deleteScriptOutput(l);
          response.sendRedirect(request.getHeader("Referer"));
        %>
