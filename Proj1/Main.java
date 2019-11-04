

//To compile the file, open your terminal and type
//$javac filename.java
//To run the generated class file, use
//$java filename

import java.lang.Math; 

class Main
{
  public static void main(String[] args)
  {
    final int N=10;
    int MOD=1%5;
    System.out.println("MOD="+MOD);
    double [] array=new double[N];
    for(int i=0; i<N; ++i) array[i]=Math.sin(Math.PI/(double)180*i);
    Class1 o;
    double a=Double.NEGATIVE_INFINITY;
    double b=Double.POSITIVE_INFINITY;
    double c=Double.NaN;
    System.out.println("a="+a+" b="+b+" c="+c);
    System.out.println("Hello World!");
  }
}
