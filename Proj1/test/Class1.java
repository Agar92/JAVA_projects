

//To compile the file, open your terminal and type
//$javac filename.java
//To run the generated class file, use
//$java filename

package test;

import static java.lang.Math.*;

class Class1
{
  public Class1()
  {
    num=0;
    x=0.0;
    array=new double[N];
    for(int i=0; i<N; ++i) array[i]=Math.sin(Math.PI);
    
  }
  private static int N=100;
  private int num;
  private double x;
  private double array [];
}
