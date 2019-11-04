import java.util.Comparator;

class BirdComparator implements Comparator<Bird> {

    public int compare(Bird a, Bird b){

        return (a.weight-b.weight);
    }
}
