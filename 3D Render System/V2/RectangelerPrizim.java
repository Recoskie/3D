public class RectangelerPrizim
{
//interlize
int[][] v=new int[6][3];
int[][] flat=new int[5][3];
int[][] colors=new int[5][3];

public RectangelerPrizim()
{
//set data
//the coners that rotate in 3D or just change over fractions
//also refered to as a point

//back triangel
v[0]=new int[]{-90,-90,90}; //left botom corner
v[1]=new int[]{90,-90,90}; //right botom corner
v[2]=new int[]{0,90,90}; //top center corner

//front trangel
v[3]=new int[]{-90,-90,-90}; //left botom corner
v[4]=new int[]{90,-90,-90}; //right botom corner
v[5]=new int[]{0,90,-90}; //top center corner

//the coners that make up the falt serfaces

flat[0]=new int[]{0,1,2}; //the back tranagle
flat[1]=new int[]{3,4,5}; //the front tranangel
flat[2]=new int[]{3,0,2,5}; //left side
flat[3]=new int[]{4,1,2,5}; //right side
flat[4]=new int[]{4,1,0,3}; //botom side

//flat serfaces colors
colors[0]=new int[]{255,255,255}; //the back tranagle color
colors[1]=new int[]{255,0,0}; //the front tranangel color
colors[2]=new int[]{0,255,0}; //left side color
colors[3]=new int[]{0,0,255}; //right side color
colors[4]=new int[]{255,0,255}; //botom side color
}

}