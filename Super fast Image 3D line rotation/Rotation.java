import javax.imageio.*;import java.awt.image.*;import java.awt.event.*;
import java.awt.*;import javax.swing.*;

//this is the improved version

public class Rotation extends JPanel implements MouseMotionListener,MouseListener
{
BufferedImage a;float Rx=0,Ry=0;
int Px=0,Py=0,Pz=0;int azimuth=0,elevation=0;int mx=0,my=0;
public static int gw=700,gh=700;
int img[]=new int[gw*gh];

//load Picture
public Rotation() 
{this.setBackground(Color.gray);
addMouseListener(this);addMouseMotionListener(this);
try{java.net.URL url=getClass().getResource("559f.jpg");
a=ImageIO.read(url);}catch(java.io.IOException e){}}

//window
public static void main(String[]args)
{JFrame f=new JFrame("test");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(gw,gh);
f.setLocationRelativeTo(null);
f.setResizable(false);
f.add(new Rotation());
f.setVisible(true);}

//Rotate Alagrithm
public void paint(Graphics g)
{super.paint(g);


g.drawImage(createImage(new MemoryImageSource(gw,gh,img,0,gw)),0,0,this);
}


public void DrawCorners(int x0,int y0,int x1,int y1,int x2,int y2,int x3,int y3)
{
//0 top left
//1 top right
//2 botom left
//3 botom right

//fill distance factors

float LY=1,RY=1,LX=1,RX=1;

//scale factor of pixle selection

float inc=0;

//the bigest point in height

int MaxY=Math.max(y0,y1);
MaxY=Math.max(MaxY,y2);
MaxY=Math.max(MaxY,y3);

try{inc=(float)a.getHeight()/Math.abs(MaxY);}catch(Exception e){inc=1;}
float yo1=y0,yo2=y1,xo1=x0,xo2=x1,L=0;

//fill y distance

LY=(y2-y0);LY=(float)(LY/MaxY);RY=(y3-y1);RY=(float)(RY/MaxY);

if(Float.isInfinite(LY)){LY=0;}
if(Float.isInfinite(RY)){RY=0;}

//fill x distance
LX=(float)(x2-x0)/MaxY;RX=(float)(x3-x1)/MaxY;

if(Float.isInfinite(LX)){LX=0;}
if(Float.isInfinite(RX)){RX=0;}

MaxY=Math.abs(MaxY);

for(int y=0;y<MaxY;y++)
{

//draw a line of pixles warped to the new shape of image

DrawLine((int)(xo1+0.5),(int)(yo1+0.5),(int)(xo2+0.5),(int)(yo2+0.5),(int)L);

xo1+=LX;
xo2+=RX;
yo1+=LY;
yo2+=RY;

L+=inc;
}
}

//i nead it to draw left and right flip rotations as well

public void DrawLine(int x0,int y0,int x1,int y1,int L)
{
//how much less color to use or more if shorter or longer to size of data of color in x of image size
float CS;
try{

if(Math.abs(x0-x1)>Math.abs(y0-y1))
{CS=(float)a.getWidth()/Math.abs(x0-x1);}
else{CS=(float)a.getWidth()/Math.abs(y0-y1);}

}catch(Exception e){CS=0;}

float Increse=0;

boolean steep=Math.abs(y1-y0)>Math.abs(x1-x0);
if(steep){int t=x0;x0=y0;y0=t;t=x1;x1=y1;y1=t;}
if(x0>x1){int t=x0;x0=x1;x1=t;t=y0;y0=y1;y1=t;
CS=-CS;Increse=(a.getWidth()-1);}
int deltax=x1-x0,deltay=Math.abs(y1-y0),error=0;
try{error=deltax/2;}catch(Exception e){error=0;}
int ystep=0,y=y0;if(y0<y1){ystep=1;}else{ystep=-1;}
int x,c=0;for(x=x0;x<=x1;x++)
{
if(x>0&&y>0&&x<(gw-1)&&y<(gh-1))
{
try{

c=a.getRGB((int)Increse,L);
Increse+=CS;}catch(Exception e){}

if(x>(gw-1)){break;}
if(y>(gh-1)){break;}

if(steep){img[(gh*x)+y]=c;}
else{img[(gh*y)+x]=c;}

}
else{Increse+=CS;}

error-=deltay;
if(error<0){y+=ystep;error=error+deltax;}}

}

public void mouseEntered(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mouseClicked(MouseEvent e){}

public void mousePressed(MouseEvent e){mx=e.getX();my=e.getY();e.consume();}

public void mouseReleased(MouseEvent e){}
public void mouseMoved(MouseEvent e){}

public void mouseDragged(MouseEvent e)
{//read pictuer one pixel at a time
int w=a.getWidth(),h=a.getHeight();

Rx=(float)(Math.PI*(azimuth)/180);
Ry=(float)(Math.PI*(elevation)/180);

float CX=(float)Math.cos(Rx);
float SX=(float)Math.sin(Rx);
float CY=(float)Math.cos(Ry);
float SY=(float)Math.sin(Ry);

//convert plane to 2 halfs of left and right or top and bottom
float X=w/2,Y=h/2;

//get first and last X Point on rotation

float X1=CX*(-X);
float X2=CX*(X);
float X3=X1;
float X4=X2;

//get the hight betwean the 2 points

float Y1=-(SX*SY)*(-X)+CY*(-Y);
float Y2=-(SX*SY)*(X)+CY*(-Y);
float Y3=-(SX*SY)*(-X)+CY*(Y);
float Y4=-(SX*SY)*(X)+CY*(Y);

//add perspective to corners

int over;
float Z=(SX*CY)*(-X)-SY*(-Y);
if(w>h){over=(int)w/2;}else{over=(int)h/2;}
Z=Z/over;
X1*=10/(Z+10+0);
Y1*=10/(Z+10+0);

Z=(SX*CY)*(X)-SY*(-Y);
Z=Z/over;
X2*=10/(Z+10+0);
Y2*=10/(Z+10+0);

Z=(SX*CY)*(-X)-SY*(Y);
Z=Z/over;
X3*=10/(Z+10+0);
Y3*=10/(Z+10+0);

Z=(SX*CY)*(X)-SY*(Y);
Z=Z/over;
X4*=10/(Z+10+0);
Y4*=10/(Z+10+0);

//add the set position of points
int xw=gw/2,hy=gh/2;

X1+=xw;Y1+=hy;X2+=xw;Y2+=hy;X3+=xw;Y3+=hy;X4+=xw;Y4+=hy;

img=new int[gw*gh];
DrawCorners((int)X1,(int)Y1,(int)X2,(int)Y2,(int)X3,(int)Y3,(int)X4,(int)Y4);

int new_mx=e.getX();int new_my=e.getY();
azimuth+=new_mx-mx;
elevation+=new_my-my;
mx=new_mx;my=new_my;

if(elevation>360){elevation=0;}
if(elevation<-360){elevation=0;}

if(azimuth>360){azimuth=0;}
if(azimuth<-360){azimuth=0;}

repaint();
e.consume();}

}