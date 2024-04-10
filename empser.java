import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empser")
public class empser extends HttpServlet {

    Connection c;
    Statement st;
    ResultSet rs;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("htmls");
            out.println("<head>");
            out.println("<title>Servlet dbprogram</title>");            
            out.println("</head>");
            out.println("<body>");
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            c=DriverManager.getConnection("jdbc:derby://localhost:1527/hotel","root","");

            st=c.createStatement();
          
            rs=st.executeQuery("select * from employee");
           out.println("<table border=2><tr><td>Name</td><td>age</td><td>dob</td><td>email</td></tr>");
           while(rs.next())
               
           {
              
               String uname=rs.getString("name");
               int aage=rs.getInt("age");
               int dobb=rs.getInt("dob");
               String  emailid=rs.getString("email");
               out.println("<tr><td> " +uname + "</td><td>"+aage+"</td><td> " +dobb + "</td><td>"+emailid+"</td></tr>");
               
           }
            
           
            out.println("</table></body>");
            out.println("</html>");
        } 
        catch(Exception e)
        {
            
        }
        finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
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