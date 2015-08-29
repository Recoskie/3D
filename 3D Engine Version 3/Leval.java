public class Leval
{
//image points
public static int[][] IPoint;
//image files
public static String[] I;

//IPoints int
//x position , y position , z position , elevation , amzimth
//I Files
//String image url

public Leval()
{
IPoint=new int[2][6];

IPoint[1]=new int[]{0,0,0,0,0};
IPoint[0]=new int[]{65,0,65,90,0};

I=new String[2];
I[0]="test.png";
I[1]="test.png";

}
}