/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
    This was a servlet I tried to implement for question 2.3
    This may not have been a good idea
*/

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rkouere
 */
public class formServlet extends HttpServlet {
    private String fundationHeader = "<!DOCTYPE html>" +
            "<html>" + 
            "<head>" +
            "<title>Form servlet</title>" + 
            "<link rel='stylesheet' href='css/foundation.css' />" + 
            "<link rel='stylesheet' href='css/general.css' />" + 
            "<script src='js/vendor/modernizr.js'></script>" + 
            "<title>Form servlet</title>" + 
            "</head>";   

    private String fundationFooter = "<script src=\"js/vendor/jquery.js\"></script>\n" +
"    <script src=\"js/foundation.min.js\"></script>\n" +
"    <script>\n" +
"      $(document).foundation();\n" +
"    </script>    ";
    
    private String title     =   "";
    private String author   =   "";
    private String date     =   "";
    private boolean post    =   false;
    
    private String formulaire = null;
    
    private String generateForm() {
        return "<form method='POST' action='formDealer.jsp' >" +
            "<div>Title :  <br /><input type='text' name='title' value='" + this.title + "' /></div>" +
            "<div>Author :  <br /><input type='text' name='author' value='" + this.author + "' /></div>" +
            "<div>Date :  <br /><input type='date' name='date' value='" + this.date + "' /></div>" +
            "<div><input type='submit' value='Submit' /></div>" + 
            "</form>";
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.formulaire = generateForm();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
 
            out.println(this.fundationHeader);
            out.println("<body>");
            out.println("<h1>TP Java EE servlet</h1>");
            out.println(this.formulaire);
            out.println(this.fundationFooter);
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.formulaire = generateForm();
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.title  = request.getParameter("title");
        this.author = request.getParameter("author");
        this.date   = request.getParameter("date");
        System.out.println("===================");
        System.out.println(this.title);
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
