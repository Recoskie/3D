import javax.imageio.*;import java.awt.image.*;import java.awt.event.*;
import java.awt.*;import javax.swing.*;

//this is the improved version

public class Rotation extends JPanel implements MouseMotionListener,MouseListener
{
BufferedImage a;float Rx=0,Ry=0;
int Px=0,Py=0,Pz=0;int azimuth=0,elevation=0;int mx=0,my=0;

//load Picture
public Rotation() 
{this.setBackground(Color.gray);
addMouseListener(this);addMouseMotionListener(this);
try{java.net.URL url=getClass().getResource("test.png");
a=ImageIO.read(url);}catch(java.io.IOException e){}}

//window
public static void main(String[]args)
{JFrame f=new JFrame("test");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(1000,1000);
f.setLocationRelativeTo(null);
f.setResizable(false);
f.add(new Rotation());
f.setVisible(true);}

//Rotate Alagrithm
public void paint(Graphics g)
{super.paint(g);

//read pictuer one pixel at a time
int w=a.getWidth(),h=a.getHeight();

Rx=(float)(Math.PI*(azimuth)/180);
Ry=(float)(Math.PI*(elevation)/180);

float CX=(float)Math.cos(Rx);
float SX=(float)Math.sin(Rx);
float CY=(float)Math.cos(Ry);
float SY=(float)Math.sin(Ry);

//convert plane to 2 halfs of left and right or top and bottom
float X=w/2,Y=h/2;

//loop down image set size height line breaks
for(int y=0;y<=h-1;y++)
{
//the movment of Y distance
Y=y-(h/2);

//get first and last X Point on rotation

float X1=CX*(X);float X2=-X1;

//get the hight betwean the 2 points

float Y1=-(SX*SY)*(X)+CY*(Y);float Y2=-(SX*SY)*(-X)+CY*(Y);

//add perspective to left and right side lengths

float Z=(SX*CY)*(X)-SY*(Y);
int over;
if(w>h){over=(int)w/2;}else{over=(int)h/2;}
Z=Z/(over/2);
X1*=10/(Z+10+0);
Y1*=10/(Z+10+0);

Z=(SX*CY)*(-X)-SY*(Y);
Z=Z/(over/2);
X2*=10/(Z+10+0);
Y2*=10/(Z+10+0);

//add the set position of points

X1+=500;Y1+=500;X2+=500;Y2+=500;

//use my draw line function

DrawLine((int)X1,(int)Y1,(int)X2,(int)Y2,y,g);
}}

public void DrawLine(int x0,int y0,int x1,int y1,int L,Graphics g)
{

//how much less color to use or more if shorter or longer to size of data of color in x of image size
float CS;
try{CS=(float)a.getWidth()/Math.abs(x0-x1);}catch(Exception e){CS=0;}
//System.out.println(CS+"");
float Increse=0;//CS=(float)0.5;

boolean steep=Math.abs(y1-y0)>Math.abs(x1-x0);
if(steep){int t=x0;x0=y0;y0=t;t=x1;x1=y1;y1=t;}
if(x0>x1){int t=x0;x0=x1;x1=t;t=y0;y0=y1;y1=t;}
int deltax=x1-x0,deltay=Math.abs(y1-y0);float error;
try{error=deltax/2;}catch(Exception e){error=0;}
int ystep=0,y=y0;
if(y0<y1){ystep=1;}else{ystep=-1;}
int x;for(x=x0;x<=x1;x++)
{
try{int c=a.getRGB((int)Increse,L);int T=(c>>24)&0xff;int red=(c&0x00ff0000)>>16;
int green=(c&0x0000ff00)>>8;int blue=c&0x000000ff;Increse+=CS;
g.setColor(new Color(red,green,blue,T));}catch(Exception e){}

if(steep){g.fillRect(y,x,1,1);}else{g.fillRect(x,y,1,1);}
error-=deltay;if(error<0){y+=ystep;error=error+deltax;}}
}

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