public class ground
{
//get data hoders ready
int[][] v=new int[4][3];
int[][] flat=new int[1][4];
int[][] colors=new int[1][3];

//interlize all data
public ground()
{
v[0]=new int[]{-500,0,500};
v[1]=new int[]{500,0,500};
v[2]=new int[]{-500,0,-500};
v[3]=new int[]{500,0,-500};

flat[0]=new int[]{1,3,2,0};

colors[0]=new int[]{128,255,128};
}

}