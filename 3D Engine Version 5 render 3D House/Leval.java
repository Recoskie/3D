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
IPoint=new int[13][5];

//roof of the house

IPoint[0]=new int[]{205,0,0,-45,90};
IPoint[1]=new int[]{-205,0,0,45,90};
IPoint[2]=new int[]{0,-85,0,0,90};

//the sides

IPoint[3]=new int[]{0,42,-90,0,0};
IPoint[4]=new int[]{0,42,90,0,0};

//wood legs

//right
IPoint[5]=new int[]{299,127,0,90,0};

//left
IPoint[6]=new int[]{-299,127,0,90,0};

//right
IPoint[7]=new int[]{274,127,0,90,0};

//left
IPoint[8]=new int[]{-274,127,0,90,0};

//top and botoms of legs

//top
IPoint[9]=new int[]{287,84,0,0,90};

//bottom
IPoint[10]=new int[]{-287,170,0,0,90};

//top
IPoint[11]=new int[]{287,84,0,0,90};

//bottom
IPoint[12]=new int[]{-287,170,0,0,90};

I=new String[13];
I[0]="S.png";
I[1]="S.png";
I[2]="S.png";

I[3]="Side1.png";
I[4]="Side1.png";

I[5]="wood.png";
I[6]="wood.png";
I[7]="wood.png";
I[8]="wood.png";

I[9]="wood2.png";
I[10]="wood2.png";
I[11]="wood2.png";
I[12]="wood2.png";

}
}