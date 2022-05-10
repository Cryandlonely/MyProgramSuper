package showSuper;

import JDBC.tools;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import others.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@WebServlet({"/program/exhibit", "/program/delete", "/program/details", "/program/create", "/program/modify"})
public class pain extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false)!=null&&request.getSession(false).getAttribute("username")!=null){
            if ("/program/exhibit".equals(request.getServletPath())) {
                doList(request, response);
            } else if ("/program/delete".equals(request.getServletPath())) {
                doDel(request, response);
            } else if ("/program/modify".equals(request.getServletPath())) {
                doModify(request, response);
            } else if ("/program/details".equals(request.getServletPath())) {
                doDetails(request, response);
            }else if("/program/create".equals(request.getServletPath())){
                doCreate(request, response);
            }
        }else {
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }

    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String phonenum=request.getParameter("phonenum");
        ResourceBundle bundle = ResourceBundle.getBundle("source.account");
        String url = bundle.getString("url");
        Connection conn=null;
        PreparedStatement pss=null;
        int count=0;
        try {
            conn=tools.getConnection(url);
            String sql="insert into info(id,name,address,phonenum) values (?,?,?,?)";
            pss= conn.prepareStatement(sql);
            pss.setString(1,id);
            pss.setString(2,name);
            pss.setString(3,address);
            pss.setString(4,phonenum);
            count= pss.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            tools.close(conn,pss,null);
        }
        if (count==1) {
            response.sendRedirect(request.getContextPath()+"/program/exhibit");
        }
    }

    private void doDetails(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        Connection conn = null;
        PreparedStatement pss = null;
        ResultSet rs = null;
        List beanList = new ArrayList();
        ResourceBundle bundle = ResourceBundle.getBundle("source.account");
        String url = bundle.getString("url");
        try {
            conn = tools.getConnection(url);
            String sql = "select * from info where id=?";
            pss = conn.prepareStatement(sql);
            pss.setString(1, id);
            rs = pss.executeQuery();
            while (rs.next()) {
                bean bean = new bean();
                bean.setId(id);
                bean.setName(rs.getString("name"));
                bean.setAddress(rs.getString("address"));
                bean.setPhonenum(rs.getString("phonenum"));
                beanList.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            tools.close(conn, pss, rs);
        }
        request.setAttribute("beanList", beanList);
        request.getRequestDispatcher("/details.jsp").forward(request, response);
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phonenum = request.getParameter("phonenum");
        Connection con = null;
        PreparedStatement pst = null;
        ResourceBundle bundle = ResourceBundle.getBundle("source.account");
        String url = bundle.getString("url");
        int count = 0;
        try {
            con = tools.getConnection(url);
            String sql = "update info set name=?,address=?,phonenum=? where id=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, address);
            pst.setString(3, phonenum);
            pst.setString(4, id);
            count = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            tools.close(con, pst, null);
        }
        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/program/exhibit");
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResourceBundle bundle = ResourceBundle.getBundle("source.account");
        String url = bundle.getString("url");
        int count = 0;
        try {
            conn = tools.getConnection(url);
            String sql = "delete from info where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            tools.close(conn, pstmt, null);
        }
        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/program/exhibit");
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List list = new ArrayList();
        ResourceBundle bundle = ResourceBundle.getBundle("source.account");
        String url = bundle.getString("url");
        try {
            conn = tools.getConnection(url);
            String sql = "select * from info";
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                bean bean = new bean();
                bean.setId(res.getString("id"));
                bean.setName(res.getString("name"));
                bean.setAddress(res.getString("address"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            tools.close(conn, pstmt, res);
        }
        request.setAttribute("beanList", list);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
