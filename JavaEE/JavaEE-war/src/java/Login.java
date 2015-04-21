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
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Was supposed to let a user login or crate a new user
 * @author rkouere
 */
public class Login extends HttpServlet {
   @EJB
   private UserFacadeLocalItf user;
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
        user.init();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(Tools.header);
            HttpSession session = request.getSession(true);
            
            while(session.getAttributeNames().hasMoreElements())
                out.println(session.getAttributeNames().nextElement());
//            
//            Cookie[] cookies = request.getCookies();
//
//            if(cookies !=null){
//                for(Cookie cookie : cookies){
//                   
//                    if(cookie.getName().equals("user")) {
//                        if(cookie.getValue().equals("fail")) {
//                            out.println("You are unknown to our database");
//                        }
//                        else if(cookie.getValue().equals("failAlreadyInDatabase")) {
//                            out.println("This user is already in the database. Please, either login with this username or choose another username");
//                        }
//                        else
//                            out.println("Welcome " + cookie.getValue() + " !");
//                    }
//                }
//            }
            
            
            
            out.println(
                    "<div id='login'>"
                            + "<h1>Login</h1>"
                            + "<div id='newLogin'>"
                                + "<h2>New user</h2>"
                                + "<form action='Login' method='POST'>" +
                                        "<div><input required='' type='text' name='pseudoNew' /></div>" +
                                        "<div><input type='submit' value='Submit' /></div>" +
                                    "</form>"
                            + "</div>"
                        + "<div id='checkLogin'>"
                                + "<h2>Returning user</h2>"
                                + "<form action='Login' method='POST'>" +
                                        "<div><input required='' type='text' name='pseudoCheck' /></div>" +
                                        "<div><input type='submit' value='Submit' /></div>" +
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
     * Checks whether a user is trying toi login or is trying to be a new user.
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
        if(request.getParameter("pseudoCheck") != null) { 
            // si l'utilisateur existe
            if(user.checkUserExists(request.getParameter("pseudoCheck")).size() == 1) {
                HttpSession session = request.getSession(true);
                session.setAttribute( "USER", user.checkUserExists(request.getParameter("pseudoCheck")) );
                response.sendRedirect(response.encodeRedirectURL("Login"));
            }
            else {
                Cookie userName = new Cookie("user", "fail");
                userName.setMaxAge(30*60);
                response.addCookie(userName);
                response.sendRedirect("Login");
            }
        }
        // if the user is already registered
        else if(request.getParameter("pseudoNew") != null) {
            //si l'utilisateur existe deja
            if(user.checkUserExists(request.getParameter("pseudoNew")).size() != 0) {
                Cookie userName = new Cookie("user", "failAlreadyInDatabase");
                userName.setMaxAge(30*60);
                response.addCookie(userName);
                response.sendRedirect("Login");
            }
            else {
                user.addUser(request.getParameter("pseudoNew"));

                Cookie userName = new Cookie("user", request.getParameter("pseudoNew"));
                userName.setMaxAge(30*60);
                response.addCookie(userName);
                response.sendRedirect("GetListBooks");
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
