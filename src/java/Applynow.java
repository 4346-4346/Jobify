/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author devya
 */
public class Applynow extends HttpServlet {

    
    

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
        String mail = request.getParameter("email");
        String cloc = request.getParameter("location");
        String res = request.getParameter("resume");
        String cletter = request.getParameter("cover-letter");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jobify", "root", "");
            PreparedStatement stm = con.prepareStatement("INSERT INTO `applynow`(`Full_Name`, `Email`, `Current_Location`, `Resume`, `Cover_Letter`) VALUES (?,?,?,?,?)");
            stm.setString(1, fname);
            stm.setString(2, mail);
            stm.setString(3, cloc);
            stm.setString(4, res);
            stm.setString(5, cletter);
            int i = stm.executeUpdate();

            if (i > 0) {
                response.sendRedirect("confirmation.html");
            } else {
                response.sendRedirect("applynow.html");
//              prt.println("<script>alert('Fill All The Fields')</script>");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Applynow.class.getName()).log(Level.SEVERE, null, ex);
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

}
