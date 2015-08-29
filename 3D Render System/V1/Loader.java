import javax.imageio.*;
import java.awt.image.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class S extends JPanel implements MouseMotionListener,MouseListener
{//this 3D engine whas created by damian recoskie off the idea of othorgraphics porjection and perspective

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

//interlize varibles used by 3D shape
int[][] v,flat,colors;

//create point array
Point[] points;

//Load 3D shape data
Cube c=new Cube();
Piramid p=new Piramid();
RectangelerPrizim rp=new RectangelerPrizim();

//width hight of window and mouse position
int width=700,height=700,mx,my;

//point to rotate all 3D shapes on
int azimuth=0,elevation=0;

//the order to draw the pionts in
int[] order;

//distance from eye to near plane
float near=10;

//distance from near plane to center of object
float nearToObj=(float)0;

//set up listeners and background color
public S(){this.setBackground(Color.black);
addMouseListener(this);addMouseMotionListener(this);}

//where the app starts or video game 3D render or engine or what ever you want to call it
public static void main(String[]args)
{new S().window();}

//create a drawable window on os
public void window(){JFrame.setDefaultLookAndFeelDecorated(true);
JFrame f=new JFrame("test");f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(10+width,30+height);f.setLocationRelativeTo(null);
f.setResizable(false);f.add(new S());f.setVisible(true);}

//handel the mouse events or exetera
public void mouseEntered(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mouseClicked(MouseEvent e){}

public void mousePressed(MouseEvent e){mx=e.getX();my=e.getY();e.consume();}

public void mouseReleased(MouseEvent e){}
public void mouseMoved(MouseEvent e){}

public void mouseDragged(MouseEvent e)
{int new_mx=e.getX();int new_my=e.getY();
azimuth-=new_mx-mx;elevation+=new_my-my;
mx=new_mx;my=new_my;repaint();e.consume();}


//where the calculations happen and where the graphics draw
public void paint(Graphics g)
{super.paint(g);

//loop though all 3D shapes being rendered
for(int obj=0;obj<Data.length;++obj)
{
//get 3d Shape Data
if(Data[obj][0]==0)
{v=c.v;flat=c.flat;colors=c.colors;}
if(Data[obj][0]==1)
{v=p.v;flat=p.flat;colors=p.colors;}
if(Data[obj][0]==2)
{v=rp.v;flat=rp.flat;colors=rp.colors;}

//set the order to draw the sides
order=SetOrder(flat,v,azimuth+Data[obj][4],elevation+Data[obj][5]);

int j;

points=new Point[v.length];

//loop though the pionts
for(j=0;j<v.length;++j)
{
//calculate angels
double theta=Math.PI*(azimuth+Data[obj][4])/180.0;
double phi=Math.PI*(elevation+Data[obj][5])/180.0;
float cosT=(float)Math.cos(theta),sinT=(float)Math.sin(theta);
float cosP=(float)Math.cos(phi),sinP=(float)Math.sin(phi);
float cosTcosP=cosT*cosP,cosTsinP=cosT*sinP,sinTcosP=sinT*cosP,sinTsinP=sinT*sinP;

int x0=v[j][0];
int y0=v[j][1];
int z0=v[j][2];

//set player position for testing
P.Move(200,50,200);
//get players position
int[] p0=P.GetPos();
int px=p0[0],py=p0[1],pz=p0[2];

//calculate 3D shape
//rotate pionts
float x1=cosT*x0+sinT*z0;
float y1=-sinTsinP*x0+cosP*y0+cosTsinP*z0;
float z1=cosTcosP*(z0+pz)-sinTcosP*(x0+px)-sinP*(y0+py);

//calculate perspective
z1=(float)((z1/90));
x1*=near/(z1+near+nearToObj);
y1*=near/(z1+near+nearToObj);


//recalculate for positioning
theta=Math.PI*azimuth/180.0;
phi=Math.PI*elevation/180.0;
cosT=(float)Math.cos(theta);sinT=(float)Math.sin(theta);
cosP=(float)Math.cos(phi);sinP=(float)Math.sin(phi);
cosTcosP=cosT*cosP;cosTsinP=cosT*sinP;sinTcosP=sinT*cosP;sinTsinP=sinT*sinP;

//calculate shape rotation around other shapes
//x
x0=Data[obj][1]+px;
//y
y0=Data[obj][2]+py;
//z
z0=Data[obj][3]+pz;
//x over terning distance or size of cercal z for when looking at the side

float x2=z0*cosT+sinT*x0;
//y rotate
float y2=(sinTsinP*z0+cosP*y0+cosTsinP*x0);
//calculate perspective
float z2=-cosTcosP*Data[obj][3]-sinTcosP*Data[obj][1];
float z3=cosTsinP*Data[obj][3]-sinP*Data[obj][2]-sinTsinP*Data[obj][1];
//x2+=z2;
//y2*=near/((z3/180)+near+nearToObj);


//this piont is now calculated now store it so can draw to all pionts with color
points[j]=new Point((int)(x2+x1+0.5),(int)(y2-y1+0.5));}

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
}
//end of graphics

//calculate the difrent sides of what draws first made by damian recoskie
//the more back is always drawen first then the sides colser to the front
public int[] SetOrder(int f[][],int v[][],int azimuth,int elevation)
{//calculate angles as constances used for perspectives over difrent values of each side
double theta=Math.PI*azimuth/180.0,phi=Math.PI*elevation/180.0;
float cosT=(float)Math.cos(theta),sinT=(float)Math.sin(theta);
float cosP=(float)Math.cos(phi),sinP=(float)Math.sin(phi);
float cosTcosP=cosT*cosP,sinTcosP=sinT*cosP;
//record in and out distaces of what you will see the most perspective
float[] temp=new float[v.length];
for(int j=0;j<v.length;++j)
{int x=v[j][0];
int y=v[j][1];
float z=v[j][2];
z=cosTcosP*z-sinTcosP*x-sinP*y;z=-z;
temp[j]=z;}
//add the values of in and out over the pionts of the flat serfaces
//to get full values of exactly where thay sit in the back
//the size of the amount of serfaces
float[] temp2=new float[flat.length];
//added value of in and out
float v2=0;
for(int j=0;j<flat.length;++j)
{//reset v after calculation of serface
v2=0;
//add each piont of perspective of serface
for(int j2=0;j2<flat[j].length;++j2)
{v2+=temp[flat[j][j2]];}
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
{if(temp[j]!=900&temp[j]<b||R){R=false;b=temp[j];p=j;}}
temp3[j2]=p;
//set value null so it is not reused
temp[p]=900;
//set R for reset to a value that whas not used
R=true;}
//reset uneaded data
temp=null;
temp2=null;
//set order
return(temp3);}

}