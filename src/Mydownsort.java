import java.util.Comparator;

public class Mydownsort implements Comparator {
    public int compare(Object o1,Object o2){
        student p1 = (student) o1;
        student p2 = (student) o2;
        if(p1.id<p2.id)
            return 1;
        else
            return -1;
    }
}
