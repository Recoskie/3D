import javax.imageio.*;
import java.awt.image.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class S extends JPanel implements MouseMotionListener,MouseListener,KeyListener
{//this 3D engine whas created by damian recoskie off the idea of othorgraphics porjection and perspective

//interlize arrays

public int[][] v=new int[0][0];
public int[][] flat=new int[0][0];
public int[][] colors=new int[0][0];

//the player

class Player
{public int x=0,y=0,z=0;

public void Move(int x1,int y1,int z1){x=x1;y=y1;z=z1;}

public int[] GetPos()
{int[] o=new int[3];
o[0]=x;o[1]=y;o[2]=z;
return(o);}
}

//load in players position
Player P=new Player();

//file to load
Leval1 D=new Leval1();
int[][] Data=D.Data;

//create point array
Point[] points;

//Load 3D shape data
Cube c=new Cube();
Piramid p=new Piramid();
RectangelerPrizim rp=new RectangelerPrizim();
ground bg=new ground();

//width hight of window and mouse position
int width=600,height=600,mx,my;

//point to rotate all 3D shapes on
int azimuth=0,elevation=0;

//the order to draw the pionts in
int[] order;

//distance from eye to near plane
float near=10;

//distance from near plane to center of object
float nearToObj=(float)0;

//****************************************************************Rotate 3D Vertices Array

public int[][] Rotate(int[][] Data,int elevation,int azmith,boolean b)
{
//players position
int[] p0=P.GetPos();
int px=p0[0],py=p0[1],pz=p0[2];

//what the lengths are
float x1,y1,z1;
double theta=Math.PI*(azmith)/180.0;
double phi=Math.PI*(elevation)/180.0;
float cosT=(float)Math.cos(theta),sinT=(float)Math.sin(theta);
float cosP=(float)Math.cos(phi),sinP=(float)Math.sin(phi);
float cosTcosP=cosT*cosP,cosTsinP=cosT*sinP,sinTcosP=sinT*cosP,sinTsinP=sinT*sinP;
//loop though 1 vertice at a time
for(int i=0;i<Data.length;i++)
{//get position of vertices of back or front distance or left to right
if(b){int x=Data[i][0]+px,y=Data[i][1]+py,z=Data[i][2]+pz;
//put the lengths to the front and sides and top
x1=cosT*(x)+sinT*(z);
y1=-sinTsinP*(x)+cosP*(y)+cosTsinP*(z);
//perspecitve is over the opaste of shunk lengths of what is coming closer in length to iey
//shink persective over fraction from close to screan to iey
z1=Perspective(x,y,z,elevation,azmith,false)/90;
x1/=near/(z1+near+nearToObj);
y1/=near/(z1+near+nearToObj);}

else{int x=Data[i][0],y=Data[i][1],z=Data[i][2];
//put the lengths to the front and sides and top
x1=cosT*(x)+sinT*(z);
y1=-sinTsinP*(x)+cosP*(y)+cosTsinP*(z);
//perspecitve is over the opaste of shunk lengths of what is coming closer in length to iey
//shink persective over fraction from close to screan to iey
z1=Perspective(x,y,z,elevation,azmith,false);}
//set vertices
Data[i][0]=(int)(x1+0.5);Data[i][1]=(int)(y1+0.5);Data[i][2]=(int)(z1+0.5);}

//output length changed Data or smaler numbers or biger over side you look at or half and half
return(Data);}

//******************************************************************************Add Perspective

public float Perspective(int x,int y,int z,int elevation,int azmith,boolean b)
{float z1=0;
//what the lengths are
double theta=Math.PI*(azmith)/180.0;double phi=Math.PI*(elevation)/180.0;
float cosT=(float)Math.cos(theta),sinT=(float)Math.sin(theta);
float cosP=(float)Math.cos(phi),sinP=(float)Math.sin(phi);
float cosTcosP=cosT*cosP,sinTcosP=sinT*cosP;
if(!b){
//more visable sides
z1=cosTcosP*(z)-sinTcosP*(x)-sinP*(y);}
else{
//more visable sides
z1=cosTcosP*(z)-sinTcosP*(x)-sinP*(y);}

//output
return(z1);}

//*********************************************************set up listeners and background color

public S(){this.setBackground(Color.black);
this.setFocusable(true);
addMouseListener(this);addMouseMotionListener(this);
this.addKeyListener(this);}

//*********where the app starts or video game 3D render or engine or what ever you want to call it

public static void main(String[]args)
{new S().window();}

//******************************************************************create a drawable window on os

public void window(){P.Move(0,0,0);LoadLeval();
JFrame.setDefaultLookAndFeelDecorated(true);
JFrame f=new JFrame("test");f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(10+width,30+height);f.setLocationRelativeTo(null);
f.setResizable(false);f.add(new S());f.setVisible(true);}

//************************************create an array of pionts and set up new flat cerfance array

public void LoadLeval()
{

//reset 3d loader data
int[][] v2=new int[0][0],f2=new int[0][0],c2=new int[0][0];
v=new int[0][0];
flat=new int[0][0];
colors=new int[0][0];

//loop though all 3D shapes being rendered

//set up data

for(int obj=0;obj<Data.length;++obj)
{
//rotation
int az=Data[obj][4],el=Data[obj][5];

//reload data stop error
Cube c=new Cube();
Piramid p=new Piramid();
RectangelerPrizim rp=new RectangelerPrizim();
ground bg=new ground();

//the 3d shape data

if(Data[obj][0]==0){v2=c.v;f2=c.flat;c2=c.colors;}
if(Data[obj][0]==1){v2=p.v;f2=p.flat;c2=p.colors;}
if(Data[obj][0]==2){v2=rp.v;f2=rp.flat;c2=rp.colors;}
if(Data[obj][0]==3){v2=bg.v;f2=bg.flat;c2=bg.colors;}

//load in data

//add value to flat cerfaces of size of vertices
int[][] temp=addv(f2,v.length);

//add it togather
flat=add(flat,temp);

//set position of vertices
//set piont rotate vertices
//where to place vertices on screan
v2=Rotate(v2,el,az,false);
v=addvert(v,v2,Data[obj][1],Data[obj][2],Data[obj][3]);

//add colors
colors=add(colors,c2);

}
}

//***************************************************************handel the mouse events or exetera

public void mouseEntered(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mouseClicked(MouseEvent e){}

public void mousePressed(MouseEvent e){mx=e.getX();my=e.getY();e.consume();}

public void mouseReleased(MouseEvent e){}
public void mouseMoved(MouseEvent e){}

public void mouseDragged(MouseEvent e)
{int new_mx=e.getX();int new_my=e.getY();
azimuth+=new_mx-mx;elevation+=new_my-my;
mx=new_mx;my=new_my;

if(elevation>90){elevation=90;}
if(elevation<-90){elevation=-90;}

if(azimuth>360){azimuth=0;}
if(azimuth<-360){azimuth=0;}

System.out.println("view piont");
System.out.println("X="+azimuth+",Y="+elevation+"");

repaint();
e.consume();}

//key event handeling
public void keyTyped(KeyEvent e){}

//***********************************************************************************move player

public void keyPressed(KeyEvent ee){int e=ee.getKeyCode();

/*double theta=Math.PI*(azimuth)/180.0;
double phi=Math.PI*(elevation)/180.0;
float cosT=(float)Math.cos(theta),sinT=(float)Math.sin(theta);
float cosP=(float)Math.cos(phi),sinP=(float)Math.sin(phi);

int x=P.GetPos()[0],y=P.GetPos()[1],z=P.GetPos()[2];

//in
if(e==38){P.Move((int)(((x+20)*sinT)+(x*cosT)),(y),(int)(((z-20)*cosT)+(z*sinT)));}

//out
if(e==40){P.Move((int)((x-20)*sinT),(y),(int)((z+20)*cosT));}

//left
if(e==37){P.Move((int)((x+20)*cosT),(y),(int)((z-20)*sinT));}

//right
if(e==39){P.Move((int)((x-20)*cosT),(y),(int)((z-20)*sinT));*/

int x=P.GetPos()[0],y=P.GetPos()[1],z=P.GetPos()[2];

//in
if(e==38){P.Move((x),y,(z-20));}

//out
if(e==40){P.Move((x),(y),(z+20));}

//left
if(e==37){P.Move((x+20),(y),(z));}

//right
if(e==39){P.Move((x-20),(y),(z));

}

repaint();}

public void keyReleased(KeyEvent e){}


//***********************************************************************where the graphics draw

public void paint(Graphics g)
{super.paint(g);

LoadLeval();

//set the order to draw the sides
order=SetOrder(elevation,azimuth);

/*//show the order
System.out.println("wich points draw in specific order");
for(int j2=0;j2<order.length;j2++)
{
System.out.print(order[j2]+",");
}*/

int j;

points=new Point[v.length];

//calculate 3D Rotate
int[][] Temp=Rotate(v,elevation,azimuth,true);

//loop though the pionts
for(j=0;j<Temp.length;++j)
{points[j]=new Point((int)(Temp[j][0]+(width/2)+0.5),(int)(Temp[j][1]+(height/2)+0.5));}

//fill in the serfaces

for(j=0;j<order.length;++j)
{int f=order[j];
Polygon p=new Polygon();
for(int j2=0;j2<flat[f].length;++j2)
{Point po=points[flat[f][j2]];
p.addPoint(po.x,po.y);}
g.setColor(new Color(colors[f][0],colors[f][1],colors[f][2]));
g.fillPolygon(p);}

}

//********************************************************************array operation codes

//****************************************************add a number to all integers in array

public int[][] addv(int[][] i,int a)
{int[][] o=new int[i.length][4];
for(int j2=0;j2<i.length;j2++)
{//interlize
o[j2]=i[j2];
//add value to elements
for(int j=0;j<(i[j2]).length;j++)
{o[j2][j]=i[j2][j]+a;}}return(o);}

//*********************************************************************add 2 arrays togater

public int[][] add(int[][] i,int[][] i2)
{//set array size to the size of the 2 arrays
int[][] o=new int[(i.length+i2.length)][4];
//add first array
for(int j=0;j<i.length;j++)
{o[j]=i[j];}
//add secont array
for(int j=i.length;j<(i.length+i2.length);j++)
{o[j]=i2[j-i.length];}
//output
return(o);}

//**************************************************************add vertices on set pionts

public int[][] addvert(int[][] i,int[][] i2,int x,int y,int z)
{//set array size to the size of the 2 arrays
int[][] o=new int[(i.length+i2.length)][4];
//add first array
for(int j=0;j<i.length;j++)
{o[j]=i[j];}

//*************************************************add secont array with vertice set pionts

for(int j=i.length;j<(i.length+i2.length);j++)
{o[j]=i2[j-i.length];
o[j][0]=o[j][0]+x;o[j][1]=o[j][1]+y;o[j][2]=o[j][2]+z;}

//output
return(o);}


//******************calculate the difrent sides of what draws first made by damian recoskie
//******************the more back is always drawen first then the sides closer to the front

//*******************WARNING THIS FUNCTION SEAMS TO HAVE SMALL PROBLEMBS********************

public int[] SetOrder(int azimuth,int elevation)
{
//calculate angles as constances used for perspectives over difrent values of each side
double theta=Math.PI*azimuth/180.0,phi=Math.PI*elevation/180.0;
float cosT=(float)Math.cos(theta),sinT=(float)Math.sin(theta);
float cosP=(float)Math.cos(phi),sinP=(float)Math.sin(phi);
float cosTcosP=cosT*cosP,sinTcosP=sinT*cosP;

//record in and out distaces of what you will see the most perspective
float[] temp=new float[v.length];

//get players position
int[] p0=P.GetPos();
int px=p0[0],py=p0[1],pz=p0[2];
for(int j=0;j<v.length;++j)
{temp[j]=Perspective(v[j][0]+px,v[j][1]+py,v[j][2]+pz,azimuth,elevation,true);}

//add the values of in and out over the pionts of the flat serfaces
//to get full values of exactly where thay sit in the back
//the size of the amount of serfaces
float[] temp2=new float[flat.length];

//added value of in and out
float v2=0,v3=0;

for(int j=0;j<flat.length;j++)
{
//reset v after calculation of serface
v2=0;v3=0;

//add each piont of perspective of serface

for(int j2=0;j2<flat[j].length;j2++)
{if(v2<temp[flat[j][j2]]){v2=(temp[flat[j][j2]]);}}

for(int j2=0;j2<flat[j].length;j2++)
{v2+=(temp[flat[j][j2]]);}

//record value in order over each side
temp2[j]=(Float)v2;}
temp=temp2;temp2=null;
int[] temp3=new int[temp.length];

//set the order of distance across array
//the number across of smalest value
float b=0;

//the element the smalest amount whas at
int p=0;

//this boolean make shere that b is set to at least a value in the array
Boolean R=true;

//the looping
for(int j2=0;j2<temp.length;++j2)
{for(int j=0;j<temp.length;++j)
{if(temp[j]!=99999999&temp[j]<b||R){R=false;b=temp[j];p=j;}}
temp3[j2]=p;

//set value null so it is not reused
temp[p]=99999999;

//set R for reset to a value that whas not used
R=true;}

//reset uneaded data
temp=null;
temp2=null;

//set order
return(temp3);}

}