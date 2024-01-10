/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author shiva
 */
public class Loginservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        PrintWriter prt = response.getWriter();
        String mail = request.getParameter("username");
        String pwd = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jobify", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `seekers_profile`");
            while (rs.next()) {
                String email = rs.getString("Email");
                String pass = rs.getString("Password");
                if (email.equals(mail) && pass.equals(pwd)) {
                    RequestDispatcher req = request.getRequestDispatcher("Jobify-home.html");
                    req.forward(request, response);
                }
                else {
//                    prt.println("<script>alert('Email and Password are incorrect')</script>");
                    response.sendRedirect("index.html");
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Loginservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
