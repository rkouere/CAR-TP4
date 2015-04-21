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
import java.util.Enumeration;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet
 * @author rkouere
 */
public class Purchase extends HttpServlet {
    private String tmp = null;
    private List<Books> listPurchase = null;
    @EJB
    private MakePurchaseLocal pu;
    
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
        response.setContentType("text/html;charset=UTF-8");
        final HttpSession session = request.getSession();
        Enumeration<String> e = session.getAttributeNames();
        
        try (PrintWriter out = response.getWriter()) {
//            finalPurchase
            /* TODO output your page here. You may use following sample code. */
            out.println(Tools.header);
            
            out.println("<h1>Summary of you basket</h1>");

            listPurchase = Tools.getCartBooks(e, session);
            
            if(this.listPurchase.size() == 0)
                out.println("<h1>Votre panier est vide</h1>");
            else {
                out.println(Tools.tableHeader);

                for(Books b:listPurchase)
                    out.println("<tr><td>" + b.getAuthor()+ "</td><td>" + b.getTitle()+ "</td><td>" + b.getDate()+ "</td>"
                            + "<td><form action='GetListBooks' method='POST'>"
                                + "<input type='hidden' name='removeFromCart' value='" + b.getTitle() + "'/>"
                                + "<input type='Submit' value='Remove' />"
                            + "</form></td>"
                            + "</tr>");

                out.println(Tools.tableFooter);
                out.println("<div id='purchase'>");
                    out.println("<form action='GetListBooks' method='POST'>"
                        + "<input type='hidden' name='removeAll' value='remove'/>"
                        + "<input type='Submit' class ='button tiny' value='Reset' />"
                    + "</form>");
                    out.println("<form action='Purchase' method='POST'>"
                        + "<input type='hidden' name='bringInTheMoney' value='add'/>"
                        + "<input type='Submit' class ='button tiny' value='Purchase' />"
                    + "</form>");
                out.println("</div>");

            }
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
        
        final HttpSession session = request.getSession();
        Enumeration<String> e = session.getAttributeNames();
        // we manage the different request
        Enumeration<String> params = request.getParameterNames();
        String element = null;
        while(params.hasMoreElements()) {
            element = params.nextElement();
            switch(element){
                case "bringInTheMoney":
                    listPurchase = Tools.getCartBooks(e, session);
                    pu.makePurchase(listPurchase);
                    listPurchase = new ArrayList<>();
                    session.setAttribute("cart", listPurchase);
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
