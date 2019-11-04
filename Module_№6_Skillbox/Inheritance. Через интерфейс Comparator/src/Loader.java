
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Loader   {
    public static void main(String[] args){
            BirdComparator bcmr = new BirdComparator();
            Set <Bird> set = new TreeSet(bcmr);
            Strauss bird1 = new Strauss();
            set.add(bird1);
            Strauss bird2 = new Strauss();
            set.add(bird2);
            Sparrow bird3 = new Sparrow();
            set.add(bird3);
            Duck bird4 = new Duck();
            set.add(bird4);
            Hen bird5 = new Hen();
            set.add(bird5);
            Iterator<Bird> iter = set.iterator();
            while (iter.hasNext()) {
                System.out.println(iter.next().weight);
            }
            for(Bird item : set){
            System.out.println(item.name+" "+item.weight);
            }
    }
}
