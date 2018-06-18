<%-- 
    Document   : Error
    Created on : 14/06/2018, 11:55:54 AM
    Author     : jllpz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script>
            alert(<c:out value="${error}"/>);
            window.location.href="inicio.htm";
        </script>
    </body>
</html>
