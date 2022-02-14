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
            sdmx.vtlenviron.entities.Script s = sessionBean.findScript(l);
          ScriptInputUtil.createScriptInput(s);
          sessionBean.mergeScript(s);
          response.sendRedirect("edit.jsp?id="+s.getId());
        %>
