public class Leval1
{public static int[][] Data;

//shape number , x position , y position , z position , elevation , amzimth
//0 cube
//1 piramid
//2 rectangular prizam

public Leval1()
{Data=new int[13][6];
Data[0]=new int[]{0,-180,0,0,0,0};
Data[1]=new int[]{0,-180,180,0,0,0};
Data[2]=new int[]{0,-180,360,0,0,0};
Data[3]=new int[]{0,180,0,0,0,0};
Data[4]=new int[]{0,180,180,0,0,0};
Data[5]=new int[]{0,180,360,0,0,0};

Data[6]=new int[]{0,-180,0,360,0,0};
Data[7]=new int[]{0,-180,180,360,0,0};
Data[8]=new int[]{0,-180,360,360,0,0};
Data[9]=new int[]{0,180,0,360,0,0};
Data[10]=new int[]{0,180,180,360,0,0};
Data[11]=new int[]{0,180,360,360,0,0};

Data[12]=new int[]{2,0,540,180,0,0};}
}