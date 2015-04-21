/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import car.dadatabse.Books;
import car.ejb.BooksFacadeLocalItf;
import car.ejb.MakePurchaseLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Displays a list of the titles present in the database
 * @author rkouere
 */
public class GetListBooks extends HttpServlet { 
    /**
     * Access to the Books db
     * 
     * 
     */   
    @EJB
    private BooksFacadeLocalItf bf;
    /**
     * Access to the Purchase db
     * 
     * 
     */
    @EJB
    private MakePurchaseLocal pu;
 
    
    private String tmp = null;
    /**
     * The list of books currently being purchased
     */
    private List<Books> listPurchase = null;
    /**
     * The title of the book to add
     */
    private String title    =   "";
    /** 
     * The name of the author
     */
    private String author   =   "";
    /**
     * The date of publication
     */
    private String date     =   "";
    /**
     * Is the book db initialised ?
     */
    boolean init            =   false;
    /**
     * Is a user logged in ?
     */
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
 
        response.setContentType("text/html;charset=UTF-8");
        final HttpSession session = request.getSession();
        Enumeration<String> e = session.getAttributeNames();
        
        this.logedIn = Tools.isLogedIn(e, session);

        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            out.println(Tools.header);
            if(this.logedIn)
                out.println(Tools.logout);
            
            out.println("<form action='GetListBooks' method='POST'>"
                            + "<input type='hidden' name='addDummyBooks' value='add'/>"
                            + "<input type='Submit' value='Add books' />"
                        + "</form>");
            
            out.println("<h1>Liste des titres dans la base</h1>");

            out.println(Tools.tableHeader);
            // we display all the titles
            List<Books> list = bf.findAllTitles();
            for(Books book:list)
                out.println("<tr><td>" + book.getAuthor()+ "</td><td>" + book.getTitle()+ "</td><td>" + book.getDate()+ "</td>"
                        + "<td><form action='GetListBooks' method='POST'>"
                            + "<input type='hidden' name='addToCart' value='" + book.getTitle() + "'/>"
                            + "<input type='Submit' value='Add to cart' />"
                        + "</form></td>"
                        + "</tr>");
 
            
            out.println(Tools.tableFooter);
            out.println("</form>");
            if(this.logedIn)
                out.println("<div><a href='addBook.jsp'>Rajouter un titre à la base de données.</a></div>");

            out.println("<div><a href='SearchBook'>Search a book.</a></div>");
            
 
            
            // on essait de trouver le parametre qui correspond au cart
            //e = session.getAttributeNames();
            listPurchase = Tools.getCartBooks(e, session);
            if(!this.logedIn)
                out.println("<div>You have to be logged in to acces this list</div>");
            // we display all the books the user wants to purchase
            else {
                if(listPurchase.size() > 0) {
                    out.println("<div><h2>Shopping Cart</h2>");
                    out.println("<div id='purchase'>");

                        out.println("<form action='GetListBooks' method='POST'>"
                                    + "<input type='hidden' name='removeAll' value='remove'/>"
                                    + "<input type='Submit' class ='button tiny' value='Reset' />"
                                + "</form>");
                        out.println("<form action='Purchase' method='POST'>"
                                    + "<input type='hidden' name='finalPurchase' value='remove'/>"
                                    + "<input type='Submit' class ='button tiny' value='Purchase' />"
                                + "</form>");
                    out.println("</div>");

                    out.println(Tools.tableHeader);

                    for(Books b:listPurchase)
                        out.println("<tr><td>" + b.getAuthor()+ "</td><td>" + b.getTitle()+ "</td><td>" + b.getDate()+ "</td>"
                                + "<td><form action='GetListBooks' method='POST'>"
                                    + "<input type='hidden' name='removeFromCart' value='" + b.getTitle() + "'/>"
                                    + "<input type='Submit' value='Remove' />"
                                + "</form></td>"
                                + "</tr>");

                    out.println(Tools.tableFooter);
                }
            }
            
            out.println("</div>");
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
     * Post method manager. Can do any of the followings
     * - add a book
     * - remove all the books from the cart
     * - remove one item from the cart
     * - add an item to the cart
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        final HttpSession session = request.getSession();
        Enumeration<String> e = session.getAttributeNames();
        // we manage the different request
        Enumeration<String> params = request.getParameterNames();
        while(params.hasMoreElements()) {
            String element = params.nextElement();
            // we we want to add a book to the database
            switch(element){
                case "addBook":
                    this.title  = request.getParameter("title");
                    this.author = request.getParameter("author"); 
                    this.date   = request.getParameter("date");
                    bf.addTitle(this.title, this.author, this.date);
                    break;
                case "removeAll":
                    listPurchase = new ArrayList<>();
                    session.setAttribute("cart", listPurchase);
                    response.sendRedirect(response.encodeRedirectURL("GetListBooks"));
                    break;
                case "removeFromCart":
                    
                    listPurchase = Tools.getCartBooks(e, session);
                    tmp = request.getParameter("removeFromCart");
                    for(Books b : listPurchase) {
                        if(b.getTitle().equals(tmp)) {
                            System.out.println("XXXXXX " + tmp);
                            listPurchase.remove(b); 
                        }
                    }
                    request.removeAttribute("removeFromCart");
                    session.setAttribute("cart", listPurchase);
                    response.sendRedirect(response.encodeRedirectURL("GetListBooks"));
                    break;
                case "addToCart":
                    this.title  = request.getParameter("addToCart");

                     // on essait de trouver le parametre qui correspond au cart
                     listPurchase = Tools.getCartBooks(e, session);


                    for(Books b : bf.findAllTitles()){
                         if(b.getTitle().equals(this.title))
                             listPurchase.add(b);
                    }
                    request.removeAttribute("addToCart");
                    session.setAttribute("cart", listPurchase);                
                    response.sendRedirect(response.encodeRedirectURL("GetListBooks"));
                    break;
                    case "addDummyBooks":
                        bf.init();
                        response.sendRedirect(response.encodeRedirectURL("GetListBooks"));
                        break;
                default: 
                     break;
            
            }
   
        }
        
            
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
