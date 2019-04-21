package servlets;

import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    public  static Connection getcon() {
        Connection con = null;
        try {
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalearn?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Chongqing&useSSL=false&allowPublicKeyRetrieval=true", "root", "lipeihong0419");
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return con;
    }

    public static void main(String[] args) {
        getcon();
    }
}