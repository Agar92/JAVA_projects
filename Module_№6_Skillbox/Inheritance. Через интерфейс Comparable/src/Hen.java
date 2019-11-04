
public class Hen extends Birds {
    static int InitialWeight=500;
    static int WeightPlus=1500;
    int weight = InitialWeight+(int)(WeightPlus*Math.random());
    String voice = "Kur-kur";
    static String name = "Hen";
    public void dabGrains()
    {

    }
    public void eat()
    {
        dabGrains();
        //super.eat();
    }
    public Hen(){
        super(name, InitialWeight, WeightPlus);
        voice();
    }
    public void voice() {
        System.out.println(this.voice);;
    }
}
