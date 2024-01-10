
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Seeprofile extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();

        // Initialize the database connection
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Create the database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobify", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");

        try {
            PreparedStatement pre = connection.prepareStatement("Select * from seekers_profile ");
            ResultSet re = pre.executeQuery();
            out.println("<html><body>");
            out.println("<table border='1' style='background:gray;'>");
            out.println("<tr><th>Full Name</th><th>Email</th><th>Mobile Number</th><th>Address</th><th>Resume</th><th>Picture</th><th>Linkedin Profile</th><th>Qualifications</th><th>Skills</th><th>Experience</th></tr>");
            while (re.next()) {

                String name = re.getString("Full_name");
                String mail = re.getString("Email");
                String num = re.getString("Phone_Number");
                String add = re.getString("Address");
                String res = re.getString("Upload_Your_Resume");
                String pic = re.getString("Upload_Your_Picture");
                String linkedin = re.getString("Linkedin_Profile_URL");
                String ed = re.getString("Education");
                String skills = re.getString("Skills");
                String exp = re.getString("Work_Experience");
                if (email.equals(mail)) {
                    out.println("<tr><td>" + name + "</td><td>" + mail + "</td><td>" + num + "</td><td>" + add + "</td><td>" + res + "</td><td>" + pic + "</td><td>" + linkedin + "</td><td>" + ed + "</td><td>" + skills + "</td><td>" + exp + "</td><td><a href='editprofile.html' style='background:'>Edit</a></td><td>Delete</td></tr>");

                    out.println("</table>");
                    out.println("<a href='Jobify-home.html'><button>Home</buuton></a>");
                    out.println("</body></html>");
                    re.close();
                    pre.close();
                }
            }
        } catch (Exception e) {

        }
    }

    // Close the result set and statement
    @Override
    public void destroy() {
        super.destroy();

        // Close the database connection
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
