/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rkouere
 */
public class Tools {
    public static final String header = 
            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<title>Servlet GetListBooks</title>"
            + "<link rel=\"stylesheet\" href=\"css/foundation.css\" />"
            + "<link rel=\"stylesheet\" href=\"css/general.css\" />"
            + "</head>"
            + "<body>"
            + "<div class='wrapper'>"
            + "<header>"
                + "<h1><a href='http://localhost:8080/PAC-TP4/'>CAR TP4</a></h1>"
                + "<ul>"
                    + "<li><a href='http://localhost:8080/PAC-TP4/formJSPEx2.jsp'>Ex2 : JSP</a></li>"
                    + "<li><a href='http://localhost:8080/PAC-TP4/GetListBooks'>Ex3</a></li>"
                + "</ul>"
            + "</header>";
    
    public static final String footer = 
            "</div>"
            + "</body>"
            + "</html>";
    
    public static final String tableHeader = 
                        "<table width='100%'> "
                        + "<thead>"
                            + "<tr>" 
                                + "<td>Author</td>"
                                + "<td>Title</td>"
                                + "<td>Date</td>"
                            + "</tr>"
                        + "</thead>"
                        + "<tbody>";
    public static final String tableFooter = "</tbody>" + "</table>";
    
}
 