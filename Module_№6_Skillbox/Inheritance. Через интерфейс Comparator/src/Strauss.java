
public class Strauss extends Birds{
    //����� � �������
    static int InitialWeight = 1000;
    static int WeightPlus =10000;
    int weight = InitialWeight+(int)(WeightPlus*Math.random());
    static String name = "Strauss";
    static String voice = "Stra-stra";
    public Strauss(){
        super(name, InitialWeight, WeightPlus);
    }
    public void voice() {
        System.out.println(this.voice);;
    }
}
