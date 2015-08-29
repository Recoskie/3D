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
IPoint=new int[5][5];

//roof of the house

IPoint[0]=new int[]{205,0,0,45,90};
IPoint[1]=new int[]{-205,0,0,-45,90};
IPoint[2]=new int[]{0,85,0,0,90};

//the sides

IPoint[3]=new int[]{0,0,-90,0,0};
IPoint[4]=new int[]{0,0,90,0,0};

I=new String[5];
I[0]="S.png";
I[1]="S.png";
I[2]="S.png";

I[3]="Side1.png";
I[4]="Side1.png";

}
}