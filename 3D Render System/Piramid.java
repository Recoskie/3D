public class Piramid
{
//interlize
int[][] v=new int[5][3];
int[][] flat=new int[5][4];
int[][] colors=new int[5][3];

//load in data
public Piramid()
{//the coners that rotate in 3D or just change over fractions
//also refered to as a point
v[0]=new int[]{-90,0,-90}; //top left corner
v[1]=new int[]{90,0,-90}; //top right corner
v[2]=new int[]{-90,0,90}; //left botom corner
v[3]=new int[]{90,0,90}; //right botom corner
v[4]=new int[]{0,180,0}; //tip of piramid

//the coners that make up the falt serfaces
//imagin looking at the pionts from the top of the piramid
flat[0]=new int[]{3,2,0,1}; //botom sqwear
flat[1]=new int[]{0,1,4}; //top side
flat[2]=new int[]{1,3,4}; //right side
flat[3]=new int[]{2,3,4}; //botom side
flat[4]=new int[]{0,2,4}; //left side

//flat serfaces colors
colors[0]=new int[]{255,255,255}; //botom sqwear color
colors[1]=new int[]{255,0,0}; //top side color
colors[2]=new int[]{0,255,0}; //right side color
colors[3]=new int[]{0,0,255}; //botom side color
colors[4]=new int[]{255,0,255}; //left side color
}
}