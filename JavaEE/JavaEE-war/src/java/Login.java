/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import car.dadatabse.Client;
import car.ejb.BooksFacadeLocalItf;
import car.ejb.UserFacadeLocalItf;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * WManages the user loggin
 * @author rkouere
 */
public class Login extends HttpServlet {
   @EJB
   private UserFacadeLocalItf user;
   private boolean logedIn = false;
   
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
        HttpSession session = request.getSession(true);
        Enumeration<String> e = session.getAttributeNames();

        user.init();
        
        this.logedIn = Tools.isLogedIn(e, session);
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(Tools.header);
            if(this.logedIn)
                out.println(Tools.logout);

            
            out.println(
                    "<div id='login'>"
                            + "<h1>Login</h1>"
                            + "<div id='newLogin'>"
                                + "<h2>New user</h2>"
                                + "<form action='Login' method='POST'>" +
                                        "<div><input required='' type='text' name='pseudoNew' /></div>" +
                                        "<div><input type='submit' value='Submit'  class='button tiny'/></div>" +
                                    "</form>"
                            + "</div>"
                        + "<div id='checkLogin'>"
                                + "<h2>Returning user</h2>"
                                + "<form action='Login' method='POST'>" +
                                        "<div><input required='' type='text' name='pseudoCheck' /></div>" +
                                        "<div><input type='submit' value='Submit' class='button tiny' /></div>" +
                                    "</form>"
                        + "</div>"
                    + "</div>");
            

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
     * Handles the loggin manager
     * - Checks whether a user is trying toi login or is trying to be a new user.
     * If the user exists, we set a session 
     * If the user wants to be a new customer, we add him in the database and we create a session
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // if the user is already registered
        final HttpSession session = request.getSession();
        // we manage the different request
        Enumeration<String> params = request.getParameterNames();
        String element = null;
        while(params.hasMoreElements()) {
            element = params.nextElement();
            switch(element){
                case "pseudoCheck":
                    if(user.checkUserExists(request.getParameter("pseudoCheck")))
                        session.setAttribute("login", "true");
                        response.sendRedirect(response.encodeRedirectURL("GetListBooks"));
                    break;
                case "pseudoNew":
                    if(!user.checkUserExists(request.getParameter("pseudoNew")))
                        user.addUser(request.getParameter("pseudoNew"));
                        session.setAttribute("login", "true");
                        response.sendRedirect(response.encodeRedirectURL("GetListBooks"));
                    break;
                case "logout":
                    session.setAttribute("login", "false");
                    response.sendRedirect(response.encodeRedirectURL("Login"));
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
