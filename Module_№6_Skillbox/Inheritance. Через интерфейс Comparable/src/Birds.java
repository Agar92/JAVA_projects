
public class Birds extends Bird {
    int InitialWeight;
    int WeightPlus;
    int weight = InitialWeight+(int)(WeightPlus*Math.random());
    String name;
    String voice;
    public Birds(String name, int InitialWeight, int WeightPlus)
    {
        super(name,InitialWeight,WeightPlus);
    }
    public void voice(){}
    public int compareTo(Birds bird){
        return this.weight-bird.weight;
    }
}
