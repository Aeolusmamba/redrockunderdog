package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet("/serverregister")
public class serverregister extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection con = connection.getcon();
        String user = request.getParameter("new_user");
        String pwd = request.getParameter("new_pwd");
//        response.getWriter().write(key);
        if (user.equals("") || pwd.equals("")) {
            response.getWriter().write("请填写账号或密码！");
        } else {
            String reg_sql = "INSERT INTO users(username,password) VALUES('" + user + "','" + pwd + "')";
            try {
                Statement statement = con.createStatement();
                int rowsAffected = statement.executeUpdate(reg_sql);
                if(rowsAffected > 0){
                    response.getWriter().write("注册成功！");
                }
                else{
                    response.getWriter().write("注册失败，请重试！");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}