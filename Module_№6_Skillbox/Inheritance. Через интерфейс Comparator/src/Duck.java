
public class Duck extends Birds {
    //����� � �������
    static int InitialWeight = 300;
    static int WeightPlus = 500;
    int weight = InitialWeight + (int) (WeightPlus * Math.random());
    static String voice = "Krya-krya";
    static String name = "Duck";
    public Duck(){
        super(name, InitialWeight, WeightPlus);
    }
            public void voice() {
                System.out.println(this.voice);;
            }

}
