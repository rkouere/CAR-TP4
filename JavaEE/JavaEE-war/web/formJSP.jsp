<%-- 
    Document   : formJSP
    Created on : Apr 14, 2015, 10:43:21 AM
    Author     : rkouere

    Reponse Q2.3 :
--%>

<%-- 
    Document   : form
    Created on : Apr 14, 2015, 10:17:44 AM
    Author     : rkouere
    
    Answer question 2.3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Foundation | Welcome</title>
    <link rel="stylesheet" href="css/foundation.css" />
    <link rel="stylesheet" href="css/general.css" />
    <script src="js/vendor/modernizr.js"></script>
  </head>
    <body>    <%--
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