
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;

public class MyClass {

    private static List<student> stuList = null;
    private static int size = 0;

    public static void createclass() {
        String names[] = {"Lee", "Wang", "Zhang", "Liu", "Xie"};
        long id[] = {2019212333, 2019329223, 2019222999, 2019212889, 2019212765};
        int ages[] = {17, 20, 17, 18, 19};
        char sexes[] = {'男', '女', '男', '女', '男'};
        long classid = 11011702;
        size = names.length;
        stuList = new ArrayList<student>();

        for (int i = 0; i < size; i++) {
            student temp;
            temp = new student(classid, names[i], id[i], ages[i], sexes[i]);
            stuList.add(temp);
        }
    }

    public static int Add() {
        student temp;
        temp = new student(11011702, "Xu", 2017000994, 19, '男');
        stuList.add(temp);
        size = stuList.size();
        return stuList.size();
    }

    public static int Remove() {
        stuList.remove(2);
        size = stuList.size();
        return stuList.size();
    }

    public static void Output() {
        int i;
      /*
      **遍历一：
        for(i=0;i<stuList.size();i++)
            System.out.println(stuList.get(i));
      */
      /*
      **遍历二：
         for(student s:stuList){
         System.out.println(s);
      */
      /*
      遍历三：
       */
        Iterator<student> it = stuList.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        //Lamada迭代遍历 it.forEachRemaining(s-> System.out.println("Lamada迭代 "+s));
    }

    public static void main(String[] args) throws IOException {
        createclass();
        Add();
        Remove();
        Output();
        Comparator up = new Myupsort();
        Collections.sort(stuList, up);
        System.out.println("After ascending sort: ");
        Output();
        Comparator down = new Mydownsort();
        Collections.sort(stuList, down);
        System.out.println("After descending sort: ");
        Output();


        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(new FileOutputStream("Myclass.txt"));
            for (student k : stuList)
                oo.writeObject(k);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Read
        File file = new File("Myclass.txt");
        File Nfile = new File("Myclass-vice.txt");
         BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(file)
        );

        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(Nfile)
        );
        int c;
        while ((c = bis.read()) != -1) {
            bos.write(c);
            bos.flush(); //刷新缓冲
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new FileInputStream("Myclass-vice.txt")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (student student1 : stuList) {
            try {
                student1 = (student) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("name: " + student1.getName() + " sex: " + student1.getSex()+" age: "+student1.getAge()+" id: "+student1.getID()+" class: "+student1.getClasses());
        }
        }
}