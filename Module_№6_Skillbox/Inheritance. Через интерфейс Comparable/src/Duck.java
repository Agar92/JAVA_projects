
public class Duck extends Birds {
    //масса в граммах
    static int InitialWeight = 300;
    static int WeightPlus = 500;
    int weight = InitialWeight + (int) (WeightPlus * Math.random());
    String voice = "Krya-krya";
    static String name = "Duck";
    public Duck(){
        super(name, InitialWeight, WeightPlus);
        voice();
    }
            public void voice() {
                System.out.println(this.voice);;
            }

}
