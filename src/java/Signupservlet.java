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
public class Signupservlet extends HttpServlet {

    private Object Drivermanager;
   

//    public void init() throws ServletException {
//        super.init();
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jobify", "root", "");
//        } catch (Exception e) {
//
//        }
//    }
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
        String pwd = request.getParameter("password");
        String cpwd = request.getParameter("confirmpassword");
        String num = request.getParameter("phone");
        String add = request.getParameter("address");
        String res = request.getParameter("resume");
        String img = request.getParameter("image");
        String linkp = request.getParameter("linkedin");
        String ed = request.getParameter("education");
        String skill = request.getParameter("skills");
        String exp = request.getParameter("experience");
        if (pwd.isEmpty() && cpwd.isEmpty()) {
            prt.println("<script>alert('Fill the fields')</script>");
        }
        if (pwd.equals(cpwd)) {
//            prt.println("<script>alert('Password and Confrim password does not match')</script>");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jobify", "root", "");
                PreparedStatement stm = con.prepareStatement("INSERT INTO `seekers_profile`(`Full_name`, `Email`, `Password`, `Confirm_password`, `Phone_Number`, `Address`, `Upload_Your_Resume`, `Upload_Your_Picture`, `Linkedin_Profile_URL`, `Education`, `Skills`, `Work_Experience`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                stm.setString(1, fname);
                stm.setString(2, mail);
                stm.setString(3, pwd);
                stm.setString(4, cpwd);
                stm.setString(5, num);
                stm.setString(6, add);
                stm.setString(7, res);
                stm.setString(8, img);
                stm.setString(9, linkp);
                stm.setString(10, ed);
                stm.setString(11, skill);
                stm.setString(12, exp);
                int i = stm.executeUpdate();
                if (i > 0) {
                    response.sendRedirect("login page.html");
                } else {
                    response.sendRedirect("profile.html");
                }

            } //catch (SQLException ex) {
            //                Logger.getLogger(Signupservlet.class.getName()).log(Level.SEVERE, null, ex);
            ////            } catch (ClassNotFoundException ex) {
            ////                Logger.getLogger(Signupservlet.class.getName()).log(Level.SEVERE, null, ex);
            ////            }
             catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Signupservlet.class.getName()).log(Level.SEVERE, null, ex);
            }
//        String data=fetchData();
//        request.setAttribute("data", data);
//        request.getRequestDispatcher("profile.jsp").forward(request, response);

//    private String fetchData(){
//        String data=null;
//        try{
//            String sql="SELECT * FROM `seekers_profile`";
//            PreparedStatement prd=con.prepareStatement(sql);
//            ResultSet rs=prd.executeQuery();
//            while(rs.next()){
//                data=rs.getString("Full_name");
//            }
        }
    }

//        return data;
//    }
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
