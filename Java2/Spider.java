import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Spider {
//    // 连接对象
//    private Connection con;
//    // 传递sql语句
//    private Statement stt;
//    // 结果集
//    private ResultSet set;

    // 查询
    private static int num = 1;

    public static void main(String[] args) {
        Connection con = connect.getConn();
        String url1 = "http://jwzx.cqupt.edu.cn/kebiao/index.php#kbTabs-bj";
        InputStream is = null;  //创建输入流用于读取流
        BufferedReader br = null; //包装流,加快读取速度
        StringBuffer html = new StringBuffer(); //用来保存读取页面的数据.
        String temp = ""; //创建临时字符串用于保存每一次读的一行数据，然后html调用append方法写入temp;
        try {
            URL url2 = new URL(url1); //获取URL;
            is = url2.openStream(); //打开流，准备开始读取数据;
            br = new BufferedReader(new InputStreamReader(is)); //将流包装成字符流，调用br.readLine()可以提高读取效率，每次读取一行;
            while ((temp = br.readLine()) != null) {//读取数据,调用br.readLine()方法每次读取一行数据,并赋值给temp,如果没数据则值==null,跳出循环;
                html.append(temp); //将temp的值追加给html,这里注意的时String跟StringBuffere的区别前者不是可变的后者是可变的;
            }
            //System.out.println(html); //打印出爬取页面的全部代码;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String regex_sub = "\\\\s+|\\t|\\r|\\n|\\v";     //替换换行符
        Pattern pattern = Pattern.compile(regex_sub);
        Matcher matcher = pattern.matcher(html);
        String strNoBlank = matcher.replaceAll("`````````");
        while (num <= 16) {     //一共16个学院，循环16次
            String regex_exp2 = "<h3>.+?</table>";
            Pattern pattern1 = Pattern.compile(regex_exp2);
            Matcher matcher1 = pattern1.matcher(strNoBlank);
            String part;
            String institute;   //学院名字放在这里面
            String regex_insti = "(?<=<h3>).+?(?=</h3>)";   //匹配出学院名字
            Pattern pattern2 = Pattern.compile(regex_insti);
            Matcher matcher2;  //学院
            Matcher matcher3;  //班级
            Matcher matcher4;  //专业
            List<String> list = new ArrayList<>();
            List<String> list0 = new ArrayList<>();
            String regex_class = "(?<=bj=.{5,9}' target=_blank>).+?(?=</a>)";   //匹配班级
            Pattern pattern3 = Pattern.compile(regex_class);
            String regex_major = "([0-9]{4}-|L[0-9]{3}-).+?(\\(.+?\\))?(?=</td>)";   //匹配专业
            Pattern pattern4 = Pattern.compile(regex_major);
            try {
                String insti = "";
                if (matcher1.find()) {
                    part = matcher1.group();    //找到第一个匹配结果整体
                    matcher2 = pattern2.matcher(part);
                    if (matcher2.find()) {
                        institute = matcher2.group();
                        insti = institute;
                    }
                    matcher3 = pattern3.matcher(part);
                    while (matcher3.find()) {
                        list.add(matcher3.group());     //将班级放入数组
                    }
                    matcher4 = pattern4.matcher(part);
                    while (matcher4.find()) {       //专业放入数组
                        list0.add(matcher4.group());
                    }
                    strNoBlank = delete(strNoBlank, part);
                }
                String[] grade = {"2015级", "2016级", "2017级", "2018级"};
                for (String s : list) {
                    String maj = s.substring(0, 4);     //匹配专业
                    int len = s.length();
                    String gra = s.substring(len - 1 - 4, len - 1 - 2);
                    String sql = "insert into classes(class) values('" + s + "')";
                    Statement state = con.createStatement();
                    state.executeUpdate(sql);
                    String ini = "update classes set institute='" + insti + "' where class = '" + s + "'";
                    Statement state0 = con.createStatement();
                    state0.executeUpdate(ini);
                    for (String t : list0) {
                        String ori = t.substring(0, 4);
                        if (maj.equals(ori)) {
                            String sql2 = "update classes set major='" + t + "' where class = '" + s + "'";    //插入专业
                            Statement state2 = con.createStatement();
                            state2.executeUpdate(sql2);
                            break;
                        }
                    }
                    for (int i = 0; i < grade.length; i++) {
                        String ori2 = grade[i].substring(2, 4);
                        if (ori2.equals(gra)) {
                            String agent = grade[i];
                            String sql3 = "update classes set grade='" + agent + "' where class = '" + s + "'";
                            Statement state3 = con.createStatement();
                            state3.executeUpdate(sql3);
                            break;
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            num++;
        }
    }

    private static String delete(String str1, String str2) {
        StringBuilder sb = new StringBuilder(str1);
        int delCount = 0;
        String str = "";
        while (true) {
            int index = sb.indexOf(str2);
            if (index == -1) {
                break;
            }
            sb.delete(index, index + str2.length());
            delCount++;
        }
        if (delCount != 0) {
            str = sb.toString();
        } else {
            System.out.println("删除不成功！");
        }
        return str;

    }
}