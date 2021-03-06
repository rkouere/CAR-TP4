<%-- 
    This is the jsp that provides the user with a recap of the book
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="general/header.html"%>

        <h1>Récapitulatif</h1>
        <%-- 
            We get the parameters from the POST method and we update the session informations
        --%>
        <% 
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String date = request.getParameter("date");
            if(title.isEmpty() || author.isEmpty() || date.isEmpty()) {
                session.setAttribute("error", "true");
                %>
                <jsp:forward page="formJSP.jsp"></jsp:forward>
                <%
                
                
            }
            session.setAttribute("error", "false"); 
            session.setAttribute("title", title);
            session.setAttribute("author", author);
            session.setAttribute("date", date);
            
        %>
        <div><strong>Titre : </strong><%= 
                title
        %></div>
        <div><strong>Author :  </strong><%= 
                author
        %></div>
        <div><strong>Date :  </strong><%= 
               date
        %></div>
        <a href="formJSPEx2.jsp" class="button small" >OK</a>
        <script src="js/vendor/jquery.js"></script>
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
    </body>
</html>
