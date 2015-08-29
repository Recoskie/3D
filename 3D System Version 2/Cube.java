public class Cube
{
public int[][] v=new int[8][3];
public int[][] flat=new int[6][4];
public int[][] colors=new int[6][3];

public Cube()
{
//the coners that rotate in 3D or just change over fractions
//also refered to as a point
v[0]=new int[]{-90,-90,-90}; //left top back corner
v[1]=new int[]{-90,-90,90}; //left top front corner
v[2]=new int[]{-90,90,-90}; //left botom back corner
v[3]=new int[]{-90,90,90}; //left botom front corner
v[4]=new int[]{90,-90,-90}; //right top back corner
v[5]=new int[]{90,-90,90}; //right top front corner
v[6]=new int[]{90,90,-90}; //right botom back corner
v[7]=new int[]{90,90,90}; //right botom front corner

//the coners that make up the falt serfaces
//draw the pionts on a paper and connect them to the front sides and back over the corners
//cube has 6 sides betwean it's 4 corners
flat[0]=new int[]{7,3,1,5}; //back
flat[1]=new int[]{6,2,0,4}; //front
flat[2]=new int[]{7,6,4,5}; //right side
flat[3]=new int[]{7,3,2,6}; //botom side
flat[4]=new int[]{1,0,2,3}; //left side
flat[5]=new int[]{1,0,4,5}; //top side

//flat serfaces colors
colors[0]=new int[]{255,255,255}; //back color
colors[1]=new int[]{255,0,0}; //front color
colors[2]=new int[]{0,255,0}; //right side color
colors[3]=new int[]{0,0,255}; //botom side color
colors[4]=new int[]{255,0,255}; //left side color
colors[5]=new int[]{255,255,0}; //top side color
}

}