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
 * Search a book by title
 * @author rkouere
 */
public class SearchBook extends HttpServlet {
    @EJB
    private BooksFacadeLocalItf bf;
    private List<Books> titles = new ArrayList<>();
    
    private String form = "<form action=\"SearchBook\" method=\"POST\">\n" +
"                <div><input required=\"\" type=\"text\" name=\"author\" /></div>\n" +

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
        

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(Tools.header);
            out.println("<h2>Online search by author</h2>");
            out.println(this.form);
            out.println("<div><br /></div>");

            if(this.titles.size() > 0) {
                out.println("<h3>Results</h3>");
                out.println(Tools.tableHeader);
                for(Books title:this.titles) 
                    out.println("<tr><td>" + title.getAuthor()+ "</td><td>" + title.getTitle()+ "</td><td>" + title.getDate()+ "</td></tr>");
                out.println(Tools.tableFooter);
            }
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
        System.out.println(request.getParameter("author"));
        for(Books book:list)
            this.titles.add(book);
        
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
