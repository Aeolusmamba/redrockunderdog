import java.io.Serializable;


public class student implements Serializable {
    long id;
    private String name;
    private long classid;
    private int age;
    private char sex;
    public String getName(){
     return name;
    }
    public long getID(){
        return id;
    }
    public int getAge(){
        return age;
    }
    public char getSex(){
        return sex;
    }
    public long getClasses(){
        return classid;
    }
    @Override
    public String toString(){
        return "班级："+classid+" "+"姓名："+name+" "+"学号："+id+" "+"年龄："+age+" "+"性别："+sex;
    }
    public student(long classid,String name,long id,int age,char sex){
        this.classid=classid;
        this.id=id;
        this.age=age;
        this.sex=sex;
        this.name=name;
    }
}
