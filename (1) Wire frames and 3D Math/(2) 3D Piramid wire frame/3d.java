import javax.imageio.*;
import java.awt.image.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class S extends JPanel implements MouseMotionListener,MouseListener
{int width=700,height=700,mx,my;
int azimuth=0,elevation=0;Point3D[] vertices;Edge[] edges;Point[] points;

JLabel XX,YY;

float near=3;
//distance from eye to near plane

float nearToObj=(float)1.5;
//distance from near plane to center of object

class Point3D{public int x,y,z;public Point3D(int X,int Y,int Z){x=X;y=Y;z=Z;}}
class Edge{public int a,b;public Edge(int A,int B){a=A;b=B;}}

public S(){vertices=new Point3D[5];
vertices[0]=new Point3D(-90,0,-90);
vertices[1]=new Point3D(90,0,-90);
vertices[2]=new Point3D(-90,0,90);
vertices[3]=new Point3D(90,0,90);
vertices[4]=new Point3D(0,180,0);
edges=new Edge[8];
edges[0]=new Edge(0,1);
edges[1]=new Edge(0,2);
edges[2]=new Edge(1,3);
edges[3]=new Edge(2,3);
edges[4]=new Edge(0,4);
edges[5]=new Edge(1,4);
edges[6]=new Edge(2,4);
edges[7]=new Edge(3,4);

XX=new JLabel("Near "+near+"");
YY=new JLabel("Near To Obj "+nearToObj+"");

JSlider RX=new JSlider(JSlider.HORIZONTAL,0,100,1);
JSlider RY=new JSlider(JSlider.HORIZONTAL,0,100,1);

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
JFrame f=new JFrame("3D Wire Frames");f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

public void calculate()
{//compute coefficients for the projection
double theta=Math.PI*azimuth/180.0;
double phi=Math.PI*elevation/180.0;
float cosT=(float)Math.cos(theta),sinT=(float)Math.sin(theta);
float cosP=(float)Math.cos(phi),sinP=(float)Math.sin(phi);
float cosTcosP=cosT*cosP,cosTsinP=cosT*sinP,sinTcosP=sinT*cosP,sinTsinP=sinT*sinP;
//project vertices onto the 2D viewport
int j;



points=new Point[vertices.length];
for (j=0;j<vertices.length;++j)
{int x0=vertices[j].x;
int y0=vertices[j].y;
int z0=vertices[j].z;
//compute an orthographic projection
float x1=cosT*x0+sinT*z0;
float y1=-sinTsinP*x0+cosP*y0+cosTsinP*z0;

//now adjust things to get a perspective projection
float z1=cosTcosP*z0-sinTcosP*x0-sinP*y0;

//adjust the value of Z perspective
z1=(float)((z1/90)*1);

//calculate Z over front back perspective of near and near to object as the distance your looking at it from
x1*=near/(z1+near+nearToObj);
y1*=near/(z1+near+nearToObj);

//the 0.5 is to round off when converting to int
//and set up a piont that the edge array draws lines to
points[j]=new Point((int)(width/2+x1+0.5),(int)(height/2-y1+0.5));}}

public void paint(Graphics g)
{super.paint(g);

calculate();

g.setColor(Color.white);for(int j=0;j<edges.length;++j)
{g.drawLine(points[edges[j].a].x,points[edges[j].a].y,points[edges[j].b].x,points[edges[j].b].y);}}

public void Draw(){XX.setText("Near "+near+"");
YY.setText("Near To Obj  "+nearToObj+"");repaint();}}