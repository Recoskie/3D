import javax.imageio.*;import java.awt.image.*;import java.awt.event.*;
import java.awt.*;import javax.swing.*;

//this is the slow version

public class Rotation extends JPanel implements MouseMotionListener,MouseListener
{
BufferedImage a;double Rx=Math.toRadians(0),Ry=Math.toRadians(0);
int Px=110,Py=115,Pz=10;int azimuth=0,elevation=0;int mx=0,my=0;

//load Picture
public Rotation() 
{this.setBackground(Color.black);
addMouseListener(this);addMouseMotionListener(this);
try{java.net.URL url=getClass().getResource("test.png");
a=ImageIO.read(url);}catch(java.io.IOException e){}}

//window
public static void main(String[]args)
{JFrame f=new JFrame("test");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(300,230);
f.setLocationRelativeTo(null);
f.setResizable(false);
f.add(new Rotation());
f.setVisible(true);}

//Rotate Alagrithm
public void paint(Graphics g)
{super.paint(g);

//read pictuer one pixel at a time
int w=a.getWidth(),h=a.getHeight();

Rx=Math.PI*(azimuth)/180.0;
Ry=Math.PI*(elevation)/180.0;

double CX=Math.cos(Rx);
double SX=Math.sin(Rx);
double CY=Math.cos(Ry);
double SY=Math.sin(Ry);

for(int y=0;y<=h-1;y++){for(int x=0;x<=w-1;x++)
{int c=a.getRGB(x,y);int T=(c>>24)&0xff;int red=(c&0x00ff0000)>>16;
int green=(c&0x0000ff00)>>8;int blue=c&0x000000ff;
g.setColor(new Color(red,green,blue,T));

//convert plane to 2 halfs of left and right or top and bottom
double X=x-(w/2),Y=y-(h/2);

//Rotate as if 3D Piont
double X2=CX*(X);
double Y2=-(SX*SY)*(X)+CY*(Y);

//Perspective
double  Z=(SX*CY)*(X)-SY*(Y);
Z=Z/(130/2);
X2*=10/(Z+10+0);
Y2*=10/(Z+10+0);

//Round output
int NewX=(int)(X2+(300/2)+0.5),NewY=(int)(Y2+(230/2)+0.5);
g.fillRect(NewX,NewY,1,1);}}}

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

if(elevation>360){elevation=0;}
if(elevation<-360){elevation=0;}

if(azimuth>360){azimuth=0;}
if(azimuth<-360){azimuth=0;}

repaint();
e.consume();}

}