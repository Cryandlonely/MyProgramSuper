package showSuper;

import JDBC.tools;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

@WebServlet({"/checkSession"})
public class checkCookie extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    name = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }

        if (name != null && password != null) {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            Boolean success = false;
            try {
                ResourceBundle bundle = ResourceBundle.getBundle("source.account");
                con = tools.getConnection(bundle.getString("urlcheck"));
                ps = con.prepareStatement("select * from list where username=? and password=?");
                ps.setString(1,name);
                ps.setString(2,password);
                rs = ps.executeQuery();
                while (rs.next()) {
                    success = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                tools.close(con, ps, rs);
            }
            if(success){
                response.sendRedirect(request.getContextPath()+"/program/exhibit");
            }else {
                response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
        }
    }
}
