public class Leval1
{public static int[][] Data;

//shape number , x position , y position , z position , elevation , amzimth
//0 cube
//1 piramid
//2 rectangular prizam
//3 ground

public Leval1()
{
Data=new int[4][6];

Data[0]=new int[]{2,180,0,0,0,90};
Data[1]=new int[]{2,-180,0,-180,180,90};
Data[2]=new int[]{2,0,0,180,90,90};
Data[3]=new int[]{2,0,0,-180,270,90};

}
}