<%-- 
    Document   : formJSP
    Created on : Apr 14, 2015, 10:43:21 AM
    Author     : rkouere

    Reponse Q2.3 :
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="general/header.html"%>
    <%--
        We check that there has not been any kind of weird post sent
        If it is the case we display a message
    --%>
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
     
     <h2>Ex2</h2>

      <form action="GetListBooks" method="POST">
            <div>Title :  <br />
                
                <input required="" type="text" name="title" /></div>
            <div>Author :  <br />
                <input required="" type="text" name="author"/></div>
            <div>Date :  <br /><input required="" type="date" name="date"/></div>
            <div><input type="submit" value="Submit" /></div>
        </form>

<%@include file="general/footer.html"%>