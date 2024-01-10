/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
 * @author devya
 */
public class PostJobservlet extends HttpServlet {

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
        PrintWriter prt=response.getWriter();
        String title = request.getParameter("job title");
        String name = request.getParameter("company name");
        String loc = request.getParameter("job location");
        String des = request.getParameter("job description");
        String req = request.getParameter("job requirements");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jobify", "root", "");
            PreparedStatement stm = con.prepareStatement("INSERT INTO `post_job`(`Job_title`, `Company_name`, `Job_location`, `Job_description`, `Job_requirements`) VALUES (?,?,?,?,?)");
            stm.setString(1, title);
            stm.setString(2, name);
            stm.setString(3, loc);
            stm.setString(4, des);
            stm.setString(5, req);
            int i = stm.executeUpdate();

            if (i > 0) {
                response.sendRedirect("jobposted.html");
            } else {
                response.sendRedirect("posting job.html");
                prt.println("<script>alert('Fill All The Fields')</script>");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostJobservlet.class.getName()).log(Level.SEVERE, null, ex);
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
