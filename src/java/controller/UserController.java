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

    //check if password meets criteria
 /*
 ^ - start of string anchor
(?=.*[0-9]) - positive lookahead assertion for at least one digit
(?=.*[a-z]) - positive lookahead assertion for at least one lowercase letter
(?=.*[A-Z]) - positive lookahead assertion for at least one uppercase letter
(?=.*[@#$%^&+=]) - positive lookahead assertion for at least one special character (here, @#$%^&+=)
(?=\\S+$) - positive lookahead assertion for no whitespace characters in the entire string
.{8,} - match any character (except newline) at least 8 times
$ - end of string anchor */
 
    boolean isValidPassword = password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    if (!isValidPassword) {
        //password does not meet criteria, show error message
        req.setAttribute("errorMessage", "Password must be at least 8 characters long and contain at least one special character and one capital letter.");
        req.getRequestDispatcher("registerAccount.jsp").forward(req, resp);
        return;
    }
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
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    resp.sendRedirect("mainpage.jsp");

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
