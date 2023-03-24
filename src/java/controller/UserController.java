package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author User
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "register":
                    insertUser(request, response);
                    break;
                case "signIn":
                    searchUser(request, response);
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "dashboard.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void insertUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException, ClassNotFoundException {

        //get all data from register page
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //keep data into javabeans
        User newUser = new User();

        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);

        //pass the bean to DAO
        UserDAO userdao = new UserDAO();
        userdao.save(newUser);

        //save the bean as attribute and pass to view
        req.setAttribute("user", newUser);
        resp.sendRedirect("index.jsp");
    }

    private void searchUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException, ClassNotFoundException {

        if (req.getParameter("action") != null) {

            String username = req.getParameter("username");
            String password = req.getParameter("password");

            UserDAO userDAO = new UserDAO();

            try {
                User user = userDAO.retrieveUser(username, password);

                if (user != null) {
                    //usertype = req.getParameter("usertype");
                    HttpSession session = req.getSession();
                    //session.setAttribute("userid", user.getUserId());
                    session.setAttribute("user", user);
                    resp.sendRedirect("mainpage.jsp");
                    // session.setAttribute("loginusertype", user.getUserType());

                    //loginusertype = user.getUserType();
                    /* if (loginusertype.equals("ADMIN"))
                    {
                       
                        //updatelogin(request, response);
                        resp.sendRedirect("dashboardadmin.jsp");
                    }
                    else
                    {
                        resp.sendRedirect("dashboard.jsp");
                    }
                   
                    
                   //resp.sendRedirect("dashboard.jsp");
                     */
                } else {
                    req.setAttribute("error", "Invalid email or password!");
                    RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                    rd.forward(req, resp);
                }

            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

    }
}
