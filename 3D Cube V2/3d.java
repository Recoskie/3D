import javax.imageio.*;
import java.awt.image.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class S extends JPanel implements MouseMotionListener,MouseListener
{int width=700,height=700,mx,my;
int azimuth=0,elevation=0;Point3D[] vertices;
int[][] flat;int[][] colors;Point[] points;
//the order to draw the pionts in
int[] order;

JLabel XX,YY;

float near=10;
//distance from eye to near plane

float nearToObj=(float)0;
//distance from near plane to center of object

class Point3D{public int x,y,z;public Point3D(int X,int Y,int Z){x=X;y=Y;z=Z;}}

public S(){

//the coners that rotate in 3D or just change over fractions
//also refered to as a point
vertices=new Point3D[8];
vertices[0]=new Point3D(-90,-90,-90); //left top back corner
vertices[1]=new Point3D(-90,-90,90); //left top front corner
vertices[2]=new Point3D(-90,90,-90); //left botom back corner
vertices[3]=new Point3D(-90,90,90); //left botom front corner
vertices[4]=new Point3D(90,-90,-90); //right top back corner
vertices[5]=new Point3D(90,-90,90); //right top front corner
vertices[6]=new Point3D(90,90,-90); //right botom back corner
vertices[7]=new Point3D(90,90,90); //right botom front corner

//the coners that make up the falt serfaces
//draw the pionts on a paper and connect them to the front sides and back over the corners
//cube has 6 sides betwean it's 4 corners
flat=new int[6][4];
flat[0]=new int[]{7,3,1,5}; //back
flat[1]=new int[]{6,2,0,4}; //front
flat[2]=new int[]{7,6,4,5}; //right side
flat[3]=new int[]{7,3,2,6}; //botom side
flat[4]=new int[]{1,0,2,3}; //left side
flat[5]=new int[]{1,0,4,5}; //top side

//flat serfaces colors
colors=new int[6][3];
colors[0]=new int[]{255,255,255}; //back color
colors[1]=new int[]{255,0,0}; //front color
colors[2]=new int[]{0,255,0}; //right side color
colors[3]=new int[]{0,0,255}; //botom side color
colors[4]=new int[]{255,0,255}; //left side color
colors[5]=new int[]{255,255,0}; //top side color

XX=new JLabel("Near "+near+"");
YY=new JLabel("Near To Obj "+nearToObj+"");

JSlider RX=new JSlider(JSlider.HORIZONTAL,0,100,(int)(near*2));
JSlider RY=new JSlider(JSlider.HORIZONTAL,0,100,(int)(nearToObj*2));

RX.addChangeListener(new ChangeListener()
{public void stateChanged(ChangeEvent e)
{JSlider source=(JSlider)e.getSource();
near=(float)source.getValue();near=near/2;Draw();}});
RY.addChangeListener(new ChangeListener()
{public void stateChanged(ChangeEvent e)
{JSlider source=(JSlider)e.getSource();
nearToObj=(float)source.getValue();nearToObj=nearToObj/2;Draw();}});
this.add(RX);this.add(XX);this.add(RY);this.add(YY);

this.setBackground(Color.black);

addMouseListener(this);addMouseMotionListener(this);}

public static void main(String[]args)
{new S().window();}

public void window(){JFrame.setDefaultLookAndFeelDecorated(true);
JFrame f=new JFrame("test");f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(10+width,30+height);f.setLocationRelativeTo(null);
f.setResizable(false);f.add(new S());f.setVisible(true);}

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

public void paint(Graphics g)
{super.paint(g);

//set the order to draw the sides
order=SetOrder(flat);

double theta=Math.PI*azimuth/180.0;
double phi=Math.PI*elevation/180.0;
float cosT=(float)Math.cos(theta),sinT=(float)Math.sin(theta);
float cosP=(float)Math.cos(phi),sinP=(float)Math.sin(phi);
float cosTcosP=cosT*cosP,cosTsinP=cosT*sinP,sinTcosP=sinT*cosP,sinTsinP=sinT*sinP;

int j;

points=new Point[vertices.length];

//loop though the pionts

for(j=0;j<vertices.length;++j)
{int x0=vertices[j].x;
int y0=vertices[j].y;
int z0=vertices[j].z;

//compute an orthographic projection
float x1=cosT*x0+sinT*z0;
float y1=-sinTsinP*x0+cosP*y0+cosTsinP*z0;
float z1=cosTcosP*z0-sinTcosP*x0-sinP*y0;
z1=(float)((z1/90)*1);
x1*=near/(z1+near+nearToObj);
y1*=near/(z1+near+nearToObj);

//this piont is now calculated now store it so can draw to all pionts with color
points[j]=new Point((int)(width/2+x1+0.5),(int)(height/2-y1+0.5));}

//draw the sides
for(j=0;j<order.length;++j)
{int f=order[j];
Polygon p=new Polygon();
//set the pionts of the polygon
for(int j2=0;j2<flat[f].length;++j2)
{Point po=points[flat[f][j2]];
//get the value of the piont and add it to polygon piont over the selected points that are the serfaces
p.addPoint(po.x,po.y);
/*first piont of boudary of distace though all pionts of polygon*/}
//the pionts are now set time to get the color for that serface
g.setColor(new Color(colors[f][0],colors[f][1],colors[f][2]));
//draw it
g.fillPolygon(p);
/*loop though next serface until finished*/}

}

public void Draw(){XX.setText("Near "+near+"");
YY.setText("Near To Obj  "+nearToObj+"");repaint();}

//calculate the difrent sides of what draws first made by damian recoskie
//the back is always drawen first then the front
public int[] SetOrder(int f[][])
{//calculate angles as constances used for perspectives over difrent values of each side
double theta=Math.PI*azimuth/180.0,phi=Math.PI*elevation/180.0;
float cosT=(float)Math.cos(theta),sinT=(float)Math.sin(theta);
float cosP=(float)Math.cos(phi),sinP=(float)Math.sin(phi);
float cosTcosP=cosT*cosP,sinTcosP=sinT*cosP;
//record in and out distaces of what you will see the most perspective
float[] temp=new float[vertices.length];
for(int j=0;j<vertices.length;++j)
{int x=vertices[j].x;
int y=vertices[j].y;
float z=vertices[j].z;
z=cosTcosP*z-sinTcosP*x-sinP*y;z=-z;
temp[j]=z;}
//add the values of in and out over the pionts of the flat serfaces
//to get full values of exactly where thay sit in the back
//the size of the amount of serfaces
float[] temp2=new float[flat.length];
//added value of in and out
float v=0;
for(int j=0;j<flat.length;++j)
{//reset v after calculation of serface
v=0;
//add each piont of perspective of serface
for(int j2=0;j2<flat[j].length;++j2)
{v+=temp[flat[j][j2]];}
//record value in order over each side
temp2[j]=(Float)v;}
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