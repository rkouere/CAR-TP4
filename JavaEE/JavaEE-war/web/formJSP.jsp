<%-- 
    Document   : formJSP
    Created on : Apr 14, 2015, 10:43:21 AM
    Author     : rkouere

    Reponse Q2.3 :
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="general/header.html"%>
    <%--
        We check if this is a new session.
        If it is, we make sure that we print the parameteres in the form.
    --%>
    <%
        boolean getParam = false;
        if(!session.isNew()) {
            getParam = true;
            
        }
        else
            getParam = false;
            
    %>
     <h1>TP Java EE</h1>
     <% 
        if(session.getAttribute("error") == "true") {
            %>
            <div data-alert class="alert-box">
                Nous ne savons pas pourquoi mais quelques chose de très bizzard est arrivé : vous avez réussi à envoyer un post ne contenant pas tous les paramètres, bravo !!
                <a href="#" class="close">&times;</a>
            </div>
            <%
        }
     %>
      <form action="formDealer.jsp" method="POST">
            <div>Title :  <br />
                
                <input type="text" name="title" <% if(getParam) {  %> value="<%= session.getAttribute("title") %>"<%}%> /></div>
            <div>Author :  <br />
                <input required="" type="text" name="author" <% if(getParam) {  %> value="<%= session.getAttribute("author") %>"<%}%>/></div>
            <div>Date :  <br /><input required="" type="date" name="date" <% if(getParam) {  %> value="<%= session.getAttribute("date") %>"<%}%>/></div>
            <div><input type="submit" value="Submit" /></div>
        </form>

<%@include file="general/footer.html"%>