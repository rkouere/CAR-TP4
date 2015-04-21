
import car.dadatabse.Books;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpSession;

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
    public static final String logout = "<form action='Login' method='POST'>" +
                        "<div><input type='hidden' required='' type='text' name='logout' /></div>" +
                        "<div><input type='submit' value='Logout' class='button tiny' /></div>" +
                    "</form>";
    
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
                    + "<li><a href='http://localhost:8080/PAC-TP4/Login'>Login</a></li>"
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
                                + "<td> </td>"
                            + "</tr>"
                        + "</thead>"
                        + "<tbody>";
    public static final String tableFooter = "</tbody>" + "</table>";
    public static final List<Books> getCartBooks(Enumeration<String> e, HttpSession session) {
        String tmp = null;
        List<Books> listPurchase = new ArrayList<>();
        
        while(e.hasMoreElements()){
            tmp = e.nextElement();
            if(tmp.equals("cart")){ 
                listPurchase = (List<Books>) session.getAttribute("cart"); 
            }
         }
        return listPurchase;
    }
    
    public static final boolean isLogedIn(Enumeration<String> e, HttpSession session) {
        while(e.hasMoreElements()) {
            String tmp = e.nextElement().toString();
            if(tmp.equals("login"))
                if(session.getAttribute("login").equals("true"))
                    return true;
        }
        return false;
    }
}
 