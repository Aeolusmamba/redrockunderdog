//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//public class GETRUL {
//static{
//    try{
//        String test= "com.mysql.cj.jdbc.Driver";
//        Class.forName(test);
//    }catch (Exception e){
//        e.printStackTrace();
//    }
//}
//public static Connection connect{
//    Connection conn = null;
//    try{
//        conn = DriverManager.connect("jdbc:")
//    }
//    }
//}
//导入包
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 数据库连接
 */
public class connect {
    public static Connection getConn(){
        Connection con = null;
        try{
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalearn?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false","root","lipeihong0419" );
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
    getConn();
    }
}
