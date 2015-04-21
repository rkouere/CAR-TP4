/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import car.dadatabse.Books;
import car.ejb.BooksFacadeLocalItf;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Displays a list of the titles present in the database
 * @author rkouere
 */
public class GetListBooks extends HttpServlet {
    @EJB
   private BooksFacadeLocalItf bf;
    
    private String title    =   "";
    private String author   =   "";
    private String date     =   "";
    boolean init            =   false;
    boolean logedIn         =   false;
    /**
     * Displays the list of books currently in the database and offers the user to add a book
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

            if(cookies !=null){
                for(Cookie cookie : cookies){
                   
                    if(cookie.getName().equals("user")) {
                        if(cookie.getValue().equals("fail")) {
                            this.logedIn = false;
                        }
                        else if(cookie.getValue().equals("failAlreadyInDatabase")) {
                            this.logedIn = false;
                        }
                        else
                            this.logedIn = true;
                    }
                }
            }
        response.setContentType("text/html;charset=UTF-8");
        ServletContext ctx = getServletContext();
        
        // si on a jamais encore initialise la base de donnee, on le fait
        if(ctx.getAttribute("init") != "true") {
            bf.init();
            ctx.setAttribute("init", "true");
        }
            
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(Tools.header);

            
            out.println("<h1>Liste des titres dans la base</h1>");
            out.println(Tools.tableHeader);
     
            List<Books> list = bf.findAllTitles();
            
            for(Books book:list)
                out.println("<tr><td>" + book.getAuthor()+ "</td><td>" + book.getTitle()+ "</td><td>" + book.getDate()+ "</td></tr>");
 
            out.println(Tools.tableFooter);
            
            out.println("<div><a href='addBook.jsp'>Rajouter un titre à la base de données.</a></div>");
            

            out.println("<div><a href='SearchBook'>Search a book.</a></div>");
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
     * Add a title to the database and then re-display the list of titles
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
        bf.addTitle(this.title, this.author, this.date);
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
