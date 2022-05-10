package showSuper;

import JDBC.tools;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

@WebServlet({"/program/check", "/program/exit", "/program/register"})
public class check extends HttpServlet {
    public static void main(String[] args) {

    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ("/program/check".equals(request.getServletPath())) {
            doCheck(request, response);
        } else if ("/program/exit".equals(request.getServletPath())) {
            doExit(request, response);
        } else if ("/program/register".equals(request.getServletPath())) {
            doRegister(request, response);
        }
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        Connection conn = null;
        PreparedStatement ps = null;
        ResourceBundle bundle = ResourceBundle.getBundle("source.account");
        String url = bundle.getString("urlcheck");
        int flag = 0;
        try {
            conn = tools.getConnection(url);
            String sql = "insert into list (username,password) values (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            tools.close(conn,ps,null);
        }
        if (flag==1) {
            response.sendRedirect(request.getContextPath());
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            response.sendRedirect(request.getContextPath());
        }
    }

    private void doCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checklogin=request.getParameter("checkbox");
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ResourceBundle bundle = ResourceBundle.getBundle("source.account");
        String url = bundle.getString("urlcheck");
        try {
            conn = tools.getConnection(url);
            String sql = "select * from list where username=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next() && password.equals(rs.getString("password"))) {
                if(checklogin.equals("1")){
                    Cookie namecookie=new Cookie("username",username);
                    Cookie pwdcookie=new Cookie("password",password);
                    namecookie.setMaxAge(60*60*24*10);
                    pwdcookie.setMaxAge(60*60*24*10);
                    namecookie.setPath(request.getContextPath());
                    pwdcookie.setPath(request.getContextPath());
                    response.addCookie(namecookie);
                    response.addCookie(pwdcookie);
                }
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/program/exhibit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            tools.close(conn, pst, rs);
        }
    }
}
