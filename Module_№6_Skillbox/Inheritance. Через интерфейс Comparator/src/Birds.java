
public class Birds extends Bird {
    int InitialWeight;
    int WeightPlus;
    int weight = InitialWeight+(int)(WeightPlus*Math.random());
    String name;
    static String voice;
    public Birds(String name, int InitialWeight, int WeightPlus)
    {
        super(name,InitialWeight,WeightPlus);
        voice();
    }
    public void voice(){
        System.out.println(voice);
    }
    }
