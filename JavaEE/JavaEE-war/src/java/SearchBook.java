/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import car.dadatabse.Books;
import car.ejb.BooksFacadeLocalItf;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rkouere
 */
public class SearchBook extends HttpServlet {
    @EJB
    private BooksFacadeLocalItf bf;
    private List<String> titles = new ArrayList<>();
    
    private String form = "<form action=\"SearchBook\" method=\"POST\">\n" +
"            <div>Title :  <br />\n" +
"                <input required=\"\" type=\"text\" name=\"author\" /></div>\n" +

"            <div><input type=\"submit\" value=\"Submit\" /></div>\n" +
"        </form>";
    boolean init = false;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        ServletContext ctx = getServletContext();
        
        if(ctx.getAttribute("init") != "true") {
            bf.init();
            ctx.setAttribute("init", "true");
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(Tools.header);
            out.println("<div>What are you looking for ?</div>");
            out.println(this.form);
            
            for(String title:this.titles) 
                out.println("<div>" + title + "</div>");
            out.println("<div><a href='GetListBooks'>Back to book listing</a></div>");
           out.println(Tools.footer);
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
        List<Books> list = bf.findBooksByAuthor(request.getParameter("author"));
        for(Books book:list)
            this.titles.add(book.getTitle());
        
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
