package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

@WebServlet("/serverlogin")
public class serverlogin extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection con = connection.getcon();
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
//        response.getWriter().write(key);
        String user_sql = "SELECT password FROM users WHERE username='" + user + "'";
        try {
            Statement state = con.createStatement();
//            state.executeUpdate(user_sql);
            boolean hasResult = state.execute(user_sql);
            if (hasResult) {
                ResultSet resultSet = state.getResultSet();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                //            获取接口数量
                int columnCount = resultSetMetaData.getColumnCount();
                while(resultSet.next()) {
                    for(int i = 0 ; i<columnCount;i++){
                        if(pwd.equals(resultSet.getString(i + 1))){
                                    response.getWriter().write("登录成功！");
                        }
                        else{
                                    response.getWriter().write("密码有误！");
                        }
                    }
                }
            }
            else{
                        response.getWriter().write("账号输入有误！");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public static void main(String[] args) {
        Connection con = connection.getcon();
    }
}
