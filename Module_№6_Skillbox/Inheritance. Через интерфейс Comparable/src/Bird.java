
public abstract class Bird implements Comparable<Bird> {
    int InitialWeight;
    int WeightPlus;
    int weight = InitialWeight+(int)(WeightPlus*Math.random());
    String name;
    String voice;
    public Bird(){}
    public Bird(String namy, int InitialWeight, int WeightPlus)
    {
        this.name = namy;
        this.InitialWeight = InitialWeight;
        this.WeightPlus = WeightPlus;
        this.weight = InitialWeight+(int)(WeightPlus*Math.random());
        System.out.println(this.name+" "+this.weight);
    }
    public void walk()
    {

    }
    public void run()
    {

    }
    public void eat()
    {
        //ingest();
        //digest();
    }
    public abstract void voice();
    public int compareTo(Bird bird){
        return this.weight-bird.weight;
    }
}
