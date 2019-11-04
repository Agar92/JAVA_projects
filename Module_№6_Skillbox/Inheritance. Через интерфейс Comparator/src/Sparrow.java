
public class Sparrow extends Birds{
    //масса в граммах
    static int InitialWeight =50;
    static int WeightPlus = 100;
    int weight = InitialWeight+(int)(WeightPlus*Math.random());
    static String voice = "Chik-chirik";
    static String name = "Sparrow";
    public Sparrow(){
        super(name, InitialWeight, WeightPlus);
    }
    public void voice() {
        System.out.println(this.voice);
    }
}
