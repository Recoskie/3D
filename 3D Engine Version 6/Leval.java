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
IPoint=new int[6][5];

//sides

IPoint[0]=new int[]{0,0,-75,0,0};
IPoint[1]=new int[]{75,0,0,90,0};
IPoint[2]=new int[]{-75,0,0,90,0};
IPoint[3]=new int[]{0,0,75,0,0};

//top

IPoint[4]=new int[]{0,-52,0,0,90};

//botom

IPoint[5]=new int[]{0,52,0,0,90};

I=new String[6];
I[0]="test.png";
I[1]="test2.png";
I[2]="test3.png";
I[3]="test4.png";

I[4]="test5.png";
I[5]="test6.png";

}
}