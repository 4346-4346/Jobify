<%-- 
    Document   : profile
    Created on : May 22, 2023, 6:26:27 PM
    Author     : devya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Profile Page</h1>
        <% 
            String data=(String)request.getAttribute("data");
            if(data!=null){
                    %>
                    <p>Your Profile: <%=data%></p>
                    <% }else{ %>
                    <p>Profile Does not exist.</p>
                    <% } %>
    </body>
</html>
