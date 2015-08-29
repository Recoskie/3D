public class Leval
{
//image points
public static int[][] IPoint;
//image files
public static String[] I;

//IPoints int
//x position , y position , z position , amzimth , elevation 
//I Files
//String image url

public Leval()
{
IPoint=new int[4][6];

IPoint[0]=new int[]{0,0,0,0,0};
IPoint[1]=new int[]{0,0,52,90,0};
IPoint[2]=new int[]{0,0,-52,90,0};
IPoint[3]=new int[]{0,0,-140,0,0};

I=new String[4];
I[0]="test.png";
I[1]="test2.png";
I[2]="test.png";
I[3]="test2.png";

}
}