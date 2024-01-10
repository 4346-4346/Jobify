/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author devya
 */
public class employerssignupservlet extends HttpServlet {

    private Object Drivermanager;

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
        String fname = request.getParameter("name");
        String pwd = request.getParameter("password");
        String cpwd = request.getParameter("confirmpassword");
        String cname = request.getParameter("company_name");
        String mail = request.getParameter("email");
        String num = request.getParameter("phone");
        String ind = request.getParameter("industry");
        if (pwd.isEmpty() && cpwd.isEmpty()) {
            prt.println("<script>alert('Fill the fields')</script>");
        }
        if (pwd.equals(cpwd)) {
            prt.println("<script>alert('Password and Confrim password does not match')</script>");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jobify", "root", "");
                PreparedStatement stm = con.prepareStatement("INSERT INTO `employers_signup`(`full_name`, `Password`, `Confirm_Password`,`Company_Name`, `Email`, `Mobile_Number`, `Industry`) VALUES (?,?,?,?,?,?,?)");
                stm.setString(1, fname);
                stm.setString(2, pwd);
                stm.setString(3, cpwd);
                stm.setString(4, cname);
                stm.setString(5, mail);
                stm.setString(6, num);
                stm.setString(7, ind);
                int i = stm.executeUpdate();

                if (i > 0) {
                    response.sendRedirect("employers login.html");
                } else {
                    response.sendRedirect("SignupEmployers.html");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(employerssignupservlet.class.getName()).log(Level.SEVERE, null, ex);
            }
//        } else {
//                  prt.println("<script>alert('Password and Confirm Password are not same.')");
//        }
        }}

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
